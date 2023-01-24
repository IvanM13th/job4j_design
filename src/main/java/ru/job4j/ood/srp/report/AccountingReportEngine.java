package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountingReportEngine implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    private final InMemoryCurrencyConverter currencyConverter;
    private final Currency sourceCurrency;
    private final Currency targetCurrency;

    public AccountingReportEngine(Store store, DateTimeParser<Calendar> dateTimeParser, InMemoryCurrencyConverter currencyConverter, Currency sourceCurrency, Currency targetCurrency) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currencyConverter = currencyConverter;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
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
                    .append(currencyConverter.convert(sourceCurrency, employee.getSalary(), targetCurrency)).append(" ")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
