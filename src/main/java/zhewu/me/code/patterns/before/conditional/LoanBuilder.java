package zhewu.me.code.patterns.before.conditional;

import java.time.LocalDateTime;

public class LoanBuilder {
    private Double commitment;
    private LocalDateTime expiry;
    private LocalDateTime maturity;
    private LocalDateTime start;
    private Double riskRating;
    private Double status;

    public LoanBuilder withCommitment(Double commitment) {
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

    public LoanBuilder withRiskRating(Double riskRating) {
        this.riskRating = riskRating;
        return this;
    }

    public LoanBuilder withStatus(Double status) {
        this.status = status;
        return this;
    }

    public Loan build() {
        return new Loan(commitment, status, expiry, maturity, start, riskRating);
    }

}
