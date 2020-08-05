package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.utils.AppProperty;
import com.example.demo.utils.ResponseResult;

@Service
public class errorManagerService {

	private boolean showException;
	
	@Autowired
	public errorManagerService(AppProperty properties) {
		this.showException = properties.getShowException() == 1;
	}
	
	public String manejarExcepcion(Exception e) {
		if (this.showException) {
			return e.getMessage();
		}
		String mensaje = ResponseResult.fail.getMessage();
		String message = e.getMessage();
		if (message == null) {
			mensaje = "Los datos indicados no son validos, favor de verificar";
		} else {
			message = message.toLowerCase();
			if (message.contains("no value present")) {
				mensaje = "Hay incrongruencia con los datos, favor de indicar al administrador del sistema";
			}
		}
		return mensaje;
	}
	
}
