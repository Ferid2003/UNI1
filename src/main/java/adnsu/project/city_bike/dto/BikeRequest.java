package adnsu.project.city_bike.dto;

import adnsu.project.city_bike.entity.BikeStatus;
import adnsu.project.city_bike.entity.BikeType;

public record BikeRequest(String serialNumber, BikeType bikeType, BikeStatus statusCharacter, Integer currentDockId) {
}
