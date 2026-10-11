package adnsu.project.city_bike.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import adnsu.project.city_bike.dto.BikeRequest;
import adnsu.project.city_bike.dto.BikeResponse;
import adnsu.project.city_bike.service.BikeService;

@RestController
@RequestMapping("/api/bikes")
@RequiredArgsConstructor
public class BikeController {

    private final BikeService service;

    @GetMapping
    public List<BikeResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BikeResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BikeResponse create(@RequestBody BikeRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public BikeResponse update(@PathVariable Integer id, @RequestBody BikeRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
