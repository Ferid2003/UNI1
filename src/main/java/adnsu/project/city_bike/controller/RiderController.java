package adnsu.project.city_bike.controller;

import org.springframework.web.bind.annotation.*;

import adnsu.project.city_bike.dto.RiderRequest;
import adnsu.project.city_bike.dto.RiderResponse;
import adnsu.project.city_bike.service.RiderService;

@RestController
@RequestMapping("/api/riders")
public class RiderController extends AbstractCrudController<RiderRequest, RiderResponse> {

    public RiderController(RiderService service) {
        super(service);
    }
}
