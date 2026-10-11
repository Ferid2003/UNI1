package adnsu.project.city_bike.dto;

import adnsu.project.city_bike.entity.MembershipType;

public record RiderRequest(String firstName, String lastName, String email, String passwordHash, String phone,
        MembershipType membershipType) {
}
