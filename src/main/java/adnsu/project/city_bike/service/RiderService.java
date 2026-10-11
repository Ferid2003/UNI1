package adnsu.project.city_bike.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import adnsu.project.city_bike.entity.Rider;
import adnsu.project.city_bike.dto.RiderRequest;
import adnsu.project.city_bike.dto.RiderResponse;
import adnsu.project.city_bike.repository.RiderRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RiderService {

    private final RiderRepository repo;

    @Transactional(readOnly = true)
    public List<RiderResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RiderResponse findById(Integer id) {
        return toResponse(get(id));
    }

    public RiderResponse create(RiderRequest r) {
        return toResponse(repo.save(apply(new Rider(), r)));
    }

    public RiderResponse update(Integer id, RiderRequest r) {
        return toResponse(repo.save(apply(get(id), r)));
    }

    public void delete(Integer id) {
        repo.delete(get(id));
    }

    private Rider get(Integer id) {
        return Found.of(repo.findById(id), "Rider", id);
    }

    private Rider apply(Rider x, RiderRequest r) {
        x.setFirstName(r.firstName());
        x.setLastName(r.lastName());
        x.setEmail(r.email());
        x.setPasswordHash(r.passwordHash());
        x.setPhone(r.phone());
        x.setMembershipType(r.membershipType());
        return x;
    }

    private RiderResponse toResponse(Rider x) {
        return new RiderResponse(x.getId(), x.getFirstName(), x.getLastName(), x.getEmail(), x.getPhone(),
                x.getMembershipType(), x.getRegisteredAt());
    }
}
