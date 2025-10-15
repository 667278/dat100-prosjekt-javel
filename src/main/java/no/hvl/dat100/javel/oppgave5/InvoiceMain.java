package no.hvl.dat100.javel.oppgave5;

import no.hvl.dat100.javel.oppgave3.Customer;
import no.hvl.dat100.javel.oppgave3.PowerAgreementType;
import no.hvl.dat100.javel.oppgave2.MonthPowerData;           // sjekk riktig pakke/klasse i prosjektet
import no.hvl.dat100.javel.oppgave5.CustomerPowerUsageData;   // testdata for kunder (finnes i startkoden)

public class InvoiceMain {

    public static void main(String[] args) {

        System.out.println("==============");
        System.out.println("OPPGAVE 5 - TEST");
        System.out.println("==============");
        System.out.println();

        // Opprett kundar — bytt evt. til dei kundane du har i testdata
        Customer cust1 = new Customer(1003, "Charlie Rose", "charlie@example.com", PowerAgreementType.POWERSUPPORT);
        Customer cust2 = new Customer(1004, "Diana Prince", "diana@example.com", PowerAgreementType.NORGESPRICE);
        Customer cust3 = new Customer(1005, "Ethan Hunt", "ethan@example.com", PowerAgreementType.SPOTPRICE);

        // Hent bruk/pris-data frå startkoden. Namna kan vere andre i din kodebase.
        double[][] usage1 = CustomerPowerUsageData.usage_month_customer1;
        double[][] usage2 = CustomerPowerUsageData.usage_month_customer2;
        double[][] usage3 = CustomerPowerUsageData.usage_month_customer3;

        double[][] prices = MonthPowerData.monthPrices; // bytt til rett felt i MonthPowerData, f.eks. MonthPowerData.PRICES_JANUARY

        Invoice inv1 = new Invoice(cust1, "January", usage1, prices);
        Invoice inv2 = new Invoice(cust2, "January", usage2, prices);
        Invoice inv3 = new Invoice(cust3, "January", usage3, prices);

        Invoice[] invoices = { inv1, inv2, inv3 };

        Invoices.processInvoices(invoices);
    }
}