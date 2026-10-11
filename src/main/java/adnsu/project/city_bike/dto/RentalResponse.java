package adnsu.project.city_bike.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record RentalResponse(Integer id, Instant startTime, Instant endTime, BigDecimal cost, Integer bikeId, Integer riderId,
        Integer startDockId, Integer endDockId) {
}
