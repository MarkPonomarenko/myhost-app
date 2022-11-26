package app.web.controller.personal;

import com.liqpay.LiqPay;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import app.facade.PLPFacade;
import app.facade.SearchServerFacade;
import app.facade.ServerFacade;
import app.persistence.entity.server.Server;
import app.persistence.entity.transactions.Transaction;
import app.persistence.entity.user.Personal;
import app.persistence.repository.user.PersonalRepository;
import app.service.PersonalCrudService;
import app.service.ServerService;
import app.service.TransactionService;
import app.web.dto.request.payment.CheckoutDto;

import java.util.*;

@Validated
@Controller
@RequestMapping("/personal")
public class PersonalController {

    private final PersonalCrudService personalService;

    private final ServerService serverService;

    private final PersonalRepository personalRepository;

    private final SearchServerFacade searchServerFacade;

    private final ServerFacade serverFacade;

    private final PLPFacade plpFacade;

    private final TransactionService transactionService;

    public PersonalController(PersonalCrudService personalService, ServerService serverService, PersonalRepository personalRepository, SearchServerFacade searchServerFacade, ServerFacade serverFacade, PLPFacade plpFacade, TransactionService transactionService) {
        this.personalService = personalService;
        this.serverService = serverService;
        this.personalRepository = personalRepository;
        this.searchServerFacade = searchServerFacade;
        this.serverFacade = serverFacade;
        this.plpFacade = plpFacade;
        this.transactionService = transactionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication personal = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("personal",personalRepository.findByEmail(personal.getName()));
        model.addAttribute("servers",personalRepository.findByEmail(personal.getName()).getRented());
        return "pages/personal/dashboard";
    }

    @GetMapping("/dashboard/delete/{serverId}")
    public String dashboardRemoveRented(@PathVariable Long serverId) {

        Authentication personalAuth = SecurityContextHolder.getContext().getAuthentication();

        Personal personal = personalRepository.findByEmail(personalAuth.getName());
        System.out.println(personal.getFirstName());
        Server server = serverService.findById(serverId).get();
        System.out.println(server.getServerName());
        personal.removeRented(server);
        personalService.update(personal);
        serverService.update(server);

        return "redirect:/personal/dashboard";
    }

    @GetMapping("/catalog")
    public String viewCatalog(Model model, WebRequest webRequest) {
        model.addAttribute("serverList", plpFacade.search(webRequest));
        return "pages/personal/personal_plp";
    }

    @PostMapping("/search")
    private String allServerSearch(
            RedirectAttributes redirectAttributes, @RequestParam String serverSearch) {
        System.out.println(serverSearch + " triggered");
        redirectAttributes.addAttribute("serverSearch", serverSearch);
        return "redirect:/personal/catalog";
    }

    @PostMapping("/search_prov")
    private String allServersSearch(RedirectAttributes redirectAttributes, @PathVariable Long provider) {
        redirectAttributes.addAttribute("provider", provider);
        return "redirect:/personal/personal_plp";
    }

    @GetMapping("/suggestions")
    @ResponseBody
    public List<String> fetchSuggestions(@RequestParam(value = "q", required = false) String query) {
        System.out.println("SearchController.fetchSuggestions: " + query);
        return searchServerFacade.fetchSuggestions(query);
    }

    @GetMapping("/proceed/{serverId}+{personalEmail}")
    public String proceedBuy(@PathVariable Long serverId, @PathVariable String personalEmail) {
        Personal personal = personalRepository.findByEmail(personalEmail);
        Server server = serverService.findById(serverId).get();
        server.setPersonal(personal);
        personal.setBalance(personal.getBalance() - server.getPrice());
        personalService.update(personal);
        serverService.update(server);
        return "redirect:/personal/catalog";
    }

    @GetMapping("/payment/{email}")
    public String redirectToPayment(Model model) {
        model.addAttribute("form", new CheckoutDto());
        return "pages/personal/personal_payment";
    }

    @PostMapping("/payment/{email}")
    public String paymentCheck(@ModelAttribute("form") CheckoutDto dto, @PathVariable String email) {
        try {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("action", "pay");
            params.put("version", "3");
            params.put("phone", dto.getPhone());
            params.put("amount", dto.getAmount());
            params.put("currency", "USD");
            params.put("order_id", dto.generateOrderId());
            params.put("card", dto.getCard());
            params.put("card_exp_month", dto.getExpirationMonth());
            params.put("card_exp_year", dto.getExpirationYear());
            params.put("card_cvv", dto.getCvv());
            System.out.println(dto);
            LiqPay liqpay = new LiqPay("sandbox_i62948753128", "sandbox_qSPN00iDHAZYFrlVNTVrtaJh1NNOwSxY8h6ne36F");
            Map<String, Object> res = liqpay.api("request", params);
            if (res.get("status") == "failure") {
                System.out.println("payment failure");
                return "redirect:/personal/payment";
            } else {
                System.out.println("payment success");
                Transaction transaction = new Transaction();
                transaction.setAmount(Integer.parseInt(dto.getAmount()));
                transaction.setEmail(email);
                transactionService.create(transaction);
                Personal personal = personalRepository.findByEmail(email);
                personal.setBalance(personal.getBalance() + Integer.parseInt(dto.getAmount()));
                personalService.update(personal);
                return "redirect:/personal/dashboard";
            }
        } catch (Exception e ) {
            System.out.println("e = " + Arrays.toString(e.getStackTrace()));
        }
        return "redirect:/personal/dashboard";
    }
}
