package adnsu.project.city_bike.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import adnsu.project.city_bike.dto.StationRequest;
import adnsu.project.city_bike.dto.StationResponse;
import adnsu.project.city_bike.service.StationService;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService service;

    @GetMapping
    public List<StationResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StationResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StationResponse create(@RequestBody StationRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public StationResponse update(@PathVariable Integer id, @RequestBody StationRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
