package app.local.likeplaylist;

import app.local.user.User;
import app.local.playlist.PlayList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LikePlaylist {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private PlayList playlist;
    @OneToOne
    private User user;
}
