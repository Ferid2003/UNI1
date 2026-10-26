package adnsu.project.city_bike.controller;

import org.springframework.web.bind.annotation.*;

import adnsu.project.city_bike.dto.RentalRequest;
import adnsu.project.city_bike.dto.RentalResponse;
import adnsu.project.city_bike.service.AbstractCrudService;

@RestController
@RequestMapping("/api/rentals")
public class RentalController extends AbstractCrudController<RentalRequest, RentalResponse> {

    public RentalController(AbstractCrudService<?, RentalRequest, RentalResponse> service) {
        super(service);
    }
}
