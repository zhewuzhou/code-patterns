package zhewu.me.code.patterns.before.conditional;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

class LoanTest {

    public static final LocalDateTime START = LocalDateTime.of(2019, 1, 1, 0, 0);
    public static final LocalDateTime MATURITY = LocalDateTime.of(2020, 1, 1, 0, 0);
    public static final double COMMITMENT = 10000D;
    public static final double RISK_RATING = 1D;
    public static final double PAYMENT_AMOUNT = 1100D;

    @Test
    void capitalShouldWorksForTermLoan() {
        Loan termLoan = LoanFactory.createTermLoan(COMMITMENT, START, MATURITY, RISK_RATING);
        ArrayList<Payment> payments = createPayments();
        for (int i = 0; i < 12; i++) {
            termLoan.addPayment(payments.get(i).getAmount(), payments.get(i).getDate());
        }

        Double result = termLoan.capital();

        assertThat(result, closeTo(1483.5, 0.2));
    }

    @NotNull
    private ArrayList<Payment> createPayments() {
        ArrayList<Payment> payments = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            payments.add(new Payment(PAYMENT_AMOUNT,
                LocalDateTime.of(2019, i, 15, 0, 0)));
        }
        return payments;
    }
}
