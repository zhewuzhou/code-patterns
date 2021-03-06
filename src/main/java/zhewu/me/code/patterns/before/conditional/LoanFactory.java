package zhewu.me.code.patterns.before.conditional;

import java.time.LocalDateTime;

public class LoanFactory {

    public static Loan createTermLoan(double commitment,
                                      LocalDateTime start,
                                      LocalDateTime maturity,
                                      double riskRating) {
        return new LoanBuilder()
            .withCommitment(commitment)
            .withStatus(commitment)
            .withStart(start)
            .withMaturity(maturity)
            .withRiskRating(riskRating)
            .build();
    }

    public static Loan createResolver(double commitment,
                                      LocalDateTime start,
                                      LocalDateTime expiry,
                                      double riskRating
    ) {
        return new LoanBuilder()
            .withCommitment(commitment)
            .withStatus(0D)
            .withStart(start)
            .withExpiry(expiry)
            .withRiskRating(riskRating)
            .build();
    }

    public static Loan createAdvisedLine(double commitment,
                                         LocalDateTime start,
                                         LocalDateTime expiry,
                                         double riskRating) {
        if (riskRating > 3) return null;
        Loan loan = new LoanBuilder()
            .withCommitment(commitment)
            .withStart(start)
            .withExpiry(expiry)
            .withRiskRating(riskRating)
            .build();
        loan.setUnusedPercentage(0.1);
        return loan;
    }
}
