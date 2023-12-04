package app.repositories;

import app.models.Cabin;
import jakarta.persistence.*;
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
        TypedQuery<Cabin> query = entityManager.createQuery(
                "SELECT c FROM Cabin c", Cabin.class
        );
        return query.getResultList();
    }

    @Override
    public Cabin findById(int id) {
        try {
            TypedQuery<Cabin> query = entityManager.createQuery(
                    "SELECT c FROM Cabin c WHERE c.id = :id", Cabin.class
            );
            query.setParameter("id", id); // Set the parameter value
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Handle the NoResultException, for example, by returning null
            return null;
        }
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
        Cabin cabinToDelete = findById(id); // Find the entity by id
        if (cabinToDelete != null) {
            entityManager.remove(cabinToDelete); // Remove the entity
        }
        return cabinToDelete; // Return the deleted entity or null if not found
    }

    @Override
    public List<Cabin> findByQuery(String jpqlName, Object... params) {
        try {
            TypedQuery<Cabin> query = entityManager.createNamedQuery(jpqlName, Cabin.class);

            int index = 1;
            for (Object param : params) {
                query.setParameter(index++, param);
            }

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            // Handle exceptions or return null/empty list as required
            e.printStackTrace();
            return null;
        }
    }
}
