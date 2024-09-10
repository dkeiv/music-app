package app.local.playlistcomment;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlaylistCommentRepository extends PagingAndSortingRepository<PlaylistComment, Long> {
    void save(PlaylistComment playlistComment);
    void deletePlaylistCommentById(Long id);
    PlaylistComment findById(Long id);
}
