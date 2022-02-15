package ApiUtils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {




        public static RequestSpecification requestSpec(StringBuilder url){
            return new RequestSpecBuilder()
                    .setBaseUri(String.valueOf(url))
                    .setContentType("application/json")
                    .build();
        }

        public static ResponseSpecification responseSpecOK201(){
            return new ResponseSpecBuilder()
                    .expectStatusCode(201)
                    .build();
        }
    public static ResponseSpecification responseSpecOK200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecOK404(){
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }

        public static ResponseSpecification responseSpecError400(){
            return new ResponseSpecBuilder()
                    .expectStatusCode(400)
                    .build();
        }
        public static ResponseSpecification responseSpec(int status){
            return new ResponseSpecBuilder()
                    .expectStatusCode(status)
                    .build();
        }
        public static void installSpecification(RequestSpecification requestSpec, ResponseSpecification responseSpec){
            RestAssured.requestSpecification = requestSpec;
            RestAssured.responseSpecification = responseSpec;
        }




    }

