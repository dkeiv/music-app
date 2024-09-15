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

    @PostMapping("/{playlistId}/update")
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

//    Song - Playlist


    @PostMapping("/{playlistId}/song/{songId}")
    public String addPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) throws NotFoundException {
        playListService.addSongPlaylist(songId, playlistId);
        return "redirect:/music-app/playlists/"+playlistId+"/songs";
    }

    @GetMapping("/{playlistId}/songs")
    public ModelAndView getSongsInPlaylist(@PathVariable Long playlistId) {
        PlayList playList1 = playListService.increaseViews(playlistId);
//        PlayList playList = playListService.findById(playlistId);
        List<Song> songs = playListService.getSongsInPlaylist(playlistId);
        ModelAndView modelAndView = new ModelAndView("playlist/songs");
        modelAndView.addObject("playlist", playList1);
//        modelAndView.addObject("playlist", playList);
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

}
