package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.Store;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XMLReport implements Report {
    private final Store store;
    private final DateTimeParser dateTimeParser;
    private final Marshaller marshaller;
    private final StringWriter writer;
    private final Employees workers;

    public XMLReport(Store store, DateTimeParser dateTimeParser, Marshaller marshaller, StringWriter writer, Employees workers) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.marshaller = marshaller;
        this.writer = writer;
        this.workers = workers;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
       try {
           marshaller.marshal(workers.getEmployees(), writer);
       } catch (JAXBException e) {
           e.printStackTrace();
       }
        System.out.println(writer.getBuffer().toString());
        return writer.getBuffer().toString();
    }
}
