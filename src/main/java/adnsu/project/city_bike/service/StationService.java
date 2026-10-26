package adnsu.project.city_bike.service;

import org.springframework.stereotype.Service;

import adnsu.project.city_bike.entity.Station;
import adnsu.project.city_bike.dto.StationRequest;
import adnsu.project.city_bike.dto.StationResponse;
import adnsu.project.city_bike.repository.StationRepository;

@Service
public class StationService extends AbstractCrudService<Station, StationRequest, StationResponse> {

    public StationService(StationRepository repo) {
        super(repo, "Station", Station::new);
    }

    @Override
    protected Station apply(Station s, StationRequest r) {
        s.setName(r.name());
        s.setAddress(r.address());
        s.setLatitude(r.latitude());
        s.setLongitude(r.longitude());
        s.setIsActive(r.isActive());
        return s;
    }

    @Override
    protected StationResponse toResponse(Station s) {
        return new StationResponse(s.getId(), s.getName(), s.getAddress(), s.getLatitude(), s.getLongitude(),
                s.getIsActive());
    }
}
