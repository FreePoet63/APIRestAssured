package tests;

import client.AnimalClient;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.Animal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.hamcrest.Matchers.equalTo;

@Feature("Animals API")
public class AnimalApiTest {

    private final AnimalClient animalClient = new AnimalClient();

    @Test
    @Story("Список животных")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Возвращается три животных")
    void shouldReturnThreeAnimals() {
        assertThat(animalClient.getAll()).hasSize(3);
    }

    @Test
    @Story("Список животных")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Тип и имя каждого животного совпадают с ожидаемым")
    void shouldReturnExpectedTypeNamePairs() {
        List<Animal> animals = animalClient.getAll();

        assertThat(animals)
                .extracting(Animal::getType, Animal::getName)
                .containsExactlyInAnyOrder(
                        tuple("Snake", "Python"),
                        tuple("Zebra", "Zed"),
                        tuple("Elephant", "Ellie"));
    }

    @Test
    @Story("Получение животного")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Животное запрашивается по имени")
    void shouldReturnAnimalByName() {
        List<Animal> result = animalClient.getByName("Zed");

        assertThat(result)
                .hasSize(1)
                .first()
                .extracting(Animal::getName)
                .isEqualTo("Zed");
    }
}