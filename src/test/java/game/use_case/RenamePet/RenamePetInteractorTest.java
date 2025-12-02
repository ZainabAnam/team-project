package game.use_case.RenamePet;
import game.data_access.RenamePetDataAccessObject;
import game.entity.Pet;
import game.entity.User;
import game.use_case.PetCard.RenamePet.*;
import org.junit.jupiter.api.Test;

import javax.swing.ImageIcon;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RenamePetInteractorTest {
    @Test
    void successTest() {
        // Prepare input data
        RenamePetInputData inputData = new RenamePetInputData(0, "Rex", "testUser");

        // Create user and pets
        User user = new User();
        ImageIcon icon = new ImageIcon();
        Pet pet1 = new Pet("Dog", "Golden Retriever", "Common", 100, 10, 5, icon);
        pet1.setName("Buddy");
        Pet pet2 = new Pet("Cat", "Siamese", "Elite", 80, 8, 4, icon);
        pet2.setName("Whiskers");

        // Set pet inventory using reflection
        try {
            Field petInventoryField = User.class.getDeclaredField("petInventory");
            petInventoryField.setAccessible(true);
            List<Pet> pets = new ArrayList<>();
            pets.add(pet1);
            pets.add(pet2);
            petInventoryField.set(user, pets);
        } catch (Exception e) {
            fail("Failed to set pet inventory: " + e.getMessage());
        }

        RenamePetDataAccessInterface userRepository = new RenamePetDataAccessObject(user);

        // Create success presenter
        RenamePetOutputBoundary successPresenter = new RenamePetOutputBoundary() {
            @Override
            public void prepareSuccessView(RenamePetOutputData outputData) {
                assertEquals("Buddy", outputData.getOldName());
                assertEquals("Rex", outputData.getNewName());
                assertTrue(outputData.isSuccess());
                assertEquals("Successfully renamed Buddy to Rex!", outputData.getMessage());

                // Verify pet name was actually changed
                assertEquals("Rex", pet1.getName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        RenamePetInputBoundary interactor = new RenamePetInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidPetIndexNegativeTest() {
        RenamePetInputData inputData = new RenamePetInputData(-1, "Rex", "testUser");

        User user = new User();
        RenamePetDataAccessInterface userRepository = new RenamePetDataAccessObject(user);

        RenamePetOutputBoundary failurePresenter = new RenamePetOutputBoundary() {
            @Override
            public void prepareSuccessView(RenamePetOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid pet index", error);
            }
        };

        RenamePetInputBoundary interactor = new RenamePetInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidPetIndexOutOfBoundsTest() {
        RenamePetInputData inputData = new RenamePetInputData(5, "Rex", "testUser");

        User user = new User();
        // Add one pet to inventory
        try {
            Field petInventoryField = User.class.getDeclaredField("petInventory");
            petInventoryField.setAccessible(true);
            List<Pet> pets = new ArrayList<>();
            pets.add(new Pet("Dog", "Golden Retriever", "Common", 100, 10, 5, new ImageIcon()));
            petInventoryField.set(user, pets);
        } catch (Exception e) {
            fail("Failed to set pet inventory: " + e.getMessage());
        }

        RenamePetDataAccessInterface userRepository = new RenamePetDataAccessObject(user);

        RenamePetOutputBoundary failurePresenter = new RenamePetOutputBoundary() {
            @Override
            public void prepareSuccessView(RenamePetOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid pet index", error);
            }
        };

        RenamePetInputBoundary interactor = new RenamePetInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyNameTest() {
        RenamePetInputData inputData = new RenamePetInputData(0, "   ", "testUser");

        User user = new User();
        ImageIcon icon = new ImageIcon();
        Pet pet = new Pet("Dog", "Golden Retriever", "Common", 100, 10, 5, icon);
        pet.setName("Buddy");

        try {
            Field petInventoryField = User.class.getDeclaredField("petInventory");
            petInventoryField.setAccessible(true);
            List<Pet> pets = new ArrayList<>();
            pets.add(pet);
            petInventoryField.set(user, pets);
        } catch (Exception e) {
            fail("Failed to set pet inventory: " + e.getMessage());
        }

        RenamePetDataAccessInterface userRepository = new RenamePetDataAccessObject(user);

        RenamePetOutputBoundary failurePresenter = new RenamePetOutputBoundary() {
            @Override
            public void prepareSuccessView(RenamePetOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Pet name cannot be empty", error);
            }
        };

        RenamePetInputBoundary interactor = new RenamePetInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureDuplicateNameTest() {
        RenamePetInputData inputData = new RenamePetInputData(0, "Whiskers", "testUser");

        User user = new User();
        ImageIcon icon = new ImageIcon();
        Pet pet1 = new Pet("Dog", "Golden Retriever", "Common", 100, 10, 5, icon);
        pet1.setName("Buddy");
        Pet pet2 = new Pet("Cat", "Siamese", "Elite", 80, 8, 4, icon);
        pet2.setName("Whiskers");

        try {
            Field petInventoryField = User.class.getDeclaredField("petInventory");
            petInventoryField.setAccessible(true);
            List<Pet> pets = new ArrayList<>();
            pets.add(pet1);
            pets.add(pet2);
            petInventoryField.set(user, pets);
        } catch (Exception e) {
            fail("Failed to set pet inventory: " + e.getMessage());
        }

        RenamePetDataAccessInterface userRepository = new RenamePetDataAccessObject(user);

        RenamePetOutputBoundary failurePresenter = new RenamePetOutputBoundary() {
            @Override
            public void prepareSuccessView(RenamePetOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Pet name 'Whiskers' already exists", error);
            }
        };

        RenamePetInputBoundary interactor = new RenamePetInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void successSameNameDifferentCaseTest() {
        // Test that renaming with different case should succeed
        RenamePetInputData inputData = new RenamePetInputData(0, "BUDDY", "testUser");

        User user = new User();
        ImageIcon icon = new ImageIcon();
        Pet pet1 = new Pet("Dog", "Golden Retriever", "Common", 100, 10, 5, icon);
        pet1.setName("Buddy");
        Pet pet2 = new Pet("Cat", "Siamese", "Elite", 80, 8, 4, icon);
        pet2.setName("Whiskers");

        try {
            Field petInventoryField = User.class.getDeclaredField("petInventory");
            petInventoryField.setAccessible(true);
            List<Pet> pets = new ArrayList<>();
            pets.add(pet1);
            pets.add(pet2);
            petInventoryField.set(user, pets);
        } catch (Exception e) {
            fail("Failed to set pet inventory: " + e.getMessage());
        }

        RenamePetDataAccessInterface userRepository = new RenamePetDataAccessObject(user);

        RenamePetOutputBoundary successPresenter = new RenamePetOutputBoundary() {
            @Override
            public void prepareSuccessView(RenamePetOutputData outputData) {
                assertEquals("Buddy", outputData.getOldName());
                assertEquals("BUDDY", outputData.getNewName());
                assertTrue(outputData.isSuccess());

                // Verify pet name was actually changed
                assertEquals("BUDDY", pet1.getName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        RenamePetInputBoundary interactor = new RenamePetInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}