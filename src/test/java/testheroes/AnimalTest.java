package testheroes;

import datatest.DataAnimals;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.Test;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static serviceapi.RestService.initSpec;
import static org.assertj.core.api.Assertions.assertThat;
import static serviceapi.RestService.listAnimals;
import static testconfig.TestConfig.URL_ANIMAL;

public class AnimalTest {

    @Test
    public void responseSize() {
        given()
                .filter(new AllureRestAssured())
                .spec(initSpec(URL_ANIMAL))
                .when()
                .get()
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("animals.size()",is(3));
    }

    @Test
    public void responseType() {
       List<String> listTypeAnimals = given()
                .filter(new AllureRestAssured())
                .spec(initSpec(URL_ANIMAL))
                .when()
                .get()
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract().path("animals.type");
        assertThat(listTypeAnimals)
                .isNotNull()
                .extracting(typeAnimal -> typeAnimal.contains("Snake"));
    }

    @Test
    public void getType() {
         List<String> animalList = given()
                 .filter(new AllureRestAssured())
                 .spec(initSpec(URL_ANIMAL))
                .when()
                .get()
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract().path("animals.type");
        assertThat(animalList)
                .isNotEmpty()
                .contains("Snake", "Zebra", "Elephant");
    }

    @Test
    public void responseName() {
        List<String> nameList = given()
                .filter(new AllureRestAssured())
                .spec(initSpec(URL_ANIMAL))
                .when()
                .get()
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract().path("animals.name");
        assertThat(nameList)
                .isNotNull()
                .contains("Python", "Zed", "Ellie");
    }

    @Test
    public void listAnimalsEqual() {
        assertThat(listAnimals())
                .isNotEmpty()
                .extracting(DataAnimals::getName)
                .contains("Python", "Zed", "Ellie");
        assertThat(listAnimals())
                .isNotNull()
                .extracting(DataAnimals::getType)
                .contains("Snake","Zebra","Elephant");
    }

    @Test
    public void responseItem() {
        given()
                .filter(new AllureRestAssured())
                .spec(initSpec(URL_ANIMAL))
                .basePath("/Zed")
                .when()
                .get()
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("animals.name", equalTo("Zed"));
    }
}
