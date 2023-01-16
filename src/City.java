import java.util.Arrays;

class City {
    private  String name;
    private  String district;
    private String[] streets;

    public City(String name, String district, String[] streets) {
        this.name = name;
        this.district = district;
        this.streets = streets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String[] getStreets() {
        return streets;
    }

    public void setStreets(String[] streets) {
        this.streets = streets;
    }



    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", district='" + district + '\'' +
                ", streets=" + Arrays.toString(streets) +
                '}';
    }

}
