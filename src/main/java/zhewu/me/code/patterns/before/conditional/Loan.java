package zhewu.me.code.patterns.before.conditional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Loan {
    public static final double NORMAL_UNUSED_PERCENTAGE = 1.0;
    private static Double MILLIS_PER_DAY = 86400000D;
    private static Double DAYS_PER_YEAR = 365D;

    private Double commitment;
    private LocalDateTime expiry;
    private LocalDateTime maturity;
    private Double outstanding;
    private List<Payment> payments = new ArrayList<>();
    private LocalDateTime today;
    private LocalDateTime start;
    private Double riskRating;
    private Double unusedPercentage;
    private Double shouldBeRemoved;

    public Loan(Double commitment,
                Double status,
                LocalDateTime expiry,
                LocalDateTime maturity,
                LocalDateTime start,
                Double riskRating) {
        this.commitment = commitment;
        this.expiry = expiry;
        this.maturity = maturity;
        this.start = start;
        this.riskRating = riskRating;
        this.shouldBeRemoved = status;
        this.unusedPercentage = 1.0;
    }

    public static Loan createTermLoan(double commitment,
                                      LocalDateTime start,
                                      LocalDateTime maturity,
                                      Double riskRating) {
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
                                      Double riskRating
    ) {
        return new LoanBuilder()
            .withCommitment(commitment)
            .withStatus(0D)
            .withStart(start)
            .withExpiry(expiry)
            .withRiskRating(riskRating)
            .build();
    }

    public static Loan createAdvisedLine(Double commitment,
                                         LocalDateTime start,
                                         LocalDateTime expiry,
                                         Double riskRating) {
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

    public void addPayment(Double amount, LocalDateTime paymentDate) {
        payments.add(new Payment(amount, paymentDate));
    }


    public Double duration() {
        if (expiry == null && maturity != null) {
            return getWeightedAverageDuration();
        } else if (expiry != null && maturity == null) {
            return yearsTo(expiry);
        }
        return 0D;
    }

    public Double capital() {
        double result = 0D;
        if (expiry == null && maturity != null) {
            result = commitment * duration() * getRiskFactor();
        } else if (expiry != null && maturity == null) {
            if (unusedPercentage != NORMAL_UNUSED_PERCENTAGE) {
                result = commitment * unusedPercentage * duration() * getRiskFactor();
            } else {
                result = (outstanding * duration() * getRiskFactor()) +
                    (getUnusedRiskAmount() * duration() * getUnusedRiskFactor());
            }
        }
        return result;
    }

    public void setUnusedPercentage(Double unusedPercentage) {
        this.unusedPercentage = unusedPercentage;
    }

    public Double getUnusedPercentage() {
        return unusedPercentage;
    }

    public void setToday(LocalDateTime today) {
        this.today = today;
    }

    public void setOutstanding(Double outstanding) {
        this.outstanding = outstanding;
    }

    private Double getRiskFactor() {
        return RiskFactor.getInstance().forRating(riskRating);
    }

    private Double getUnusedRiskFactor() {
        return UnusedRiskFactor.getInstance().forRating(riskRating);
    }

    private Double yearsTo(LocalDateTime end) {
        LocalDateTime begin = (today == null) ? start : start;
        return (getMillSeconds(end) - getMillSeconds(begin)) / MILLIS_PER_DAY / DAYS_PER_YEAR;
    }

    private Double getWeightedAverageDuration() {
        double duration = 0D;
        double weightedAverage = 0D;
        double sumOfPayments = 0D;

        for (Payment payment : payments) {
            sumOfPayments += payment.getAmount();
            weightedAverage += yearsTo(payment.getDate()) * payment.getAmount();
        }

        if (commitment != 0.0) {
            duration = weightedAverage / sumOfPayments;
        }

        return duration;
    }

    private Double getMillSeconds(LocalDateTime date) {
        return (double) date.atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli();
    }

    private Double getUnusedRiskAmount() {
        return commitment - outstanding;
    }
}
