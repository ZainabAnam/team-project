package game.data_access;
import game.entity.User;
import game.entity.Pet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for user data implemented using the files to persist data.
 */
public class JSONUserDataAccessObject implements UserDataAccessObjectInterface {

    private final File userLoadData = new File("data/userLoadData.json");
    private final File newGameData = new File("data/newGameData.json");

    // New User
    @Override
    public User newUser() {
        try {
            String jsonText = Files.readString(newGameData.toPath());
            return UserFromFile(new JSONObject(jsonText));

        } catch (IOException e) {
            throw new RuntimeException("Error reading default file", e);
        }
    }

    // Load User
    @Override
    public User loadUser() {
        try {
            String jsonText = Files.readString(userLoadData.toPath());
            return UserFromFile(new JSONObject(jsonText));

        } catch (IOException e) {
            throw new RuntimeException("Error reading save file", e);
        }
    }

    // Save User
    @Override
    public void saveUser(User user) {
        JSONObject saveData = UserToFile(user);

        try (FileWriter writer = new FileWriter(userLoadData)) {
            writer.write(saveData.toString(9));
        } catch (IOException e) {
            throw new RuntimeException("Error writing save file", e);
        }
    }

    // Read the data from the JSON file and turn it to in-game entities.
    private User UserFromFile(JSONObject obj) {
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
                    p.getString("type"),
                    p.getString("breed"),
                    p.getString("tier"),
                    p.getInt("base energy"),
                    p.getInt("base click"),
                    p.getInt("base recovery"),
                    p.getString("image path")
            );

            user.addToPetInventory(pet);
        }
        // add items too
        return user;
    }


    // Turn the current user's data into a JSONObject to store in the file
    private JSONObject UserToFile(User user) {
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
            p.put("type", pet.getPetType());
            p.put("breed", pet.getPetBreed());
            p.put("tier", pet.getTier());
            p.put("base energy", pet.getBaseEnergy());
            p.put("base click", pet.getBaseClick());
            p.put("base recovery", pet.getBaseRecovery());
            p.put("visual", pet.getPetVisualPath());
            p.put("energy Level", pet.getEnergyLevel());
            p.put("affection XP", pet.getAffectionXP());
            p.put("affection XP", pet.getAffectionXP());
            petsArray.put(p);
        }
        return petsArray;
    }

}


