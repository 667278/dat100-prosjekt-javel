package no.hvl.dat100.javel.oppgave5;

public class Invoices {

    public static void processInvoices(Invoice[] invoices) {

        if (invoices == null) {
            return;
        }

        for (Invoice inv : invoices) {
            System.out.println("========================");
            inv.computeAmount();
            inv.printInvoice();
            System.out.println("========================");
            System.out.println();
        }
    }
}
