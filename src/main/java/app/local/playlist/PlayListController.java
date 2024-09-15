package app.local.playlist;

import app.local.exception.NotFoundException;
import app.local.song.Song;
import app.local.song.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/music-app/playlists")
@CrossOrigin("*")
public class PlayListController {
    @Autowired
    private PlayListService playListService;

    @Autowired
    private SongService songService;

    @PostMapping
    public ModelAndView createPlayList(@RequestParam("name") String name,
                                       @RequestParam("views") int views,
                                       @RequestParam("image") MultipartFile file) {
        PlayListRequest playListRequest = new PlayListRequest(name, views);
        playListService.save(playListRequest, file);
        return new ModelAndView("redirect:/music-app/admin");
    }

    @GetMapping
    public ModelAndView getPlayLists(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "12") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PlayList> playLists = playListService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/playlist/albums-store");
        modelAndView.addObject("playLists", playLists);
        return modelAndView;
    }

    @GetMapping("/{playlistId}")
    public ModelAndView getPlayListById(@PathVariable("playlistId") long playlistId) {
        PlayList playList = playListService.getPlayList(playlistId);
        ModelAndView modelAndView = new ModelAndView("playlists/detail");
        modelAndView.addObject("playList", playList);
        return modelAndView;
    }

    @PostMapping("/{playlistId}/create")
    public ModelAndView updatePlayList(@PathVariable("playlistId") long playlistId,
                                       @RequestParam("name") String name,
                                       @RequestParam("views") int views,
                                       @RequestParam(value = "image", required = false) MultipartFile image) {
        PlayListRequest playListRequest = new PlayListRequest(name, views);
        if (playListService.updatePlayList(playlistId, playListRequest, image)) {
            return new ModelAndView("redirect:/music-app/admin");
        } else {
            return new ModelAndView("error/404");
        }
    }

    @PostMapping("/{playlistId}/delete")
    public ModelAndView deletePlayList(@PathVariable("playlistId") long playlistId) {
        playListService.deletePlayList(playlistId);
        return new ModelAndView("redirect:/music-app/admin");
    }

//    @PostMapping("/{playlistId}")
//    public ModelAndView viewPlayList(@PathVariable("playlistId") Long playlistId) {
//        PlayList playList = playListService.increaseViews(playlistId);
//        if (playList != null) {
//            return new ModelAndView("playlists/detail", "playList", playList);
//        } else {
//            return new ModelAndView("error/404");
//        }
//    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public ModelAndView addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        PlayList updatedPlaylist = playListService.addSongToPlaylist(playlistId, songId);
        return new ModelAndView("playlists/detail", "playList", updatedPlaylist);
    }

    @GetMapping("/{playlistId}/songs")
    public ModelAndView getSongsInPlaylist(@PathVariable Long playlistId) {
        List<Song> songs = playListService.getSongsInPlaylist(playlistId);
        ModelAndView modelAndView = new ModelAndView("playlist/songs");
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ModelAndView removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        PlayList updatedPlaylist = playListService.removeSongFromPlaylist(playlistId, songId);
        return new ModelAndView("playlists/detail", "playList", updatedPlaylist);
    }


    @PostMapping("/playlists/{playlistId}/song/{songId}")
    public String addPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) throws NotFoundException {
        playListService.addSongPlaylist(songId, playlistId);
        return "/music-app/playlists/" + playlistId;
    }
}
