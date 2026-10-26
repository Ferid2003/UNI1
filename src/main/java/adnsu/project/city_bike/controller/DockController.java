package adnsu.project.city_bike.controller;

import org.springframework.web.bind.annotation.*;

import adnsu.project.city_bike.dto.DockRequest;
import adnsu.project.city_bike.dto.DockResponse;
import adnsu.project.city_bike.service.AbstractCrudService;

@RestController
@RequestMapping("/api/docks")
public class DockController extends AbstractCrudController<DockRequest, DockResponse> {

    public DockController(AbstractCrudService<?, DockRequest, DockResponse> service) {
        super(service);
    }
}
