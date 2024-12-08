package DriveIT.JH;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CountriesApiTest {

    @Mock
    private country_service countryService;

    @InjectMocks
    private countries_api countriesApi;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCountry() {
        String countryCode = "POL";
        String mockResponse = "Country{name_common='Poland', name_official='Republic of Poland'}";

        when(countryService.get_country(countryCode)).thenReturn(mockResponse);

        String response = countriesApi.get_country(countryCode);

        assertEquals(mockResponse, response);
        verify(countryService, times(1)).get_country(countryCode);
    }
}
