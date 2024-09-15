package app.local.admin;

import app.local.artist.Artist;
import app.local.artist.ArtistService;
import app.local.playlist.PlayList;
import app.local.playlist.PlayListService;
import app.local.song.Song;
import app.local.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/music-app")
@RequiredArgsConstructor
public class AppController {

    private final SongService songService;
    private final PlayListService playListService;
    private final ArtistService artistService;

    @RequestMapping("")
    public String dashboard(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 6;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Song> songList = songService.findAll(pageable);
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

    //    @GetMapping("/playlists")
//    public String songIndex() {
//        return "playlist/index";
//    }

    @GetMapping("/contact")
    public String contactIndex() {
        return "contact/contact";
    }


    @GetMapping("/search")
    public String searchIndex(
            @RequestParam(required = false) String query,
            Model model,
            @PageableDefault(value = 20) Pageable pageable
    ) {
        if(query == null) {
            return "search";
        }

        if(!query.isEmpty()) {
            Page<Song> songResult = songService.findSongByTitle(query, pageable);
            model.addAttribute("songResult", songResult);

            Page<PlayList> playlistResult = playListService.findPlayListsByName(query, pageable);
            model.addAttribute("playlistResult", playlistResult);

            model.addAttribute("query", query);
        }

        return "search";
    }

    @GetMapping("/admin")
    public String adminDashboard() {
        return "admin"; // Trả về file admin.html trong thư mục templates/admin
    }
}
