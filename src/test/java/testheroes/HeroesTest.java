package testheroes;

import datatest.DataHeroes;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpHeaders;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static serviceapi.DataService.newHeroPost;
import static serviceapi.RestService.*;
import static testconfig.TestConfig.BASE_URL;

public class HeroesTest {

    @Test
    public void createHero() {
        given()
                .filter(new AllureRestAssured())
                .spec(initSpec(BASE_URL))
                .body(newHeroPost())
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().header(HttpHeaders.LOCATION);

       DataHeroes newDataHeroes = given()
                .filter(new AllureRestAssured())
                .spec(initSpec(BASE_URL))
                .when()
                .get("/1420")
                .then()
                .statusCode(200)
                .extract().as(DataHeroes.class);
        assertThat(newDataHeroes.getFullName()).isEqualTo(newHeroPost().getFullName());
    }

    @Test
    public void getListHeroes() {
        DataHeroes heroes = newHeroPost();
        createResource(heroes);
        DataHeroes newHeroes = getResource("/1264", DataHeroes.class);
        assertEqualHero(heroes, newHeroes);
    }

    @Test
    public void getAllCityHeroes() {
        JsonPath heroList = given()
                .spec(initSpec(BASE_URL))
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().jsonPath();
        assertThat(heroList.getList("city")).contains(newHeroPost().getCity());
    }

    @Test
    public void getFullNameHeroes() {
        JsonPath heroFullNameList = given()
                .spec(initSpec(BASE_URL))
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().jsonPath();
        assertThat(heroFullNameList.getList("fullName")).contains(newHeroPost().getFullName());
    }

    @Test
    public void testDataHeroes() {
        JsonPath hero = given()
                .spec(initSpec(BASE_URL))
                .when()
                .get("/1420")
                .then()
                .statusCode(200)
                .extract().jsonPath();
        assertThat(hero.getString("city")).isEqualTo("Orenburg");
    }

    @Test
    public void allHero() {
        assertThat(listHeroes("city")).isNotEmpty();
        assertThat(listHeroes("city")).asList().contains("Orenburg");
    }
}
