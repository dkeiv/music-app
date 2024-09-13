package app.local.admin;

import app.local.playlist.PlayList;
import app.local.playlist.PlayListService;
import app.local.song.Song;
import app.local.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AppController {

    private final SongService songService;
    private final PlayListService playListService;


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


    @GetMapping("/music-app/search")
    public String searchIndex(
            @RequestParam(required = false) String query,
            Model model,
            Pageable pageable
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
}
