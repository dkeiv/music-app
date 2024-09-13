package app.local.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {


    @GetMapping("/music-app")
    public String dashboard() {
        return "index";
    }

//    @GetMapping("/playlists")
//    public String songIndex() {
//        return "playlist/index";
//    }

    @GetMapping("/music-app/contact")
    public String contactIndex() {
        return "contact/contact";
    }



}
