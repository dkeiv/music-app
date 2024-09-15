package app.local.song;

import app.local.genre.Genre;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import app.local.artist.Artist;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.beans.Transient;
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


    Page<Song> findSongByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query(value = "SELECT s FROM Song s JOIN s.artists a WHERE a.id = :artistId")
    List<Song> findSongsByArtistId(@Param("artistId") Long artistId);


    @Query("SELECT a FROM Song s JOIN s.artists a WHERE s.id = :songId")
    List<Artist> findArtistsBySongId(@Param("songId") Long songId);

    @Query("SELECT g FROM Song s JOIN s.genres g WHERE s.id = :songId")
    List<Genre> findGenreBySongId(@Param("songId") Long songId);

    @Transactional
    @Modifying
    @Query (value = "CALL sp_delete_song(:songId)", nativeQuery = true )
    void deleteSong(@Param("songId") Long songId);
}
