package tests;

import client.HeroClient;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.Hero;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testdata.HeroFactory;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Heroes API")
public class HeroesApiTest {

    private final HeroClient heroClient = new HeroClient();

    @Test
    @Story("Создание героя")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Созданный герой доступен по своему id")
    void shouldGetCreatedHero() {
        Hero expected = HeroFactory.newHero();

        Hero created = heroClient.create(expected);
        Hero actual = heroClient.get("/" + created.getId());

        assertHeroSaved(expected, actual);
    }

    @ParameterizedTest(name = "Создание героя: город {0}, навык {1}")
    @CsvSource({
            "Orenburg, Doctor",
            "Moscow, Engineer",
            "Kazan, Teacher"
    })
    @Story("Создание героя")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Герой создаётся с разными городами и навыками")
    void shouldCreateHeroWithDifferentData(String city, String skill) {
        Hero expected = HeroFactory.newHero()
                .setCity(city)
                .setMainSkill(skill);

        Hero created = heroClient.create(expected);
        Hero actual = heroClient.get("/" + created.getId());

        assertHeroSaved(expected, actual);
    }

    @Test
    @Story("Список героев")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Созданный герой появляется в общем списке")
    void shouldFindCreatedHeroInList() {
        Hero expected = HeroFactory.newHero();

        Hero created = heroClient.create(expected);

        assertThat(heroClient.getAll())
                .extracting(Hero::getId)
                .contains(created.getId());
    }

    @Test
    @Story("Обновление героя")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Герой обновляется и изменения сохраняются")
    void shouldUpdateHero() {
        Hero created = heroClient.create(HeroFactory.newHero());
        Hero updated = created.setCity("Novosibirsk").setMainSkill("Surgeon");

        heroClient.update("/" + created.getId(), updated);
        Hero actual = heroClient.get("/" + created.getId());

        assertThat(actual.getCity()).isEqualTo("Novosibirsk");
        assertThat(actual.getMainSkill()).isEqualTo("Surgeon");
    }

    @Test
    @Story("Удаление героя")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удалённый герой пропадает из списка")
    void shouldDeleteHero() {
        Hero created = heroClient.create(HeroFactory.newHero());

        heroClient.delete("/" + created.getId());

        assertThat(heroClient.getAll())
                .extracting(Hero::getId)
                .doesNotContain(created.getId());
    }

    @Test
    @Story("Получение героя")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Несуществующий герой возвращает клиентскую ошибку 4xx")
    void shouldReturnClientErrorForUnknownHero() {
        int status = heroClient.getRaw("/999999999").statusCode();

        assertThat(status).isBetween(400, 499);
    }

    private void assertHeroSaved(Hero expected, Hero actual) {
        assertThat(actual.getId())
                .as("Сервер должен сгенерировать id")
                .isNotNull();

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id", "phone")
                .isEqualTo(expected);
    }
}
