package no.hvl.dat100.javel.oppgave5;

import no.hvl.dat100.javel.oppgave3.Customer;
import no.hvl.dat100.javel.oppgave2.MonthlyPower;

import java.util.Arrays;

public class Invoice {

    private Customer c; 
    private String month; 
    private double[][] usage; 
    private double[][] prices; 

    private double amount; 


    public Invoice(Customer c, String month, double[][] usage, double[][] power_prices) {

        // konstruktør:
        this.c = c;
        this.month = month;
        this.usage = usage;
        this.prices = power_prices;
        this.amount = 0.0;
    }

    public void computeAmount() {

        // beregn amount ut fra hvilken avtale kunden har
        if (c == null) {
            amount = 0.0;
            return;
        }

        switch (c.getAgreement()) {

            case SPOTPRICE:
                // total kostnad basert på spotpris (uten støtte)
                amount = MonthlyPower.computeSpotPrice(usage, prices);
                break;

            case POWERSUPPORT:
                // spotpris minus støtte
                double spot = MonthlyPower.computeSpotPrice(usage, prices);
                double support = MonthlyPower.computePowerSupport(usage, prices);
                amount = spot - support;
                break;

            case NORGESPRICE:
                // fastpris (Norgespris)
                amount = MonthlyPower.computeNorgesPrice(usage);
                break;

            default:
                amount = 0.0;
                break;
        }
    }

    public void printInvoice() {

        double totalUsage = MonthlyPower.computePowerUsage(usage);

        System.out.println("========================");
        if (c != null) {
            System.out.printf("Customer number %d%n", c.getCustomerId());
            System.out.printf("Name  %s%n", c.getName());
            System.out.printf("Email %s%n", c.getEmail());
            System.out.printf("Agreement %s%n", c.getAgreement());
        } else {
            System.out.println("Customer: null");
        }
        System.out.printf("Month: %s%n", month == null ? "" : month);
        System.out.printf("Usage:   %10.2f kWh%n", totalUsage);
        System.out.printf("Amount:  %10.2f NOK%n", amount);
        System.out.println("========================");
    }
}
