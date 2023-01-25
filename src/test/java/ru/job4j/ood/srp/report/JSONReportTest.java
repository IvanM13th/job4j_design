package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONReportTest {
    @Test
    public void whenJSONReport() {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        ReportDateTimeParser parser = new ReportDateTimeParser();
        Employee worker = new Employee("Ivan", date, date, 100000);
        memStore.add(worker);
        Gson gson = new GsonBuilder().create();
        JSONReport engine = new JSONReport(memStore, parser, gson);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        String toJson = gson.toJson(expect);
        assertThat(engine.generate(em -> true)).isEqualTo(toJson);
    }
}
