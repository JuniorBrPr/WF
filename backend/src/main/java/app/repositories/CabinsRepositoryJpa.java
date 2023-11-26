package app.repositories;

import app.models.Cabin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
@Primary
public class CabinsRepositoryJpa implements CabinsRepository<Cabin> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cabin> findAll() {
        return null;
    }

    @Override
    public Cabin findById(int id) {
        return null;
    }

    @Override
    public Cabin save(Cabin cabin) {
        if (cabin.getId() == 0) {
            entityManager.persist(cabin); // Persist new entity
        } else {
            cabin = entityManager.merge(cabin); // Update existing entity
        }
        return cabin;
    }

    @Override
    public Cabin deleteById(int id) {
        return null;
    }
}
