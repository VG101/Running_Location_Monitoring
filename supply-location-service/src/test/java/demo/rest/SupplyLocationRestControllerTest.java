package demo.rest;

import demo.domain.SupplyLocation;
import demo.domain.SupplyLocationRepository;
import demo.service.SupplyLocationService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SupplyLocationRestControllerTest {

    private SupplyLocationRepository repository;
    private SupplyLocationService service;
    private SupplyLocationRestController controller;
    private List<SupplyLocation> inputLocations;

    @Before
    public void setupMock() {
        repository = mock(SupplyLocationRepository.class);
        service = mock(SupplyLocationService.class);
        controller = new SupplyLocationRestController(repository, service);

        inputLocations = new ArrayList<>();
        inputLocations.add(generateSupplyLocations(4.0, 4.0, "504"));
        inputLocations.add(generateSupplyLocations(5.0, 5.0, "505"));
        inputLocations.add(generateSupplyLocations(6.0, 6.0, "506"));
    }

    @Test
    public void whenListFilteredAndSaved_expectSuccess() {
        List<SupplyLocation> locations = new ArrayList<>();
        locations.add(generateSupplyLocations(4.0, 4.0, "504"));

        when(service.saveSupplyLocationsZipContains504(inputLocations)).thenReturn(locations);

        assertEquals(1, controller.uploadFilteredLocations(inputLocations).size());
        assertEquals("504", controller.uploadFilteredLocations(inputLocations).get(0).getZip());

    }

    private SupplyLocation generateSupplyLocations(double longitude, double latitude, String zip) {
        SupplyLocation location = new SupplyLocation(longitude, latitude);
        location.setZip(zip);
        return location;
    }
}
