package my.app;

public class ReportingManager {
    private static ThreadLocal<Reporting> threadLocalReport = new ThreadLocal<Reporting>();

    public static void setReporting(Reporting report) {
	threadLocalReport.set(report);
    }

    public static Reporting getReporting() {
	return threadLocalReport.get();
    }

}
