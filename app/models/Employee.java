package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee extends Model{

    @Id
    public Integer id;
    @Constraints.Required
    public String name;
    @Constraints.Required
    @Constraints.Email
    public String email;
    @Constraints.Required
    public String contactNumber;
    @Constraints.Required
    public String dob;
    @Constraints.Required
    public String department;

    public static Finder<Integer, Employee> finder = new Finder<>(Employee.class);
}
