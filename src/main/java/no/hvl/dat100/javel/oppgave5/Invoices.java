package no.hvl.dat100.javel.oppgave5;

public class Invoices {

    /**     * Prosesserer ei liste med fakturaer: for kvar faktura kall computeAmount() og printInvoice()     * og skriv separatorar som i eksempelutskrift.     */    public static void processInvoices(Invoice[] invoices) {

        if (invoices == null) return;

        for (Invoice inv : invoices) {
            System.out.println("========================");
            if (inv != null) {
                inv.computeAmount();
                inv.printInvoice();
            }
            System.out.println("================================================");
        }
        System.out.println("========================");
    }
}
