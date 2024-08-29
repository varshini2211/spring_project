package com.kgisl.springproject;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/employee")
@Controller
public class EmployeeController {

    private final EmployeeDao employeeDAO;

    public EmployeeController() {
        String jdbcURL = "jdbc:mysql://localhost:3306/employeedb";
        String jdbcUsername = "root";
        String jdbcPassword = "";

        employeeDAO = new EmployeeDao(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getEmployees(Model model) {
        try {
            List<Employee> employeeList = employeeDAO.SELECT_ALL_EMPLOYEES();
            model.addAttribute("employees", employeeList);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve employees");
        }
        return "employeeList"; // The name of the JSP view to render
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable("id") long id, Model model) {
        Employee employee = employeeDAO.selectEmployee(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "employeeDetail"; // JSP view name
        } else {
            model.addAttribute("error", "Employee ID not found");
            return "error"; // Error JSP view name
        }
    }
    
    

    

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String createEmployee(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        try {
            employeeDAO.insertEmployee(employee);
            redirectAttributes.addFlashAttribute("message", "Employee inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error inserting employee");
        }
        return "redirect:/employee/"; // Redirect to the list view
    }
   
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee"; // This should match the name of your JSP file
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            boolean result = employeeDAO.deleteEmployee(id);
            if (result) {
                redirectAttributes.addFlashAttribute("message", "Employee deleted successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "Employee ID not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error deleting employee");
        }
        return "redirect:/employee/"; // Redirect to the list view
    }
    
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        try {
            boolean result = employeeDAO.updateEmployee(employee);
            if (result) {
                redirectAttributes.addFlashAttribute("message", "Employee updated successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "Error updating employee");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error updating employee");
        }
        return "redirect:/employee/"; // Redirect to the list view
    }
    
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
public String showUpdateEmployeeForm(@PathVariable("id") int id, Model model) {
    Employee employee = employeeDAO.selectEmployee(id);
    if (employee != null) {
        model.addAttribute("employee", employee);
        return "updateEmployee"; // Name of the JSP file for the update form
    } else {
        model.addAttribute("error", "Employee ID not found");
        return "error"; // Error JSP view name
    }
}


    
}
