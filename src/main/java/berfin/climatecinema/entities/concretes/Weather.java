package berfin.climatecinema.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String weatherType;

    @Column(name = "location")
    private String weatherLocation;

    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "weather")
    private List<WatchedMovie> watchedMovie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
