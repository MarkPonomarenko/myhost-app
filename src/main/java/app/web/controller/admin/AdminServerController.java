package app.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import app.facade.PLPFacade;
import app.facade.ProviderFacade;
import app.facade.ServerFacade;
import app.persistence.type.CPU;
import app.web.controller.AbstractController;
import app.web.dto.request.ServerRequestDto;
import app.web.dto.response.PageData;
import app.web.dto.response.ServerResponseDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Validated
@Controller
@RequestMapping("/admin/servers")
public class AdminServerController extends AbstractController {

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("CPU Series", "cpuSeries", "cpuSeries"),
            new HeaderName("server name", "serverName", "serverName"),
            new HeaderName("RAM", "ram", "ram"),
            new HeaderName("CPU model", "cpuModel", "cpuModel"),
            new HeaderName("created", "created", "created"),
            new HeaderName("price", "price", "price"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    private final ServerFacade serverFacade;
    private final PLPFacade plpFacade;
    private final ProviderFacade providerFacade;

    public AdminServerController(ServerFacade serverFacade, PLPFacade plpFacade, ProviderFacade providerFacade) {
        this.serverFacade = serverFacade;
        this.plpFacade = plpFacade;
        this.providerFacade = providerFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<ServerResponseDto> response = serverFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/servers");
        model.addAttribute("createNew", "/admin/servers/new");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "Servers");
        return "pages/admin/server/server_all";
    }

    @PostMapping()
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/servers");
    }

    @GetMapping("/new")
    public String redirectToNewServerPage(Model model) {
        model.addAttribute("server", new ServerRequestDto());
        model.addAttribute("providerList", providerFacade.findAll());
        model.addAttribute("types", CPU.values());
        return "pages/admin/server/server_new";
    }

    @GetMapping("/plp")
    public String allServers(Model model, WebRequest webRequest) {
        model.addAttribute("serverList", plpFacade.search(webRequest));
        System.out.println(webRequest);
        plpFacade.search(webRequest).forEach(System.out::println);
        return "pages/admin/server/server_plp";
    }

    @PostMapping("/create")
    public String createNewDepartment(@ModelAttribute("server") ServerRequestDto dto) {
        serverFacade.create(dto);
        return "redirect:/admin/servers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        serverFacade.delete(id);
        return "redirect:/admin/servers";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") String id, Model model) {
        try {
            Long serverId = Long.parseLong(id);
            ServerResponseDto dto = serverFacade.findById(serverId);
            model.addAttribute("server", dto);
            return "pages/admin/server/server_details";
        } catch (NumberFormatException e) {
            throw new NumberFormatException("incorrect value id");
        }
    }
}
