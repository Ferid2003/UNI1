package adnsu.project.city_bike.dto;

import java.time.Instant;

import adnsu.project.city_bike.entity.MembershipType;

public record RiderResponse(Integer id, String firstName, String lastName, String email, String phone,
        MembershipType membershipType, Instant registeredAt) {
}
