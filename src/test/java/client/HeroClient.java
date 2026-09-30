package client;

import config.ApiSpecs;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Hero;

import java.util.List;

import static config.TestConfig.HERO_URL;
import static io.restassured.RestAssured.given;

public class HeroClient {
    private final RequestSpecification spec = ApiSpecs.base(HERO_URL);

    @Step("Создать героя")
    public Hero create(Hero hero) {
        return given().spec(spec)
                .body(hero)
                .when().post()
                .then().log().ifValidationFails()
                .statusCode(201)
                .extract().as(Hero.class);
    }

    @Step("Получить героя: {path}")
    public Hero get(String path) {
        return given().spec(spec)
                .when().get(path)
                .then().log().ifValidationFails()
                .statusCode(200)
                .extract().as(Hero.class);
    }

    @Step("Получить cписок героев")
    public List<Hero> getAll() {
        Hero[] heroes = given().spec(spec)
                .when().get()
                .then().log().ifValidationFails()
                .statusCode(200)
                .extract().as(Hero[].class);
        return List.of(heroes);
    }

    @Step("Обновить героя: {path}")
    public Hero update(String path, Hero hero) {
        return given().spec(spec)
                .body(hero)
                .when().put(path)
                .then().log().ifValidationFails()
                .statusCode(200)
                .extract().as(Hero.class);
    }

    @Step("Удалить героя: {path}")
    public void delete(String path) {
        given().spec(spec)
                .when().delete(path)
                .then().log().ifValidationFails()
                .statusCode(200);
    }

    @Step("Запросить героя без проверки статуса: {path}")
    public Response getRaw(String path) {
        return given().spec(spec)
                .when().get(path);
    }
}