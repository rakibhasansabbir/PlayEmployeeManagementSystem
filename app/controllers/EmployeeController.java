package controllers;

import models.Employee;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import views.html.employee.*;

import javax.inject.Inject;
import java.util.List;

public class EmployeeController extends Controller {

    @Inject
    FormFactory formFactory;


    public Result index() {
        List<Employee> employees = Employee.finder.all();
        if (employees == null) {
            return notFound("Employee Not Found");
        }
        Form<Employee> bookForm = formFactory.form(Employee.class);

        return ok(index.render(employees,bookForm));
    }

    public Result create() {

        Form<Employee> bookForm = formFactory.form(Employee.class);
        return ok(create.render(bookForm));
    }

    public Result save() {
        Form<Employee> employeeForm = formFactory.form(Employee.class).bindFromRequest();
        if (employeeForm.hasErrors()){
            flash("danger","Please correct the form below");
            return badRequest(create.render(employeeForm));
        }
        Employee employee = employeeForm.get();
        employee.save();
        flash("success","Employee added");

        return redirect(routes.EmployeeController.index());
    }

    public Result edit(Integer id) {

        Employee employee = Employee.finder.byId(id);
        if (employee == null) {
            return notFound("Employee Not Found");
        }
        Form<Employee> bookForm = formFactory.form(Employee.class).fill(employee);

        return ok(edit.render(bookForm));
    }

    public Result update() {

        Form<Employee> employeeForm = formFactory.form(Employee.class).bindFromRequest();
        if (employeeForm.hasErrors()){
            flash("danger","Please correct the form below");
            return badRequest(edit.render(employeeForm));
        }
        Employee employee = employeeForm.get();
        Employee oldEmployee = Employee.finder.byId(employee.id);
        if (oldEmployee == null) {
            return notFound("Employee Not Found");
        }

        oldEmployee.name = employee.name;
        oldEmployee.email = employee.email;
        oldEmployee.contactNumber = employee.contactNumber;
        oldEmployee.dob = employee.dob;
        oldEmployee.department = employee.department;

        oldEmployee.update();
        flash("success","Employee updated successfully");

        return redirect(routes.EmployeeController.index());
    }

    public Result show(Integer id) {
        Employee employee = Employee.finder.byId(id);
        if (employee == null) {
            return notFound("Employee Not Found");
        }
        return ok(show.render(employee));
    }

    public Result delete(Integer id) {
        Employee employee = Employee.finder.byId(id);
        if (employee == null) {
            return notFound("Employee Not Found");
        }
        employee.delete();
        return redirect(routes.EmployeeController.index());

    }
}
