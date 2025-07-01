package bank;

public class CustomerDeposit {
    private Customer customer;
    private double monthlyAmount;
    private int tenureMonths;
    private double interestRate;

    public CustomerDeposit(Customer customer,
                           double monthlyAmount,
                           int tenureMonths,
                           double interestRate) {
        this.customer = customer;
        this.monthlyAmount = monthlyAmount;
        this.tenureMonths = tenureMonths;
        this.interestRate = interestRate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getMonthlyAmount() {
        return monthlyAmount;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }
}

