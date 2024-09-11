package app.local.playlist;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayListRepository extends PagingAndSortingRepository<PlayList, Long> {
    PlayList save(PlayList playlist);

    Optional<PlayList> findById(Long id);

    void deleteById(Long id);

    List<PlayList> findPlayListByUserId(Long id);
}
