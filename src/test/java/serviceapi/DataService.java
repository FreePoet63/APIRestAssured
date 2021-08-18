package serviceapi;

import datatest.DataHeroes;

public class DataService {
    public static DataHeroes newHeroPost() {
        return new DataHeroes()
                .setId(1500)
                .setFullName("Natasha Terekhova")
                .setBirthDate("1963-07-18")
                .setCity("Orenburg")
                .setMainSkill("Doctor")
                .setGender("F")
                .setPhone(777999333);
    }
}
