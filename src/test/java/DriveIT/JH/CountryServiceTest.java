package DriveIT.JH;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CountryServiceTest {

    @Mock
    private country_repo repo;

    @InjectMocks
    private country_service countryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetResponse() {
        WebClient.Builder mockBuilder = mock(WebClient.Builder.class, RETURNS_DEEP_STUBS);
        countryService = new country_service(repo) {
            @Override
            public String get_response(String country_code) {
                return "{\"name\":{\"common\":\"Poland\",\"official\":\"Republic of Poland\"}," +
                        "\"capital\":[\"Warsaw\"]," +
                        "\"currencies\":{\"PLN\":{\"name\":\"Polish złoty\",\"symbol\":\"zł\"}}," +
                        "\"region\":\"Europe\"," +
                        "\"subregion\":\"Eastern Europe\"," +
                        "\"languages\":{\"pol\":\"Polish\"}," +
                        "\"population\":38386000," +
                        "\"borders\":[\"BLR\",\"CZE\",\"DEU\",\"LTU\",\"SVK\",\"UKR\"]," +
                        "\"timezones\":[\"UTC+01:00\"]}";
            }
        };

        String countryCode = "POL";
        String response = countryService.get_response(countryCode);

        assertNotNull(response);
        assertTrue(response.contains("Poland"));
    }

    @Test
    void testGetCountry() {
        String countryCode = "POL";
        String mockResponse = """
            {
                "name": {
                    "common": "Poland",
                    "official": "Republic of Poland"
                },
                "capital": ["Warsaw"],
                "currencies": {
                    "PLN": {
                        "name": "Polish złoty",
                        "symbol": "zł"
                    }
                },
                "region": "Europe",
                "subregion": "Eastern Europe",
                "languages": {
                    "pol": "Polish"
                },
                "population": 38386000,
                "borders": ["BLR", "CZE", "DEU", "LTU", "SVK", "UKR"],
                "timezones": ["UTC+01:00"]
            }
        """;

        country_service serviceSpy = spy(countryService);
        doReturn(mockResponse).when(serviceSpy).get_response(countryCode);

        String response = serviceSpy.get_country(countryCode);

        assertNotNull(response);
        assertTrue(response.contains("Poland"));
        verify(serviceSpy, times(1)).save_country_search(any(country.class));
    }

}
