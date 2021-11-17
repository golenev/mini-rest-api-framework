package org.example.api.store;



import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StoreApiTest {

    private Order order;
    private int id;

    @BeforeClass
    public void prepare() throws IOException {
        order = new Order();
        id = new Random().nextInt(5000);
        int petId = new Random().nextInt(3000);
        int quantity = new Random().nextInt(50);
        order.setId(id);
        order.setPetId(petId);
        order.setQuantity(quantity);
        order.setShipDate("2021-05-26T19:53:29.159Z");
        order.setStatus("placed");
        order.setComplete(true);

        System.getProperties().load(ClassLoader.getSystemResourceAsStream("my.properties"));
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/")
                .addHeader("api_key", System.getProperty("api.key"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.filters(new ResponseLoggingFilter());

        given()
                .body(order)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200);
    }

    @Test
    public void createOrder() {

        Order current =
                given()
                        .pathParam("id", id)
                        .when()
                        .get("/store/order/{id}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Order.class);

        assertEquals(current.getId(), order.getId());
    }

    @Test
    public void getOrder() {

        Order current =
                given()
                        .pathParam("id", order.getId())
                        .when()
                        .get("/store/order/{id}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Order.class);

        assertEquals(current.getId(), order.getId());
    }

    @Test
    public void orderDelete() {

        given()
                .pathParam("id", order.getId())
                .when()
                .delete("/store/order/{id}")
                .then()
                .statusCode(200);
        given()
                .pathParam("id", order.getId())
                .when()
                .get("/store/order/{id}")
                .then()
                .statusCode(404);
    }


    @Test
    public void tryWithMap() {
        Map inventory =
                given()
                        .when()
                        .get("/store/inventory")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Map.class);
        assertTrue(inventory.containsKey("sold"), "нет статуса sold");
    }
}
