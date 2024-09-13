package app.local.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomErrorController implements ErrorController {
    @RequestMapping
    public String errorPath() {
        return "error/404";
    }
}
