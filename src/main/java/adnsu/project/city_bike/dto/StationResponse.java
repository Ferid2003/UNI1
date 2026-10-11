package adnsu.project.city_bike.dto;

import java.math.BigDecimal;

public record StationResponse(Integer id, String name, String address, BigDecimal latitude, BigDecimal longitude, Boolean isActive) {
}
