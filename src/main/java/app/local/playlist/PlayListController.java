package app.local.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/playlists")
public class PlayListController {
    @Autowired
    private PlayListService playListService;

    @PostMapping
    ResponseEntity<PlayList> createPlayList(@RequestBody PlayListRequest request) {
        playListService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<Page<PlayList>> getPlayLists(@PageableDefault(value = 10) Pageable pageable) {
        Page<PlayList> playLists = playListService.findAll(pageable);
        return ResponseEntity.ok(playLists);
    }

    @GetMapping("/{playlistId}")
    ResponseEntity<PlayList> getPlayListById(@PathVariable("playlistId") long playlistId) {
        return ResponseEntity.ok().body(playListService.getPlayList(playlistId));
    }

    @PutMapping("/{playlistId}")
    ResponseEntity<PlayList> updatePlayList(@PathVariable("playlistId") long playlistId,@RequestBody PlayListRequest request) {
        playListService.updatePlayList(playlistId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{playlistId}")
    ResponseEntity<?> deletePlayList(@PathVariable("playlistId") long playlistId) {
        playListService.deletePlayList(playlistId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{playlistId}")
    ResponseEntity<PlayList> viewPlayList(@PathVariable("playlistId") Long playlistId) {
        PlayList playList = playListService.increaseViews(playlistId);
        if (playList != null) {
            return ResponseEntity.ok(playList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
