package client;

import config.ApiSpecs;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import models.Animal;

import java.util.List;

import static config.TestConfig.ANIMAL_URL;
import static io.restassured.RestAssured.given;

public class AnimalClient {
    private final RequestSpecification spec = ApiSpecs.base(ANIMAL_URL);

    @Step("Получить список животных")
    public List<Animal> getAll() {
        Animal[] animals = given().spec(spec)
                .when().get()
                .then().log().ifValidationFails()
                .statusCode(200)
                .extract().as(Animal[].class);
        return List.of(animals);
    }

    @Step("Получить животное: {name}")
    public List<Animal> getByName(String name) {
        return given().spec(spec)
                .queryParam("name", name)
                .when().get()
                .then().log().ifValidationFails()
                .statusCode(200)
                .extract().jsonPath().getList(".", Animal.class);
    }
}