package zhewu.me.code.patterns.before.conditional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Loan {
    public static final double NORMAL_UNUSED_PERCENTAGE = 1.0;
    private static double MILLIS_PER_DAY = 86400000D;
    private static double DAYS_PER_YEAR = 365D;

    private double commitment;
    private LocalDateTime expiry;
    private LocalDateTime maturity;
    private double outstanding;
    private List<Payment> payments = new ArrayList<>();
    private LocalDateTime today;
    private LocalDateTime start;
    private double riskRating;
    private double unusedPercentage;
    private double shouldBeRemoved;

    public Loan(double commitment,
                double shouldBeRemoved,
                LocalDateTime expiry,
                LocalDateTime maturity,
                LocalDateTime start,
                double riskRating) {
        this.commitment = commitment;
        this.expiry = expiry;
        this.maturity = maturity;
        this.start = start;
        this.riskRating = riskRating;
        this.shouldBeRemoved = shouldBeRemoved;
        this.unusedPercentage = 1.0;
    }

    public void addPayment(double amount, LocalDateTime paymentDate) {
        payments.add(new Payment(amount, paymentDate));
    }


    public double duration() {
        if (expiry == null && maturity != null) {
            return getWeightedAverageDuration();
        } else if (expiry != null && maturity == null) {
            return yearsTo(expiry);
        }
        return 0D;
    }

    public double capital() {
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

    public void setUnusedPercentage(double unusedPercentage) {
        this.unusedPercentage = unusedPercentage;
    }

    public double getUnusedPercentage() {
        return unusedPercentage;
    }

    public void setToday(LocalDateTime today) {
        this.today = today;
    }

    public void setOutstanding(double outstanding) {
        this.outstanding = outstanding;
    }

    private double getRiskFactor() {
        return RiskFactor.getInstance().forRating(riskRating);
    }

    private double getUnusedRiskFactor() {
        return UnusedRiskFactor.getInstance().forRating(riskRating);
    }

    private double yearsTo(LocalDateTime end) {
        LocalDateTime begin = (today == null) ? start : start;
        return (getMillSeconds(end) - getMillSeconds(begin)) / MILLIS_PER_DAY / DAYS_PER_YEAR;
    }

    private double getWeightedAverageDuration() {
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

    private double getMillSeconds(LocalDateTime date) {
        return (double) date.atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli();
    }

    private double getUnusedRiskAmount() {
        return commitment - outstanding;
    }
}
