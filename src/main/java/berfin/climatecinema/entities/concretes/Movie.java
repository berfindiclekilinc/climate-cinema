package berfin.climatecinema.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @Column(name = "tmdb_id", unique = true)
    private Long tmdbId;

    @Column(name = "genre")
    private String movieGenre;

    @Column(name = "name")
    private String movieName;

    @Column(name = "rating")
    private String movieRating;

    @Column(name = "year")
    private String movieYear;

    @Column(name = "description")
    private String movieDesc;

    @OneToMany(mappedBy = "movie")
    private List<WatchedMovie> watchedMovies;

}
