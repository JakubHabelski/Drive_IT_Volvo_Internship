package DriveIT.JH;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class country_service {

    private final country_repo repo;

    public country_service(country_repo repo) {
        this.repo = repo;
    }
    private final String url_begin = "https://restcountries.com/v3.1/alpha/";
    private final String url_end= "?fields=name,capital,currencies,capital,region,subregion,languages,population,borders,timezones";

    public String get_response(String country_code){
        WebClient.Builder client = WebClient.builder();
        String url= url_begin + country_code + url_end;
        return  client.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }

    public country save_country_search(country country){
        return repo.save(country);
    }

    public String get_country(String country_code){
        String response = get_response(country_code);
        JSONObject jsonObject =  new JSONObject(response);
        String common = jsonObject.getJSONObject("name").getString("common");
        String official = jsonObject.getJSONObject("name").getString("official");
        String capital = jsonObject.getJSONArray("capital").toString().replace("[", "").replace("]", "").replace("\"", "");
        String region = jsonObject.getString("region");
        String subregion = jsonObject.getString("subregion");
        Long population = jsonObject.getLong("population");
        String borders = jsonObject.getJSONArray("borders").toString().replace("[", "").replace("]", "").replace("\"","").toString();
        Map<String, String> languages_map = jsonObject.getJSONObject("languages").toMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().toString()));
        List<String> languages_array = languages_map.values().stream().toList();
        String timezones = jsonObject.getJSONArray("timezones").toString().replace("[", "").replace("]", "").replace("\"", "");
        Map<String, Object> currencies = jsonObject.getJSONObject("currencies").toMap();
        StringBuilder final_currencies = new StringBuilder();
        currencies.forEach((key, value) -> {
            JSONObject currencyData = new JSONObject((Map) value);
            String name = currencyData.getString("name");
            String symbol = currencyData.getString("symbol");

            currency Currency = new currency(name, symbol);
            System.out.println(key+ ": "+Currency);
            String final_String = "Key: " + key + ", name: " + name + ", symbol: " + symbol+"\n";
            final_currencies.append(final_String);
            System.out.println(final_String);
        });
        System.out.println("final_currencies: "+ final_currencies.toString());
        country Country = new country();
        Country.setName_common(common);
        Country.setName_official(official);
        Country.setCapital(capital);
        Country.setRegion(region);
        Country.setSubregion(subregion);
        Country.setPopulation(population);
        Country.setBorders(borders);
        Country.setLanguages(languages_array.toString().replace("[", "").replace("]", ""));
        Country.setTime_zones(timezones);
        Country.setCurrencies(final_currencies.toString());
        save_country_search(Country);
        return Country.toString();
    }

}
