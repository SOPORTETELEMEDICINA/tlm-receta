package net.amentum.niomedic.receta.exception;

import org.springframework.http.HttpStatus;

import net.amentum.common.v2.GenericException;

public class MedicamentosException extends GenericException {

	private static final long serialVersionUID = 5267501119661639531L;
	
	public static final String SERVER_ERROR = "No fue posible %s el Medicamento";
	public static final String ERROR_PLM = "Ocurrio un problema al consumir servicios de terceros";
	
	public MedicamentosException(HttpStatus status, String message) {
		super(status, message);
	}

}
