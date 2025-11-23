package game.data_access;

import game.entity.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;
import game.use_case.MainScreenManualClicker.ManualClickerUserDataAccessInterface;

import java.io.IOException;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements ManualClickerUserDataAccessInterface {
    private final String userID;
    String url = "fillerURL";

    public DBUserDataAccessObject(String userID) {
        this.userID = userID;
    }

    /**
     * Note: this method is executed assuming that userInDatabase evaluates to true
     * There is a filler String as a URL for the method.  This will be updated when a database URL is specified.
     */
    @Override
    public User getUser(String userID) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());
            for (String s : responseBody.keySet()) {
                if (s.equals(userID)) {
                     return (User) responseBody.get(s);
                }
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    /**
     * There is a filler String as a URL for the method.  This will be updated when a database URL is specified.
     * @param userID
     * @return
     */
    @Override
    public Boolean userExists(String userID) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());
            for (String s : responseBody.keySet()) {
                if (s.equals(userID)) {
                    return true;
                    }
                }
            return false;
            }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }
}