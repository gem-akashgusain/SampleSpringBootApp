package com.example.BackendExample;

import com.example.BackendExample.model.Employee;
import com.example.BackendExample.repository.EmployeeRepository;
import com.example.BackendExample.service.EmployeeService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendExampleApplicationTests {

    @MockBean
    EmployeeService employeeService ;

    @DisplayName("Test for checking whether we are getting correct data or not")
    @Test
    public void getEmployeeTest(){
        when(employeeService.getAllEmployees()).thenReturn(Stream
                .of(new Employee(11,"aman","gupta","ag@gmail.com"),
                        new Employee(12,"rishabh","bisht","rb@gmail.com"))
                .collect(Collectors.toList()));
        assertEquals(2,employeeService.getAllEmployees().size());

    }

    @Test
    public void getEmployeeByIdTest()
    {
        when(employeeService.getEmployeeById(1L)).thenReturn(new Employee(1,"Lokesh","Gupta","user@email.com"));

        Employee emp = employeeService.getEmployeeById(1L);

        assertEquals("Lokesh", emp.getFirstName());
        assertEquals("Gupta", emp.getLastName());
        assertEquals("user@email.com", emp.getEmail());
    }

    @DisplayName("This test is used to check whether correct data is added to the db or not")
    @Test
    public void saveEmployeeTest(){
        Employee e = new Employee(1001,"akshay","kumar","akumar@gmail.com");
        when(employeeService.saveEmployee(e)).thenReturn(e);
        assertEquals(e,employeeService.saveEmployee(e));
    }


}
