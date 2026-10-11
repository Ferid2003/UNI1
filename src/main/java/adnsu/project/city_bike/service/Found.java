package adnsu.project.city_bike.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

final class Found {

    private Found() {
    }

    static <T> T of(Optional<T> value, String what, Integer id) {
        return value.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, what + " " + id + " not found"));
    }
}
