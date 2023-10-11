package app.repositories;

import app.models.Cabin;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CabinsRepositoryMock implements CabinsRepository {
    private final ArrayList<Cabin> cabins;

    CabinsRepositoryMock() {
        cabins = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cabins.add(Cabin.generateRandomCabin(i));
        }
    }

    @Override
    public List<Cabin> findAll() {
        return cabins;
    }

    @Override
    public Cabin findById(int id) {
        return cabins.stream().filter(cabin -> cabin.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Cabin save(Cabin cabin) {
        Cabin existingCabin = findById(cabin.getId());
        if (existingCabin == null) {
            cabins.add(cabin);
            return cabin;
        }
        cabins.set(cabins.indexOf(existingCabin), cabin);
        return cabin;
    }

    @Override
    public Cabin deleteById(int id) {
        Cabin cabin = findById(id);
        if (cabin != null) {
            cabins.remove(cabin);
        }
        return cabin;
    }
}