package app.local.song;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song, Long> {
    Song save(Song song);
    Optional<Song> findById(Long id);
    void deleteById(Long id);
}
