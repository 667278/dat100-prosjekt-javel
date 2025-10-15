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

      // Eksempel: opprett tre kunder
        Customer cust1 = new Customer(1003, "Charlie Rose", "charlie@example.com", PowerAgreementType.POWERSUPPORT);
        Customer cust2 = new Customer(1004, "Diana Prince", "diana@example.com", PowerAgreementType.NORGESPRICE);
        Customer cust3 = new Customer(1005, "Ethan Hunt", "ethan@example.com", PowerAgreementType.SPOTPRICE);

        // Bruk testdata (endre navn på feltene hvis din klasse heter noe annet)
        double[][] usage1 = CustomerPowerUsageData.usage_month_customer1;
        double[][] usage2 = CustomerPowerUsageData.usage_month_customer2;
        double[][] usage3 = CustomerPowerUsageData.usage_month_customer3;

        // Priser for januar: bytt til det feltet/metoden som finnes i ditt prosjekt
        double[][] januaryPrices = MonthPowerData.PRICES_JANUARY; // <-- BYTT hvis feil navn

        Invoice inv1 = new Invoice(cust1, "January", usage1, januaryPrices);
        Invoice inv2 = new Invoice(cust2, "January", usage2, januaryPrices);
        Invoice inv3 = new Invoice(cust3, "January", usage3, januaryPrices);

        Invoice[] invoices = { inv1, inv2, inv3 };

        Invoices.processInvoices(invoices);
    }
}
