package app.local.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AppController {
    @RequestMapping("")
    public String dashboard() {
        return "index";
    }
}
