package app.local.playlist;

import app.local.playlistcomment.PlaylistComment;
import app.local.song.Song;
import app.local.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlist")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int views;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs;

    @OneToMany
    private List<PlaylistComment> comments;
}
