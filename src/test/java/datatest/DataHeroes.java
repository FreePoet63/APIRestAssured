package datatest;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataHeroes {
    private Integer id;
    private String fullName;
    private String birthDate;
    private String city;
    private String mainSkill;
    private String gender;
    private Integer phone;

    public DataHeroes() {}

    public DataHeroes(Integer id, String fullName, String birthDate, String city,
                      String mainSkill, String gender, Integer phone) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.city = city;
        this.mainSkill = mainSkill;
        this.gender = gender;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public DataHeroes setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public DataHeroes setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public DataHeroes setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getCity() {
        return city;
    }

    public DataHeroes setCity(String city) {
        this.city = city;
        return this;
    }

    public String getMainSkill() {
        return mainSkill;
    }

    public DataHeroes setMainSkill(String mainSkill) {
        this.mainSkill = mainSkill;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public DataHeroes setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Integer getPhone() {
        return phone;
    }

    public DataHeroes setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "DataHeroes{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", city='" + city + '\'' +
                ", mainSkill='" + mainSkill + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}
