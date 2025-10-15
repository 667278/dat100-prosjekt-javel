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


    public Invoice(Customer c, String month, double[][] usage, double[][] power_prices) {
this.c = c;
        this.month = month;
        this.usage = usage;
        this.prices = power_prices;
        this.amount = 0.0;
    }

    public void computeAmount() {

        double totalAmount = 0.0;

        // Antall dager/perioder og timer per dag antas å være matchende mellom usage og prices.
        // For SPOTPRICE beregnes per-time kostnad: usage * price
        // For POWERSUPPORT og NORGESPRICE brukes faste satser per kWh.
        PowerAgreementType agreement = c.getAgreement();

        if (agreement == PowerAgreementType.SPOTPRICE) {

            for (int d = 0; d < usage.length; d++) {
                for (int h = 0; h < usage[d].length; h++) {
                    // safety: hvis prices ikke har samme dimensjon, sjekk for indekser
                    double price = (prices != null && prices.length > d && prices[d].length > h) ?
                                   prices[d][h] : 0.0;
                    totalAmount += usage[d][h] * price;
                }
            }

        } else if (agreement == PowerAgreementType.POWERSUPPORT) {

            for (int d = 0; d < usage.length; d++) {
                for (int h = 0; h < usage[d].length; h++) {
                    totalAmount += usage[d][h] * POWERSUPPORT_RATE;
                }
            }

        } else if (agreement == PowerAgreementType.NORGESPRICE) {

            for (int d = 0; d < usage.length; d++) {
                for (int h = 0; h < usage[d].length; h++) {
                    totalAmount += usage[d][h] * NORGESPRICE_RATE;
                }
            }

        } else {
            // Ukjent avtale: sett amount til 0 (eller håndter etter behov)
            totalAmount = 0.0;
        }

        this.amount = totalAmount;
    }

    public void printInvoice() {

        // summer total bruk (kWh) for utskrift
        double totalUsage = 0.0;
        for (int d = 0; d < usage.length; d++) {
            for (int h = 0; h < usage[d].length; h++) {
                totalUsage += usage[d][h];
            }
        }

        // Her antas at Customer-klassen har metoder:
        // getNumber(), getName(), getEmail(), getAgreement()
        System.out.println("Customer number " + c.getNumber());
        System.out.println("Name  " + c.getName());
        System.out.println("Email " + c.getEmail());
        System.out.println("Agreement " + c.getAgreement());
        System.out.println();
        System.out.println("Month: " + month);
        System.out.printf("Usage:      %.2f kWh%n", totalUsage);
        System.out.printf("Amount:    %.2f NOK%n", amount);
    }

    // valgfri getter
    public double getAmount() {
        return amount;
    }
}
