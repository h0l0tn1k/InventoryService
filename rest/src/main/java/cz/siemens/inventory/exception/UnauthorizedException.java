package cz.siemens.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "You provided wrong credentials or user doesn't exist.")
public class UnauthorizedException extends RuntimeException {

}