package no.hvl.dat100.javel.oppgave1;

import no.hvl.dat100.javel.oppgave2.MonthlyPower;

public class DayMain {

    public static void main(String[] args) {

        // test data
        double[] powerusage_day = DayPowerData.powerusage_day;

        double[] powerprices_day = DayPowerData.powerprices_day;

        System.out.println("==============");
        System.out.println("OPPGAVE 1");
        System.out.println("==============");
        System.out.println();

        // a) og b) Utskrift av timesdata
        System.out.println("=== Strømforbruk per time (kWh) ===");
        DailyPower.printPowerUsage(powerusage_day);

        System.out.println("\n=== Spotpris per time (NOK/kWh) ===");
        DailyPower.printPowerPrices(powerprices_day);

        // c) Total strømforbruk
        double totalUsage = DailyPower.computePowerUsage(powerusage_day);
        System.out.printf("%nTotalt strømforbruk i dag: %.2f kWh%n", totalUsage);

        // d) Total kostnad med spotpris
        double spotCost = DailyPower.computeSpotPrice(powerusage_day, powerprices_day);
        System.out.printf("Total kostnad med spotpris: %.2f NOK%n", spotCost);

        // f) Strømstøtte (beregnes fra usage og prices)
        double support = DailyPower.computePowerSupport(powerusage_day, powerprices_day);
        System.out.printf("Strømstøtte i dag: %.2f NOK%n", support);

        // g) Norgespris (fastpris 0.50 NOK/kWh)
        double norges = DailyPower.computeNorgesPrice(powerusage_day);
        System.out.printf("Kostnad med Norgespris (0.50 NOK/kWh): %.2f NOK%n", norges);

        // h) Største forbrukstime
        double peak = DailyPower.findPeakUsage(powerusage_day);
        System.out.printf("Største forbruk i én time: %.2f kWh%n", peak);

        // i) Gjennomsnittlig forbruk per time
        double avg = DailyPower.findAvgPower(powerusage_day);
        System.out.printf("Gjennomsnittlig forbruk per time: %.2f kWh%n", avg);
    }
}
