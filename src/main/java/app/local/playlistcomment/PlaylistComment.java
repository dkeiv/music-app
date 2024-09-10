package app.local.playlistcomment;

import app.local.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.BindParam;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistComment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 255)
    private String content;

    @OneToOne
    private User user;
}
