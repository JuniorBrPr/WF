package app.rest;

import app.models.Cabin;
import app.models.Rentals;
import app.repositories.CabinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/cabins/")
public class CabinsController {

    @Autowired
    CabinsRepository<Cabin> cabinsRepository;

    @GetMapping(path = "", produces = "application/json")
    public List<Cabin> getTestCabins() {
        return cabinsRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cabin> getCabinById(@PathVariable int id) {
        Cabin cabin = cabinsRepository.findById(id);
        if (cabin == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cabin not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cabin);
    }

    @PostMapping("")
    public ResponseEntity<Cabin> addCabin(@RequestBody Cabin cabin) {
        if (cabin.getId() == 0) {
            do {
                cabin.setId(Cabin.generateId(1000, 100));
            } while (cabinsRepository.findById(cabin.getId()) != null);
        }

        if (cabinsRepository.findById(cabin.getId()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cabin already exists");
        }

        Cabin savedCabin = cabinsRepository.save(cabin);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(cabin.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(savedCabin);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cabin> updateCabin(@PathVariable int id, @RequestBody Cabin cabin) {
        if (id != cabin.getId()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Cabin id mismatch");
        }
        if (cabinsRepository.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cabin not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cabinsRepository.save(cabin));
    }

//    @JsonView(Views.Summary.class)
//    @GetMapping("/cabins/summary")
//    public ResponseEntity<?> getCabinsSummary() {
//        return new ResponseEntity<>(cabinsRepository.findAll(), HttpStatus.OK);
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cabin> deleteCabin(@PathVariable int id) {
        Cabin cabin = cabinsRepository.deleteById(id);
        if (cabin != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cabin);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cabin not found");
    }

    @PostMapping("{cabinId}/rentals")
    public ResponseEntity<Rentals> addRentals(@PathVariable int cabinId, @RequestBody Rentals rentals) {
        // Check if cabin exists
        Cabin cabin = cabinsRepository.findById(cabinId);
        if (cabin == null) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Cabin not found");
        }

        // Check if start and end dates are provided, else set default values
        if (rentals.getStart() == null) {
            rentals.setStart(LocalDate.now().plusDays(1)); // Start date set to tomorrow if not provided
        }
        if (rentals.getEnd() == null) {
            rentals.setEnd(rentals.getStart().plusWeeks(1)); // End date set to start date + 1 week if not provided
        }

        // Validate start and end dates
        if (rentals.getEnd().isBefore(rentals.getStart()) ||
                rentals.getStart().plusDays(1).until(rentals.getEnd(), ChronoUnit.DAYS) % 7 != 0) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Invalid date range");
        }

        // Create new Rental and associate it with the cabin
        Rentals newRental = new Rentals();
        newRental.setStart(rentals.getStart());
        newRental.setEnd(rentals.getEnd());
        newRental.setCost(rentals.getCost()); // Set cost as provided

//        newRental.assignCabin(cabin);
        cabin.addRental(rentals);

        // Return the created Rental
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(newRental.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(newRental);
    }
}
