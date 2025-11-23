package game.use_case.GetPetFact;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

import game.data_access.CatApiPetFactDataAccessObject;
import game.data_access.DogApiPetFactDataAccessObject;
import game.data_access.CompositePetFactDataAccessObject;

// Interactor Unit Test
class GetPetFactInteractorTest {

    private static class StubPetFactDAO implements PetFactDataAccessInterface {
        private final Optional<GetPetFactOutputData> resultToReturn;
        String lastSpeciesCalled;
        String lastBreedCalled;

        StubPetFactDAO(Optional<GetPetFactOutputData> resultToReturn) {
            this.resultToReturn = resultToReturn;
        }

        @Override
        public Optional<GetPetFactOutputData> fetchFact(String species, String breed) {
            this.lastSpeciesCalled = species;
            this.lastBreedCalled = breed;
            return resultToReturn;
        }
    }

    /**
     * Stub presenter: just stores the last output data it received.
     */
    private static class StubPresenter implements GetPetFactOutputBoundary {
        GetPetFactOutputData lastOutput;

        @Override
        public void present(GetPetFactOutputData outputData) {
            this.lastOutput = outputData;
            System.out.println("Did you know: " + outputData.getFactText());
        }
    }

    @Test
    void successDogFactTest() {
        // Arrange
        GetPetFactOutputData daoResult =
                new GetPetFactOutputData("Beagle dog fact", "DogAPI", true);
        StubPetFactDAO dao = new StubPetFactDAO(Optional.of(daoResult));
        StubPresenter presenter = new StubPresenter();

        GetPetFactInputBoundary interactor = new GetPetFactInteractor(dao, presenter);

        // Act
        interactor.execute("dog", "Beagle");

        // Assert DAO was called correctly
        assertEquals("dog", dao.lastSpeciesCalled);
        assertEquals("Beagle", dao.lastBreedCalled);

        // Assert presenter received the expected output data
        assertNotNull(presenter.lastOutput);
        assertEquals("Beagle dog fact", presenter.lastOutput.getFactText());
        assertEquals("DogAPI", presenter.lastOutput.getSource());
        assertTrue(presenter.lastOutput.isSuccess());
    }

    @Test
    void successCatFactTest() {
        // Arrange
        GetPetFactOutputData daoResult =
                new GetPetFactOutputData("Siamese cat fact", "CatAPI", true);
        StubPetFactDAO dao = new StubPetFactDAO(Optional.of(daoResult));
        StubPresenter presenter = new StubPresenter();

        GetPetFactInputBoundary interactor = new GetPetFactInteractor(dao, presenter);

        // Act
        interactor.execute("cat", "Siamese");

        // Assert DAO was called correctly
        assertEquals("cat", dao.lastSpeciesCalled);
        assertEquals("Siamese", dao.lastBreedCalled);

        // Assert presenter received the expected output data
        assertNotNull(presenter.lastOutput);
        assertEquals("Siamese cat fact", presenter.lastOutput.getFactText());
        assertEquals("CatAPI", presenter.lastOutput.getSource());
        assertTrue(presenter.lastOutput.isSuccess());
    }

    @Test
    void failureNoFactReturnedTest() {
        // Arrange: DAO returns Optional.empty()
        StubPetFactDAO dao = new StubPetFactDAO(Optional.empty());
        StubPresenter presenter = new StubPresenter();

        GetPetFactInputBoundary interactor = new GetPetFactInteractor(dao, presenter);

        // Act
        interactor.execute("dog", "UnknownBreed");

        // Assert DAO was called
        assertEquals("dog", dao.lastSpeciesCalled);
        assertEquals("UnknownBreed", dao.lastBreedCalled);

        // Interactor should create the default failure output
        assertNotNull(presenter.lastOutput);
        assertEquals("", presenter.lastOutput.getFactText());
        assertEquals("", presenter.lastOutput.getSource());
        assertFalse(presenter.lastOutput.isSuccess());
    }
}


// Integration Test (for API functionality)
class LiveApiFactTest {

    @Test
    void dogApi_beagleFact() {
        // real DAOs
        CompositePetFactDataAccessObject dao =
                new CompositePetFactDataAccessObject(
                        new DogApiPetFactDataAccessObject(),
                        new CatApiPetFactDataAccessObject());

        // call the real API via DAO
        Optional<GetPetFactOutputData> result = dao.fetchFact("dog", "Beagle");

        if (result.isPresent()) {
            System.out.println("Did you know: " + result.get().getFactText());
        } else {
            System.out.println("NO FACT AVAILABLE");
        }
    }
    @Test
    void liveDogApiThroughInteractor() {
        CompositePetFactDataAccessObject dao =
                new CompositePetFactDataAccessObject(
                        new DogApiPetFactDataAccessObject(),
                        new CatApiPetFactDataAccessObject());

        GetPetFactOutputBoundary presenter = new GetPetFactOutputBoundary() {
            @Override
            public void present(GetPetFactOutputData outputData) {
                System.out.println("Did you know: " + outputData.getFactText());
            }
        };

        GetPetFactInteractor interactor = new GetPetFactInteractor(dao, presenter);
        interactor.execute("dog", "Beagle");   // this will hit the real API
    }
    @Test
    void catApi_siameseFact() {
        CatApiPetFactDataAccessObject catDao = new CatApiPetFactDataAccessObject();

        Optional<GetPetFactOutputData> result = catDao.fetchFact("cat", "Siamese");

        if (result.isPresent()) {
            System.out.println("Did you know: " + result.get().getFactText());
        } else {
            System.out.println("NO FACT AVAILABLE");
        }
    }
    @Test
    void liveCatApiThroughInteractor() {
        // real DAO
        CatApiPetFactDataAccessObject catDao = new CatApiPetFactDataAccessObject();

        // minimal presenter that prints the result
        GetPetFactOutputBoundary presenter = new GetPetFactOutputBoundary() {
            @Override
            public void present(GetPetFactOutputData outputData) {
                if (outputData.isSuccess()) {
                    System.out.println("Did you know: " + outputData.getFactText());
                } else {
                    System.out.println("NO FACT AVAILABLE");
                }
            }
        };

        GetPetFactInteractor interactor = new GetPetFactInteractor(catDao, presenter);
        interactor.execute("cat", "Siamese");  // hits real API
    }
}
