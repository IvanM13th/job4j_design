package ru.job4j.ood.srp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {
    @XmlElement(name = "employee")
    private List<Employee> employees = null;

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employees() {

    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
