package no.hvl.dat100.javel.oppgave4;

import no.hvl.dat100.javel.oppgave3.Customer;
import no.hvl.dat100.javel.oppgave3.PowerAgreementType;

public class CustomersMain {

    public static void main(String[] args) {

        System.out.println("==============");
        System.out.println("OPPGAVE 4");
        System.out.println("==============");
        System.out.println();

        // opprett Customers med kapasitet 5
        Customers repo = new Customers(5);

        // opprett noen Customer-objekter (eksempeldata)
        Customer c1 = new Customer("Alice Smith", "alice@example.com", 1002, PowerAgreementType.SPOTPRICE);
        Customer c2 = new Customer("Bob Johnson", "bob@example.com", 1003, PowerAgreementType.NORGESPRICE);
        Customer c3 = new Customer("Charlie Rose", "charlie@example.com", 1004, PowerAgreementType.POWERSUPPORT);
        Customer c4 = new Customer("Diana Prince", "diana@example.com", 1005, PowerAgreementType.NORGESPRICE);
        Customer c5 = new Customer("Ethan Hunt", "ethan@example.com", 1006, PowerAgreementType.POWERSUPPORT);

        // d) legg til kunder
        System.out.println("Legger til kunder:");
        System.out.println("add c1 -> " + repo.addCustomer(c1));
        System.out.println("add c2 -> " + repo.addCustomer(c2));
        System.out.println("add c3 -> " + repo.addCustomer(c3));
        System.out.println("add c4 -> " + repo.addCustomer(c4));
        System.out.println("add c5 -> " + repo.addCustomer(c5)); // forventet true (fem plasser)

        // prøv legge til en ekstra (skal feile fordi full)
        Customer extra = new Customer("Overflow", "o@x.com", 9999, PowerAgreementType.SPOTPRICE);
        System.out.println("add extra -> " + repo.addCustomer(extra)); // forventet false

        // b) tell antall kunder
        System.out.println("\nAntall kunder lagret: " + repo.countNonNull());

        // c) hent kunde
        System.out.println("\nHenter kunde med id 1004:");
        Customer hentet = repo.getCustomer(1004);
        System.out.println(hentet != null ? hentet.toString() : "Ikke funnet");

        // e) fjern kunde
        System.out.println("\nFjerner kunde med id 1003:");
        Customer fjernet = repo.removeCustomer(1003);
        System.out.println(fjernet != null ? "Fjernet: " + fjernet.toString() : "Fant ingen å fjerne");

        // tell igjen
        System.out.println("\nAntall kunder etter fjerning: " + repo.countNonNull());

        // prøv legge til ekstra nå (skal lykkes fordi en plass ble frigitt)
        System.out.println("\nPrøver legge til 'extra' etter fjerning: " + repo.addCustomer(extra));
        System.out.println("Antall kunder nå: " + repo.countNonNull());

        // f) hent tabell med kun eksisterende kunder
        System.out.println("\nEksisterende kunder (fra getCustomers):");
        Customer[] list = repo.getCustomers();
        for (Customer c : list) {
            System.out.println(c.toString());
        }

        // skriv intern tabell (valgfritt)
        System.out.println("\n-- Intern tabell (debug) --");
        repo.printInternal();

    }
}
