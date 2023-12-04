package app.repositories;

import java.util.List;

public interface RentalsRepository<Rentals> {
    List<Rentals> findAll();
    Rentals findById(int id);
    Rentals save(Rentals Rentals);
    Rentals deleteById(int id);
    List<Rentals> findByQuery(String jpqlName, Object... params);
}
