package app.local.artist;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {
    void save(Artist artist);

    Optional<Artist> findById(Long id);

    @Query("SELECT a FROM Artist a WHERE a.id IN :ids")
    List<Artist> findAllByIds(@Param("ids") List<Long> ids);
    boolean existsById(Long id);

    void deleteById(Long id);
}
