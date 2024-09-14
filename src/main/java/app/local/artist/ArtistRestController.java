package app.local.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artists")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ArtistRestController {
    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<Page<Artist>> findAllArtist(Pageable pageable) {
        return ResponseEntity.ok(artistService.findAll(pageable));
    }
}

