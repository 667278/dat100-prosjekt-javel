package no.hvl.dat100.javel.oppgave4;

import no.hvl.dat100.javel.oppgave3.Customer;

public class Customers {

    private Customer[] customers;

    // a) Konstruktør
    public Customers(int size) {
        customers = new Customer[size];
    }

    // b) Telle antall kunder (ikke-null)
    public int countNonNull() {
        int count = 0;
        for (Customer c : customers) {
            if (c != null) {
                count++;
            }
        }
        return count;
    }

    // c) Hente kunde etter customerId
    public Customer getCustomer(int customerId) {
        if (customers == null) return null;
        for (Customer c : customers) {
            if (c != null && c.getCustomerId() == customerId) {
                return c;
            }
        }
        return null;
    }

    // d) Sette inn kunde på første ledige plass
    public boolean addCustomer(Customer c) {
        if (c == null || customers == null) return false;

        for (int i = 0; i < customers.length; i++) {
            if (customers[i] == null) {
                customers[i] = c;
                return true;
            }
        }
        return false; // ingen ledig plass
    }

    // e) Slette kunde (sett plass til null og returner referansen)
    public Customer removeCustomer(int customerId) {
        if (customers == null) return null;
        for (int i = 0; i < customers.length; i++) {
            Customer c = customers[i];
            if (c != null && c.getCustomerId() == customerId) {
                customers[i] = null;
                return c;
            }
        }
        return null;
    }

    // f) Returner ny tabell med kun eksisterende kunder (ingen nuller)
    public Customer[] getCustomers() {
        int n = countNonNull();
        Customer[] existing = new Customer[n];
        int idx = 0;
        for (Customer c : customers) {
            if (c != null) {
                existing[idx++] = c;
            }
        }
        return existing;
    }

    // Hjelpemetode for testing / debug (valgfri)
    public void printInternal() {
        System.out.println("Internal customers array (length=" + customers.length + "):");
        for (int i = 0; i < customers.length; i++) {
            System.out.printf("slot %d: %s%n", i, customers[i] == null ? "null" : customers[i].toString());
        }
    }
}