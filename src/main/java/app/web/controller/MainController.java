package app.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import app.persistence.entity.user.User;
import app.persistence.repository.user.UserRepository;
import app.persistence.type.Roles;

@Controller
public class MainController {

    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String main() {
        return "redirect:/servers";
    }


    @GetMapping("/dashboard")
    public String redirectToDashBoards() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        if (user.getRole() == Roles.ROLE_ADMIN) {
            return "redirect:/admin/dashboard";
        }
        if (user.getRole() == Roles.ROLE_PERSONAL) {
            return "redirect:/personal/dashboard";
        }
        return "redirect:/servers";
    }

    @GetMapping("/main_page")
    public String redirectToMainPage() {
        return "title";
    }
}
