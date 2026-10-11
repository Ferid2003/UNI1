package adnsu.project.city_bike.dto;

import adnsu.project.city_bike.entity.DockStatus;

public record DockRequest(Integer dockNumber, DockStatus status, Integer stationId) {
}
