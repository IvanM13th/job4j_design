package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XMLReport implements Report {
    private final Store store;

    private  Marshaller marshaller;

    public XMLReport(Store store) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        this.marshaller = context.createMarshaller();
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringWriter writer = new StringWriter();
        try {
            marshaller.marshal(new Employees(store.findBy(em -> true)), writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return writer.getBuffer().toString();
    }

    @XmlRootElement(name = "employees")
    public static class Employees {
        private List<Employee> employeeList;

        public Employees() {

        }

        public Employees(List<Employee> employeeList) {
            this.employeeList = employeeList;
        }

        public List<Employee> getEmployeeList() {
            return employeeList;
        }

        public void setEmployeeList(List<Employee> employeeList) {
            this.employeeList = employeeList;
        }
    }
}
