package datatest;

public class DataAnimals {
    private String type;
    private String name;

    public DataAnimals() {}

    public DataAnimals(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataAnimals{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
