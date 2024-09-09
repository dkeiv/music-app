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

    @GetMapping
    public String list() {
        return "song/list";
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