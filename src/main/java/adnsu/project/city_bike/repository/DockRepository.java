package adnsu.project.city_bike.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import adnsu.project.city_bike.entity.Dock;

public interface DockRepository extends JpaRepository<Dock, Integer> {
}
