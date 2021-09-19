package br.com.gsr.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {

	public static void setUp() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}

	@Test
	public void deveRetornarTarefas() {
		RestAssured.given().body("{ \"task\": \"Teste via API\", \"dueDate\": \"2020-12-30\" }")
				.contentType(ContentType.JSON).when().get("/todo").then().statusCode(200);
	}

	public void deveAdicionarTarefaComSucesso() {
		RestAssured.given().when().post("/todo").then().statusCode(201);
	}

	public void naoDeveAdicionarTarefaInvalida() {
		RestAssured.given()
			.body("{ \"task\": \"Teste via API\", \"dueDate\": \"2020-12-30\" }")
			.contentType(ContentType.JSON)
		.when()
			.get("/todo")
		.then()
			.statusCode(400)
			.body("message", CoreMatchers.is("Due date must not be in past"));
			;
	}

}
