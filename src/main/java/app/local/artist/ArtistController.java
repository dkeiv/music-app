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
        return "artists/list";
    }

    @GetMapping("/{id}")
    public String viewArtist(@PathVariable Long id, Model model) {
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
        return "redirect:/artists";
    }

    @GetMapping("/{id}/edit")
    public String editArtist(@PathVariable Long id, Model model) {
        return "artists/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateArtist(@PathVariable Long id,
                               @ModelAttribute ArtistRequest artistRequest,
                               @RequestParam(value = "image", required = false) MultipartFile file) {
        return "redirect:/artists";
    }

    @GetMapping("/{id}/delete")
    public String deleteArtist(@PathVariable Long id) {
        return "redirect:/artists";
    }
}
