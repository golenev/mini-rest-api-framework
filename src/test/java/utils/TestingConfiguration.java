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

    public static String getLatFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("lat");
    }
    public static String getLngFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("lng");
    }
    public static String getStreetFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("street");
    }

    public static String getSuiteFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("suite");
    }

    public static String getCityFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("city");
    }

    public static String getZipcodeFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("zipcode");
    }

    public static String getCompanyNameFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("companyName");
    }

    public static String getCompanyCatchPhraseFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("companyCatchPhrase");
    }

    public static String getCompanyBsFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("companyBs");
    }

    public static int getUserIdFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return Integer.parseInt((String) testingDataObject.get("userId")) ;
    }

    public static String getUserUserNameFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("userUserName");
    }

    public static String getUserNameFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("userName");
    }

    public static String getUserEmailFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("userEmail");
    }

    public static String getUserPhoneFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("userPhone");
    }

    public static String getUserWebsiteFromTestingData(){
        if (testingDataObject == null)
            prepareConfig();
        return (String) testingDataObject.get("userWebsite");
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

