package adnsu.project.city_bike.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import adnsu.project.city_bike.service.AbstractCrudService;

public abstract class AbstractCrudController<REQ, RES> {

    private final AbstractCrudService<?, REQ, RES> service;

    protected AbstractCrudController(AbstractCrudService<?, REQ, RES> service) {
        this.service = service;
    }

    @GetMapping
    public List<RES> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RES findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RES create(@RequestBody REQ request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public RES update(@PathVariable Integer id, @RequestBody REQ request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
