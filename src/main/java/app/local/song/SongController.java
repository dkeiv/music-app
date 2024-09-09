package app.local.song;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService service;

    @GetMapping("/{id}")
    public String viewSong() {
        return "song/detail";
    }


    @GetMapping("/create")
    public String createForm() {
        return "song/create";
    }

    @PostMapping("/create")
    public String save() {
        return "song/create";
    }

    @GetMapping("/{id}/update")
    public String updateForm() {
        return "song/update";
    }

    @PostMapping("/{id}/update")
    public String update() {
       return "song/update";
    }

    @PostMapping("/delete")
    public String delete() {
        return "/";
    }
}