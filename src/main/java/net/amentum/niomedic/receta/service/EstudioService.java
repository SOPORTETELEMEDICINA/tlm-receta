package net.amentum.niomedic.receta.service;

import net.amentum.niomedic.receta.exception.EstudioException;
import net.amentum.niomedic.receta.views.EstudioView;

import java.util.List;
import java.util.UUID;

public interface EstudioService {

   EstudioView createEstudio(EstudioView estudioView) throws EstudioException;

   EstudioView updateEstudio(EstudioView estudioView) throws EstudioException;

   EstudioView getEstudioById(UUID idEstudio) throws EstudioException;

   List<EstudioView> getAllEstudioByConsultaId(Long idConsulta, Boolean activo) throws EstudioException;

   void deleteEstudio(UUID idEstudio) throws EstudioException;

   EstudioView getEstudioByFolio(Long folio) throws EstudioException;
}
