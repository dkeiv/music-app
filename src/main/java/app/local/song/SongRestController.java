package app.local.song;

import app.local.exception.NotFoundException;
import app.local.songcomment.SongComment;
import app.local.songcomment.SongCommentService;
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
    private final SongCommentService songCommentService;

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
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestPart("songRequest") SongRequest songRequest) {
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

    @GetMapping("/{id}/comments")
    public ResponseEntity<Page<SongComment>> getSongComments(@PathVariable Long id, @PageableDefault(value = 10) Pageable pageable) {
        Page<SongComment> songComments = songCommentService.findAllCommentBySongId(id, pageable);
        return new ResponseEntity<>(songComments, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/comments/{id}")
    public ResponseEntity<String> deleteSongComments(@PathVariable Long id, @PathVariable Long commentId, @RequestHeader("User-Id") Long currentUserId) throws NotFoundException {
        try {
            songCommentService.delete(commentId, currentUserId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

    }
}

