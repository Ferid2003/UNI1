package adnsu.project.city_bike.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import adnsu.project.city_bike.entity.Dock;
import adnsu.project.city_bike.dto.DockRequest;
import adnsu.project.city_bike.dto.DockResponse;
import adnsu.project.city_bike.repository.DockRepository;
import adnsu.project.city_bike.repository.StationRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class DockService {

    private final DockRepository repo;
    private final StationRepository stations;

    @Transactional(readOnly = true)
    public List<DockResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public DockResponse findById(Integer id) {
        return toResponse(get(id));
    }

    public DockResponse create(DockRequest r) {
        return toResponse(repo.save(apply(new Dock(), r)));
    }

    public DockResponse update(Integer id, DockRequest r) {
        return toResponse(repo.save(apply(get(id), r)));
    }

    public void delete(Integer id) {
        repo.delete(get(id));
    }

    private Dock get(Integer id) {
        return Found.of(repo.findById(id), "Dock", id);
    }

    private Dock apply(Dock d, DockRequest r) {
        d.setDockNumber(r.dockNumber());
        d.setStatus(r.status());
        d.setStation(Found.of(stations.findById(r.stationId()), "Station", r.stationId()));
        return d;
    }

    private DockResponse toResponse(Dock d) {
        return new DockResponse(d.getId(), d.getDockNumber(), d.getStatus(), d.getStation().getId());
    }
}
