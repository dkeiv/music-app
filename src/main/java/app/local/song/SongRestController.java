package app.local.song;

import app.local.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongRestController {

    private final SongService songService;
    private final SongRepository songRepository;

    @PostMapping("/{id}/play")
    public ResponseEntity<?> playSong(@PathVariable Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            Song song = optionalSong.get();
            song.setPlayCount(song.getPlayCount() + 1);
            songRepository.save(song);
            return ResponseEntity.ok().build(); // Hoặc trả về bài hát, nếu cần
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<Song>> fillAllSong(@PageableDefault(value = 10) Pageable pageable) {
        Page<Song> songs = songService.findAll(pageable);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> findSongById(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(song.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @ModelAttribute SongRequest songRequest) {
        try {
            Song updatedSong = songService.updateSongRest(id, songRequest);
            return new ResponseEntity<>(updatedSong, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Song> createSong( @ModelAttribute SongRequest songRequest) {
        try {
            Song song = songService.save(songRequest);
            return new ResponseEntity<>(song, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {

        songService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

