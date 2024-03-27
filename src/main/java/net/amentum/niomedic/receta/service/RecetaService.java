package net.amentum.niomedic.receta.service;

import net.amentum.niomedic.receta.exception.RecetaException;
import net.amentum.niomedic.receta.views.RecetaView;

import java.util.UUID;

public interface RecetaService {
	RecetaView createReceta(RecetaView recetaView) throws RecetaException;

	RecetaView updateReceta(RecetaView recetaView) throws RecetaException;

   RecetaView getDetailsByIdReceta(UUID idReceta) throws  RecetaException;

   void deleteReceta(UUID idReceta) throws RecetaException;

   RecetaView getDetailsByConsultaId(Long consultaId) throws RecetaException;

}
