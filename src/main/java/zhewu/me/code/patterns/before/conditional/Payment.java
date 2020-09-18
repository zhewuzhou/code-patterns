package zhewu.me.code.patterns.before.conditional;

import java.time.LocalDateTime;

public class Payment {
    private Double amount;
    private LocalDateTime date;

    public Payment(Double amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
