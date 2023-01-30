package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLReportTest {

    @Test
    public void whenXMLReport() throws JAXBException {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        Date stringDate = date.getTime();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(stringDate);
        Employee worker = new Employee("Ivan", date, date, 150000);
        memStore.add(worker);
        XMLReport engine = new XMLReport(memStore);
        assertThat(engine.generate(em -> true)).isEqualTo(String.format(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                        + "<employees>"
                        + "<employeeList name=\"Ivan\" "
                        + "hired=\"%s\" "
                        + "fired=\"%s\" "
                        + "salary=\"150000.0\"/>"
                        + "</employees>", formattedDate, formattedDate));
    }
}
