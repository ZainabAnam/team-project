package game.data_access;

import game.use_case.GetPetFact.GetPetFactOutputData;
import game.use_case.GetPetFact.PetFactDataAccessInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class CatApiPetFactDataAccessObject implements PetFactDataAccessInterface {

    private static final String CAT_FACT_URL =
            "https://catfact.ninja/fact?max_length=140";

    @Override
    public Optional<GetPetFactOutputData> fetchFact(String species, String breed) {

        if (!"cat".equalsIgnoreCase(species)) {
            return Optional.empty();
        }

        try {
            URL url = new URL(CAT_FACT_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                return Optional.empty();
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            String json = sb.toString();

            // Extract "fact":"...text..."
            String fact = extractValue(json, "\"fact\":\"", "\"");

            if (fact == null) {
                return Optional.empty();
            }

            return Optional.of(new GetPetFactOutputData(fact, breed, true));

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private String extractValue(String json, String key, String end) {
        int start = json.indexOf(key);
        if (start == -1) return null;

        start += key.length();
        int endIndex = json.indexOf(end, start);
        if (endIndex == -1) return null;

        return json.substring(start, endIndex);
    }
}
