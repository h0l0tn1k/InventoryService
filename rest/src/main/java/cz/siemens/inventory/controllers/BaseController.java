package cz.siemens.inventory.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class BaseController {

	protected  <T> ResponseEntity<T> returnOptional(Optional<T> optional) {
		return optional.map(x -> new ResponseEntity<>(x, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
