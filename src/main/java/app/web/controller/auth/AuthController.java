package app.web.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import app.config.security.SecurityService;
import app.facade.AuthValidatorFacade;
import app.facade.RegistrationFacade;
import app.persistence.repository.user.UserRepository;
import app.persistence.type.Roles;
import app.persistence.datatable.util.SecurityUtil;
import app.web.controller.AbstractController;
import app.web.dto.request.register.AuthDto;

@Controller
public class AuthController extends AbstractController {

    private final RegistrationFacade registrationFacade;
    private final AuthValidatorFacade authValidatorFacade;
    private final SecurityService securityService;
    private final UserRepository userRepository;

    public AuthController(
            RegistrationFacade registrationFacade,
            AuthValidatorFacade authValidatorFacade,
            SecurityService securityService, UserRepository userRepository) {
        this.registrationFacade = registrationFacade;
        this.authValidatorFacade = authValidatorFacade;
        this.securityService = securityService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        showMessage(model, false);
        boolean authenticated = securityService.isAuthenticated();
        model.addAttribute("authForm", new AuthDto());
        if (authenticated) {
            if (SecurityUtil.hasRole(Roles.ROLE_ADMIN.name())) {
                return "redirect:/admin/servers";
            }
            if (SecurityUtil.hasRole(Roles.ROLE_PERSONAL.name())) {
                return "redirect:/personal/dashboard";
            }
        }
        if (error != null) {
            showError(model, "Your email and password is invalid.");
        }
        if (logout != null) {
            showInfo(model, "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectProcess(model);
        }
        model.addAttribute("authForm", new AuthDto());
        return "login";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("authForm") AuthDto authForm, BindingResult bindingResult, Model model) {
        showMessage(model, false);
        authValidatorFacade.validate(authForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        registrationFacade.registration(authForm);
        securityService.autoLogin(authForm.getEmail(), authForm.getPasswordConfirm());
        return redirectProcess(model);
    }

    private String redirectProcess(Model model) {
        showMessage(model, false);
        if (SecurityUtil.hasRole(Roles.ROLE_ADMIN.name())) {
            System.out.println("triggerred2");
            return "redirect:/admin/servers";
        }
        if (SecurityUtil.hasRole(Roles.ROLE_PERSONAL.name())) {
            return "redirect:/personal/dashboard";
        }
        return "redirect:/login";
    }
}
