package app.cron;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import app.persistence.entity.server.Server;
import app.persistence.entity.user.Personal;
import app.service.PaymentService;
import app.service.PersonalCrudService;
import app.service.ServerService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PaymentCronService {

    private final PersonalCrudService personalService;
    private final PaymentService paymentService;
    private final ServerService serverService;

    public PaymentCronService(PersonalCrudService personalService, PaymentService paymentService, ServerService serverService) {
        this.personalService = personalService;
        this.paymentService = paymentService;
        this.serverService = serverService;
    }

    @Scheduled(fixedDelay = 60000)
    public void checkTime() {
        System.out.println("CRON triggered");
        List<Personal> personals = personalService.findAll();
        for (Personal personal : personals) {
            System.out.println(personal.getEmail());
            if (personal.getRented() != null) {
                for (Server server : personal.getRented()) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(server.getUpdated());
                    c.add(Calendar.MINUTE, 5); //5 minutes for example
                    if (new Date().after(c.getTime())) {
                        System.out.println(server.getServerName());
                        paymentService.paymentProcess(server.getPersonal(), server);
                    }
                }
            }
        }
    }
}
