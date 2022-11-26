package app.service;

import app.persistence.entity.server.Server;
import app.persistence.entity.user.Personal;

public interface PaymentService {

    void paymentProcess(Personal personal, Server server);

    void paymentProcess(Long personalId, Integer serverPrice);
}
