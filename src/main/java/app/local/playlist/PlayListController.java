package app.local.playlist;

import app.local.song.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/playlists")
@CrossOrigin("*")
public class PlayListController {
    @Autowired
    private PlayListService playListService;

    @PostMapping
    public ModelAndView createPlayList(@RequestParam("name") String name,
                                       @RequestParam("views") int views,
                                       @RequestParam("image") MultipartFile file) {
        PlayListRequest playListRequest = new PlayListRequest(name, views);
        playListService.save(playListRequest, file);
        return new ModelAndView("redirect:/playlists");
    }

    @GetMapping
    public ModelAndView getPlayLists(@PageableDefault(value = 10) Pageable pageable) {
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

    @PutMapping("/{playlistId}")
    public ModelAndView updatePlayList(@PathVariable("playlistId") long playlistId,
                                       @RequestParam("name") String name,
                                       @RequestParam("views") int views,
                                       @RequestParam(value = "image", required = false) MultipartFile image) {
        PlayListRequest playListRequest = new PlayListRequest(name, views);
        if (playListService.updatePlayList(playlistId, playListRequest, image)) {
            return new ModelAndView("redirect:/playlists");
        } else {
            return new ModelAndView("error/404");
        }
    }

    @DeleteMapping("/{playlistId}")
    public ModelAndView deletePlayList(@PathVariable("playlistId") long playlistId) {
        playListService.deletePlayList(playlistId);
        return new ModelAndView("redirect:/playlists");
    }

    @PostMapping("/{playlistId}")
    public ModelAndView viewPlayList(@PathVariable("playlistId") Long playlistId) {
        PlayList playList = playListService.increaseViews(playlistId);
        if (playList != null) {
            return new ModelAndView("playlists/detail", "playList", playList);
        } else {
            return new ModelAndView("error/404");
        }
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public ModelAndView addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        PlayList updatedPlaylist = playListService.addSongToPlaylist(playlistId, songId);
        return new ModelAndView("playlists/detail", "playList", updatedPlaylist);
    }

    @GetMapping("/{playlistId}/songs")
    public ModelAndView getSongsInPlaylist(@PathVariable Long playlistId) {
        List<Song> songs = playListService.getSongsInPlaylist(playlistId);
        ModelAndView modelAndView = new ModelAndView("playlists/songs");
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ModelAndView removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        PlayList updatedPlaylist = playListService.removeSongFromPlaylist(playlistId, songId);
        return new ModelAndView("playlists/detail", "playList", updatedPlaylist);
    }
}
