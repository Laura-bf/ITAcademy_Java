package com.empleados.S402.nivel3.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorsController implements ErrorController{
	
	private Map<String, String> mapErrors;
	
	@GetMapping("/Errors")
	public ModelAndView controlErrors(HttpServletRequest request, ModelAndView mav) {
		
		Object errorStatusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		Object errorRequestUri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
		Object errorExceptionType = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
		Object errorException = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		
		Integer statusCode = Integer.valueOf(errorStatusCode.toString());
		
		if(errorMessage!=null) {
			if(statusCode == HttpStatus.NOT_FOUND.value())
				errorMessage = "La página solicitada no se encuentra";
			else if (statusCode == HttpStatus.BAD_REQUEST.value())
				errorMessage = "Los datos introducidos no son válidos";
			else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
				errorMessage = "Internal Server Error";
		}
		
		this.addError("", errorMessage);
		this.addError("Status", errorStatusCode);
		this.addError("URI", errorRequestUri);
		this.addError("Exception Type", errorExceptionType);
		this.addError("Exception", errorException);
		
		mav.addObject("ErrorsMap", this.mapErrors);
		mav.setViewName("error");
		
		return mav;
	}
	//para añadir elementos al mapa de errores
	private void addError(String key, Object value) {
		if(this.mapErrors == null)
			mapErrors = new HashMap<String,String>();
		if(value != null && value instanceof String)
			mapErrors.put(key, value.toString());
		if(value != null && value instanceof Integer)
			mapErrors.put(key, value.toString());
	}

}
