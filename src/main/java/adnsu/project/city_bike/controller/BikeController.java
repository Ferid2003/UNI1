package adnsu.project.city_bike.controller;

import org.springframework.web.bind.annotation.*;

import adnsu.project.city_bike.dto.BikeRequest;
import adnsu.project.city_bike.dto.BikeResponse;
import adnsu.project.city_bike.service.AbstractCrudService;

@RestController
@RequestMapping("/api/bikes")
public class BikeController extends AbstractCrudController<BikeRequest, BikeResponse> {

    public BikeController(AbstractCrudService<?, BikeRequest, BikeResponse> service) {
        super(service);
    }
}
