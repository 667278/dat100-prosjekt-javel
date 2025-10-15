package no.hvl.dat100.javel.oppgave3;

import no.hvl.dat100.javel.oppgave4.Customers;

public class CustomerMain {

    public static void main(String[] args) {

        System.out.println("==============");
        System.out.println("OPPGAVE 3");
        System.out.println("==============");
        System.out.println();

        // Oppretter en kunde
        Customer c = new Customer("Aleksander Koding", "AlexCoding@Protonmail.com", 1001, PowerAgreementType.SPOTPRICE);

        // Tester toString()
        System.out.println("=== Kunde informasjon ===");
        System.out.println(c.toString());

        // Tester getters
        System.out.println("\n=== Tester getters ===");
        System.out.println("Name: " + c.getName());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Customer id: " + c.getCustomerId());
        System.out.println("Agreement: " + c.getAgreement());

        // Tester setters
        c.setName("Alex Coding");
        c.setEmail("AlexKoding@Tuta.com");
        c.setCustomerId(2002);
        c.setAgreement(PowerAgreementType.POWERSUPPORT);

        // Skriv ut etter endringer
        System.out.println("\n=== Etter oppdatering via setters ===");
        System.out.println(c.toString());
    }
}
