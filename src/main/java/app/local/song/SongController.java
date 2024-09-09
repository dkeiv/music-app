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

    @RequestMapping
    public ModelAndView list() {
        Iterable<Song> songs = service.findAll();
        return new ModelAndView("song/list", "songs", songs);
    }

    @GetMapping("/{id}/view")
    public String viewSong(@PathVariable Long id, Model model) {
        Optional<Song> song = service.findById(id);
        model.addAttribute("song", song);
        return "song/detail";
    }


    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView model = new ModelAndView("song/create");
        model.addObject("song", new SongForm());
        return model;
    }

//    @PostMapping("/create")
//    public ModelAndView save(@ModelAttribute("song") SongForm songForm) {
//        try {
//            Song song = service.saveSong(songForm);
//            ModelAndView modelAndView = new ModelAndView("redirect:/songs");
//            modelAndView.addObject("song", song);
//            modelAndView.addObject("message", "Song created successfully!");
//            return modelAndView;
//        } catch (RuntimeException e) {
//            ModelAndView modelAndView = new ModelAndView("redirect:/error");
//            modelAndView.addObject("message", e.getMessage());
//            return modelAndView;
//        }
//    }
//
//    @GetMapping("/{id}/edit")
//    public ModelAndView editForm(@PathVariable Long id) {
//        Optional<Song> song = service.findById(id);
//        ModelAndView modelAndView = new ModelAndView("song/edit");
//        modelAndView.addObject("song", song.get());
//        return modelAndView;
//    }
//
//    @PostMapping("/{id}/edit")
//    public ModelAndView update(@ModelAttribute("song") SongForm songForm) {
//        try {
//            Song updatedSong = service.updateSong(songForm);
//            ModelAndView modelAndView = new ModelAndView("redirect:/songs");
//            modelAndView.addObject("message", "Song updated successfully!");
//            return modelAndView;
//        } catch (RuntimeException e) {
//            ModelAndView modelAndView = new ModelAndView("redirect:/error");
//            modelAndView.addObject("message", e.getMessage());
//            return modelAndView;
//        }
//    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        Optional<Song> song = service.findById(id);
        ModelAndView modelAndView = new ModelAndView("song/delete");
        modelAndView.addObject("song", song);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.remove(id);
        redirectAttributes.addFlashAttribute("message", "Song deleted successfully!");
        return new ModelAndView("redirect:/songs");
    }
}