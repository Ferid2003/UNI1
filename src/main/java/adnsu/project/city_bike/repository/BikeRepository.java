package adnsu.project.city_bike.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import adnsu.project.city_bike.entity.Bike;

public interface BikeRepository extends JpaRepository<Bike, Integer> {
}
