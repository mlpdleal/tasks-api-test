package br.com.mlpdleal.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiTest {
	
	@BeforeClass
	public static void setup() {
		
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
		
	}

	@Test
	public void deveRetornarTarefas() {
		RestAssured
			.given()
			.when()
				.get("/todo")
			.then()
				.statusCode(200);

	}
	
	@Test
	public void deveAdicionarTarefaComSucesso() {
		RestAssured
			.given()
				.body("{\"task\":\"teste 2\",\"dueDate\":\"2040-12-20\"}")
				.contentType(ContentType.JSON)
			.when()
				.post("/todo")
			.then()
				.statusCode(201);

	}
	

	@Test
	public void naoDeveCadastrarTarefa() {
		RestAssured
			.given()
				.body("{\"task\":\"teste 2\",\"dueDate\":\"2010-12-20\"}")
				.contentType(ContentType.JSON)
			.when()
				.post("/todo")
			.then()
				.statusCode(400)
				.body("message", CoreMatchers.is("Due date must not be in past"));

	}
	
	
}
