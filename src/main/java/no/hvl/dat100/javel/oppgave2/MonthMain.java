package no.hvl.dat100.javel.oppgave2;

import no.hvl.dat100.javel.oppgave1.DayPowerData;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MonthMain {
    public static void main(String[] args) {

        // test data
        double[][] power_prices_month = MonthPowerData.powerprices_month;

        double[][] power_usage_month = MonthPowerData.powerusage_month;

        System.out.println("==============");
        System.out.println("OPPGAVE 2");
        System.out.println("==============");
        System.out.println();

        System.out.println("=== Månedlig forbruk (per dag) ===");
        MonthlyPower.print_PowerUsage(power_usage_month);

        System.out.println("\n=== Månedlige priser (per dag) ===");
        MonthlyPower.print_PowerPrices(power_prices_month);

        double totalMonthUsage = MonthlyPower.computePowerUsage(power_usage_month);
        System.out.printf("%nTotalt månedlig forbruk: %.2f kWh%n", totalMonthUsage);

        double spotCost = MonthlyPower.computeSpotPrice(power_usage_month, power_prices_month);
        System.out.printf("Total kostnad med spotpris: %.2f NOK%n", spotCost);

        double support = MonthlyPower.computePowerSupport(power_usage_month, power_prices_month);
        System.out.printf("Total strømstøtte for måneden: %.2f NOK%n", support);

        double norges = MonthlyPower.computeNorgesPrice(power_usage_month);
        System.out.printf("Total kostnad med Norgespris (0.50 NOK/kWh): %.2f NOK%n", norges);

        double threshold = 1000.0; // f.eks. hyttegrense
        boolean exceeds = MonthlyPower.exceedThreshold(power_usage_month, threshold);
        System.out.printf("Overskrider forbruket %.0f kWh? %b%n", threshold, exceeds);

    }
}