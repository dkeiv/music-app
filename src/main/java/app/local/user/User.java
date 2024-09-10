package app.local.user;

import app.local.playlist.PlayList;
import app.local.song.Song;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @JsonIgnore
    @Column(nullable = false, length = 50)
    private String password;

    @Email
    @Column(unique = true, nullable = false, length = 50)
    private String email;

    private String profilePicture;
    private String bio;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Song> likedSongs;

    @ManyToMany
    private List<PlayList> likedPlaylists;

    @OneToMany
    private List<Song> createdSongs;

    @OneToMany
    private List<PlayList> createdPlaylists;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Role> roles;
}
