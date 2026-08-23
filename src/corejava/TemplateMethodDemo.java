package corejava;

/*
                 ReportGenerator
                       |
             generateReport()
                       |
        +--------------+--------------+
        |              |              |
    fetchData()    processData()   formatReport()
        |              |              |
        +--------------+--------------+
                       |
                  exportReport()
                       |
          +------------+------------+
          |            |            |
        PDF           CSV         Excel
*/
import java.util.List;

// Template class
abstract class ReportGenerator {

    // Template Method
    public final void generateReport() {
        System.out.println("Starting report generation...");

        List<String> data = fetchData();

        List<String> processedData = processData(data);

        String formattedReport = formatReport(processedData);

        exportReport(formattedReport);

        System.out.println("Report generation completed.");
    }

    // Required steps - subclasses must implement
    protected abstract List<String> fetchData();

    protected abstract List<String> processData(List<String> data);

    protected abstract String formatReport(List<String> data);

    protected abstract void exportReport(String report);

    // Optional hook
    protected void validate() {
        System.out.println("Default validation...");
    }
}

class PdfReportGenerator extends ReportGenerator {

    @Override
    protected List<String> fetchData() {
        System.out.println("Fetching data from database...");
        return List.of("Order-101", "Order-102", "Order-103");
    }

    @Override
    protected List<String> processData(List<String> data) {
        System.out.println("Processing data for PDF...");
        return data.stream()
                .map(String::toUpperCase)
                .toList();
    }

    @Override
    protected String formatReport(List<String> data) {
        System.out.println("Formatting data as PDF...");
        return String.join("\n", data);
    }

    @Override
    protected void exportReport(String report) {
        System.out.println("Exporting PDF report...");
        System.out.println(report);
    }
}

class CsvReportGenerator extends ReportGenerator {

    @Override
    protected List<String> fetchData() {
        System.out.println("Fetching data from database...");
        return List.of("Order-101", "Order-102", "Order-103");
    }

    @Override
    protected List<String> processData(List<String> data) {
        System.out.println("Processing data for CSV...");
        return data;
    }

    @Override
    protected String formatReport(List<String> data) {
        System.out.println("Formatting data as CSV...");
        return String.join(",", data);
    }

    @Override
    protected void exportReport(String report) {
        System.out.println("Writing CSV file...");
        System.out.println(report);
    }
}

class ExcelReportGenerator extends ReportGenerator {

    @Override
    protected List<String> fetchData() {
        System.out.println("Fetching data from database...");
        return List.of("Order-101", "Order-102", "Order-103");
    }

    @Override
    protected List<String> processData(List<String> data) {
        System.out.println("Processing data for Excel...");
        return data;
    }

    @Override
    protected String formatReport(List<String> data) {
        System.out.println("Formatting data as Excel...");
        return String.join(" | ", data);
    }

    @Override
    protected void exportReport(String report) {
        System.out.println("Creating Excel file...");
        System.out.println(report);
    }
}

public class TemplateMethodDemo {

    public static void main(String[] args) {

        ReportGenerator pdfReport = new PdfReportGenerator();
        pdfReport.generateReport();

        System.out.println("\n----------------------\n");

        ReportGenerator csvReport = new CsvReportGenerator();
        csvReport.generateReport();

        System.out.println("\n----------------------\n");

        ReportGenerator excelReport = new ExcelReportGenerator();
        excelReport.generateReport();
    }
}
