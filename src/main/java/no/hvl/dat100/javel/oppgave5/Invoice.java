package no.hvl.dat100.javel.oppgave5;

import no.hvl.dat100.javel.oppgave3.Customer;
import no.hvl.dat100.javel.oppgave2.MonthlyPower;

import java.util.Arrays;

public class Invoice {

    private Customer c; // customer associated with this invoice
    private String month; // month that the invoice covers
    private double[][] usage; // power usage this month (per day and per hour)
    private double[][] prices; // power prices for this month

    private double amount; // power price for this month

    private static final double SUPPORT_THRESHOLD = 0.9375; // 93,75 øre = 0.9375 NOK
    private static final double SUPPORT_RATE = 0.90;       // 90%
    private static final double NORGES_PRICE_RATE = 0.50;  // 50 øre = 0.50 NOK/kWh

    public Invoice(Customer c, String month, double[][] usage, double[][] power_prices) {
        this.c = c;
        this.month = month;
        this.usage = usage;
        this.prices = power_prices;
        this.amount = 0.0;
    }

    public void computeAmount() {

        double total = 0.0;

        PowerAgreementType agreement = c.getAgreement();

        if (agreement == PowerAgreementType.SPOTPRICE) {

            // sum usage * price per hour over whole month
            for (int d = 0; d < usage.length; d++) {
                for (int h = 0; h < usage[d].length; h++) {
                    double u = usage[d][h];
                    double p = (prices != null && prices.length > d && prices[d].length > h) ? prices[d][h] : 0.0;
                    total += u * p;
                }
            }

        } else if (agreement == PowerAgreementType.POWERSUPPORT) {

            // for each hour: cost = usage * price - support
            // support = usage * max(0, price - SUPPORT_THRESHOLD) * SUPPORT_RATE
            for (int d = 0; d < usage.length; d++) {
                for (int h = 0; h < usage[d].length; h++) {
                    double u = usage[d][h];
                    double p = (prices != null && prices.length > d && prices[d].length > h) ? prices[d][h] : 0.0;
                    double hourCost = u * p;
                    double support = 0.0;
                    if (p > SUPPORT_THRESHOLD) {
                        support = u * (p - SUPPORT_THRESHOLD) * SUPPORT_RATE;
                    }
                    total += hourCost - support;
                }
            }

        } else if (agreement == PowerAgreementType.NORGESPRICE) {

            // flat rate per kWh
            double totalUsage = 0.0;
            for (int d = 0; d < usage.length; d++) {
                for (int h = 0; h < usage[d].length; h++) {
                    totalUsage += usage[d][h];
                }
            }
            total = totalUsage * NORGES_PRICE_RATE;

        } else {
            total = 0.0; // ukjent avtale
        }

        this.amount = total;
    }

    public void printInvoice() {

        // total usage
        double totalUsage = 0.0;
        for (int d = 0; d < usage.length; d++) {
            for (int h = 0; h < usage[d].length; h++) {
                totalUsage += usage[d][h];
            }
        }

        // Assume Customer has methods: getNumber(), getName(), getEmail(), getAgreement()
        System.out.println("Customer number " + c.getNumber());
        System.out.println("Name  " + c.getName());
        System.out.println("Email " + c.getEmail());
        System.out.println("Agreement " + c.getAgreement());
        System.out.println();
        System.out.println("Month: " + month);
        System.out.printf("Usage:     %.2f kWh%n", totalUsage);
        System.out.printf("Amount:   %.2f NOK%n", amount);
    }

    public double getAmount() {
        return amount;
    }

    public Customer getCustomer() {
        return c;
    }

    public String getMonth() {
        return month;
    }
}
