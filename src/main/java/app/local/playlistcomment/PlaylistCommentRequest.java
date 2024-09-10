package app.local.playlistcomment;

import app.local.user.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistCommentRequest {
    private Long id;
    private String content;
    private User user;
}
