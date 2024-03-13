package berfin.climatecinema.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_user")
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    /*@JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Weather> weather;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<WatchedMovie> watchedMovie;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @Column
    private Set<Role> roles;

}
