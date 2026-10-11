package adnsu.project.city_bike.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import adnsu.project.city_bike.entity.Bike;
import adnsu.project.city_bike.dto.BikeRequest;
import adnsu.project.city_bike.dto.BikeResponse;
import adnsu.project.city_bike.repository.BikeRepository;
import adnsu.project.city_bike.repository.DockRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BikeService {

    private final BikeRepository repo;
    private final DockRepository docks;

    @Transactional(readOnly = true)
    public List<BikeResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public BikeResponse findById(Integer id) {
        return toResponse(get(id));
    }

    public BikeResponse create(BikeRequest r) {
        return toResponse(repo.save(apply(new Bike(), r)));
    }

    public BikeResponse update(Integer id, BikeRequest r) {
        return toResponse(repo.save(apply(get(id), r)));
    }

    public void delete(Integer id) {
        repo.delete(get(id));
    }

    private Bike get(Integer id) {
        return Found.of(repo.findById(id), "Bike", id);
    }

    private Bike apply(Bike b, BikeRequest r) {
        b.setSerialNumber(r.serialNumber());
        b.setBikeType(r.bikeType());
        b.setStatusCharacter(r.statusCharacter());
        b.setCurrentDock(r.currentDockId() == null ? null
                : Found.of(docks.findById(r.currentDockId()), "Dock", r.currentDockId()));
        return b;
    }

    private BikeResponse toResponse(Bike b) {
        return new BikeResponse(b.getId(), b.getSerialNumber(), b.getBikeType(), b.getStatusCharacter(),
                b.getCurrentDock() == null ? null : b.getCurrentDock().getId());
    }
}
