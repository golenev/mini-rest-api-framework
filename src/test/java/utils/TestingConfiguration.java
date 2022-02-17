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

    public static String getStreetFromTestingData () {
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("Street");
    }

    public static String getSuitFromTestingData () {
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("Suite");
    }
    public static String getCityFromTestingData () {
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("City");
    }

    public static String getZipcodeFromTestingData () {
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("Zipcode");
    }
    public static String getLatFromTestingData () {
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("getLat");
    }
    public static String getLngFromTestingData () {
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("getLng");
    }
    public static String getNameFromTestingData () {
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("Name");
    }

    public static String getEmailFromTestingData () {
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("Email");
    }
    public static String getBsFromTestingData () {
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("Bs");
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

