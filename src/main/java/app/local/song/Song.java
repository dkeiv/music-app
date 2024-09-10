package app.local.song;

import app.local.songcomment.SongComment;
import app.local.artist.Artist;
import app.local.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotBlank(message = "Media URL is required")
    @Column(name = "mediaUrl", nullable = false)
    private String mediaUrl;

    @NotBlank(message = "Media URL is required")
    @Column(name = "avatarUrl", nullable = false)
    private String avatarUrl;

    @Min(value = 0, message = "Play count must be zero or a positive number")
    @Column(name = "playCount", nullable = true)
    private int playCount;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongComment> songComments;

    @ManyToMany
    private List<Artist> artists;

    @OneToOne
    private User user;

}