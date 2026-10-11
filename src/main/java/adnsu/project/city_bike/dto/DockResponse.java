package adnsu.project.city_bike.dto;

import adnsu.project.city_bike.entity.DockStatus;

public record DockResponse(Integer id, Integer dockNumber, DockStatus status, Integer stationId) {
}
