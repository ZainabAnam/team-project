package game.entity;

import game.Constants;

import javax.swing.ImageIcon;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Loot box for random new pets.
 */
public class LootBox extends Item {
    /**
     * Pet list.
     */
    private List<Pet> petList = new ArrayList<>();

    /**
     * Constructs LootBox.
     */
    public LootBox() {
        super("LootBox", Constants.LOOT_BOX_PRICE, "LootBox");
        initializePetList();
    }

    /**
     * Reads pet-list file and creates Pet objects for all breeds.
     */
    private void initializePetList() {
        try {
            Path filePath = Paths.get("pet-list");
            List<String> lines = Files.readAllLines(filePath);

            String currentType = null;
            boolean inTable = false;

            for (String line : lines) {
                line = line.trim();

                // Skip empty lines
                if (line.isEmpty() || line.startsWith("_")) {
                    inTable = false;
                    continue;
                }

                // Detect type (Dogs or Cats)
                if (line.equals("Dogs")) {
                    currentType = "Dog";
                    inTable = false;
                    continue;
                } else if (line.equals("Cats")) {
                    currentType = "Cat";
                    inTable = false;
                    continue;
                }

                // find header
                if (line.contains("Breed") && line.contains("Tier")) {
                    inTable = true;
                    continue;
                }

                // Skip
                if (line.startsWith("|") && line.contains("---")) {
                    continue;
                }

                // Parse table rows
                if (inTable && line.startsWith("|") && currentType != null) {
                    String[] parts = line.split("\\|");
                    final int minPartsLength = 6;
                    if (parts.length >= minPartsLength) {
                        final int breedIndex = 1;
                        final int tierIndex = 2;
                        final int energyIndex = 3;
                        final int clickIndex = 4;
                        final int recoveryIndex = 5;
                        String breed = parts[breedIndex].trim();
                        String tier = parts[tierIndex].trim();
                        String baseEnergyStr = parts[energyIndex].trim();
                        String baseClickStr = parts[clickIndex].trim();
                        String baseRecoveryStr = parts[recoveryIndex].trim();

                        try {
                            int baseEnergy = Integer.parseInt(baseEnergyStr);
                            int baseClick = Integer.parseInt(baseClickStr);
                            int baseRecovery = Integer.parseInt(
                                    baseRecoveryStr);

                            Pet pet = new Pet(currentType, breed, tier,
                                    baseEnergy, baseClick, baseRecovery,
                                    getPetIcon(breed));
                            petList.add(pet);
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing number for "
                                    + "breed: " + breed);
                            continue;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read pet-list file", e);
        }
    }

    /**
     * Gets a random pet.
     * @return random pet
     */
    public Pet getPet() {
        if (petList.isEmpty()) {
            throw new RuntimeException("Pet list is empty. "
                    + "Cannot get pet.");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(petList.size());
        Pet selectedPet = petList.get(randomIndex);

        // Create a new Pet instance with the same properties
        return new Pet(
                selectedPet.getPetType(),
                selectedPet.getPetBreed(),
                selectedPet.getTier(),
                selectedPet.getBaseEnergy(),
                selectedPet.getBaseClick(),
                selectedPet.getBaseRecovery(),
                selectedPet.getPetIcon()
        );
    }

    /**
     * Gets pet icon.
     * @param breed pet breed
     * @return image icon
     */
    private ImageIcon getPetIcon(final String breed) {
        return new ImageIcon();
    }

    /**
     * Generates random name.
     * @return random name
     */
    private String generateRandomName() {
        Random random = new Random();
        String[] names = {"Buddy", "Max", "Charlie", "Lucy", "Bailey",
                "Daisy", "Molly", "Jack"};
        return names[random.nextInt(names.length)];
    }
}
