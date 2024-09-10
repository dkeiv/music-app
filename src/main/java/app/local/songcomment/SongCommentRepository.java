package app.local.songcomment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SongCommentRepository extends PagingAndSortingRepository<SongComment, Long> {
    Optional<SongComment> findAllBySongId(Long songId);

    SongComment save(SongComment comment);

    void deleteById(Long id);

    Page<SongComment> findAllCommentBySongId(Long songId, Pageable pageable);
}
