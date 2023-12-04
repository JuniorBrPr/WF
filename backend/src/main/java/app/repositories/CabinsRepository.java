package app.repositories;

import app.models.Cabin;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CabinsRepository<Cabin> {
    List<Cabin> findAll();
    Cabin findById(int id);
    Cabin save(Cabin cabin);
    Cabin deleteById(int id);
    List<Cabin> findByQuery(String jpqlName, Object... params);
}
