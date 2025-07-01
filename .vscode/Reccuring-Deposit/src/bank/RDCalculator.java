package bank;

public class RDCalculator {
    public double calculateRD(double monthlyAmount, int months, double interestRate) {
        double total = monthlyAmount * months;
        double maturity = total + (total * interestRate / 100);
        return maturity;
    }
}
