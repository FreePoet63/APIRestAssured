package serviceapi;

import datatest.DataAnimals;
import datatest.DataHeroes;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static testconfig.TestConfig.BASE_URL;
import static testconfig.TestConfig.URL_ANIMAL;


public class RestService {
    public static RequestSpecification spec;

    public static RequestSpecification initSpec(String value) {
        return spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(value)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    public static String createResource(Object body) {
        return given()
                .spec(initSpec(BASE_URL))
                .body(body)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().header(HttpHeaders.LOCATION);
    }

    public static  <T> T getResource(String path, Class<T> responseClass) {
        return given()
                .spec(initSpec(BASE_URL))
                .when()
                .get(path)
                .then()
                .statusCode(200)
                .extract().as(responseClass);
    }

    public static void assertEqualHero(DataHeroes newDataHeroes, DataHeroes newHero ) {
        assertThat(newDataHeroes.getFullName()).isEqualTo(newHero.getFullName());
    }

    public static List<DataHeroes> listHeroes(String path) {
        return given()
                .spec(initSpec(BASE_URL))
                .get()
                .jsonPath().getList(path);
    }

    public static List<DataAnimals> listAnimals() {
        return given()
                .spec(initSpec(URL_ANIMAL))
                .get()
                .jsonPath().getList("animals", DataAnimals.class);
    }
}
