package app.local.playlistcomment;


import app.local.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistCommentService {
    private final PlaylistCommentRepository playlistCommentRepository;

    public void save (PlaylistCommentRequest request) {
        var playlistComment = PlaylistComment.builder()
                .id(request.getId())
                .content(request.getContent())
                .user((request.getUser()))
                .build();
        playlistCommentRepository.save(playlistComment);
    }

    public PlaylistComment findById(Long id) {
        return playlistCommentRepository.findById(id);
    }

    public void delete (Long userId, Long commentId) throws Exception {
        PlaylistComment comment = findById(commentId);
        if(comment == null) {
            throw new NotFoundException("Playlist comment not found");
        }
        if(comment.getUser().getId().equals(userId)) {
            playlistCommentRepository.deletePlaylistCommentById(commentId);
        }
        throw new Exception("Cannot delete playlist comment");
    }
}
