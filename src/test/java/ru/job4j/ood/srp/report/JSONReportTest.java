package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONReportTest {
    @Test
    public void whenJSONReport() {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        Date stringDate = date.getTime();
        Employee worker = new Employee("Ivan", date, date, 100000);
        memStore.add(worker);
        Gson gson = new GsonBuilder().create();
        JSONReport engine = new JSONReport(memStore, gson);
        assertThat(engine.generate(em -> true)).isEqualTo(String.format(
                "[{\"name\":\"Ivan\","
                        + "\"hired\":{\"year\":%s,\"month\":0,\"dayOfMonth\":%s,\"hourOfDay\":%s,\"minute\":%s,\"second\":%s},"
                        + "\"fired\":{\"year\":%s,\"month\":0,\"dayOfMonth\":%s,\"hourOfDay\":%s,\"minute\":%s,\"second\":%s},"
                        + "\"salary\":100000.0}]",
                2023,
                stringDate.getDate(),
                stringDate.getHours(),
                stringDate.getMinutes(),
                stringDate.getSeconds(),
                2023,
                stringDate.getDate(),
                stringDate.getHours(),
                stringDate.getMinutes(),
                stringDate.getSeconds()
                )
        );
    }
}
