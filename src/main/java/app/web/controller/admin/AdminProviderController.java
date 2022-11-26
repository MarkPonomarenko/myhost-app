package app.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import app.facade.ProviderFacade;
import app.web.controller.AbstractController;
import app.web.dto.request.ProviderRequestDto;
import app.web.dto.response.PageData;
import app.web.dto.response.ProviderResponseDto;

import javax.validation.constraints.Min;

@Controller
@RequestMapping("/admin/providers")
public class AdminProviderController extends AbstractController {

    private final ProviderFacade providerFacade;

    public AdminProviderController(ProviderFacade providerFacade) {
        this.providerFacade = providerFacade;
    }

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("name", "name", "name"),
            new HeaderName("country", "country", "country"),
            new HeaderName("created", "created", "created"),
            new HeaderName("delete", null, null)
    };

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<ProviderResponseDto> response = providerFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/providers/all");
        model.addAttribute("createNew", "/admin/providers/new");
        model.addAttribute("cardHeader", "Providers");
        return "pages/admin/provider/provider_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "providers");
    }

    @GetMapping("/new")
    public String redirectToNewServerPage(Model model) {
        model.addAttribute("provider", new ProviderRequestDto());
        return "pages/admin/provider/provider_new";
    }

    @PostMapping("/create")
    public String createNewDepartment(RedirectAttributes attributes, @ModelAttribute("provider") ProviderRequestDto dto/*, @RequestParam("file") MultipartFile file*/) {
        providerFacade.create(dto);
        return "redirect:/admin/providers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        providerFacade.delete(id);
        return "redirect:/admin/providers";
    }
}
