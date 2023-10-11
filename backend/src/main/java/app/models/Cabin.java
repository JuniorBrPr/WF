package app.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cabin {
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
    @Id
    private Long id;
    private int image;
    private Type type;
    private float pricePerWeek;
    private String location;
    private String description;
    private int numAvailable;

    static public Cabin generateRandomCabin(int i) {
        Cabin cabin = new Cabin();
        Random random = new Random();
        cabin.setId((long) i + 3000);
        cabin.setImage(random.nextInt(4));
        cabin.setType(Type.values()[random.nextInt(Type.values().length)]);
        cabin.setPricePerWeek(1000 + i * 100);
        cabin.setLocation(locations.get(random.nextInt(locations.size())));
        cabin.setDescription(descriptions.get(random.nextInt(descriptions.size())));
        cabin.setNumAvailable(random.nextInt(10));
        return cabin;
    }

    public enum Type {
        SmallDayTime,
        SmallLodge,
        LargeLodge,
        FamilyLodge
    }
}
