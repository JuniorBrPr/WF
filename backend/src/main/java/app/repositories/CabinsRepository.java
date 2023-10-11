package app.repositories;

import app.models.Cabin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabinsRepository {
    List<Cabin> findAll();
}
