package no.hvl.dat100.javel.oppgave1;

public class DailyPower {

    // a) Skriv ut strømpriser (NOK per kWh)
    public static void printPowerPrices(double[] prices) {
        for (int i = 0; i < prices.length; i++) {
            System.out.printf("%.2f NOK ", prices[i]);
        }
        System.out.println();
    }

    // b) Skriv ut strømforbruk (kWh)
    public static void printPowerUsage(double[] usage) {
        for (int i = 0; i < usage.length; i++) {
            System.out.printf("%.2f kWh ", usage[i]);
        }
        System.out.println();
    }

    // c) Totalt strømforbruk for en dag (sum kWh)
    public static double computePowerUsage(double[] usage) {
        double total = 0.0;
        for (int i = 0; i < usage.length; i++) {
            total += usage[i];
        }
        return total;
    }

    // d) Strømpris med spotpris (sum over usage[i] * prices[i])
    public static double computeSpotPrice(double[] usage, double[] prices) {
        int n = Math.min(usage.length, prices.length);
        double total = 0.0;
        for (int i = 0; i < n; i++) {
            total += usage[i] * prices[i];
        }
        return total;
    }

    // e) Hjelpemetode: støtte for én time
    // Dekkes 90% av prisen som er over 93.75 øre (0.9375 NOK)
    private static double getSupport(double usage, double price) {
        double threshold = 0.9375; // NOK
        double excess = price - threshold;
        if (excess <= 0.0) {
            return 0.0;
        }
        return usage * 0.9 * excess;
    }

    // f) Strømstøtte for hele dagen
    public static double computePowerSupport(double[] usage, double[] prices) {
        int n = Math.min(usage.length, prices.length);
        double totalSupport = 0.0;
        for (int i = 0; i < n; i++) {
            totalSupport += getSupport(usage[i], prices[i]);
        }
        return totalSupport;
    }

    // g) Norgespris: fast pris 0.50 NOK per kWh
    public static double computeNorgesPrice(double[] usage) {
        double rate = 0.50; // NOK per kWh
        double total = 0.0;
        for (int i = 0; i < usage.length; i++) {
            total += usage[i] * rate;
        }
        return total;
    }

    // h) Størst strømforbruk (maks kWh på en time)
    public static double findPeakUsage(double[] usage) {
        if (usage == null || usage.length == 0) {
            return 0.0;
        }
        double max = usage[0];
        for (int i = 1; i < usage.length; i++) {
            if (usage[i] > max) {
                max = usage[i];
            }
        }
        return max;
    }

    // i) Gjennomsnitt strømforbruk per time
    public static double findAvgPower(double[] usage) {
        if (usage == null || usage.length == 0) {
            return 0.0;
        }
        double total = computePowerUsage(usage);
        return total / usage.length;
    }

}
