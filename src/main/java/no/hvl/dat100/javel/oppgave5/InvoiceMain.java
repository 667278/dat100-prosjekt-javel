package no.hvl.dat100.javel.oppgave5;

import no.hvl.dat100.javel.oppgave3.Customer;
import no.hvl.dat100.javel.oppgave3.PowerAgreementType;
import no.hvl.dat100.javel.oppgave2.MonthPowerData;

public class InvoiceMain {

    public static void main(String[] args) {
        System.out.println("==============");
        System.out.println("OPPGAVE 5");
        System.out.println("==============");
        System.out.println();

        // Hent ferdige testdata frå MonthPowerData        double[][] usage = MonthPowerData.powerusage_month;
        double[][] prices = MonthPowerData.powerprices_month;

        // Opprett testkundar med avtalar        Customer c1 = new Customer("Charlie Rose", "charlie@example.com", 1001, PowerAgreementType.POWERSUPPORT);
        Customer c2 = new Customer("Diana Prince", "diana@example.com", 1002, PowerAgreementType.NORGESPRICE);
        Customer c3 = new Customer("Ethan Hunt", "ethan@example.com", 1003, PowerAgreementType.SPOTPRICE);

        // Lag Invoice-objekt som brukar MonthPowerData for både usage og prices        Invoice inv1 = new Invoice(c1, "January", usage, prices);
        Invoice inv2 = new Invoice(c2, "January", usage, prices);
        Invoice inv3 = new Invoice(c3, "January", usage, prices);

        Invoice[] invoices = { inv1, inv2, inv3 };

        Invoices.processInvoices(invoices);
    }
}
