package com.senior.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<ExceptionDto> EntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
	}
	   
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<ExceptionDto> EntidadeEmUsoException(EntidadeEmUsoException e){

		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDto(HttpStatus.CONFLICT, e.getMessage()));
	}
	   
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<ExceptionDto> NegocioException(NegocioException e){

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ExceptionDto(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage()));
	}
	   
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			  org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		List<String> listaErros =  ex.getAllErrors().stream()
        .map(objectError -> {String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
               
        String name = objectError.getObjectName();
               
        if (objectError instanceof FieldError) {
        	name = ((FieldError) objectError).getField();                   
         }
             
        return name + " " + message;
           }).collect(Collectors.toList());
		  
		String retornoErros = "";
		for (var erro : listaErros) {
			if (retornoErros.equalsIgnoreCase("")) {
				retornoErros = erro;
			} else {
				retornoErros = retornoErros + ", " + erro;
			}
		}
		   
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ExceptionDto(HttpStatus.UNPROCESSABLE_ENTITY, retornoErros));   
	}
			
}
