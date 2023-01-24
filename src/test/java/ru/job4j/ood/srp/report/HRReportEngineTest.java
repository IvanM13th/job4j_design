package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.tools.SalaryComparator;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HRReportEngineTest {

    @Test
    public void whenHRReport() {
        MemStore store = new MemStore();
        Calendar calendar = Calendar.getInstance();
        Employee ivan = new Employee("Ivan", calendar, calendar, 100000);
        Employee petr = new Employee("Petr", calendar, calendar, 150000);
        Employee sergei = new Employee("Sergei", calendar, calendar, 5000);
        Employee anna = new Employee("Anna", calendar, calendar, 88000);
        Employee darya = new Employee("Darya", calendar, calendar, 200000);
        store.add(ivan);
        store.add(petr);
        store.add(sergei);
        store.add(anna);
        store.add(darya);
        List<Employee> workers = store.findBy(em -> true);
        workers.sort(new SalaryComparator());
        Report engine = new HRReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee worker : workers) {
            expect.append(worker.getName()).append(" ")
                    .append(worker.getSalary())
                    .append(System.lineSeparator());

        }
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}
