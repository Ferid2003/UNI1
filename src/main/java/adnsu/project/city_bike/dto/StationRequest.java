package adnsu.project.city_bike.dto;

import java.math.BigDecimal;

public record StationRequest(String name, String address, BigDecimal latitude, BigDecimal longitude, Boolean isActive) {
}
