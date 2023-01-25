package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLReportTest {

    @Test
    public void whenXMLReport() throws JAXBException {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        ReportDateTimeParser parser = new ReportDateTimeParser();
        Employee worker = new Employee("Ivan", date, date, 150000);
        Employee worker2 = new Employee("Petr", date, date, 110000);
        Employee worker3 = new Employee("Darya", date, date, 130000);
        Employees workers = new Employees(memStore.getEmployees());
        memStore.add(worker);
        memStore.add(worker2);
        memStore.add(worker3);
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();

        XMLReport engine = new XMLReport(memStore, parser, marshaller, writer, workers);
        marshaller.marshal(workers, writer);
        assertThat(engine.generate(em -> true)).isEqualTo(writer.toString());
    }
}
