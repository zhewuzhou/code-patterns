package zhewu.me.code.patterns.before.conditional;

public class RiskFactor {
    private static final RiskFactor factor = new RiskFactor();

    private RiskFactor() {
    }

    public static RiskFactor getInstance() {
        return factor;
    }

    public Double forRating(Double riskRating) {
        return 0.3D;
    }
}
