import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.*;

public class SuperHeroesTest {

    @Test
    public void responsePost() {
        given().baseUri("https://superhero.qa-test.csssr.com/superheroes").
                filter(new AllureRestAssured()).
                contentType(JSON).
                accept(JSON).
                header("Content-Type","application/json").
                body("{" +
                        "\"id\":300," +
                        "\"fullName\":\"Nikolay Terekhov\"," +
                        "\"birthDate\":\"1963-06-23\"," +
                        "\"city\":\"Orenburg\"," +
                        "\"mainSkill\":\"Magic\"," +
                        "\"gender\":\"W\",\"" +
                        "phone\":1234567}").
                when().
                post().
                then().
                log().body().
                body("city", equalTo("Orenburg"));
    }

    @Test
    public void requestGet() {
        given().
                filter(new AllureRestAssured()).
                get("https://superhero.qa-test.csssr.com/superheroes").
                then().
                statusCode(200).
                headers("Content-Type","application/json;charset=UTF-8").
                extract().response().prettyPrint();
        System.out.println();
        Response response = get("https://superhero.qa-test.csssr.com/superheroes");
        for (Header header : response.getHeaders()) {
            System.out.println(header.getName() + ":" + header.getValue());
        }
    }

    @Test
    public void responseFullName() {
        given().
                filter(new AllureRestAssured()).
                get("https://superhero.qa-test.csssr.com/superheroes").
                then().
                statusCode(200).
                headers("Content-Type","application/json;charset=UTF-8").
                log().body().
                assertThat()
                .body("fullName",hasItems("Doctor Terekhov","Ztest","Nikolay Terekhov"));
    }

    @Test
    public void responseCity() {
        given().
                filter(new AllureRestAssured()).
                get("https://superhero.qa-test.csssr.com/superheroes").
                then().
                statusCode(200).
                headers("Content-Type","application/json;charset=UTF-8").
                log().body().
                assertThat()
                .body("city",hasItem("Vladimir"));
    }

    @Test
    public void responsePut() {
        given().baseUri("https://superhero.qa-test.csssr.com/superheroes/139").
                filter(new AllureRestAssured()).
                contentType(JSON).
                accept(JSON).
                header("Content-Type","application/json").
                body("{" +
                        "\"id\":139," +
                        "\"fullName\":\"Nikolay Terekhov\"," +
                        "\"birthDate\":\"1963-05-10\"," +
                        "\"city\":\"Paris\"," +
                        "\"mainSkill\":\"Magic\"," +
                        "\"gender\":\"M\",\"" +
                        "phone\":null}").
                when().
                put().
                then().
                log().body();
    }

    @Test
    public void responseId() {
        given().
                filter(new AllureRestAssured()).
                get("https://superhero.qa-test.csssr.com/superheroes/139").
                then().
                statusCode(200).
                headers("Content-Type", "application/json;charset=UTF-8").
                log().body().
                body("id", equalTo(139));
    }

    @Test
    public void responseDelete() {
        given().baseUri("https://superhero.qa-test.csssr.com/superheroes/144").
                filter(new AllureRestAssured()).
                contentType(JSON).
                accept(JSON).
                header("Content-Type","application/json").
                body("{" +
                        "\"id\":300," +
                        "\"fullName\":\"Nikolay Terekhov\"," +
                        "\"birthDate\":\"1963-06-23\"," +
                        "\"city\":\"Orenburg\"," +
                        "\"mainSkill\":\"Magic\"," +
                        "\"gender\":\"W\",\"" +
                        "phone\":1234567}").
                when().
                delete().
                then().
                log().body();
    }

}
