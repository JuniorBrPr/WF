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
    private Long id;
    private LocalDate start;
    private LocalDate end;
    public enum Status {
        REQUESTED,
        APPROVED,
        DECLINED,
        PAID,
        FULFILLED,
        CANCELLED,
        BLOCKED
    }
    public double cost;

    @ManyToOne
    public Cabin cabin;


    public boolean assignCabin(Cabin cabin) {
        if (cabin == null || this.cabin == cabin) {
            // no change required
            return false;
        }

        // update both sides of the association; beware of mutual recursion...
        this.setCabin(cabin);
        cabin.addRental(this);
        return true;
    }


    public void removeCabin() {
        if (cabin != null) {
            cabin.getRentals().remove(this);
            this.cabin = null;
        }
    }
}
