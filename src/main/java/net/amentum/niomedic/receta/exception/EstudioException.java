package net.amentum.niomedic.receta.exception;

import org.springframework.http.HttpStatus;

import net.amentum.common.v2.GenericException;

public class EstudioException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8730107955088589921L;
	public static final String SERVER_ERROR="Ocurrió un error inesperado al %s Estudio";
	public static final String NOT_FOUNT="No se encontro Estudio con el id: %s";
	public EstudioException(HttpStatus status, String message) {
		super(status, message);
	}

}
