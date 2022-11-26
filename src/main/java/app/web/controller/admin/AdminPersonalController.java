package app.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import app.facade.PersonalFacade;
import app.web.controller.AbstractController;
import app.web.dto.response.PageData;
import app.web.dto.response.PersonalResponseDto;

@Controller
@RequestMapping("/admin/personals")
public class AdminPersonalController extends AbstractController {


    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("first name", "firstName", "firstName"),
            new HeaderName("last name", "lastName", "lastName"),
            new HeaderName("balance", "balance", "balance"),
            new HeaderName("created", "created", "created"),
            new HeaderName("details", null, null),
    };

    private final PersonalFacade personalFacade;

    public AdminPersonalController(PersonalFacade personalFacade) {
        this.personalFacade = personalFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<PersonalResponseDto> response = personalFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/personals");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "Personals");
        return "pages/admin/personal/personal_all";
    }

    @PostMapping
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/personals");
    }

    @GetMapping("/details/{id}")
    public String personalDetails(@PathVariable Long id, Model model) {
        model.addAttribute("personal", personalFacade.findById(id));
        model.addAttribute("servers", personalFacade.findById(id).getRented());
        return "pages/admin/personal/personal_details";
    }
}
