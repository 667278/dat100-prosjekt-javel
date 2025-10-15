package no.hvl.dat100.javel.oppgave2;

public class MonthlyPower {

    // a) Skrive ut månedlig strømforbruk
    public static void print_PowerUsage(double[][] usage) {
        if (usage == null) {
            System.out.println("Ingen data (usage == null).");
            return;
        }
        for (int day = 0; day < usage.length; day++) {
            System.out.printf("Day %d:", day + 1);
            double[] dayArr = usage[day];
            if (dayArr != null) {
                for (int h = 0; h < dayArr.length; h++) {
                    System.out.printf("%.2f kWh ", dayArr[h]);
                }
            }
            System.out.println();
        }
    }

    // b) Skrive ut månedlige strømpriser
    public static void print_PowerPrices(double[][] prices) {
        if (prices == null) {
            System.out.println("Ingen data (prices == null).");
            return;
        }
        for (int day = 0; day < prices.length; day++) {
            System.out.printf("Day %d:", day + 1);
            double[] dayArr = prices[day];
            if (dayArr != null) {
                for (int h = 0; h < dayArr.length; h++) {
                    System.out.printf("%.2f NOK ", dayArr[h]);
                }
            }
            System.out.println();
        }
    }

    // c) Total månedlig forbruk
    public static double computePowerUsage(double[][] usage) {
        if (usage == null) return 0.0;
        double total = 0.0;
        for (int d = 0; d < usage.length; d++) {
            if (usage[d] == null) continue;
            for (int h = 0; h < usage[d].length; h++) {
                total += usage[d][h];
            }
        }
        return total;
    }

    // d) Forbruksgrense - bruk while-løkke og returner så snart grensen overskrides
    public static boolean exceedThreshold(double[][] powerusage, double threshold) {
        if (powerusage == null) return false;
        int day = 0;
        double sum = 0.0;

        while (day < powerusage.length) {
            double[] dayArr = powerusage[day];
            if (dayArr != null) {
                int hour = 0;
                while (hour < dayArr.length) {
                    sum += dayArr[hour];
                    if (sum > threshold) {
                        return true;
                    }
                    hour++;
                }
            }
            day++;
        }

        return false;
    }

    // e) Spotpris for måneden (sum usage * price) - håndter ulik lengde per dag
    public static double computeSpotPrice(double[][] usage, double[][] prices) {
        if (usage == null || prices == null) return 0.0;
        double total = 0.0;
        int days = Math.min(usage.length, prices.length);
        for (int d = 0; d < days; d++) {
            double[] uDay = usage[d];
            double[] pDay = prices[d];
            if (uDay == null || pDay == null) continue;
            int hours = Math.min(uDay.length, pDay.length);
            for (int h = 0; h < hours; h++) {
                total += uDay[h] * pDay[h];
            }
        }
        return total;
    }

    // hjelpe-metode for strømstøtte per time (gjenbruk fra oppgave 1 logikk)
    // dekker 90% av prisen som er over 0.9375 NOK
    private static double getSupport(double usage, double price) {
        double threshold = 0.9375; // NOK
        double excess = price - threshold;
        if (excess <= 0.0) return 0.0;
        return usage * 0.9 * excess;
    }

    // f) Strømstøtte for måneden
    public static double computePowerSupport(double[][] usage, double[][] prices) {
        if (usage == null || prices == null) return 0.0;
        double totalSupport = 0.0;
        int days = Math.min(usage.length, prices.length);
        for (int d = 0; d < days; d++) {
            double[] uDay = usage[d];
            double[] pDay = prices[d];
            if (uDay == null || pDay == null) continue;
            int hours = Math.min(uDay.length, pDay.length);
            for (int h = 0; h < hours; h++) {
                totalSupport += getSupport(uDay[h], pDay[h]);
            }
        }
        return totalSupport;
    }

    // g) Norgespris for måneden (fastpris 0.50 NOK/kWh) - bruk utvidet for-løkke
    public static double computeNorgesPrice(double[][] usage) {
        if (usage == null) return 0.0;
        double rate = 0.50; // NOK/kWh
        double total = 0.0;
        for (double[] day : usage) {
            if (day == null) continue;
            for (double u : day) {
                total += u * rate;
            }
        }
        return total;
    }
}
