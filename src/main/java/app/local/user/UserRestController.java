package app.local.user;


import lombok.RequiredArgsConstructor;
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
        if (users.getTotalElements() > 0) {
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(
            @PathVariable Long id
    ) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    TODO:
//    @GetMapping{"/{id}/playlists"}
//    public ResponseEntity<Page<PlayList>> getUserPlaylist(@PathVariable Long id) {
//        return null;
//    }

    //    TODO:
//    @GetMapping{"/{id}/liked-songs"}
//    public ResponseEntity<Page<Song>> getUserLikedSongs(@PathVariable Long id) {
//        return null;
//    }


    //    TODO:
//    @GetMapping{"/{id}/liked-playlists"}
//    public ResponseEntity<Page<Playlist>> getUserLikedPlaylists(@PathVariable Long id) {
//        return null;
//    }
//
    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestBody UserRequest request
    ) {
        userService.save(request);
        return null;
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser (@PathVariable Long id, @RequestBody User user) {
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
//        return null;
//    }
}
