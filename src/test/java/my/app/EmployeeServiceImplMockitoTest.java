package my.app;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Notice that above two tests [saveEmployee, saveExistingEmployee] are example
 * of stubbing void methods while the last one[findAllEmployees] is an example
 * of stubbing non void methods.
 * 
 * It's important to understand that void methods can not be stubbed using usual
 * when(x.m()).then()construct[as in findAllEmployees()]as compiler complains
 * about calling a void method inside when.To get rid off this problem,you can
 * use any of doNothing(),doThrow(),doAnswer(),doReturn()constructs as we did
 * in[saveEmployee,saveExistingEmployee]tests.
 */

public class EmployeeServiceImplMockitoTest {

    // Objects annotated with @Mock are not real instances, they are test
    // objects created by Mockito using Class
    // of the object it is applied on, instrumented to track interactions with
    // them.
    // Once created, mock object will remember all interactions with it. Then
    // you can selectively verify whatever
    // interaction you are interested in.
    @Mock
    EmployeeDAO dao;

    // This annotation is used to mark the field on which injection should be
    // performed. Mockito will create a real instance
    // of the object this annotation is applied on, using either of constructor
    // injection, setter injection, or property injection.
    // Mockito will also make sure to inject any dependencies(@mock objects) the
    // actual object in test might need.
    @InjectMocks
    EmployeeServiceImpl employeeService;

    // Objects annotated with @Spy are real instances. These objects are real
    // objects but with a difference that they remember
    // all interactions/operation applied on them, which we can verify further
    // down in our test.
    @Spy
    List<Employee> employees = new ArrayList<Employee>();

    // Captor is used to capture argument values which can be asserted further
    // in test, after the actual verification.
    @Captor
    ArgumentCaptor<Employee> captor;

    @BeforeClass
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	employees = getEmployeeList();
    }

    /*
     * Scenario for Successful [error-free] data persistence Void method
     * stubbing example
     */
    @Test(priority = 0)
    public void saveEmployee() {
	/*
	 * Instruct mockito to do nothing when dao.saveEmployee will be called.
	 */
	doNothing().when(dao).saveEmployee(any(Employee.class));
	employeeService.saveEmployee(employees.get(0));

	/*
	 * Verify that dao.saveEmployee was indeed called one time.
	 */
	verify(dao, times(1)).saveEmployee(captor.capture());

	/*
	 * Assert that dao.saveEmployee was called with a particular Employee,
	 * assert employee details
	 */
	Assert.assertEquals(captor.getValue().getName(), "Axel");

	Assert.assertEquals(2, employees.size());
	verify(employees, times(2)).add(any(Employee.class));
    }

    /*
     * Scenario for Failed data persistence (due to existing user) Void method
     * stubbing example
     */
    @Test(expectedExceptions = RuntimeException.class, priority = 2)
    public void saveExistingEmployee() {
	/*
	 * Instruct mockito to throw an exception when dao.saveEmployee will be
	 * called.
	 */
	doThrow(RuntimeException.class).when(dao).saveEmployee(employees.get(0));
	employeeService.saveEmployee(any(Employee.class));
    }

    /*
     * Scenario for Successful data retrieval.
     */
    @Test(priority = 1)
    public void findAllEmployees() {
	/*
	 * Instruct mockito to return pre-populated employees list whenever
	 * dao.findAllEmployees will be called.
	 */
	when(dao.findAllEmployees()).thenReturn(employees);
	Assert.assertEquals(employeeService.findAllEmployees(), employees);
	verify(dao, times(1)).findAllEmployees();
    }

    /*
     * Scenario for Successful data deletion Void method stubbing example
     */
    @Test(priority = 2)
    public void deleteEmployeeBySsn() {
	/*
	 * Instruct mockito to do nothing when dao.deleteEmployeeBySsn will be
	 * called.
	 */
	doNothing().when(dao).deleteEmployeeBySsn(anyString());
	employeeService.deleteEmployeeBySsn(anyString());
	verify(dao, times(1)).deleteEmployeeBySsn(anyString());
    }

    /*
     * Scenario for Failed data deletion (due to no employee found with given
     * ssn) Void method stubbing example
     */
    @Test(expectedExceptions = RuntimeException.class, priority = 3)
    public void deleteEmployeeBySsnNotExist() {
	/*
	 * Instruct mockito to throw an exception when dao.deleteEmployeeBySsn
	 * will be called.
	 * 
	 */
	doThrow(RuntimeException.class).when(dao).deleteEmployeeBySsn(anyString());
	employeeService.deleteEmployeeBySsn("XXXX");
	verify(dao, atLeastOnce()).deleteEmployeeBySsn(anyString());
    }

    /*
     * Same as above test case, demonstrates 'doAnswer().when' pattern Void
     * method stubbing example
     */
    @Test(expectedExceptions = RuntimeException.class, priority = 3)
    public void deleteEmployeeBySsnNotExistAgain() {
	/*
	 * Alternate way to Instruct mockito to throw an exception when
	 * dao.deleteEmployeeBySsn will be called.
	 */
	doAnswer(new Answer<Object>() {
	    public Object answer(InvocationOnMock invocation) {
		Object[] args = invocation.getArguments();
		String arg = (String) args[0];
		if (arg.equals("UNKNOWN_SSN")) {
		    throw new RuntimeException("Item not present");
		}
		return null;
	    }
	}).when(dao).deleteEmployeeBySsn(anyString());
	employeeService.deleteEmployeeBySsn("UNKNOWN_SSN");
	verify(dao, atLeastOnce()).deleteEmployeeBySsn(anyString());
    }

    /*
     * Simple data provider method
     */
    public List<Employee> getEmployeeList() {
	Employee e1 = new Employee();
	e1.setId(1);
	e1.setName("Axel");
	e1.setSsn("11111");

	Employee e2 = new Employee();
	e1.setId(2);
	e2.setName("Jeremy");
	e2.setSsn("11112");

	employees.add(e1);
	employees.add(e2);
	return employees;
    }
}
