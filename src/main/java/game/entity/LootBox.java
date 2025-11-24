package game.entity;

import game.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * Loot box for random new pets
 */
public class LootBox extends Item{
    private List<Pet> petList = new ArrayList<>();

    public LootBox(){
        super("LootBox", Constants.LOOT_BOX_PRICE,"LootBox");
        initializePetList();
    }
    
    /**
     * Reads pet-list file and creates Pet objects for all breeds
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
                    if (parts.length >= 6) {
                        String breed = parts[1].trim();
                        String tier = parts[2].trim();
                        String baseEnergyStr = parts[3].trim();
                        String baseClickStr = parts[4].trim();
                        String baseRecoveryStr = parts[5].trim();
                        
                        try {
                            int baseEnergy = Integer.parseInt(baseEnergyStr);
                            int baseClick = Integer.parseInt(baseClickStr);
                            int baseRecovery = Integer.parseInt(baseRecoveryStr);
                            
                            Pet pet = new Pet(currentType, breed, tier, baseEnergy, baseClick, baseRecovery,getPetIcon(breed));
                            petList.add(pet);
                        } catch (NumberFormatException e) {
                            continue;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read pet-list file", e);
        }
    }

    public Pet getPet(){
        if (petList.isEmpty()) {
            throw new RuntimeException("Pet list is empty. Cannot get pet.");
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
                selectedPet.petIcon
        );
    }

    //TODO:add imageIcon method
    private ImageIcon getPetIcon(String breed){
        return new ImageIcon();
    }
}
