package adnsu.project.city_bike.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import adnsu.project.city_bike.entity.Dock;
import adnsu.project.city_bike.entity.Rental;
import adnsu.project.city_bike.dto.RentalRequest;
import adnsu.project.city_bike.dto.RentalResponse;
import adnsu.project.city_bike.repository.BikeRepository;
import adnsu.project.city_bike.repository.DockRepository;
import adnsu.project.city_bike.repository.RentalRepository;
import adnsu.project.city_bike.repository.RiderRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository repo;
    private final BikeRepository bikes;
    private final RiderRepository riders;
    private final DockRepository docks;

    @Transactional(readOnly = true)
    public List<RentalResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RentalResponse findById(Integer id) {
        return toResponse(get(id));
    }

    public RentalResponse create(RentalRequest r) {
        return toResponse(repo.save(apply(new Rental(), r)));
    }

    public RentalResponse update(Integer id, RentalRequest r) {
        return toResponse(repo.save(apply(get(id), r)));
    }

    public void delete(Integer id) {
        repo.delete(get(id));
    }

    private Rental get(Integer id) {
        return Found.of(repo.findById(id), "Rental", id);
    }

    private Rental apply(Rental x, RentalRequest r) {
        x.setBike(Found.of(bikes.findById(r.bikeId()), "Bike", r.bikeId()));
        x.setRider(Found.of(riders.findById(r.riderId()), "Rider", r.riderId()));
        x.setStartDock(dock(r.startDockId()));
        x.setEndDock(r.endDockId() == null ? null : dock(r.endDockId()));
        x.setEndTime(r.endTime());
        x.setCost(r.cost());
        return x;
    }

    private Dock dock(Integer id) {
        return Found.of(docks.findById(id), "Dock", id);
    }

    private RentalResponse toResponse(Rental x) {
        return new RentalResponse(x.getId(), x.getStartTime(), x.getEndTime(), x.getCost(), x.getBike().getId(),
                x.getRider().getId(), x.getStartDock().getId(),
                x.getEndDock() == null ? null : x.getEndDock().getId());
    }
}
