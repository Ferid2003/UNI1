package adnsu.project.city_bike.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import adnsu.project.city_bike.entity.Dock;
import adnsu.project.city_bike.entity.Station;
import adnsu.project.city_bike.dto.DockRequest;
import adnsu.project.city_bike.dto.DockResponse;

@Service
public class DockService extends AbstractCrudService<Dock, DockRequest, DockResponse> {

    private final JpaRepository<Station, Integer> stations;

    public DockService(JpaRepository<Dock, Integer> repo, JpaRepository<Station, Integer> stations) {
        super(repo, "Dock", Dock::new);
        this.stations = stations;
    }

    @Override
    protected Dock apply(Dock d, DockRequest r) {
        d.setDockNumber(r.dockNumber());
        d.setStatus(r.status());
        d.setStation(Found.of(stations, "Station", r.stationId()));
        return d;
    }

    @Override
    protected DockResponse toResponse(Dock d) {
        return new DockResponse(d.getId(), d.getDockNumber(), d.getStatus(), d.getStation().getId());
    }
}
