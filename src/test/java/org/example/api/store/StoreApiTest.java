package org.example.api.store;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class StoreApiTest {


    @Test
    public void placeOrderTest (){
        String createPet = "{\n" +
                "  \"id\": 12074,\n" +
                "  \"petId\": 8080,\n" +
                "  \"quantity\": 3,\n" +
                "  \"shipDate\": \"2021-11-14T16:12:09.441Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";
        given()
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(createPet)
                .when()
                .post("https://petstore.swagger.io/v2/store/order")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteOrderTest() {
        // todo: удалить заказ
        // todo: проверить удаление заказа
        given()
                .header("Authorization", "special-key")
                .log()
                .all()
                .when()
                .delete("https://petstore.swagger.io/v2/store/order/12074")
                .prettyPeek()
                .then()
                .statusCode(200)
                .and()
                .body("type", CoreMatchers.is("unknown"))  //проверяем, что вместо заказа стало "unknown"
                .log()
                .all();
        // .body("message", CoreMatchers.is("Order Not Found"));
    }

}

