package bank;

import java.io.FileWriter;
import java.io.IOException;

public class ShowData {
    public static void saveToCSV(CustomerDeposit deposit, double maturityAmount) {
        try (FileWriter writer = new FileWriter("output.csv", true)) {
            Customer c = deposit.getCustomer();
            writer.append(c.getName())
                  .append(',')
                  .append(c.getDob())
                  .append(',')
                  .append(String.valueOf(c.getAge()))
                  .append(',')
                  .append(c.getSex())
                  .append(',')
                  .append(c.getPhoneNumber())
                  .append(',')
                  .append(c.getEmail())
                  .append(',')
                  .append(c.getAddress())
                  .append(',')
                  .append(String.valueOf(deposit.getMonthlyAmount()))
                  .append(',')
                  .append(String.valueOf(deposit.getTenureMonths()))
                  .append(',')
                  .append(String.valueOf(deposit.getInterestRate()))
                  .append(',')
                  .append(String.valueOf(maturityAmount))
                  .append('\n');
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
}
