package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.tools.CSVWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ProgrammersReportEngineTest {

    @Test
    public void whenProgrammersReportEngine(@TempDir Path folder) throws IOException {
        File target = folder.resolve("progReport.csv").toFile();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        CSVWriter writer = new CSVWriter();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ProgrammersReportEngine(store, parser, target);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        writer.saveToFile(target, expect.toString());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}
