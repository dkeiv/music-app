package app.local.song;

import app.local.artist.Artist;
import app.local.artist.ArtistService;
import app.local.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/music-app/admin/songs")
@RequiredArgsConstructor
public class SongAdminController {
    private final SongService songService;
    private final UserService userService;
    private final ArtistService artistService;

    @GetMapping("/{id}/view")
    public String viewSong() {
        return "song/detail";
    }



    @GetMapping("/create")
    public String createForm(Model model, Pageable pageable) {
        model.addAttribute("song", new SongRequest());
        Page<Artist> artists = artistService.findAll(pageable);
        model.addAttribute("artists", artists);
        return "song/createSong";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute SongRequest request) throws IOException {
        songService.save(request);
        return "redirect:/music-app/songs";
    }

    @GetMapping("/{id}/edit")
    public String editForm() {
        return "song/update";
    }

    @PostMapping("/edit")
    public String update() {
        return "";
    }

    @GetMapping("/{id}/delete")
    public String deleteForm() {
        return "song/delete";

    }

    @PostMapping("/delete")
    public String delete() {
        return "/";
    }

}
