package app.models;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rentals {

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specify the generation strategy
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double cost;

    private enum Status {
        REQUESTED,
        APPROVED,
        DECLINED,
        PAID,
        FULFILLED,
        CANCELLED,
        BLOCKED
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cabin cabin;

    public void assignCabin(Cabin cabin) {
        if (cabin == null || this.cabin == cabin) {
            // no change required
            return;
        }

        // update both sides of the association; beware of mutual recursion...
        this.setCabin(cabin);
        cabin.addRental(this);
    }

    public void removeCabin() {
        if (cabin != null) {
            cabin.getRentals().remove(this);
            this.cabin = null;
        }
    }
}
