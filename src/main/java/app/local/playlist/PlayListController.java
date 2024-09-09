package app.local.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/playlists")
public class PlayListController {
    @Autowired
    private PlayListService playListService;

    @PostMapping
    PlayList createPlayList(@RequestBody PlayListRequest request) {
        return playListService.createPlayList(request);
    }

    @GetMapping
    Page<PlayList> getAllPlayLists(@PageableDefault(value = 5) Pageable pageable) {
        return playListService.getPlayLists(pageable);
    }

    @GetMapping("/{playlistId}")
    PlayList getPlayList(@PathVariable("playlistId") long playlistId) {
        return playListService.getPlayList(playlistId);
    }

    @PutMapping("/{playlistId}")
    PlayList updatePlayList(@PathVariable("playlistId") long playlistId, @RequestBody PlayListRequest request) {
        return playListService.updatePlayList(playlistId, request);
    }

    @DeleteMapping("/{playlistId}")
    void deletePlayList(@PathVariable("playlistId") long playlistId) {
        playListService.deletePlayList(playlistId);
    }
}
