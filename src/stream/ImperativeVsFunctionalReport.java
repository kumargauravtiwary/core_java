package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ImperativeVsFunctionalReport {

    // =========================================================
    // DATA MODEL
    // =========================================================

    record Sale(
            String region,
            String category,
            String product,
            double revenue,
            double cost,
            int quantity,
            boolean returned
    ) {}

    record CategoryReport(
            double revenue,
            double profit,
            int quantity
    ) {}

    record BusinessReport(
            double totalRevenue,
            double totalProfit,
            int totalQuantity,
            Map<String, Map<String, CategoryReport>> regionReport
    ) {}


    public static void main(String[] args) {

        List<Sale> sales = List.of(

                new Sale(
                        "North",
                        "Electronics",
                        "Laptop",
                        120000,
                        90000,
                        5,
                        false
                ),

                new Sale(
                        "North",
                        "Electronics",
                        "Mobile",
                        80000,
                        60000,
                        8,
                        false
                ),

                new Sale(
                        "North",
                        "Furniture",
                        "Chair",
                        30000,
                        18000,
                        10,
                        false
                ),

                new Sale(
                        "South",
                        "Electronics",
                        "Laptop",
                        150000,
                        110000,
                        6,
                        false
                ),

                new Sale(
                        "South",
                        "Furniture",
                        "Table",
                        50000,
                        30000,
                        4,
                        true
                ),

                new Sale(
                        "South",
                        "Furniture",
                        "Chair",
                        40000,
                        25000,
                        8,
                        false
                ),

                new Sale(
                        "West",
                        "Electronics",
                        "Mobile",
                        100000,
                        70000,
                        10,
                        false
                ),

                new Sale(
                        "West",
                        "Furniture",
                        "Table",
                        60000,
                        35000,
                        5,
                        false
                )
        );


        // =========================================================
        // IMPERATIVE APPROACH
        // =========================================================

        System.out.println("======================================");
        System.out.println("IMPERATIVE REPORT");
        System.out.println("======================================");

        BusinessReport imperativeReport =
                generateImperativeReport(sales);

        printReport(imperativeReport);


        // =========================================================
        // FUNCTIONAL APPROACH
        // =========================================================

        System.out.println("\n======================================");
        System.out.println("FUNCTIONAL REPORT");
        System.out.println("======================================");

        BusinessReport functionalReport =
                generateFunctionalReport(sales);

        printReport(functionalReport);
    }


    // =========================================================
    // IMPERATIVE STYLE
    // =========================================================

    static BusinessReport generateImperativeReport(
            List<Sale> sales) {

        double totalRevenue = 0;
        double totalProfit = 0;
        int totalQuantity = 0;

        Map<String, Map<String, CategoryReport>> regionReport =
                new HashMap<>();


        // -----------------------------------------
        // Step 1: Iterate through sales
        // -----------------------------------------

        for (Sale sale : sales) {

            // Ignore returned products
            if (sale.returned()) {
                continue;
            }

            double profit =
                    sale.revenue() - sale.cost();

            totalRevenue += sale.revenue();
            totalProfit += profit;
            totalQuantity += sale.quantity();


            // -----------------------------------------
            // Find region
            // -----------------------------------------

            Map<String, CategoryReport> categoryMap =
                    regionReport.computeIfAbsent(
                            sale.region(),
                            r -> new HashMap<>()
                    );


            // -----------------------------------------
            // Find category
            // -----------------------------------------

            CategoryReport existing =
                    categoryMap.get(sale.category());


            if (existing == null) {

                existing = new CategoryReport(
                        sale.revenue(),
                        profit,
                        sale.quantity()
                );

            } else {

                existing = new CategoryReport(
                        existing.revenue()
                                + sale.revenue(),

                        existing.profit()
                                + profit,

                        existing.quantity()
                                + sale.quantity()
                );
            }


            categoryMap.put(
                    sale.category(),
                    existing
            );
        }


        return new BusinessReport(
                totalRevenue,
                totalProfit,
                totalQuantity,
                regionReport
        );
    }


    // =========================================================
    // FUNCTIONAL STYLE
    // =========================================================

    static BusinessReport generateFunctionalReport(
            List<Sale> sales) {


        // -----------------------------------------
        // Remove returned sales
        // -----------------------------------------

        List<Sale> validSales =
                sales.stream()
                        .filter(sale -> !sale.returned())
                        .toList();


        // -----------------------------------------
        // Total revenue
        // -----------------------------------------

        double totalRevenue =
                validSales.stream()
                        .mapToDouble(Sale::revenue)
                        .sum();


        // -----------------------------------------
        // Total profit
        // -----------------------------------------

        double totalProfit =
                validSales.stream()
                        .mapToDouble(sale ->
                                sale.revenue() - sale.cost())
                        .sum();


        // -----------------------------------------
        // Total quantity
        // -----------------------------------------

        int totalQuantity =
                validSales.stream()
                        .mapToInt(Sale::quantity)
                        .sum();


        // -----------------------------------------
        // Region → Category aggregation
        // -----------------------------------------

        Map<String, Map<String, CategoryReport>>
                regionReport =

                validSales.stream()
                        .collect(

                                Collectors.groupingBy(

                                        Sale::region,

                                        Collectors.groupingBy(

                                                Sale::category,

                                                Collectors.collectingAndThen(

                                                        Collectors.toList(),

                                                        list -> {

                                                            double revenue =
                                                                    list.stream()
                                                                            .mapToDouble(
                                                                                    Sale::revenue)
                                                                            .sum();

                                                            double profit =
                                                                    list.stream()
                                                                            .mapToDouble(
                                                                                    sale ->
                                                                                            sale.revenue()
                                                                                                    - sale.cost())
                                                                            .sum();

                                                            int quantity =
                                                                    list.stream()
                                                                            .mapToInt(
                                                                                    Sale::quantity)
                                                                            .sum();

                                                            return new CategoryReport(
                                                                    revenue,
                                                                    profit,
                                                                    quantity
                                                            );
                                                        }
                                                )
                                        )
                                )
                        );


        return new BusinessReport(
                totalRevenue,
                totalProfit,
                totalQuantity,
                regionReport
        );
    }


    // =========================================================
    // PRINT REPORT
    // =========================================================

    static void printReport(BusinessReport report) {

        System.out.printf(
                "Total Revenue : ₹%.2f%n",
                report.totalRevenue()
        );

        System.out.printf(
                "Total Profit  : ₹%.2f%n",
                report.totalProfit()
        );

        System.out.println(
                "Total Quantity: " + report.totalQuantity()
        );


        System.out.println("\nRegion / Category:");

        report.regionReport()
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(regionEntry -> {

                    System.out.println(
                            "\nRegion: " +
                            regionEntry.getKey()
                    );

                    regionEntry.getValue()
                            .entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByKey())
                            .forEach(categoryEntry -> {

                                CategoryReport reportData =
                                        categoryEntry.getValue();

                                System.out.printf(
                                        "  %-15s Revenue=₹%-10.2f " +
                                        "Profit=₹%-10.2f Qty=%d%n",

                                        categoryEntry.getKey(),

                                        reportData.revenue(),

                                        reportData.profit(),

                                        reportData.quantity()
                                );
                            });
                });
    }
}
