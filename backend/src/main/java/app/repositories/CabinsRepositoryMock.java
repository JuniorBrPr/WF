package app.repositories;

import app.models.Cabin;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CabinsRepositoryMock implements CabinsRepository {
    @Override
    public List<Cabin> findAll() {
        ArrayList<Cabin> cabins = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cabins.add(Cabin.generateRandomCabin(i));
        }
        return cabins;
    }
}