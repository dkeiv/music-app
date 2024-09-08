package app.local.user;


import app.local.error.ErrorResponse;
import app.local.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(
            @PageableDefault(value = 20) Pageable pageable
    ) {
        Page<User> users = userService.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(
            @PathVariable Long id
    ) throws NotFoundException {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestBody @Valid UserRequest request
    ) throws DataIntegrityViolationException {
        userService.save(request);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest request) throws NotFoundException {
        Optional<User> user = userService.findById(id);
        userService.save(id, request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws NotFoundException {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping{"/{id}/playlists"}
//    public ResponseEntity<Page<PlayList>> getUserPlaylist(@PathVariable Long id) {
//        // TODO:
//        return null;
//    }

//    @GetMapping{"/{id}/liked-songs"}
//    public ResponseEntity<Page<Song>> getUserLikedSongs(@PathVariable Long id) {
//        // TODO:
//        return null;
//    }

//    @GetMapping{"/{id}/liked-playlists"}
//    public ResponseEntity<Page<Playlist>> getUserLikedPlaylists(@PathVariable Long id) {
//        // TODO:
//        return null;
//    }
}
