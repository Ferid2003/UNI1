package adnsu.project.city_bike.service;

import org.springframework.stereotype.Service;

import adnsu.project.city_bike.entity.Dock;
import adnsu.project.city_bike.entity.Rental;
import adnsu.project.city_bike.dto.RentalRequest;
import adnsu.project.city_bike.dto.RentalResponse;
import adnsu.project.city_bike.repository.BikeRepository;
import adnsu.project.city_bike.repository.DockRepository;
import adnsu.project.city_bike.repository.RentalRepository;
import adnsu.project.city_bike.repository.RiderRepository;

@Service
public class RentalService extends AbstractCrudService<Rental, RentalRequest, RentalResponse> {

    private final BikeRepository bikes;
    private final RiderRepository riders;
    private final DockRepository docks;

    public RentalService(RentalRepository repo, BikeRepository bikes, RiderRepository riders, DockRepository docks) {
        super(repo, "Rental", Rental::new);
        this.bikes = bikes;
        this.riders = riders;
        this.docks = docks;
    }

    @Override
    protected Rental apply(Rental x, RentalRequest r) {
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

    @Override
    protected RentalResponse toResponse(Rental x) {
        return new RentalResponse(x.getId(), x.getStartTime(), x.getEndTime(), x.getCost(), x.getBike().getId(),
                x.getRider().getId(), x.getStartDock().getId(),
                x.getEndDock() == null ? null : x.getEndDock().getId());
    }
}
