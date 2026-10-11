package adnsu.project.city_bike.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record RentalRequest(Integer bikeId, Integer riderId, Integer startDockId, Integer endDockId, Instant endTime,
        BigDecimal cost) {
}
