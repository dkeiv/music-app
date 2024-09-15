package app.local.song;

import app.local.artist.Artist;
import app.local.artist.ArtistService;
import app.local.exception.NotFoundException;
import app.local.genre.Genre;
import app.local.playlist.PlayList;
import app.local.playlist.PlayListRepository;
import app.local.playlist.PlayListService;
import app.local.user.User;
import app.local.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/music-app/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    private final UserService userService;
    private final ArtistService artistService;
    private final PlayListService playListService;

    @GetMapping
    public String list(Model model,@PageableDefault(size = 10) Pageable pageable, @AuthenticationPrincipal User user) throws NotFoundException {
        Page<Song> allSongs = songService.findAll(pageable);
        List<PlayList> allPlaylistsByUserId = userService.getPlaylistsByUserId(user.getId());
        model.addAttribute("allSongs", allSongs);
        model.addAttribute("allPlaylistsByUserId", allPlaylistsByUserId);
        return "song/index";
    }

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

    @PostMapping("/like/{songId}")
    public String likeSong(@PathVariable Long songId, @AuthenticationPrincipal User user) throws NotFoundException {
        userService.likeSong(user.getId(), songId);
        return "redirect:/music-app/songs/" + songId;
    }

    @PostMapping("/unlike/{songId}")
    public String unlikeSong(@PathVariable Long songId, @AuthenticationPrincipal User user) throws NotFoundException {
        userService.unlikeSong(user.getId(), songId);
        return "redirect:/music-app/songs/" + songId;
    }

    @GetMapping("/{songId}")
    public ModelAndView songDetail(@PathVariable Long songId) throws NotFoundException {
        Optional<Song> song = songService.findById(songId);
        List<Artist> artistList = songService.getArtistBySong(songId);
        List<Genre> genresList = songService.getGenresBySongId(songId);
        ModelAndView modelAndView = new ModelAndView("song/detail");
        modelAndView.addObject("song", song.orElseThrow(() -> new NotFoundException("Song not found")));
        modelAndView.addObject("artists", artistList);
        modelAndView.addObject("genres", genresList);
        return modelAndView;
    }


}