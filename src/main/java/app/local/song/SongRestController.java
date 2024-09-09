package app.local.song;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongRestController {

    private final SongService service;

    @GetMapping
    public ResponseEntity<Iterable<Song>> fillAllSong() {
        List<Song> songs = (List<Song>) service.findAll();
        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> findSongById(@PathVariable Long id) {
        Optional<Song> song = service.findById(id);
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(song.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        service.createSong(song);
        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestPart("songForm") SongForm songForm) {
//        try {
//            Song updatedSong = service.updateSongRest(id, songForm);
//            return new ResponseEntity<>(updatedSong, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Song> createSong( @RequestPart("songForm") SongForm songForm) {
//        try {
//            Song createdSong = service.createSongRest(songForm);
//            return new ResponseEntity<>(createdSong, HttpStatus.CREATED);
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        Optional<Song> songOptional = service.findById(id);
        if (!songOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

