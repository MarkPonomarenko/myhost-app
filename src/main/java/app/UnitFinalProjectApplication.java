package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import app.elastic.index.ServerIndex;
import app.persistence.repository.user.AdminRepository;
import app.persistence.repository.user.PersonalRepository;
import app.service.PersonalCrudService;
import app.service.ProviderService;

import javax.annotation.PreDestroy;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class UnitFinalProjectApplication {

    private final BCryptPasswordEncoder encoder;
    private final PersonalRepository personalRepository;
    private final AdminRepository adminRepository;
    private final ProviderService providerService;
    private final PersonalCrudService personalService;
    private final ElasticsearchOperations elasticsearchOperations;

    public UnitFinalProjectApplication(BCryptPasswordEncoder encoder, PersonalRepository personalRepository, AdminRepository adminRepository, ProviderService providerService, PersonalCrudService personalService, ElasticsearchOperations elasticsearchOperations) {
        this.encoder = encoder;
        this.personalRepository = personalRepository;
        this.adminRepository = adminRepository;
        this.personalService = personalService;
        this.providerService = providerService;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public static void main(String[] args) {
        SpringApplication.run(UnitFinalProjectApplication.class, args);
    }

    @PreDestroy
    public void deleteIndex() {
        elasticsearchOperations.indexOps(ServerIndex.class).delete();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {


//                LiqPay example
//        try {
//            HashMap<String, String> params = new HashMap<String, String>();
//            params.put("action", "pay");
//            params.put("version", "3");
//            params.put("phone", "380965374034");
//            params.put("amount", "1");
//            params.put("currency", "UAH");
//            params.put("description", "description text");
//            params.put("order_id", "efefefe");
//            params.put("card", "5168755908585330");
//            params.put("card_exp_month", "05");
//            params.put("card_exp_year", "24");
//            params.put("card_cvv", "733");
//
//            LiqPay liqpay = new LiqPay("sandbox_i62948753128", "sandbox_qSPN00iDHAZYFrlVNTVrtaJh1NNOwSxY8h6ne36F");
//            Map<String, Object> res = liqpay.api("request", params);
//            System.out.println(res.get("status"));
//        } catch (Exception e ) {
//            System.out.println("e = " + e.getStackTrace());
//        }
//        System.out.println(providerService.findById(2L).get().getServers());
//        System.out.println(personalService.findById(3L).get().getFirstName());
//        Personal personal = new Personal();
//        personal.setEmail("personal@mail.com");
//        personal.setBalance(500);
//        personal.setPassword(encoder.encode("rootroot"));
//        personalRepository.save(personal);
//        Admin admin = new Admin();
//        admin.setEmail("admin@mail.com");
//        admin.setPassword(encoder.encode("rootroot"));
//        adminRepository.save(admin);
//        String pass = "123456789";
//        String encode = encoder.encode(pass);
//        System.out.println("encode = " + encode);
//        String newEncode = encoder.encode(pass);
//        System.out.println("newEncode = " + newEncode);
//        boolean matches = newEncode.matches(pass);
//        System.out.println("matches = " + matches);
    }
}
