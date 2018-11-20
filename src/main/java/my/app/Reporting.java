package my.app;

import java.util.HashMap;

public class Reporting {
    private Long executionStart;
    public HashMap<String, Object> report = new HashMap<String, Object>();

    public void setTestExecutionStar(long start) {
	executionStart = start;
    }

    public String toString() {
	return " ExecutionStart: " + executionStart + " " + report;

    }
}
