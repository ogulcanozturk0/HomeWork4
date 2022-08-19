import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;


public class PlaceOrderTest {
   Response response;

   public PlaceOrderTest(){
      baseURI = "https://petstore.swagger.io/v2/" ;
   }

String orderBody = "\"{\\r\\n  \\\"id\\\": 1,\\r\\n  \\\"petId\\\": 1,\\r\\n  \\\"quantity\\\": 5,\\r\\n  \\\"shipDate\\\": \\\"2022-07-30T19:28:44.522+0000\\\",\\r\\n  \\\"status\\\": \\\"placed\\\",\\r\\n  \\\"complete\\\": true\\r\\n}\"";
   @Test
   public void verifySuccesfullOrder() throws UnirestException {


      Unirest.setTimeouts(0, 0);
      HttpResponse<String> response =  Unirest.post("https://petstore.swagger.io/v2/store/order")
              .header("Content-Type", "application/json")
              .body(orderBody)
              .asString();

      Assertions.assertEquals(200,response.getStatus());


   }
   //RestAssured
   @Test
   public void verifySuccessfullAOrder(){


      response = given()
              .header("Content-Type","application/json")
              .body(orderBody)
              .when()
              .post("store/order")
              .then()
              .statusCode(200)
              .contentType(ContentType.JSON)
              .extract().response();

      JsonPath orderJson =  response.jsonPath();



   }

}
