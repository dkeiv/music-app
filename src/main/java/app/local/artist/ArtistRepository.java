package app.local.artist;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {
    void save(Artist artist);

    Optional<Artist> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
