package adnsu.project.city_bike.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import adnsu.project.city_bike.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
