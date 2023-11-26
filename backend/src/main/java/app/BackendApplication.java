package app;

import app.models.Cabin;
import app.repositories.CabinsRepositoryJpa;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class BackendApplication implements CommandLineRunner {
    private final CabinsRepositoryJpa cabinsRepository;

    public BackendApplication(CabinsRepositoryJpa cabinsRepository) {
        this.cabinsRepository = cabinsRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("Running CommandLine Startup");
        this.createInitialCabins();
    }

    public void createInitialCabins(){
        List<Cabin> cabins = cabinsRepository.findAll();
        int createThisManyCabins = 6;
        if(cabins.size() >= createThisManyCabins) {
            return; // If there are already 6 or more cabins, exit the method
        }

        System.out.println("Configure some initial cabins");

        for (int i = 0; i < createThisManyCabins; i++) {
            Cabin cabin = Cabin.generateRandomCabin(i); // Use 'i' or any unique identifier for cabin creation
            this.cabinsRepository.save(cabin);
        }
    }
}
