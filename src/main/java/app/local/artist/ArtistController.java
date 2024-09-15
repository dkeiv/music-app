package app.local.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String viewArtist(@PathVariable Long id, Model model) {
        return artistService.findById(id)
                .map(artist -> {
                    model.addAttribute("artist", artist);
                    return "artists/view";
                })
                .orElse("redirect:/music-app/artists");
    }
}


