package SalesDB;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_location")
public class StoreLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @OneToMany(mappedBy = "storeLocation", targetEntity = Sale.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sale> sales;

    public StoreLocation() {}

    public StoreLocation(String locationName) {
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
