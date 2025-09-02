package com.dataflow.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiTests {

    private static final String BASE_URI = "https://reqres.in/api";
    // Clave API de ejemplo. Reemplazar si la API real requiere una específica.
    private static final String API_KEY = "reqres-free-v1";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    // 1. Verificación de la existencia de un usuario (GET)
    @Test
    public void testGetUserById() {
        int expectedUserId = 2; // ID de un usuario existente

        given()
                .header("Authorization", "Bearer " + API_KEY)
                .log().uri()
                .when()
                .get("/users/{id}", expectedUserId)
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", containsString("application/json"))
                .body("data.id", equalTo(expectedUserId))
                .body("data.first_name", equalTo("Janet"))
                .time(lessThan(5000L));
    }

    // 2. Registro exitoso de un usuario nuevo (POST)
    @Test
    public void testCreateUser() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + API_KEY)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .log().body()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .time(lessThan(5000L));
    }

    // 3. Modificación del cargo de un usuario existente (PUT)
    @Test
    public void testUpdateUser() {
        int userIdToUpdate = 2;
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + API_KEY)
                .body(requestBody)
                .when()
                .put("/users/{id}", userIdToUpdate)
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .time(lessThan(5000L));
    }

    // 4. Eliminación de un usuario y validación del código de respuesta (DELETE)
    @Test
    public void testDeleteUser() {
        int userIdToDelete = 2;

        given()
                .header("Authorization", "Bearer " + API_KEY)
                .when()
                .delete("/users/{id}", userIdToDelete)
                .then()
                .log().all()
                .statusCode(204)
                .time(lessThan(5000L));
    }

    // 5. Verificación de comportamiento para usuario inexistente (404 esperado)
    @Test
    public void testGetNonExistentUser() {
        int nonExistentUserId = 999;

        given()
                .header("Authorization", "Bearer " + API_KEY)
                .when()
                .get("/users/{id}", nonExistentUserId)
                .then()
                .log().all()
                .statusCode(404)
                .time(lessThan(5000L));
    }

    // 6. Simulación de caso negativo: POST sin body (400 Bad Request esperado)
    @Test
    public void testCreateUser_NoBody() {
        // Esta prueba puede no requerir la API key si la validación de 'body' ocurre antes de la autenticación.
        // Sin embargo, se añade por consistencia.
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + API_KEY)
                .body("")
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", notNullValue())
                .time(lessThan(5000L));
    }
}