package cz.siemens.inventory.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "The resource already exists.")
public class ResourceAlreadyExistsException extends RuntimeException {
}
