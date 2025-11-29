package game.use_case.RenamePet;

import game.entity.Pet;
import game.entity.User;
import game.use_case.PetCard.RenamePet.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class RenamePetInteractorTest {

    @Test
    void successTest() {
        // Setup
        TestDataAccess dataAccess = new TestDataAccess();
        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        RenamePetInteractor interactor = new RenamePetInteractor(dataAccess, outputBoundary);

        // Create user with a pet
        User user = new User();
        Pet pet = new Pet("Dog", "labrador retriever", "Buddy");
        user.addToPetInventory(pet);
        dataAccess.saveUser(user);

        RenamePetInputData inputData = new RenamePetInputData(0, "Max", "testUser");

        // Execute
        interactor.execute(inputData);

        // Verify success output
        assertTrue(outputBoundary.lastSuccessOutput.isSuccess());
        assertEquals("Buddy", outputBoundary.lastSuccessOutput.getOldName());
        assertEquals("Max", outputBoundary.lastSuccessOutput.getNewName());
        assertEquals("Successfully renamed Buddy to Max!", outputBoundary.lastSuccessOutput.getMessage());

        // Verify pet was actually renamed
        assertEquals("Max", user.getPetInventory().get(0).getName());
    }

    @Test
    void userNotFoundTest() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        RenamePetInteractor interactor = new RenamePetInteractor(dataAccess, outputBoundary);

        // Don't save any user to dataAccess
        RenamePetInputData inputData = new RenamePetInputData(0, "NewName", "nonExistentUser");

        interactor.execute(inputData);

        assertEquals("User not found", outputBoundary.lastErrorMessage);
        assertNull(outputBoundary.lastSuccessOutput);
    }

    @Test
    void invalidPetIndexTest() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        RenamePetInteractor interactor = new RenamePetInteractor(dataAccess, outputBoundary);

        User user = new User();
        // Add one pet
        Pet pet = new Pet("Dog", "labrador retriever", "Buddy");
        user.addToPetInventory(pet);
        dataAccess.saveUser(user);

        // Try invalid index
        RenamePetInputData inputData = new RenamePetInputData(1, "Max", "testUser");

        interactor.execute(inputData);

        assertEquals("Invalid pet index", outputBoundary.lastErrorMessage);
        assertNull(outputBoundary.lastSuccessOutput);

        // Try negative index
        RenamePetInputData inputData2 = new RenamePetInputData(-1, "Max", "testUser");
        interactor.execute(inputData2);

        assertEquals("Invalid pet index", outputBoundary.lastErrorMessage);
    }

    @Test
    void emptyNameTest() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        RenamePetInteractor interactor = new RenamePetInteractor(dataAccess, outputBoundary);

        User user = new User();
        Pet pet = new Pet("Dog", "labrador retriever", "Buddy");
        user.addToPetInventory(pet);
        dataAccess.saveUser(user);

        RenamePetInputData inputData = new RenamePetInputData(0, "", "testUser");

        interactor.execute(inputData);

        assertEquals("Pet name cannot be empty", outputBoundary.lastErrorMessage);
        assertNull(outputBoundary.lastSuccessOutput);

        // Test with only whitespace
        RenamePetInputData inputData2 = new RenamePetInputData(0, "   ", "testUser");
        interactor.execute(inputData2);

        assertEquals("Pet name cannot be empty", outputBoundary.lastErrorMessage);
    }

    @Test
    void duplicateNameTest() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        RenamePetInteractor interactor = new RenamePetInteractor(dataAccess, outputBoundary);

        User user = new User();
        Pet pet1 = new Pet("Dog", "labrador retriever", "Buddy");
        Pet pet2 = new Pet("Cat", "siamese", "Max");
        user.addToPetInventory(pet1);
        user.addToPetInventory(pet2);
        dataAccess.saveUser(user);

        // Try to rename Buddy to Max (which already exists)
        RenamePetInputData inputData = new RenamePetInputData(0, "Max", "testUser");

        interactor.execute(inputData);

        assertEquals("Pet name 'Max' already exists", outputBoundary.lastErrorMessage);
        assertNull(outputBoundary.lastSuccessOutput);

        // Verify original names are unchanged
        assertEquals("Buddy", user.getPetInventory().get(0).getName());
        assertEquals("Max", user.getPetInventory().get(1).getName());
    }

    @Test
    void duplicateNameExcludingCurrentPetTest() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        RenamePetInteractor interactor = new RenamePetInteractor(dataAccess, outputBoundary);

        User user = new User();
        Pet pet1 = new Pet("Dog", "labrador retriever", "Buddy");
        Pet pet2 = new Pet("Cat", "siamese", "Max");
        user.addToPetInventory(pet1);
        user.addToPetInventory(pet2);
        dataAccess.saveUser(user);

        // Rename Max to Charlie (should succeed since we're excluding current pet from duplicate check)
        RenamePetInputData inputData = new RenamePetInputData(1, "Charlie", "testUser");

        interactor.execute(inputData);

        assertTrue(outputBoundary.lastSuccessOutput.isSuccess());
        assertEquals("Max", outputBoundary.lastSuccessOutput.getOldName());
        assertEquals("Charlie", outputBoundary.lastSuccessOutput.getNewName());

        // Verify names are updated correctly
        assertEquals("Buddy", user.getPetInventory().get(0).getName());
        assertEquals("Charlie", user.getPetInventory().get(1).getName());
    }

    @Test
    void multiplePetsRenameSuccessTest() {
        TestDataAccess dataAccess = new TestDataAccess();
        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        RenamePetInteractor interactor = new RenamePetInteractor(dataAccess, outputBoundary);

        User user = new User();
        Pet pet1 = new Pet("Dog", "labrador retriever", "Buddy");
        Pet pet2 = new Pet("Cat", "siamese", "Max");
        Pet pet3 = new Pet("Dog", "golden retriever", "Charlie");
        user.addToPetInventory(pet1);
        user.addToPetInventory(pet2);
        user.addToPetInventory(pet3);
        dataAccess.saveUser(user);

        // Rename middle pet
        RenamePetInputData inputData = new RenamePetInputData(1, "Milo", "testUser");

        interactor.execute(inputData);

        assertTrue(outputBoundary.lastSuccessOutput.isSuccess());
        assertEquals("Max", outputBoundary.lastSuccessOutput.getOldName());
        assertEquals("Milo", outputBoundary.lastSuccessOutput.getNewName());

        // Verify all pets have correct names
        assertEquals("Buddy", user.getPetInventory().get(0).getName());
        assertEquals("Milo", user.getPetInventory().get(1).getName());
        assertEquals("Charlie", user.getPetInventory().get(2).getName());
    }

    // Test helper classes
    private static class TestDataAccess implements RenamePetDataAccessInterface {
        private User user;

        @Override
        public User getUser(String username) {
            return user; // Simple implementation for testing
        }

        @Override
        public void saveUser(User user) {
            this.user = user;
        }
    }

    private static class TestOutputBoundary implements RenamePetOutputBoundary {
        private RenamePetOutputData lastSuccessOutput;
        private String lastErrorMessage;

        @Override
        public void prepareSuccessView(RenamePetOutputData outputData) {
            this.lastSuccessOutput = outputData;
            this.lastErrorMessage = null;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.lastErrorMessage = errorMessage;
            this.lastSuccessOutput = null;
        }
    }
}
