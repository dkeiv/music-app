package app.local.song;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/music-app/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping
    public String list(Model model, Pageable pageable) {
        Page<Song> allSongs = songService.findAll(pageable);
        model.addAttribute("allSongs", allSongs);
        return "song/index";
    }

    @GetMapping("/{id}/view")

    public String viewSong() {
        return "song/detail";
    }


    @GetMapping("/create")
    public String createForm() {
       return "song/create";
    }

    @PostMapping("/create")
    public String save() {
        return "";
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