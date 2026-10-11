package adnsu.project.city_bike.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bike_id")
    private Integer id;

    @Column(name = "serial_number", length = 50, nullable = false, unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "bike_type", length = 20, nullable = false)
    private BikeType bikeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_character", length = 20, nullable = false)
    private BikeStatus statusCharacter;

    // null while the bike is out on a rental
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_dock_id")
    private Dock currentDock;
}
