package adnsu.project.city_bike.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import adnsu.project.city_bike.dto.RentalRequest;
import adnsu.project.city_bike.dto.RentalResponse;
import adnsu.project.city_bike.service.RentalService;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService service;

    @GetMapping
    public List<RentalResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RentalResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentalResponse create(@RequestBody RentalRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public RentalResponse update(@PathVariable Integer id, @RequestBody RentalRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
