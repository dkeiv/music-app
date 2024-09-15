package app.local.playlist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayListRepository extends PagingAndSortingRepository<PlayList, Long> {
    PlayList save(PlayList playlist);

    Optional<PlayList> findById(Long id);

    void deleteById(Long id);

    Page<PlayList> findPlayListByUserId(Long id, Pageable pageable);
    List<PlayList> findPlayListByUserId(Long id);

    Page<PlayList> findPlayListByNameContainingIgnoreCase(String name, Pageable pageable);

//    List<Song> findSongsByPlaylistId(Long playlistId);
}
