package adnsu.project.city_bike.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractCrudService<E, REQ, RES> {

    protected final JpaRepository<E, Integer> repo;
    private final String name;
    private final Supplier<E> factory;

    protected AbstractCrudService(JpaRepository<E, Integer> repo, String name, Supplier<E> factory) {
        this.repo = repo;
        this.name = name;
        this.factory = factory;
    }

    @Transactional(readOnly = true)
    public List<RES> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RES findById(Integer id) {
        return toResponse(get(id));
    }

    public RES create(REQ r) {
        return toResponse(repo.save(apply(factory.get(), r)));
    }

    public RES update(Integer id, REQ r) {
        return toResponse(repo.save(apply(get(id), r)));
    }

    public void delete(Integer id) {
        repo.delete(get(id));
    }

    protected E get(Integer id) {
        return Found.of(repo.findById(id), name, id);
    }

    protected abstract E apply(E entity, REQ r);

    protected abstract RES toResponse(E entity);
}
