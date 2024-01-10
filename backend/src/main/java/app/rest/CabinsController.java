package app.rest;

import app.models.Cabin;
import app.models.Rentals;
import app.repositories.CabinsRepository;
import app.repositories.RentalsRepository;
import app.repositories.RentalsRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cabins/")
public class CabinsController {

    @Autowired
    CabinsRepository<Cabin> cabinsRepository;

    @Autowired
    RentalsRepository<Rentals> rentalsRepository;


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getTestCabins(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "location", required = false) String location
    ) {
        if (type != null && location != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unsupported combination of parameters: 'type' and 'location'");
        }

        if (location != null) {
            List<Cabin> cabinsByLocation = cabinsRepository.findByQuery("Cabin_find_by_locationName", location);
            return ResponseEntity.status(HttpStatus.OK).body(cabinsByLocation);
        }

        if (type != null) {
            try {
                Cabin.Type enumType = Cabin.Type.valueOf(type);
                List<Cabin> cabinsByType = cabinsRepository.findByQuery("Cabin_find_by_type", enumType);
                return ResponseEntity.status(HttpStatus.OK).body(cabinsByType);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Type value does not match any Cabin.Type enumeration value");
            }
        }

        List<Cabin> allCabins = cabinsRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allCabins);
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
        if (rentals.getStartDate() == null) {
            rentals.setStartDate(LocalDate.now().plusDays(1)); // Start date set to tomorrow if not provided
        }
        if (rentals.getEndDate() == null) {
            rentals.setEndDate(rentals.getStartDate().plusWeeks(1)); // End date set to start date + 1 week if not provided
        }

        // Validate start and end dates
        if (rentals.getEndDate().isBefore(rentals.getStartDate()) ||
                rentals.getStartDate().plusDays(1).until(rentals.getEndDate(), ChronoUnit.DAYS) % 7 != 0) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Invalid date range");
        }

        // Create new Rental and associate it with the cabin
        Rentals newRental = new Rentals();
        newRental.setId(0);
        newRental.setStartDate(rentals.getStartDate());
        newRental.setEndDate(rentals.getEndDate());
        newRental.setCost(rentals.getCost()); // Set cost as provided

//        newRental.assignCabin(cabin);
        cabin.addRental(rentals);

        // Return the created Rental
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(newRental.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(newRental);
    }

    @GetMapping("/{cabinId}/rentals")
    public ResponseEntity<List<Rentals>> getCabinRentals(
            @PathVariable int cabinId,
            @RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        Cabin cabin = cabinsRepository.findById(cabinId);
        if (cabin == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cabin not found");
        }

        // Develop a named JPQL query with three positional parameters
        List<Rentals> cabinRentals = rentalsRepository.findByQuery("Rental_find_by_cabinId_and_period", cabinId, startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(cabinRentals);
    }

    @GetMapping("/{cabinId}/rentals")
    public ResponseEntity<List<Rentals>> getCabinRentals(
            @PathVariable int cabinId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> to
    ) {
        List<Rentals> cabinRentals;
        if (from.isPresent() && to.isPresent()) {
            cabinRentals = rentalsRepository.findByQuery("Rental_find_by_cabinId_and_period", cabinId,
                    from.get(), to.get());
        } else {
            cabinRentals = rentalsRepository.findByQuery("Rental_find_by_cabinId", cabinId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cabinRentals);
    }
}
