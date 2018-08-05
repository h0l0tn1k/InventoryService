package cz.siemens.inventory.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

public class BaseController {

	protected  <T> ResponseEntity<T> returnOptional(Optional<T> optional) {
		return optional.map(x -> new ResponseEntity<>(x, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	protected <T> ResponseEntity<T> returnCreatedResponse(T object, String id) {

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/" + id).build().toUri()).body(object);
	}
}
