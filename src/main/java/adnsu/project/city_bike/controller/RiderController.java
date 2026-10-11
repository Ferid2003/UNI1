package adnsu.project.city_bike.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import adnsu.project.city_bike.dto.RiderRequest;
import adnsu.project.city_bike.dto.RiderResponse;
import adnsu.project.city_bike.service.RiderService;

@RestController
@RequestMapping("/api/riders")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService service;

    @GetMapping
    public List<RiderResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RiderResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RiderResponse create(@RequestBody RiderRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public RiderResponse update(@PathVariable Integer id, @RequestBody RiderRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
