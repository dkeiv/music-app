package app.local.auth;

import app.local.user.User;
import app.local.user.UserRepository;
import app.local.role.UserRole;
import app.local.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/music-app/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/music-app/process-register")
    public String processRegister(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.register(user);
        return "redirect:/music-app/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}
