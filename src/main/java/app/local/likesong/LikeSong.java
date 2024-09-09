package app.local.likesong;

import app.local.song.Song;
import app.local.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeSong {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Song song;
    @OneToOne
    private User user;
}
