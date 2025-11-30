package game.data_access;
import game.entity.Item;
import game.entity.User;
import game.entity.Pet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for user data implemented using the files to persist data.
 */
public class JSONUserDataAccessObject {

    private final File saveDataFile;
    private final String defaultResourceName;

    public JSONUserDataAccessObject(String savePath, String defaultResourceName) {
        this.saveDataFile = new File(savePath);
        this.defaultResourceName = defaultResourceName;
    }

    // Save User
    public void saveUser(User user) {
        JSONObject saveData = userToJson(user);

        // Ensure folder exists
        saveDataFile.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(saveDataFile)) {
            writer.write(saveData.toString(9));
        } catch (IOException e) {
            throw new RuntimeException("Error writing save file", e);
        }
    }

    // Read the data from the JSON file and turn it to in-game entities.
    private User userFromJson(JSONObject obj) {
        int coinCount = obj.getInt("coins");
        int clickBonus = obj.getInt("click bonus");
        int clickBonusTime = obj.getInt("click bonus time");
        int slotsUnlocked = obj.getInt("slots unlocked");

        User user = new User();
        user.loadCoinCount(coinCount);
        user.loadClickBonus(clickBonus);
        user.loadClickBonusTime(clickBonusTime);
        user.loadUnlockedSlots(slotsUnlocked);

        // Pets
        List<Pet> pets = new ArrayList<>();
        JSONArray petsJson = obj.getJSONArray("pets");

        for (int i = 0; i < petsJson.length(); i++) {
            JSONObject p = petsJson.getJSONObject(i);

            Pet pet = new Pet(
                    p.getString("name"),
                    p.getInt("energyLevel"),
                    p.getInt("affection")
            );

            pet.setDeployed(p.getBoolean("isDeployed"));
            pets.add(pet);
        }

        user.setPets(pets);
        return user;
    }


    // Turn the current user's data into a JSONObject to store in the file
    private JSONObject userToJson(User user) {
        JSONObject userData = new JSONObject();

        userData.put("coins", user.getCoinCount());
        userData.put("click bonus", user.getClickBonus());
        userData.put("click bonus time", user.getClickBonusTime());
        userData.put("slots unlocked", user.getUnlockedSlots());

        JSONArray petsArray = getPetsData(user);
//        JSONArray itemAmountArray = new JSONArray();
//        for (Item item : user.getItemsList()) {
//            JSONObject i = new JSONObject();
//            i.put("name", item.getName());
//            i.put("type", item.getType());
//            i.put("price", item.getPrice());
//            itemAmountArray.put(i);
//        }

        userData.put("pets", petsArray);
        return userData;
    }

    private static JSONArray getPetsData(User user) {
        JSONArray petsArray = new JSONArray();
        for (Pet pet : user.getPetInventory()) {
            JSONObject p = new JSONObject();
            p.put("name", pet.getName());
            p.put("breed", pet.getPetBreed());
         // p.put("visual", pet.getIconFilePath());
            p.put("energy Level", pet.getEnergyLevel());
            p.put("affection XP", pet.getAffectionXP());
            p.put("affection XP", pet.getAffectionXP());
            p.put("selling price", pet.getSellingPrice());
            petsArray.put(p);
        }
        return petsArray;
    }

}


