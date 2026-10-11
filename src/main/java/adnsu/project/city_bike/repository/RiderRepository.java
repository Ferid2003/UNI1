package adnsu.project.city_bike.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import adnsu.project.city_bike.entity.Rider;

public interface RiderRepository extends JpaRepository<Rider, Integer> {
}
