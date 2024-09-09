package app.local.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {
    void save(Artist artist);

    Optional<Artist> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
