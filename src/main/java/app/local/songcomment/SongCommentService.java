package app.local.songcomment;

import app.local.exception.NotFoundException;
import app.local.song.Song;
import app.local.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongCommentService {
    private final SongCommentRepository songCmtRepository;

    public void save(SongCommentRequest request) {
        SongComment songComment = SongComment.builder()
               .id(request.getId())
               .content(request.getContent())
               .user(User.builder().id(request.getUserId()).build())
               .song(Song.builder().id(request.getSongId()).build())
               .build();
        songCmtRepository.save(songComment);
    }

    public Page<SongComment> findAllCommentBySongId(Long songId, Pageable pageable) {
        return songCmtRepository.findAllCommentBySongId(songId, pageable);
    }

    public SongComment createSongComment(SongComment songComment) {
        return songCmtRepository.save(songComment);
    }

    public void delete(Long id, Long currentUserId) throws NotFoundException {
        Optional<SongComment> commentOptional = songCmtRepository.findAllBySongId(id);

        if (commentOptional.isPresent()) {
            SongComment comment = commentOptional.get();

            if (comment.getUser().getId().equals(currentUserId)) {
                songCmtRepository.deleteById(id);
            } else {
                throw new NotFoundException("You don't have permission to delete this comment");
            }
        } else {
            throw new NotFoundException("Comment not found");
        }
    }

}
