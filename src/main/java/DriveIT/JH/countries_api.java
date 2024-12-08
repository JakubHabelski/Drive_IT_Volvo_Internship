package DriveIT.JH;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class countries_api {

    private final country_service Country_service;




    public countries_api(country_service countryService) {
        Country_service = countryService;
    }

    @GetMapping("/get_country/{country_code}")
    public String get_country(@PathVariable String country_code) {
       return Country_service.get_country(country_code);
    }



}
