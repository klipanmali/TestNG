package my.app;

import java.util.List;

public interface EmployeeDAO {

    void saveEmployee(Employee employee);

    List<Employee> findAllEmployees();

    void deleteEmployeeBySsn(String ssn);
}
