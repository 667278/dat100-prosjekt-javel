package no.hvl.dat100.javel.oppgave3;

public class Customer {

    private String name;
    private String email;
    private int customerId;
    private PowerAgreementType agreement;

    // Konstruktør
    public Customer(String name, String email, int customerId, PowerAgreementType agreement) {
        this.name = name;
        this.email = email;
        this.customerId = customerId;
        this.agreement = agreement;
    }

    // Gettere og settere
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public PowerAgreementType getAgreement() {
        return agreement;
    }

    public void setAgreement(PowerAgreementType agreement) {
        this.agreement = agreement;
    }

    // toString
    @Override
    public String toString() {
        return String.format("Customer number %d%nName  %s%nEmail %s%nAgreement %s",
                customerId, name, email, agreement);
    }
}
