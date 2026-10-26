package adnsu.project.city_bike.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

final class Found {

    private Found() {
    }

    static <T> T of(JpaRepository<T, Integer> repo, String what, Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, what + " " + id + " not found"));
    }
}
