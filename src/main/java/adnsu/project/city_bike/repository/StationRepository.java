package adnsu.project.city_bike.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import adnsu.project.city_bike.entity.Station;

public interface StationRepository extends JpaRepository<Station, Integer> {
}
