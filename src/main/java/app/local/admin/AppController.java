package app.local.admin;

import app.local.artist.Artist;
import app.local.artist.ArtistService;
import app.local.playlist.PlayList;
import app.local.playlist.PlayListService;
import app.local.song.Song;
import app.local.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/music-app")
@RequiredArgsConstructor
public class AppController {

    private final SongService service;
    private final PlayListService playListService;
    private final ArtistService artistService;

    @RequestMapping("")
    public String dashboard(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 6;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Song> songList = service.findAll(pageable);
        Page<PlayList> playlistList = playListService.findAll(pageable);
        Page<Artist> artistList = artistService.findAll(pageable);

        model.addAttribute("songs", songList);
        model.addAttribute("playlist", playlistList);
        model.addAttribute("artists", artistList);
        return "index";
    }


    @RequestMapping("/song")
    public String songIndex() {
        return "song/index";
    }
public class AppController {


    @GetMapping("/music-app")
    public String dashboard() {
        return "index";
    }

//    @GetMapping("/playlists")
//    public String songIndex() {
//        return "playlist/index";
//    }

    @GetMapping("/music-app/contact")
    public String contactIndex() {
        return "contact/contact";
    }



}
