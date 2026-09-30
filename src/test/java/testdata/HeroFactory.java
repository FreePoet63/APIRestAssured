package testdata;

import models.Hero;

public final class HeroFactory {
    private HeroFactory() {}

    public static Hero newHero() {
        return Hero.builder()
                .fullName("Natasha Terekhova")
                .birthDate("1963-07-18")
                .city("Orenburg")
                .mainSkill("Doctor")
                .gender("F")
                .phone("777999333")
                .build();
    }
}