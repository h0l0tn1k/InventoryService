package cz.siemens.inventory.mapper;

import java.time.format.DateTimeFormatter;

public interface DateFormat {
    String YYYY_MM_DD = "yyyy-MM-dd";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
}
