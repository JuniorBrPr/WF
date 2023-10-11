package app.rest;

import app.models.Cabin;
import app.repositories.CabinsRepositoryMock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CabinsController {
    private final CabinsRepositoryMock cabinsRepository;

    public CabinsController(CabinsRepositoryMock cabinsRepositoryMock) {
        this.cabinsRepository = cabinsRepositoryMock;
    }

    @GetMapping("/cabins/test")
    public List<Cabin> getTestCabins() {
        return cabinsRepository.findAll();
    }
}
