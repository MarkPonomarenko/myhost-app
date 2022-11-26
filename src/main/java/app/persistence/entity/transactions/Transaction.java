package app.persistence.entity.transactions;

import app.persistence.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    private Integer amount;

    private String email;

    public Transaction() {
        super();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.getCreated());
        return calendar.get(Calendar.MONTH);
    }

    public Integer getYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.getCreated());
        return calendar.get(Calendar.YEAR);
    }

    public Integer getDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.getCreated());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}
