package cz.siemens.inventory.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Mapper<External, Internal> {

	Internal mapToInternal(External object);

	External mapToExternal(final Internal object);

	default List<Internal> mapToInternal(List<External> list) {
		return list.stream().map(this::mapToInternal).collect(Collectors.toList());
	}

	default List<External> mapToExternal(List<Internal> list) {
		return list.stream().map(this::mapToExternal).collect(Collectors.toList());
	}

	default Optional<External> mapToExternal(final Optional<Internal> object) {
		return object.map(this::mapToExternal);
	}
}
