package portfolio6;

import java.util.ArrayList;
import java.util.List;

public class SumaryTreeReport {
    private List<String> report = new ArrayList();
    private String prefix = "";

    public SumaryTreeReport(Account anAccount) {
        report.add(prefix + "Cuenta:");
        anAccount.transactions().forEach((transaction) -> {
            report.add(prefix + "  " + transaction.reportDetail());
        });
        report.add(prefix + "Balance: " + anAccount.balance());
    }
    public String report() {
        return String.join("\n", report);
    }
}
