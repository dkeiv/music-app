package app.local.admin;

import app.local.user.User;
import app.local.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/music-app")
@RequiredArgsConstructor
public class AppController {


    @GetMapping("")
    public String dashboard() {
        return "index";
    }

//    @GetMapping("/playlists")
//    public String songIndex() {
//        return "playlist/index";
//    }

    @GetMapping("/contact")
    public String contactIndex() {
        return "contact/contact";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "auth/login";
    }

}
