package utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.List;

public class ListUtils {

   public static  <T> List<T> parseJsonToList (Response response, String path, Class<T> clazz) {
       return  response.then().extract().body().jsonPath().getList(path, clazz);
   }

   public static  <T> T parseJsonToObject (Response response, String path, Class<T> clazz) {
        return response.then().extract().body().jsonPath().getObject(path, clazz);
    }

}
