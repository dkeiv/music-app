package app.local.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Artist>> findAllArtists() {
        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> findArtistById(@PathVariable Integer id) {
        return artistService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable Integer id, @RequestBody ArtistRequest request) {
        if (artistService.update(id, request)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable Integer id) {
        if (artistService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkArtistExists(@RequestParam Integer id) {
        return ResponseEntity.ok(artistService.existsById(id));
    }

//    @GetMapping("/{id}/songs")
//    public ResponseEntity<List<Song>> findSongsByArtistId(@PathVariable Long id) {
//        return artistService.findSongsByArtistId(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
