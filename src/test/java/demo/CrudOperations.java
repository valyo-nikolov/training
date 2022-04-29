package demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class CrudOperations {

  @BeforeMethod
  void setup() {
    RestAssured.baseURI = "https://gorest.co.in";
  }

  @Test
  void getDemoTest() {

//    Map<String, Object> params = new HashMap<>();
//    params.put("proba", "demo");
//    params.put("test", 123);
//    params.put("isTrue", true);

    Response response =
        given()
            .log()
            .all()
            .contentType(ContentType.JSON)
//            .queryParams(params)
            .get("/public/v2/users")
            .then()
            .log()
            .all()
            .extract()
            .response();

    Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    Assert.assertNotNull(response.getBody());
    Assert.assertNotNull(response.jsonPath().get("[0].id"));
  }

  @Test
  void postDemoTest() {

    String token = "Bearer " + "8abf5112d17e52fc5176d5a7355b29ecc377f22bfc2564cb3a14efe52d7b8d6c";

    String name = UUID.randomUUID().toString().substring(10);
    String email = name + "@abv.bg";
    String gender = "female";
    String status = "active";

    JSONObject body = new JSONObject();
    body.put("name", name);
    body.put("gender", gender);
    body.put("email", email);
    body.put("status", status);

    Response response =
        given()
            .log()
            .all()
            .header("Authorization", token)
            .contentType(ContentType.JSON)
            .body(body.toJSONString())
            .post("/public/v2/users")
            .then()
            .log()
            .all()
            .extract()
            .response();

    int userId = response.jsonPath().get("id");

    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    Assert.assertNotNull(response.getBody());
    Assert.assertEquals((Integer) response.jsonPath().get("id"), userId);
    Assert.assertEquals(response.jsonPath().get("name"), name);
    Assert.assertEquals(response.jsonPath().get("gender"), gender);
    Assert.assertEquals(response.jsonPath().get("status"), status);
  }

  @Test
  void putDemoTest() {
    String token = "Bearer " + "8abf5112d17e52fc5176d5a7355b29ecc377f22bfc2564cb3a14efe52d7b8d6c";

    String name = UUID.randomUUID().toString().substring(10);
    String email = name + "@abv.bg";
    String gender = "female";
    String status = "active";

    JSONObject body = new JSONObject();
    body.put("name", name);
    body.put("gender", gender);
    body.put("email", email);
    body.put("status", status);

    Response response =
        given()
            .log()
            .all()
            .header("Authorization", token)
            .contentType(ContentType.JSON)
            .body(body.toJSONString())
            .post("/public/v2/users")
            .then()
            .log()
            .all()
            .extract()
            .response();

    int userId = response.jsonPath().get("id");

    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    Assert.assertNotNull(response.getBody());
    Assert.assertEquals((Integer) response.jsonPath().get("id"), userId);
    Assert.assertEquals(response.jsonPath().get("name"), name);
    Assert.assertEquals(response.jsonPath().get("gender"), gender);
    Assert.assertEquals(response.jsonPath().get("status"), status);

    gender = "male";

    body = new JSONObject();
    body.put("gender", gender);

    response =
        given()
            .log()
            .all()
            .header("Authorization", token)
            .contentType(ContentType.JSON)
            .body(body.toJSONString())
            .put("/public/v2/users/" + userId)
            .then()
            .log()
            .all()
            .extract()
            .response();

    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    Assert.assertNotNull(response.getBody());
    Assert.assertEquals((Integer) response.jsonPath().get("id"), userId);
    Assert.assertEquals(response.jsonPath().get("name"), name);
    Assert.assertEquals(response.jsonPath().get("gender"), gender);
    Assert.assertEquals(response.jsonPath().get("status"), status);
  }

  @Test
  void deleteDemoTest() {
    String token = "Bearer " + "8abf5112d17e52fc5176d5a7355b29ecc377f22bfc2564cb3a14efe52d7b8d6c";

    String name = UUID.randomUUID().toString().substring(10);
    String email = name + "@abv.bg";
    String gender = "female";
    String status = "active";

    JSONObject body = new JSONObject();
    body.put("name", name);
    body.put("gender", gender);
    body.put("email", email);
    body.put("status", status);

    Response response =
            given()
                    .log()
                    .all()
                    .header("Authorization", token)
                    .contentType(ContentType.JSON)
                    .body(body.toJSONString())
                    .post("/public/v2/users")
                    .then()
                    .log()
                    .all()
                    .extract()
                    .response();

    int userId = response.jsonPath().get("id");

    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    Assert.assertNotNull(response.getBody());
    Assert.assertEquals((Integer) response.jsonPath().get("id"), userId);
    Assert.assertEquals(response.jsonPath().get("name"), name);
    Assert.assertEquals(response.jsonPath().get("gender"), gender);
    Assert.assertEquals(response.jsonPath().get("status"), status);

    response =
            given()
                    .log()
                    .all()
                    .header("Authorization", token)
                    .delete("/public/v2/users/" + userId)
                    .then()
                    .log()
                    .all()
                    .extract()
                    .response();

    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);
  }
}
