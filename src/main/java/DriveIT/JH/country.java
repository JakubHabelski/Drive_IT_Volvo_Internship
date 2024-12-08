package DriveIT.JH;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor

public class country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name_common;
    private String name_official;
    private String currencies;
    private String capital;
    private String region;
    private String subregion;
    private String languages;
    private Long population;
    private String borders;
    private String time_zones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_common() {
        return name_common;
    }

    public void setName_common(String name_common) {
        this.name_common = name_common;
    }

    public String getName_official() {
        return name_official;
    }

    public void setName_official(String name_official) {
        this.name_official = name_official;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String bordes) {
        this.borders = bordes;
    }

    public String getTime_zones() {
        return time_zones;
    }

    public void setTime_zones(String time_zones) {
        this.time_zones = time_zones;
    }

    @Override
    public String toString() {
        return "country{" +
                "name_common='" + name_common + '\'' +
                ", name_official='" + name_official + '\'' +
                ", currencies='" + currencies + '\'' +
                ", capital='" + capital + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", languages='" + languages + '\'' +
                ", population=" + population +
                ", borders='" + borders + '\'' +
                ", time_zones='" + time_zones + '\'' +
                '}';
    }
}
