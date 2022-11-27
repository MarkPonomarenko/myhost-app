package app.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import app.persistence.entity.server.Server;
import app.persistence.entity.transactions.Transaction;
import app.service.PersonalCrudService;
import app.service.ServerService;
import app.service.TransactionService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminController {

    private final TransactionService transactionService;

    private final ServerService serverService;

    private final PersonalCrudService personalService;

    public AdminController(TransactionService transactionService, ServerService serverService, PersonalCrudService personalService) {
        this.transactionService = transactionService;
        this.serverService = serverService;
        this.personalService = personalService;
    }

    @GetMapping
    public String dashboard(Model model) {
        Date date = new Date();
        List<Transaction> transactions = transactionService.findAll();
        Integer[] money = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Long moneyTotal = 0L;
        for (Transaction transaction : transactions) {
            moneyTotal += transaction.getAmount();
            for (int i = 0; i < 12; ++i) {
                if (transaction.getMonth() == i && transaction.getYear() == Calendar.getInstance().get(Calendar.YEAR)) {
                    money[i] += transaction.getAmount();
                }
            }
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);
        Integer[] days = new Integer[YearMonth.of(year, Calendar.getInstance().get(Calendar.MONTH)+1).lengthOfMonth()];

        for (int i = 0; i < days.length; ++i) {
            days[i] = 0;
            for (Transaction transaction : transactions) {
                if (transaction.getMonth()+1 == LocalDate.now().getMonth().getValue() && transaction.getDayOfMonth() == i+1) {
                    days[i] += transaction.getAmount();
                }
            }
        }

        for (Integer day : days) {
            System.out.print(day + " ");
        }

        int serverCount = 0;

        for (Server server : serverService.findAll()) {
            if (server.getPersonal() != null) {
                serverCount++;
            }
        }

        boolean twentyNine = false;
        boolean thirty = false;
        boolean thirtyOne = false;
        if (days.length ==31) {
            twentyNine = true;
            thirty = true;
            thirtyOne = true;
        } else if (days.length == 30) {
            twentyNine = true;
            thirty = true;
        } else if (days.length == 29){
            twentyNine = true;
        }

        model.addAttribute("serverCount", serverService.findAll().size());
        model.addAttribute("moneyTotal", moneyTotal);
        model.addAttribute("customerCount", personalService.findAll().size());
        model.addAttribute("activeCount", serverCount);
        model.addAttribute("month_money", money);
        model.addAttribute("b29", twentyNine);
        model.addAttribute("b30", thirty);
        model.addAttribute("b31", thirtyOne);
        model.addAttribute("daysCount", days.length);
        model.addAttribute("days", days);
        return "pages/admin/dashboard";
    }
}
