package zhewu.me.code.patterns.before.conditional;

public class UnusedRiskFactor {
    private static final UnusedRiskFactor factor = new UnusedRiskFactor();

    private UnusedRiskFactor() {
    }

    public static UnusedRiskFactor getInstance() {
        return factor;
    }

    public Double forRating(Double riskRating) {
        return 0.01D;
    }
}
