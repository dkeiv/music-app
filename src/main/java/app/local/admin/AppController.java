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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

        Optional<Artist> featuredArtist = artistService.findById(1L);
        model.addAttribute("featuredArtist", featuredArtist.get());

        List<Song> featuredSong = songService.findByArtist(featuredArtist);
        model.addAttribute("featuredSong", featuredSong.get(0));


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

    @GetMapping("/music-app/contact")
    public String contactIndex() {
        return "contact/contact";
    }



}
