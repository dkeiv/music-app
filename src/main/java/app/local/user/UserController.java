package app.local.user;

import app.local.exception.NotFoundException;
import app.local.playlist.PlayList;
import app.local.playlist.PlayListService;
import app.local.song.Song;
import app.local.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/music-app/me")
public class UserController {
    private final UserService userService;
    private final PlayListService playListService;
    private final SongService songService;

    @GetMapping()
    public String userProfile(Model model, @AuthenticationPrincipal User currentUser) {
        try {
            Long id = currentUser.getId();
            Optional<User> user = userService.findById(id);
            model.addAttribute("user", user);

            List<PlayList> createdPlayLists = playListService.getPlayListsByUserId(id);
            model.addAttribute("createdPlayLists", createdPlayLists);

            List<Song> createdSongs = songService.getSongByUserId(id);
            model.addAttribute("createdSongs", createdSongs);

            List<Song> likedSongs = songService.findLikedSongsByUserId(id);
            model.addAttribute("likedSongs", likedSongs);

            return "user/profile";
        } catch (Exception e) {
            return "/error/404";
        }
    }

//    @GetMapping("me/update")
//    public String userProfileUpdate(@PathVariable Long id, Model model) {
//        try {
//            Optional<User> user = userService.findById(id);
//            model.addAttribute("user", user);
//            return "user/update";
//        } catch (NotFoundException e) {
//            return "/error/404";
//        }
//    }
}
