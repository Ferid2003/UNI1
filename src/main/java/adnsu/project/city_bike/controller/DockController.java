package adnsu.project.city_bike.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import adnsu.project.city_bike.dto.DockRequest;
import adnsu.project.city_bike.dto.DockResponse;
import adnsu.project.city_bike.service.DockService;

@RestController
@RequestMapping("/api/docks")
@RequiredArgsConstructor
public class DockController {

    private final DockService service;

    @GetMapping
    public List<DockResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DockResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DockResponse create(@RequestBody DockRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public DockResponse update(@PathVariable Integer id, @RequestBody DockRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
