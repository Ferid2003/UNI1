package adnsu.project.city_bike.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import adnsu.project.city_bike.entity.Dock;
import adnsu.project.city_bike.entity.Rental;
import adnsu.project.city_bike.entity.Rider;
import adnsu.project.city_bike.entity.Bike;
import adnsu.project.city_bike.dto.RentalRequest;
import adnsu.project.city_bike.dto.RentalResponse;

@Service
public class RentalService extends AbstractCrudService<Rental, RentalRequest, RentalResponse> {

    private final JpaRepository<Bike, Integer> bikes;
    private final JpaRepository<Rider, Integer> riders;
    private final JpaRepository<Dock, Integer> docks;

    public RentalService(JpaRepository<Rental, Integer> repo, JpaRepository<Bike, Integer> bikes,
            JpaRepository<Rider, Integer> riders, JpaRepository<Dock, Integer> docks) {
        super(repo, "Rental", Rental::new);
        this.bikes = bikes;
        this.riders = riders;
        this.docks = docks;
    }

    @Override
    protected Rental apply(Rental x, RentalRequest r) {
        x.setBike(Found.of(bikes, "Bike", r.bikeId()));
        x.setRider(Found.of(riders, "Rider", r.riderId()));
        x.setStartDock(dock(r.startDockId()));
        x.setEndDock(r.endDockId() == null ? null : dock(r.endDockId()));
        x.setEndTime(r.endTime());
        x.setCost(r.cost());
        return x;
    }

    private Dock dock(Integer id) {
        return Found.of(docks, "Dock", id);
    }

    @Override
    protected RentalResponse toResponse(Rental x) {
        return new RentalResponse(x.getId(), x.getStartTime(), x.getEndTime(), x.getCost(), x.getBike().getId(),
                x.getRider().getId(), x.getStartDock().getId(),
                x.getEndDock() == null ? null : x.getEndDock().getId());
    }
}
