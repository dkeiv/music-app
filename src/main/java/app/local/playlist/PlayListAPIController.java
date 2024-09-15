package app.local.playlist;

import app.local.song.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/playlists")
@CrossOrigin("*")
public class PlayListAPIController {
    @Autowired
    private PlayListService playListService;

    @PostMapping
    ResponseEntity<PlayList> createPlayList(@RequestParam("name") String name,
                                            @RequestParam("views") int views,
                                            @RequestParam("image") MultipartFile file) {
        PlayListRequest playListRequest = new PlayListRequest(name, views);
        playListService.save(playListRequest, file);
        return ResponseEntity.accepted().build();
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
    ResponseEntity<?> updatePlayList(@PathVariable("playlistId") long playlistId,
                                     @RequestParam("name") String name,
                                     @RequestParam("views") int views,
                                     @RequestParam(value = "image", required = false) MultipartFile image) {
        PlayListRequest playListRequest = new PlayListRequest(name,views);
        if (playListService.updatePlayList(playlistId, playListRequest, image)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<PlayList> addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        PlayList updatedPlaylist = playListService.addSongToPlaylist(playlistId, songId);
        return ResponseEntity.ok(updatedPlaylist);
    }

    @GetMapping("/{playlistId}/songs")
    public ResponseEntity<List<Song>> getSongsInPlaylist(@PathVariable Long playlistId) {
        List<Song> songs = playListService.getSongsInPlaylist(playlistId);
        return ResponseEntity.ok(songs);
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<PlayList> removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        PlayList updatedPlaylist = playListService.removeSongFromPlaylist(playlistId, songId);
        return ResponseEntity.ok(updatedPlaylist);
    }
}
