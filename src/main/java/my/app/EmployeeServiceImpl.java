package my.app;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO dao;

    public EmployeeServiceImpl(EmployeeDAO dao) {
	this.dao = dao;
    }

    public void saveEmployee(Employee employee) {
	dao.saveEmployee(employee);
    }

    public List<Employee> findAllEmployees() {
	return dao.findAllEmployees();
    }

    public void deleteEmployeeBySsn(String ssn) {
	dao.deleteEmployeeBySsn(ssn);
    }

}
