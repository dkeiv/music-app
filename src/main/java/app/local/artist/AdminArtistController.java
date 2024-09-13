package app.local.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/music-app/admin")
@RequiredArgsConstructor
public class AdminArtistController {
    private final ArtistService artistService;

    @GetMapping("/artists")
    public String listArtist(Model model, Pageable pageable) {
        model.addAttribute("artists", artistService.findAll(pageable).getContent());
        return "artists/list_admin";
    }

    @GetMapping("/artists/{id}")
    public String viewArtist(@PathVariable Long id, Model model) {
        return artistService.findById(id)
                .map(artist -> {
                    model.addAttribute("artist", artist);
                    return "artists/view"; // Tên của file HTML trong thư mục templates
                })
                .orElse("redirect:/music-app/admin/artists"); // Redirect nếu không tìm thấy artist
    }

    @GetMapping("/create")
    public String createArtist(Model model) {
        model.addAttribute("artistRequest", new ArtistRequest());
        return "artists/create";
    }

    @PostMapping("/create")
    public String saveArtist(@ModelAttribute ArtistRequest artistRequest,
                             @RequestParam(value = "image", required = false) MultipartFile file,
                             RedirectAttributes redirectAttributes) {
        try {
            artistService.save(artistRequest, file);
            redirectAttributes.addFlashAttribute("message", "Artist created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to create artist.");
        }
        return "redirect:/artists";
    }

    @GetMapping("/artists/{id}/edit")
    public String editArtist(@PathVariable Long id, Model model) {
        artistService.findById(id).ifPresent(artist -> model.addAttribute("artist", artist));
        return "artists/edit";
    }

    @PostMapping("/artists/{id}/edit")
    public String updateArtist(@PathVariable Long id,
                               @ModelAttribute ArtistRequest artistRequest,
                               @RequestParam(value = "image", required = false) MultipartFile file,
                               RedirectAttributes redirectAttributes) {
        try {
            artistService.update(id, artistRequest, file);
            redirectAttributes.addFlashAttribute("message", "Artist updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update artist.");
        }
        return "redirect:/music-app/admin/artists";
    }

    @GetMapping("/{id}/delete")
    public String deleteArtist(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            artistService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Artist deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete artist.");
        }
        return "redirect:/artists";
    }
}
