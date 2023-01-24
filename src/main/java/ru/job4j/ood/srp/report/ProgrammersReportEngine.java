package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.tools.CSVWriter;

import java.io.File;
import java.util.Calendar;
import java.util.function.Predicate;

public class ProgrammersReportEngine implements Report {
    private final MemStore store;

    private final DateTimeParser<Calendar> dateTimeParser;

    private final File targetDir;

    public ProgrammersReportEngine(MemStore store, DateTimeParser<Calendar> dateTimeParser, File targetDir) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.targetDir = targetDir;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        new CSVWriter().saveToFile(targetDir, text.toString());
        return text.toString();
    }
}