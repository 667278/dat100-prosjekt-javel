package no.hvl.dat100.javel.oppgave5;

import no.hvl.dat100.javel.oppgave3.Customer;
import no.hvl.dat100.javel.oppgave3.PowerAgreementType;

public class Invoice {
    private Customer c; // customer associated with this invoice    private String month; // month that the invoice covers    private double[][] usage; // power usage this month (per day and per hour)    private double[][] prices; // power prices for this month    private double amount; // power price for this month    public Invoice(Customer c, String month, double[][] usage, double[][] power_prices) {
        this.c = c;
        this.month = month;
        this.usage = usage;
        this.prices = power_prices;
        this.amount = 0.0;
    }

    public void computeAmount() {
        double total = 0.0;
        double totalUsage = 0.0;

        if (usage == null) {
            this.amount = 0.0;
            return;
        }

        int days = usage.length;
        int priceDays = (prices != null) ? prices.length : 0;

        for (int d = 0; d < days; d++) {
            if (usage[d] == null) continue;
            int hours = usage[d].length;
            int priceHours = (prices != null && d < priceDays && prices[d] != null) ? prices[d].length : 0;
            for (int h = 0; h < hours; h++) {
                double u = usage[d][h];
                totalUsage += u;
                double p = 0.0;
                if (prices != null && d < priceDays && prices[d] != null && h < priceHours) {
                    p = prices[d][h];
                }
                total += u * p;
            }
        }

        // Bestem avtale og juster beløpet        PowerAgreementType agreement = null;
        if (c != null) {
            try {
                agreement = c.getAgreement();
            } catch (Exception e) {
                agreement = null;
            }
        }

        if (agreement == PowerAgreementType.NORGESPRICE) {
            // NORGESPRICE: fast lav pris 0.5 NOK/kWh (gitt i oppgåveeksempel)            this.amount = totalUsage * 0.5;
        } else if (agreement == PowerAgreementType.POWERSUPPORT) {
            // POWERSUPPORT: 10% rabatt på ordinær sum            this.amount = total * 0.9;
        } else {
            // SPOTPRICE eller andre: betal sum basert på timeprisar            this.amount = total;
        }
    }

    public void printInvoice() {
        double totalUsage = 0.0;
        if (usage != null) {
            for (int d = 0; d < usage.length; d++) {
                if (usage[d] == null) continue;
                for (int h = 0; h < usage[d].length; h++) {
                    totalUsage += usage[d][h];
                }
            }
        }

        // Skriv ut faktura        System.out.println("Customer number " + (c != null ? c.getCustomerId() : ""));
        System.out.println("Name  " + (c != null ? c.getName() : ""));
        System.out.println("Email " + (c != null ? c.getEmail() : ""));
        System.out.println("Agreement " + (c != null ? c.getAgreement() : ""));
        System.out.println("Month: " + month);
        System.out.printf("Usage: \t %8.2f kWh%n", totalUsage);
        System.out.printf("Amount: \t%8.2f NOK%n", amount);
    }
}
