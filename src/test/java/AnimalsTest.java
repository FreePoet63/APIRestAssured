import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class AnimalsTest {

    @Test
    public void requestGetAnimals() {
        given().
                filter(new AllureRestAssured()).
                get("https://qastormnet.pythonanywhere.com/animal").
                then().
                statusCode(200).
                headers("Content-Type", "application/json").
                extract().response().prettyPrint();
        System.out.println();
        Response response = get("https://qastormnet.pythonanywhere.com/animal");
        for (Header header : response.getHeaders()) {
            System.out.println(header.getName() + ":" + header.getValue());
        }
    }

    @Test
    public void responseName() {
        given().
                filter(new AllureRestAssured()).
                get("https://qastormnet.pythonanywhere.com/animal").
                then().
                statusCode(200).
                headers("Content-Type", "application/json").
                log().body().
                assertThat()
                .body("animals.name", hasItems("Ellie", "Python", "Zed"));
    }

    @Test
    public void responseType() {
        given().
                filter(new AllureRestAssured()).
                get("https://qastormnet.pythonanywhere.com/animal").
                then().
                statusCode(200).
                headers("Content-Type", "application/json").
                log().body().
                assertThat()
                .body("animals.type", hasItem("Snake"));
    }

    @Test
    public void responseItem() {
        given().
                filter(new AllureRestAssured()).
                get("https://qastormnet.pythonanywhere.com/animal/Zed").
                then().
                statusCode(200).
                headers("Content-Type", "application/json").
                log().body().
                assertThat()
                .body("animal.name",equalTo("Zed"));
    }

    @Test
    public void responseSize() {
        given().
                filter(new AllureRestAssured()).
                get("https://qastormnet.pythonanywhere.com/animal").
                then().
                statusCode(200).
                headers("Content-Type", "application/json").
                log().body().
                body("animals.size()",is(8));
    }
}
