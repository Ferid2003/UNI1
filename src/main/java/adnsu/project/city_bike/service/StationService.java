package adnsu.project.city_bike.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import adnsu.project.city_bike.entity.Station;
import adnsu.project.city_bike.dto.StationRequest;
import adnsu.project.city_bike.dto.StationResponse;
import adnsu.project.city_bike.repository.StationRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class StationService {

    private final StationRepository repo;

    @Transactional(readOnly = true)
    public List<StationResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public StationResponse findById(Integer id) {
        return toResponse(get(id));
    }

    public StationResponse create(StationRequest r) {
        return toResponse(repo.save(apply(new Station(), r)));
    }

    public StationResponse update(Integer id, StationRequest r) {
        return toResponse(repo.save(apply(get(id), r)));
    }

    public void delete(Integer id) {
        repo.delete(get(id));
    }

    private Station get(Integer id) {
        return Found.of(repo.findById(id), "Station", id);
    }

    private Station apply(Station s, StationRequest r) {
        s.setName(r.name());
        s.setAddress(r.address());
        s.setLatitude(r.latitude());
        s.setLongitude(r.longitude());
        s.setIsActive(r.isActive());
        return s;
    }

    private StationResponse toResponse(Station s) {
        return new StationResponse(s.getId(), s.getName(), s.getAddress(), s.getLatitude(), s.getLongitude(),
                s.getIsActive());
    }
}
