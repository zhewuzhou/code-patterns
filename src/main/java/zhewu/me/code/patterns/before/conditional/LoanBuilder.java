package zhewu.me.code.patterns.before.conditional;

import java.time.LocalDateTime;

public class LoanBuilder {
    private double commitment;
    private LocalDateTime expiry;
    private LocalDateTime maturity;
    private LocalDateTime start;
    private double riskRating;
    private double shouldBeRemoved;

    public LoanBuilder withCommitment(double commitment) {
        this.commitment = commitment;
        return this;
    }

    public LoanBuilder withExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
        return this;
    }

    public LoanBuilder withMaturity(LocalDateTime maturity) {
        this.maturity = maturity;
        return this;
    }

    public LoanBuilder withStart(LocalDateTime start) {
        this.start = start;
        return this;
    }

    public LoanBuilder withRiskRating(double riskRating) {
        this.riskRating = riskRating;
        return this;
    }

    public LoanBuilder withStatus(double status) {
        this.shouldBeRemoved = status;
        return this;
    }

    public Loan build() {
        return new Loan(commitment, shouldBeRemoved, expiry, maturity, start, riskRating);
    }

}
