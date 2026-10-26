package adnsu.project.city_bike.controller;

import org.springframework.web.bind.annotation.*;

import adnsu.project.city_bike.dto.StationRequest;
import adnsu.project.city_bike.dto.StationResponse;
import adnsu.project.city_bike.service.AbstractCrudService;

@RestController
@RequestMapping("/api/stations")
public class StationController extends AbstractCrudController<StationRequest, StationResponse> {

    public StationController(AbstractCrudService<?, StationRequest, StationResponse> service) {
        super(service);
    }
}
