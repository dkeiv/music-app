package app.local.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/music-app")
public class AppController {
    @RequestMapping("")
    public String dashboard() {
        return "index";
    }

    @RequestMapping("/song")
    public String songIndex() {
        return "song/index";
    }

    @RequestMapping("/contact")
    public String contactIndex() {
        return "contact/contact";
    }

    @RequestMapping("/login")
    public String loginForm() {
        return "login/login";
    }
}
