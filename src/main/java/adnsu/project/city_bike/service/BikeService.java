package adnsu.project.city_bike.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import adnsu.project.city_bike.entity.Bike;
import adnsu.project.city_bike.entity.Dock;
import adnsu.project.city_bike.dto.BikeRequest;
import adnsu.project.city_bike.dto.BikeResponse;

@Service
public class BikeService extends AbstractCrudService<Bike, BikeRequest, BikeResponse> {

    private final JpaRepository<Dock, Integer> docks;

    public BikeService(JpaRepository<Bike, Integer> repo, JpaRepository<Dock, Integer> docks) {
        super(repo, "Bike", Bike::new);
        this.docks = docks;
    }

    @Override
    protected Bike apply(Bike b, BikeRequest r) {
        b.setSerialNumber(r.serialNumber());
        b.setBikeType(r.bikeType());
        b.setStatusCharacter(r.statusCharacter());
        b.setCurrentDock(r.currentDockId() == null ? null
                : Found.of(docks, "Dock", r.currentDockId()));
        return b;
    }

    @Override
    protected BikeResponse toResponse(Bike b) {
        return new BikeResponse(b.getId(), b.getSerialNumber(), b.getBikeType(), b.getStatusCharacter(),
                b.getCurrentDock() == null ? null : b.getCurrentDock().getId());
    }
}
