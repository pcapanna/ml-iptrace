package com.ml.iptrace.infrastructure.in_adapter.web.trace_ip.exception;

import com.ml.iptrace.infrastructure.in_adapter.web.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UnknownIpException.class)
	public ResponseEntity<ResponseExceptionDTO> unknownIpExceptionHandler(){
		ResponseExceptionDTO errorDTO= new ResponseExceptionDTO();
		errorDTO.setName("IP INVALIDA");
		errorDTO.setMessage("La IP proporcionada no es valida");
		return new ResponseEntity<ResponseExceptionDTO>(errorDTO, HttpStatus.BAD_REQUEST);
	}

}