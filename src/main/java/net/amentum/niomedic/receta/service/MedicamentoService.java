package net.amentum.niomedic.receta.service;

import com.fasterxml.jackson.databind.JsonNode;

import net.amentum.niomedic.receta.exception.MedicamentosException;

public interface MedicamentoService {
	
	String getMedicamentos(String textoBusqueda) throws MedicamentosException;
	
	String getMedicamentoBySustanceId(Long substanceId)throws MedicamentosException;
	
	String getDetailsMedicamento(Long ProductId, Long categoryId, Long pharmaFormId,Long divisionId) throws MedicamentosException;
}
