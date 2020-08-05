package com.example.demo.utils;

public enum ResponseResult {

	fail(0), success(1), warning(-1);
	private final int value;

	private ResponseResult(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public String getMessage() {
		String msg;
		switch (value) {
		case 1:
			msg = "Proceso exitoso";
			break;
		case -1:
			msg = "Hay errores que debe verificar";
			break;
		default:
			msg = "Error al procesar, intentelo de nuevo";
			break;
		}
		return msg;
	}
	
}