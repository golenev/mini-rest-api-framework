package utils;

import java.io.*;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import models.users.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestingConfiguration {

    private static final String configPath = "/config.json";
    private static final String testPath = "/testing.json";
    private static final String requiredUserDataPath = "/requiredUser.json";
    private static JSONObject configObject = null;
    private static JSONObject testingDataObject = null;
    private static User userFromResources = null;
    private static boolean isReady = false;

    private static void prepareConfig () {
        if (isReady) return;
        try {
            JSONParser configParser = new JSONParser();
            Reader configReader = new InputStreamReader(TestingConfiguration.class.getClass().getResourceAsStream(configPath));
            configObject = (JSONObject) configParser.parse(configReader);
            JSONParser testDataParser = new JSONParser();
            Reader testDataReader = new InputStreamReader(TestingConfiguration.class.getClass().getResourceAsStream(testPath));
            testingDataObject = (JSONObject) configParser.parse(testDataReader);
            Reader userDataReader = new InputStreamReader(TestingConfiguration.class.getClass().getResourceAsStream(requiredUserDataPath));
            Gson gson = new Gson();
            userFromResources = gson.fromJson(userDataReader, User.class);
            isReady = true;

        } catch (FileNotFoundException ex) {
        } catch (IOException | ParseException | NullPointerException ex) {
        }

    }
    public static String getPostsUrl (long id) {
        return new StringBuilder(getConfigData("url")).append(getConfigData("postsPath")).append(id).toString();
    }

    public static String getPostsUrl () {
        return new StringBuilder(getConfigData("url")).append(getConfigData("postsPath")).toString();
    }

    public static String getUsersUrl (int id) {
        return new StringBuilder(getConfigData("url")).append(getConfigData("usersPath")).append(id).toString();
    }

    public static String getUsersUrl () {
        return new StringBuilder(getConfigData("url")).append(getConfigData("usersPath")).toString();
    }

    public static User getUserFromResources() {
        prepareConfig();
        return userFromResources;
    }

    public static long getTestDataAsLong(String key){
        prepareConfig();
        return (long) testingDataObject.get(key);
    }

    public static String getConfigData (String key) {
            prepareConfig();
            return (String) configObject.get(key);
    }
}

