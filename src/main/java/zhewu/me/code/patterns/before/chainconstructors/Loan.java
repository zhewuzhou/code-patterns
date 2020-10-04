package zhewu.me.code.patterns.before.chainconstructors;

import java.time.LocalDateTime;

public class Loan {
    private final CapitalStrategy strategy;
    private float notional;
    private float outstanding;
    private int rating;
    private LocalDateTime expiry;
    private LocalDateTime maturity;

    public Loan(float notional, float outstanding, int rating, LocalDateTime expiry) {
        this.notional = notional;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
        this.strategy = new TermROC();
    }

    public Loan(float notional, float outstanding, int rating, LocalDateTime expiry, LocalDateTime maturity) {
        this.notional = notional;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
        this.maturity = maturity;
        this.strategy = new RevolvingTermROC();
    }

    public Loan(CapitalStrategy strategy, float notional, float outstanding, int rating, LocalDateTime expiry, LocalDateTime maturity) {
        this.strategy = strategy;
        this.notional = notional;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
        this.maturity = maturity;
    }

    public CapitalStrategy getStrategy() {
        return strategy;
    }
}
