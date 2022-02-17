package utils;


import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestingConfiguration {

    private static final String configPath = "/config.json";
    private static final String testPath = "/testing.json";
    private static JSONObject configObject = null;
    private static JSONObject testingDataObject = null;



    private static void prepareConfig (){
        try {
            JSONParser configParser = new JSONParser();
            Reader configReader = new InputStreamReader(TestingConfiguration.class.getClass().getResourceAsStream(configPath));
            configObject = (JSONObject) configParser.parse(configReader);
            JSONParser testDataParser = new JSONParser();
            Reader testDataReader = new InputStreamReader(TestingConfiguration.class.getClass().getResourceAsStream(testPath));
            testingDataObject = (JSONObject) configParser.parse(testDataReader);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public static String getPostsUrl (int id) {
        return new StringBuilder(getUrl()).append("/").append(getPostsPath()).append("/").append(id).toString();
    }

    public static String getPostsUrl () {
        return new StringBuilder(getUrl()).append("/").append(getPostsPath()).toString();
    }

    public static String getUsersUrl (int id) {
        return new StringBuilder(getUrl()).append("/").append(getUsersPath()).append("/").append(id).toString();
    }

    public static String getUsersUrl () {
        return new StringBuilder(getUrl()).append("/").append(getUsersPath()).toString();
    }

    public static String getContentTypeFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("contentType");
    }

    public static int getExpectedUserIdFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return Integer.parseInt((String) testingDataObject.get("expectedUserId"));
    }

    public static int getExpectedIdFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return Integer.parseInt((String) testingDataObject.get("expectedId"));
    }

    public static int getFieldUserIdForConstructorTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return Integer.parseInt((String) testingDataObject.get("fieldUserIdFromConstructor"));
    }

    public static int getFieldIdForConstructorTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return Integer.parseInt((String) testingDataObject.get("fieldIdFromConstructor"));
    }

    public static int getRequiredPostsIdFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return Integer.parseInt((String) testingDataObject.get("requiredPostsId"));
    }

    public static int getRequiredUserIdFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return Integer.parseInt((String) testingDataObject.get("requiredUserId"));
    }

    public static String getUrl() {
        if (configObject == null)
            prepareConfig();
        return (String) configObject.get("url");
    }

    public static String getPostsPath() {
        if (configObject == null)
            prepareConfig();
        return (String) configObject.get("posts-path");
    }

    public static String getUsersPath() {
        if (configObject == null)
            prepareConfig();
        return (String) configObject.get("users-path");
    }



}

