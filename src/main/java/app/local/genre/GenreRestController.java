package app.local.genre;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/genres")
public class GenreRestController {

    private final GenreService genreService;
    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody GenreRequest request) {
        Genre genre = genreService.save(request);
        return ResponseEntity.ok(genre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable long id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genre> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenreById(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/{id}/songs")
//    public ResponseEntity<Page<Song>> getAllSongsByGenre(@PathVariable int id, Pageable pageable) {
//
//    }
}
