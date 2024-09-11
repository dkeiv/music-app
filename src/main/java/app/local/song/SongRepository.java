package app.local.song;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song, Long> {
    Song save(Song song);

    Optional<Song> findById(Long id);

    void deleteById(Long id);

    List<Song> findSongByUserId(Long id);

    @Query(
            value = "SELECT song.id, song.avatar_url, song.description, song.media_url, song.play_count, song.title, song.user_id FROM user_liked_songs as liked, song WHERE liked.user_id = :userId  AND song.id = liked.liked_songs_id",
            nativeQuery = true
    )
    List<Song> findLikedSongsByUserId(@Param("userId") Long id);
}
