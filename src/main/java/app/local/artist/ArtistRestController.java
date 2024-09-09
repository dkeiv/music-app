package app.local.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/artists")
@RequiredArgsConstructor
public class ArtistRestController {
    private final ArtistService artistService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ArtistRequest request) {
        artistService.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<Page<Artist>> findAllArtists(Pageable pageable) {
        return ResponseEntity.ok(artistService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> findArtistById(@PathVariable Long id) {
        return artistService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable Long id, @RequestBody ArtistRequest request) {
        if (artistService.update(id, request)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long id) {
        if (artistService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/exists/")
    public ResponseEntity<Boolean> checkArtistExists(@RequestParam Long id) {
        return ResponseEntity.ok(artistService.existsById(id));
    }

//    @GetMapping("/{id}/songs")
//    public ResponseEntity<List<Song>> findSongsByArtistId(@PathVariable Long id) {
//        return artistService.findSongsByArtistId(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
