package demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class Demo {

  @BeforeMethod
  void setup() {
    RestAssured.baseURI = "https://gorest.co.in";
  }

  @Test
  void getUsers() {

    Map<String, Object> queryParams = new HashMap<>();
    queryParams.put("key", "value");
    queryParams.put("name", "Ivan");
    queryParams.put("status", true);

    Response response =
        given()
            .log()
            .all()
            .queryParams(queryParams)
            //            .queryParam("key", "value")
            //            .queryParam("name", "Ivan")
            //            .queryParam("status", true)
            .get("/public/v2/users")
            .then()
            .log()
            .all()
            .extract()
            .response();

    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
  }

  @Test
  void createUser() {

    enum Gender {FEMALE, MALE};

    String token = "Bearer 8abf5112d17e52fc5176d5a7355b29ecc377f22bfc2564cb3a14efe52d7b8d6c";

    String userName = UUID.randomUUID().toString().substring(10);
    String email = userName + "@abv.bg";
    String gender = Gender.FEMALE.toString();
    String status = "active";

    JSONObject bodyParams = new JSONObject();
    bodyParams.put("name", userName);
    bodyParams.put("email", email);
    bodyParams.put("gender", gender);
    bodyParams.put("status", status);

    Response response =
        given()
            .log()
            .all()
            .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(bodyParams.toJSONString())
            .post("/public/v2/users")
            .then()
            .log()
            .all()
            .extract()
            .response();

    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
  }
}
