package app.local.songcomment;

import app.local.user.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
public class SongCommentRequest {
    private Long id;
    private String content;
    private Long userId;
    private Long songId;
}
