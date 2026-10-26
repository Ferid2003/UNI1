package adnsu.project.city_bike.service;

import org.springframework.stereotype.Service;

import adnsu.project.city_bike.entity.Rider;
import adnsu.project.city_bike.dto.RiderRequest;
import adnsu.project.city_bike.dto.RiderResponse;
import adnsu.project.city_bike.repository.RiderRepository;

@Service
public class RiderService extends AbstractCrudService<Rider, RiderRequest, RiderResponse> {

    public RiderService(RiderRepository repo) {
        super(repo, "Rider", Rider::new);
    }

    @Override
    protected Rider apply(Rider x, RiderRequest r) {
        x.setFirstName(r.firstName());
        x.setLastName(r.lastName());
        x.setEmail(r.email());
        x.setPasswordHash(r.passwordHash());
        x.setPhone(r.phone());
        x.setMembershipType(r.membershipType());
        return x;
    }

    @Override
    protected RiderResponse toResponse(Rider x) {
        return new RiderResponse(x.getId(), x.getFirstName(), x.getLastName(), x.getEmail(), x.getPhone(),
                x.getMembershipType(), x.getRegisteredAt());
    }
}
