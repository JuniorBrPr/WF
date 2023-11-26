package app.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private long id;
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

    public void assignCabin(Cabin cabin) {
        this.cabin = cabin;
        if (cabin != null && !cabin.getRentals().contains(this)) {
            cabin.getRentals().add(this);
        }
    }

    public void removeCabin() {
        if (cabin != null) {
            cabin.getRentals().remove(this);
            this.cabin = null;
        }
    }
}
