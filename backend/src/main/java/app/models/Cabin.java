package app.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name="Cabin_find_by_type", query = "SELECT c FROM Cabin c  WHERE c.type = ?1")
@NamedQuery(name="Cabin_find_by_locationName", query = "SELECT c FROM Cabin c  WHERE c.location = ?1")
public class Cabin {
    @JsonView(Views.Summary.class)
    @Id
    private int id;
    @JsonView(Views.Summary.class)
    private Type type;
    @JsonView(Views.Summary.class)
    private int numAvailable;
    @JsonView(Views.Summary.class)
    private String description;
    private String location;
    private float pricePerWeek;
    private String image;

    public enum Type {
        SmallDayTime,
        SmallLodge,
        LargeLodge,
        FamilyLodge
    }

    static public int generateId(int bound, int offset) {
        Random random = new Random();
        return random.nextInt(bound) + offset;
    }

    static private List<String> locations = List.of(
            "Coral Cove",
            "Sandy Shores",
            "Wavefront Retreat",
            "Seashell Bay",
            "Oceanview Haven",
            "Palm Paradise",
            "Surfside Oasis",
            "Lighthouse Point",
            "Coastal Bliss",
            "Sun-kissed Cove"
    );
    static private List<String> descriptions = List.of(
            "A tranquil cove with vibrant coral formations.",
            "Soft golden shores perfect for a leisurely stroll.",
            "A cozy retreat with breathtaking views of rolling waves.",
            "A picturesque bay adorned with scattered seashells.",
            "A haven offering stunning panoramas of the endless ocean.",
            "A tropical paradise surrounded by swaying palm trees.",
            "An oasis where the sound of waves lulls you into serenity.",
            "A point marked by a majestic lighthouse overlooking the sea.",
            "Blissful surroundings along the coast with a calming ambiance.",
            "A cove kissed by the warm rays of the sun."
    );

    static public Cabin generateRandomCabin(int i) {
        Cabin cabin = new Cabin();
        Random random = new Random();
        cabin.setId(i + 3000);
        int imageId = random.nextInt(4);
        cabin.setImage("h" + (imageId == 0 ? "" : imageId));
        cabin.setType(Type.values()[random.nextInt(Type.values().length)]);
        cabin.setPricePerWeek(1000 + i * 100);
        cabin.setLocation(locations.get(random.nextInt(locations.size())));
        cabin.setDescription(descriptions.get(random.nextInt(descriptions.size())));
        cabin.setNumAvailable(random.nextInt(10));
        return cabin;
    }

    @OneToMany(mappedBy = "cabin")
    @JsonBackReference
    private Set<Rentals> rentals = new HashSet<>(); // Initialize the set to an empty HashSet


    public boolean addRental(Rentals rental) {
        if (rental == null || this.rentals.contains(rental)) {
            // no change required
            return false;
        }

//         update both sides of the association; beware of mutual recursion...
        this.rentals.add(rental);
        rental.setCabin(this);
        return true;
    }


    public void removeRental(Rentals rental) {
        rentals.remove(rental);
        if (rental.getCabin() == this) {
            rental.setCabin(null);
        }
    }
}

