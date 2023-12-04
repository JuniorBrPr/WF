package app.repositories;

import app.models.Cabin;
import app.models.Rentals;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("RENTAL.JPA")
@Transactional
public class RentalsRepositoryJpa implements RentalsRepository<Rentals> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Rentals> findAll() {
        TypedQuery<Rentals> query = entityManager.createQuery(
                "SELECT r FROM Rentals r", Rentals.class
        );
        return query.getResultList();
    }

    @Override
    public Rentals findById(int id) {
        try {
            TypedQuery<Rentals> query = entityManager.createQuery(
                    "SELECT r FROM Rentals r WHERE r.id = :id", Rentals.class
            );
            query.setParameter("id", id); // Set the parameter value
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Handle the NoResultException, for example, by returning null
            return null;
        }
    }

    @Override
    public Rentals save(Rentals rentals) {
        if (rentals.getId() == 0) {
            entityManager.persist(rentals); // Persist new entity
        } else {
            rentals = entityManager.merge(rentals); // Update existing entity
        }
        return rentals;
    }

    @Override
    public Rentals deleteById(int id) {
        Rentals rentalsToDelete = findById(id); // Find the entity by id
        if (rentalsToDelete != null) {
            entityManager.remove(rentalsToDelete); // Remove the entity
        }
        return rentalsToDelete; // Return the deleted entity or null if not found
    }

    @Override
    public List<Rentals> findByQuery(String jpqlName, Object... params) {
        try {
            TypedQuery<Rentals> query = entityManager.createNamedQuery(jpqlName, Rentals.class);

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
