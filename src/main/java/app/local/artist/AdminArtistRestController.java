package app.local.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/admin/artists")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdminArtistRestController {
    private final ArtistService artistService;


    @PostMapping
    public ResponseEntity<?> save(@RequestParam("name") String name,
                                  @RequestParam("artistProfile") String artistProfile,
                                  @RequestParam("biography") String biography,
                                  @RequestPart(value = "image", required = false) MultipartFile file) {
        ArtistRequest artistRequest = new ArtistRequest(name, artistProfile, biography);
        artistService.save(artistRequest, file);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<Page<Artist>> findAllArtistsForAdmin(Pageable pageable) {
        return ResponseEntity.ok(artistService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> findArtistById(@PathVariable Long id) {
        return artistService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable Long id,
                                          @RequestParam("name") String name,
                                          @RequestParam("artistProfile") String artistProfile,
                                          @RequestParam("biography") String biography,
                                          @RequestPart(value = "image", required = false) MultipartFile file) {
        ArtistRequest artistRequest = new ArtistRequest(name, artistProfile, biography);
        if (artistService.update(id, artistRequest, file)) {
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

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkArtistExists(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.existsById(id));
    }
}
