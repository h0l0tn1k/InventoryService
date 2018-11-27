package cz.siemens.inventory.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.List;

public class TestUtil {

	public static <T> List<T> mapResponseToList(MvcResult mvcResult, Class<T> type) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.setFilterProvider(new SimpleFilterProvider().addFilter("exclude addDate", SimpleBeanPropertyFilter.serializeAllExcept("addDate")));
		final byte[] responseBody = mvcResult.getResponse().getContentAsByteArray();
		return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, type));
	}
}
