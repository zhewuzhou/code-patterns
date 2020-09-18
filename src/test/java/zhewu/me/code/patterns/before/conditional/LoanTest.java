package zhewu.me.code.patterns.before.conditional;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static zhewu.me.code.patterns.before.conditional.LoanFactory.*;

class LoanTest {

    private static final LocalDateTime START = LocalDateTime.of(2019, 1, 1, 0, 0);
    private static final LocalDateTime MATURITY = LocalDateTime.of(2020, 1, 1, 0, 0);
    private static final LocalDateTime EXPIRED = LocalDateTime.of(2020, 1, 1, 0, 0);
    private static final double COMMITMENT = 10000D;
    private static final double RISK_RATING = 1D;
    private static final double PAYMENT_AMOUNT = 1100D;
    private static final ArrayList<Payment> PAYMENTS = createPayments();

    @Test
    void capitalShouldWorksForTermLoan() {
        Loan termLoan = createTermLoan(COMMITMENT, START, MATURITY, RISK_RATING);
        for (int i = 0; i < 12; i++) {
            termLoan.addPayment(PAYMENTS.get(i).getAmount(), PAYMENTS.get(i).getDate());
        }

        Double result = termLoan.capital();

        assertThat(result, closeTo(1483.5, 0.2));
    }

    @Test
    void capitalShouldWorksForResolverLoan() {
        Loan resolver = createResolver(COMMITMENT, START, EXPIRED, RISK_RATING);
        for (int i = 0; i < 12; i++) {
            resolver.addPayment(PAYMENTS.get(i).getAmount(), PAYMENTS.get(i).getDate());
        }

        Double result = resolver.capital();

        assertThat(result, closeTo(100D, 0.2));
    }

    @Test
    void capitalShouldWorksForAdvisedLineLoan() {
        Loan advisedLine = createAdvisedLine(COMMITMENT, START, EXPIRED, RISK_RATING);
        for (int i = 0; i < 12; i++) {
            advisedLine.addPayment(PAYMENTS.get(i).getAmount(), PAYMENTS.get(i).getDate());
        }

        Double result = advisedLine.capital();

        assertThat(result, closeTo(300D, 0.2));
    }


    @NotNull
    private static ArrayList<Payment> createPayments() {
        ArrayList<Payment> payments = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            payments.add(new Payment(PAYMENT_AMOUNT,
                LocalDateTime.of(2019, i, 15, 0, 0)));
        }
        return payments;
    }
}
