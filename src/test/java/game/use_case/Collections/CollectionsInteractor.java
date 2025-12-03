package game.use_case.Collections;

import game.entity.Pet;
import game.entity.User;
import game.use_case.GetPetFact.GetPetFactOutputData;
import game.use_case.GetPetFact.PetFactDataAccessInterface;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static game.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for CollectionsInteractor aimed at full branch coverage.
 */
class CollectionsInteractorTest {

    private static class InMemoryCollectionsDAO implements CollectionsDataAccessInterface {
        User currentUser;

        InMemoryCollectionsDAO(User user) {
            this.currentUser = user;
        }

        @Override
        public User getCurrentUser() {
            return currentUser;
        }

        @Override
        public void saveUsr(User user) {
            this.currentUser = user;
        }
    }

    private static class FakePetFactGateway implements PetFactDataAccessInterface {
        Optional<GetPetFactOutputData> nextResult = Optional.empty();
        String lastSpecies;
        String lastBreed;

        @Override
        public Optional<GetPetFactOutputData> fetchFact(String species, String breed) {
            this.lastSpecies = species;
            this.lastBreed = breed;
            return nextResult;
        }
    }

    private static class CapturingPresenter implements CollectionsOutputBoundary {
        CollectionsOutputData successData;
        String failureMessage;

        @Override
        public void presentSuccess(CollectionsOutputData data) {
            this.successData = data;
        }

        @Override
        public void presentFailure(String message) {
            this.failureMessage = message;
        }
    }


    private User buildUserWithOnePetAndAllItems() {
        User u = new User();

        ImageIcon icon = new ImageIcon();

        Pet dog = new Pet("Dog", "Golden Retriever",
                "Common", 100, 5, 10, icon);
        dog.setName("Max");
        u.addToPetInventory(dog);

        u.addToItemList(ITEM_CANNED_FOOD);
        u.addToItemList(ITEM_KIBBLE);
        u.addToItemList(ITEM_HOME_COOKED);
        u.addToItemList(ITEM_CHEW_TOY);
        u.addToItemList(ITEM_TOSS_TOY);
        u.addToItemList(ITEM_PLUSH_TOY);

        return u;
    }

    @Test
    void execute_success_withFact() {
        User user = buildUserWithOnePetAndAllItems();
        InMemoryCollectionsDAO dao = new InMemoryCollectionsDAO(user);
        FakePetFactGateway factGateway = new FakePetFactGateway();
        CapturingPresenter presenter = new CapturingPresenter();

        factGateway.nextResult = Optional.of(
                new GetPetFactOutputData("Nice dog fact", "Dog", true)
        );

        CollectionsInteractor interactor =
                new CollectionsInteractor(presenter, dao, factGateway);

        interactor.execute(new CollectionsInputData());

        assertNotNull(presenter.successData);
        assertNull(presenter.failureMessage);

        List<CollectionsOutputData.PetInfo> pets = presenter.successData.getPets();
        assertEquals(1, pets.size());

        assertEquals(1, presenter.successData.getCannedFoodCount());
        assertEquals(1, presenter.successData.getKibbleCount());
        assertEquals(1, presenter.successData.getHomeCookedCount());
        assertEquals(1, presenter.successData.getChewToyCount());
        assertEquals(1, presenter.successData.getTossToyCount());
        assertEquals(1, presenter.successData.getPlushToyCount());

        assertEquals("Dog", factGateway.lastSpecies);
        assertEquals("Golden Retriever", factGateway.lastBreed);
    }

    @Test
    void execute_success_withNoFact_usesEmptyFactBranch() {
        User user = buildUserWithOnePetAndAllItems();
        InMemoryCollectionsDAO dao = new InMemoryCollectionsDAO(user);
        FakePetFactGateway factGateway = new FakePetFactGateway();
        CapturingPresenter presenter = new CapturingPresenter();

        factGateway.nextResult = Optional.empty();

        CollectionsInteractor interactor =
                new CollectionsInteractor(presenter, dao, factGateway);

        interactor.execute(new CollectionsInputData());

        assertNotNull(presenter.successData);
        assertNull(presenter.failureMessage);

        assertEquals(1, presenter.successData.getPets().size());
    }

    @Test
    void execute_whenGatewayThrows_callsPresentFailure() {
        CollectionsDataAccessInterface throwingDao = new CollectionsDataAccessInterface() {
            @Override
            public User getCurrentUser() {
                throw new RuntimeException("boom");
            }

            @Override
            public void saveUsr(User user) {
            }
        };

        PetFactDataAccessInterface dummyFactGateway = (species, breed) -> Optional.empty();
        CapturingPresenter presenter = new CapturingPresenter();

        CollectionsInteractor interactor =
                new CollectionsInteractor(presenter, throwingDao, dummyFactGateway);

        interactor.execute(new CollectionsInputData());

        assertNull(presenter.successData);
        assertEquals("Failed to load collection.", presenter.failureMessage);
    }
}

