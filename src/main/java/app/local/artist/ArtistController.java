package app.local.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping
    public String listArtist(Model model) {
        model.addAttribute("artists", artistService.findAll(Pageable.unpaged()));
        return "artists/list";
    }

    @GetMapping("/{id}")
    public String viewArtist(@PathVariable Long id, Model model) {
        model.addAttribute("artist", artistService.findById(id).orElse(null));
        return "artists/view";
    }

    @GetMapping("/create")
    public String createArtist(Model model) {
        model.addAttribute("artistRequest", new ArtistRequest());
        return "artists/create";
    }

    @PostMapping("/create")
    public String saveArtist(@ModelAttribute ArtistRequest artistRequest,
                             @RequestParam("image") MultipartFile file) {
        artistService.save(artistRequest, file);
        return "redirect:/artists";
    }

    @GetMapping("/{id}/edit")
    public String editArtist(@PathVariable Long id, Model model) {
        model.addAttribute("artist", artistService.findById(id).orElse(null));
        return "artists/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateArtist(@PathVariable Long id,
                               @ModelAttribute ArtistRequest artistRequest,
                               @RequestParam(value = "image", required = false) MultipartFile file) {
        artistService.update(id, artistRequest, file);
        return "redirect:/artists";
    }

    @GetMapping("/{id}/delete")
    public String deleteArtist(@PathVariable Long id) {
        artistService.delete(id);
        return "redirect:/artists";
    }
}
