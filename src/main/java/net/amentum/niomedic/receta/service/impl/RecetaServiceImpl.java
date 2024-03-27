package net.amentum.niomedic.receta.service.impl;

import net.amentum.niomedic.receta.converter.DetalleRecetaConverter;
import net.amentum.niomedic.receta.converter.DomicilioConverter;
import net.amentum.niomedic.receta.converter.RecetaConverter;
import net.amentum.niomedic.receta.exception.RecetaException;
import net.amentum.niomedic.receta.model.DetalleReceta;
import net.amentum.niomedic.receta.model.Domicilio;
import net.amentum.niomedic.receta.model.Receta;
import net.amentum.niomedic.receta.persistence.DetalleRecetaRepository;
import net.amentum.niomedic.receta.persistence.DomicilioRepository;
import net.amentum.niomedic.receta.persistence.RecetaRepository;
import net.amentum.niomedic.receta.service.RecetaService;
import net.amentum.niomedic.receta.views.DetalleRecetaView;
import net.amentum.niomedic.receta.views.RecetaView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class RecetaServiceImpl implements RecetaService {
   private final Logger logger = LoggerFactory.getLogger(RecetaServiceImpl.class);
   private RecetaRepository recetaRepository;
   private DomicilioRepository domicilioRepository;
   private DetalleRecetaRepository detalleRecetaRepository;
   private RecetaConverter recetaConverter;
   private DomicilioConverter domicilioConverter;
   private DetalleRecetaConverter detalleRecetaConverter;

   @Autowired
   public void setRecetaRepository(RecetaRepository recetaRepository) {
      this.recetaRepository = recetaRepository;
   }

   @Autowired
   public void setDomicilioRepository(DomicilioRepository domicilioRepository) {
      this.domicilioRepository = domicilioRepository;
   }

   @Autowired
   public void setDetalleRecetaRepository(DetalleRecetaRepository detalleRecetaRepository) {
      this.detalleRecetaRepository = detalleRecetaRepository;
   }

   @Autowired
   public void setRecetaConverter(RecetaConverter recetaConverter) {
      this.recetaConverter = recetaConverter;
   }

   @Autowired
   public void setDomicilioConverter(DomicilioConverter domicilioConverter) {
      this.domicilioConverter = domicilioConverter;
   }

   @Autowired
   public void setDetalleRecetaConverter(DetalleRecetaConverter detalleRecetaConverter) {
      this.detalleRecetaConverter = detalleRecetaConverter;
   }

   @Transactional(readOnly = false, rollbackFor = {RecetaException.class})
   @Override
   public RecetaView createReceta(RecetaView recetaView) throws RecetaException {
      try {
         Receta recetaExistente = recetaRepository.findByConsultaId(recetaView.getConsultaId());
         if (recetaExistente != null) {
            logger.error("===>>>consultaId esta duplicado: {}", recetaView.getConsultaId());
            RecetaException reE = new RecetaException("No se encuentra en el sistema Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
            reE.addError("consultaId esta duplicado: " + recetaView.getConsultaId());
            throw reE;
         }

         if (recetaView.getCedulaMedico() == null || recetaView.getCedulaMedico().trim().isEmpty()) {
            logger.error("===>>>Cedula del Medico NULO/VACIO: {}", recetaView.getCedulaMedico());
            RecetaException reE = new RecetaException("Existe un error", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
            reE.addError("Cedula del Medico NULO/VACIO: " + recetaView.getCedulaMedico());
            throw reE;
         }

         if (recetaView.getCurpPaciente() == null || recetaView.getCurpPaciente().trim().isEmpty()) {
            logger.error("===>>>CURP del Paciente NULO/VACIO: {}", recetaView.getCurpPaciente());
            RecetaException reE = new RecetaException("Existe un error", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
            reE.addError("CURP del Paciente NULO/VACIO: " + recetaView.getCurpPaciente());
            throw reE;
         }

         Receta receta = recetaConverter.toEntity(recetaView, new Receta(), Boolean.FALSE);
         logger.debug("===>>>Insertar nuevo Receta: {}", receta);
         recetaRepository.save(receta);
         return recetaConverter.toView(receta, Boolean.TRUE);
      } catch (RecetaException reE) {
         throw reE;
      } catch (ConstraintViolationException cve) {
         logger.error("===>>>Error de Constraint en la validacion", cve); // Sre03072020 Para ver el error
         RecetaException reE = new RecetaException("Error en la validacion", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
         final Set<ConstraintViolation<?>> violaciones = cve.getConstraintViolations();
         for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
            ConstraintViolation<?> siguiente = iterator.next();
            reE.addError(siguiente.getPropertyPath() + " " + siguiente.getMessage());
            logger.error("Detalle: " + siguiente.getPropertyPath() + " / " + siguiente.getMessage()); // Sre03072020 Para ver el error
         }
         throw reE;
      } catch (DataIntegrityViolationException dive) {
         RecetaException reE = new RecetaException("No fue posible agregar  Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_INSERT);
         reE.addError("Ocurrio un error al agregar Receta");
         logger.error("===>>>Error al insertar nuevo Receta - CODE: {} - {}", reE.getExceptionCode(), recetaView, dive);
         throw reE;
      } catch (Exception ex) {
         RecetaException reE = new RecetaException("Error inesperado al agregar  Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_INSERT);
         reE.addError("Ocurrio un error al agregar Receta");
         logger.error("===>>>Error al insertar nuevo Receta - CODE: {} - {}", reE.getExceptionCode(), recetaView, ex);
         throw reE;
      }
   }

   @Transactional(readOnly = false, rollbackFor = {RecetaException.class})
   @Override
   public RecetaView updateReceta(RecetaView recetaView) throws RecetaException {
      try {
         if (!recetaRepository.exists(recetaView.getIdReceta())) {
            logger.error("===>>>idReceta no encontrado: {}", recetaView.getIdReceta());
            RecetaException reE = new RecetaException("No se encuentra en el sistema Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
            reE.addError("idReceta no encontrado: " + recetaView.getIdReceta());
            throw reE;
         }
         Receta receta = recetaRepository.findOne(recetaView.getIdReceta());

         Receta otraReceta = recetaRepository.findByConsultaId(recetaView.getConsultaId());
         if (otraReceta != null) {
            if (receta.getIdReceta() != otraReceta.getIdReceta()) {
               logger.error("===>>>consultaId de Receta DUPLICADO en otro registro: {}", recetaView.getConsultaId());
               RecetaException reE = new RecetaException("Existe un Error", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
               reE.addError("consultaId de Receta DUPLICADO en otro registro: " + recetaView.getConsultaId());
               throw reE;
            }
         }


//         los elimino de la DB
         Collection<Long> noExistenDomicilioView = domicilioConverter.obtenerIDNoExistentesDomicilio(receta, recetaView);
         Domicilio dom;
         for (Long IDdom : noExistenDomicilioView) {
            dom = domicilioRepository.findOne(IDdom);
            dom.setReceta(null);
            domicilioRepository.delete(IDdom);
         }

         Collection<Long> noExistenDetalleRecetaView = detalleRecetaConverter.obtenerIDNoExistentesDetalleReceta(receta, recetaView);
         DetalleReceta detaRece;
         for (Long IDdetaRece : noExistenDetalleRecetaView) {
            detaRece = detalleRecetaRepository.findOne(IDdetaRece);
            detaRece.setReceta(null);
            detalleRecetaRepository.delete(IDdetaRece);
         }

//         limpiar las listas
         receta.setDomicilioList(new ArrayList<>());
         receta.setDetalleRecetaList(new ArrayList<>());

         receta = recetaConverter.toEntity(recetaView, receta, Boolean.TRUE);
         logger.debug("===>>>Editar Receta: {}", receta);
         recetaRepository.save(receta);
         
         return recetaConverter.toView(receta, Boolean.TRUE);
      } catch (RecetaException reE) {
         throw reE;
      } catch (ConstraintViolationException cve) {
         logger.error("===>>>Error en la validacion");
         RecetaException reE = new RecetaException("Error en la validacion", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
         final Set<ConstraintViolation<?>> violaciones = cve.getConstraintViolations();
         for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
            ConstraintViolation<?> siguiente = iterator.next();
            reE.addError(siguiente.getPropertyPath() + ": " + siguiente.getMessage());
         }
         throw reE;
      } catch (DataIntegrityViolationException dive) {
         RecetaException reE = new RecetaException("No fue posible modificar  Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_INSERT);
         reE.addError("Ocurrio un error al modificar Receta");
         logger.error("===>>>Error al modificar nuevo Receta - CODE: {} - {}", reE.getExceptionCode(), recetaView, dive);
         throw reE;
      } catch (Exception ex) {
         RecetaException reE = new RecetaException("Error inesperado al modificar  Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_INSERT);
         reE.addError("Ocurrio un error al modificar Receta");
         logger.error("===>>>Error al modificar nuevo Receta - CODE: {} - {}", reE.getExceptionCode(), recetaView, ex);
         throw reE;
      }
   }

   @Transactional(readOnly = false, rollbackFor = {RecetaException.class})
   @Override
   public RecetaView getDetailsByIdReceta(UUID idReceta) throws RecetaException {
      try {
         if (!recetaRepository.exists(idReceta)) {
            logger.error("===>>>idReceta no encontrado: {}", idReceta);
            RecetaException reE = new RecetaException("No se encuentra en el sistema Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
            reE.addError("idReceta no encontrado: " + idReceta);
            throw reE;
         }
         Receta receta = recetaRepository.findOne(idReceta);
         return recetaConverter.toView(receta, Boolean.TRUE);
      } catch (RecetaException reE) {
         throw reE;
      } catch (ConstraintViolationException cve) {
         logger.error("===>>>Error en la validacion");
         RecetaException reE = new RecetaException("Error en la validacion", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
         final Set<ConstraintViolation<?>> violaciones = cve.getConstraintViolations();
         for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
            ConstraintViolation<?> siguiente = iterator.next();
            reE.addError(siguiente.getPropertyPath() + ": " + siguiente.getMessage());
         }
         throw reE;
      } catch (DataIntegrityViolationException dive) {
         RecetaException reE = new RecetaException("No fue posible obtener  Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_INSERT);
         reE.addError("Ocurrio un error al obtener Receta");
         logger.error("===>>>Error al obtener nuevo Receta - CODE: {} - {}", reE.getExceptionCode(), idReceta, dive);
         throw reE;
      } catch (Exception ex) {
         RecetaException reE = new RecetaException("Error inesperado al obtener  Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_INSERT);
         reE.addError("Ocurrio un error al obtener Receta");
         logger.error("===>>>Error al obtener nuevo Receta - CODE: {} - {}", reE.getExceptionCode(), idReceta, ex);
         throw reE;
      }
   }

   @Transactional(readOnly = false, rollbackFor = {RecetaException.class})
   @Override
   public void deleteReceta(UUID idReceta) throws RecetaException {
      try {
         if (!recetaRepository.exists(idReceta)) {
            logger.error("===>>>idReceta no encontrado: {}", idReceta);
            RecetaException reE = new RecetaException("No se encuentra en el sistema Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
            reE.addError("idReceta no encontrado: " + idReceta);
            throw reE;
         }
         Receta receta = recetaRepository.findOne(idReceta);
         receta.setActivo(Boolean.FALSE);
         recetaRepository.save(receta);
      } catch (RecetaException reE) {
         throw reE;
      } catch (ConstraintViolationException cve) {
         logger.error("===>>>Error en la validacion");
         RecetaException reE = new RecetaException("Error en la validacion", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
         final Set<ConstraintViolation<?>> violaciones = cve.getConstraintViolations();
         for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
            ConstraintViolation<?> siguiente = iterator.next();
            reE.addError(siguiente.getPropertyPath() + ": " + siguiente.getMessage());
         }
         throw reE;
      } catch (DataIntegrityViolationException dive) {
         RecetaException reE = new RecetaException("No fue posible eliminar  Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_INSERT);
         reE.addError("Ocurrio un error al eliminar Receta");
         logger.error("===>>>Error al eliminar nuevo Receta - CODE: {} - {}", reE.getExceptionCode(), idReceta, dive);
         throw reE;
      } catch (Exception ex) {
         RecetaException reE = new RecetaException("Error inesperado al eliminar  Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_INSERT);
         reE.addError("Ocurrio un error al eliminar Receta");
         logger.error("===>>>Error al eliminar nuevo Receta - CODE: {} - {}", reE.getExceptionCode(), idReceta, ex);
         throw reE;
      }
   }

   @Transactional(readOnly = false, rollbackFor = {RecetaException.class})
   @Override
   public RecetaView getDetailsByConsultaId(Long consultaId) throws RecetaException {
      try {
         Receta receta = recetaRepository.findByConsultaId(consultaId);
         if (receta == null) {
            logger.error("===>>>consultaId no encontrado: {}", consultaId);
            RecetaException reE = new RecetaException("No se encuentra en el sistema Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
            reE.addError("consultaId no encontrado: " + consultaId);
            throw reE;
         }
         return recetaConverter.toView(receta, Boolean.TRUE);
      } catch (RecetaException reE) {
         throw reE;
      } catch (ConstraintViolationException cve) {
         logger.error("===>>>Error en la validacion");
         RecetaException reE = new RecetaException("Error en la validacion", RecetaException.LAYER_DAO, RecetaException.ACTION_VALIDATE);
         final Set<ConstraintViolation<?>> violaciones = cve.getConstraintViolations();
         for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
            ConstraintViolation<?> siguiente = iterator.next();
            reE.addError(siguiente.getPropertyPath() + ": " + siguiente.getMessage());
         }
         throw reE;
      } catch (DataIntegrityViolationException dive) {
         RecetaException reE = new RecetaException("No fue posible obtener  Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_INSERT);
         reE.addError("Ocurrio un error al obtener Receta");
         logger.error("===>>>Error al obtener nuevo Receta - CODE: {} - {}", reE.getExceptionCode(), consultaId, dive);
         throw reE;
      } catch (Exception ex) {
         RecetaException reE = new RecetaException("Error inesperado al obtener  Receta", RecetaException.LAYER_DAO, RecetaException.ACTION_INSERT);
         reE.addError("Ocurrio un error al obtener Receta");
         logger.error("===>>>Error al obtener nuevo Receta - CODE: {} - {}", reE.getExceptionCode(), consultaId, ex);
         throw reE;
      }
   }

}
