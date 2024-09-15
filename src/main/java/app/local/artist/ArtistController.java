package app.local.artist;

import app.local.song.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/music-app/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping
    public String listArtist(Model model, Pageable pageable) {
        Pageable adjustedPageable = PageRequest.of(pageable.getPageNumber(), 6);
        Page<Artist> artistPage = artistService.findAll(adjustedPageable);
        model.addAttribute("artists", artistPage.getContent());
        model.addAttribute("page", artistPage);
        return "artists/list";
    }


    @GetMapping("/{id}")
    public String viewArtist(@PathVariable("id") Long artistId, Model model) {
        Optional<Artist> artistOpt = artistService.findById(artistId);
        if (artistOpt.isPresent()) {
            Artist artist = artistOpt.get();
            List<Song> songs = artistService.findSongsByArtistId(artistId);
            model.addAttribute("artist", artist);
            model.addAttribute("songs", songs);
        }
        return "artists/view";
    }
}


