package ustc.sse.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ustc.sse.springboot.dao.DepartmentDao;
import ustc.sse.springboot.dao.EmployeeDao;
import ustc.sse.springboot.entities.Department;
import ustc.sse.springboot.entities.Employee;

import java.util.Collection;

/**
 * @author LRK
 * @project_name spring-boot-03-web-restfulcrud
 * @package_name ustc.sse.springboot.controller
 * @date 2018/10/31 21:37
 * @description God Bless, No Bug!
 */
@Controller
public class CustomerController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    /**
     * 查询所有员工,返回员工list
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        // 放在请求域中
        model.addAttribute("emps",employees);
        // thymeleaf 默认拼串 (prefix)classpath:/templates/ xxx (suffix).html
        return "emp/list";
    }

    /**
     * 跳转到添加员工页面
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        // 遍历部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    /**
     * 添加员工
     * SpringMVC 自动封装参数到Employee,要求表单参数名和javabean参数名一致
     * @param employee
     * @return 返回员工列表页面
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println(employee);
        return "redirect:/emps";
    }
}
