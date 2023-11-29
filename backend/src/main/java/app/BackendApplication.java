package app;

import app.models.Cabin;
import app.models.Rentals;
import app.repositories.CabinsRepositoryJpa;
import app.repositories.RentalsRepositoryJpa;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
    private final CabinsRepositoryJpa cabinsRepository;
    private final RentalsRepositoryJpa rentalsRepository;

    public BackendApplication(CabinsRepositoryJpa cabinsRepository, RentalsRepositoryJpa rentalsRepository) {
        this.cabinsRepository = cabinsRepository;
        this.rentalsRepository = rentalsRepository;
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

    public void createInitialCabins() {
        List<Cabin> cabins = cabinsRepository.findAll();
        int createThisManyCabins = 6;
        if (cabins.size() >= createThisManyCabins) {
            return;
        }

        System.out.println("Configure some initial cabins");

        for (int i = 0; i < createThisManyCabins; i++) {
            Cabin cabin = Cabin.generateRandomCabin(i);
            // Generate and associate Rentals
             generateRentalsForCabin(cabin, 3);
            this.cabinsRepository.save(cabin);
        }
    }

    private void generateRentalsForCabin(Cabin cabin, int numRentals) {
        for (int i = 0; i < numRentals; i++) {
            Rentals rental = new Rentals();
            rental.setStartDate(LocalDate.now().plusDays(i));
            rental.setEndDate(LocalDate.now().plusDays(i + 7L));
            rental.setCost(500.00 * (i + 1));

            // Set the cabin for the rental
            rental.setCabin(cabin);

            // Save the rental
            rentalsRepository.save(rental);
        }
    }

}
