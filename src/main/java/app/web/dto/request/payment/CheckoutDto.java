package app.web.dto.request.payment;

import java.util.Arrays;
import java.util.UUID;

public class CheckoutDto {

    private String card;
    private String phone;
    private String expiration;
    private String cvv;
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String[] getExpirationSeparately() {
        System.out.println(expiration);
        System.out.println(Arrays.toString(expiration.split("/")));
        return expiration.split("/");
    }

    public String getExpirationMonth(){
        return getExpirationSeparately()[0];
    }

    public String getExpirationYear(){
        return getExpirationSeparately()[1];
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String generateOrderId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "CheckoutDto{" +
                "card='" + card + '\'' +
                ", phone='" + phone + '\'' +
                ", expiration='" + expiration + '\'' +
                ", cvv='" + cvv + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
