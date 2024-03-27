package net.amentum.niomedic.receta.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.amentum.niomedic.receta.converter.EstudioConverter;
import net.amentum.niomedic.receta.exception.EstudioException;
import net.amentum.niomedic.receta.model.DetalleEstudio;
import net.amentum.niomedic.receta.model.Estudio;
import net.amentum.niomedic.receta.persistence.DetalleEstudioRepository;
import net.amentum.niomedic.receta.persistence.EstudioRepository;
import net.amentum.niomedic.receta.service.EstudioService;
import net.amentum.niomedic.receta.views.EstudioView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Slf4j
public class EstudioServiceImpl implements EstudioService {
   private EstudioRepository estudioRepository;
   private EstudioConverter estudioConverter;
   private DetalleEstudioRepository detalleEstudioRepository;

   @Autowired
   public void setEstudioRepository(EstudioRepository estudioRepository) {
      this.estudioRepository = estudioRepository;
   }

   @Autowired
   public void setEstudioCoverter(EstudioConverter estudioConverter) {
      this.estudioConverter = estudioConverter;
   }

   @Autowired
   public void setDetalleEstudioRepository(DetalleEstudioRepository detalleEstudioRepository) {
      this.detalleEstudioRepository = detalleEstudioRepository;
   }

   @Transactional(readOnly = false, rollbackFor = EstudioException.class)
   @Override
   public EstudioView createEstudio(EstudioView estudioView) throws EstudioException {
      try {
         estudioView.setActivo(true);
         if (estudioView.getFechaCreacion() == null) {
            estudioView.setFechaCreacion(new Date());
         }
         Estudio entity = estudioConverter.toEntity(estudioView, new Estudio(), false);
         log.info("createEstudio() -  Guardando estudio: {}", entity);
         estudioRepository.saveAndFlush(entity);
         log.info("createEstudio() - Se guardó exitosamente estudio: {}", entity);
         return estudioConverter.toView(entity);
      } catch (ConstraintViolationException cve) {
         final Set<ConstraintViolation<?>> violaciones = cve.getConstraintViolations();
         List<Map<String, String>> errorList = new ArrayList<>();
         for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
            ConstraintViolation<?> siguiente = iterator.next();
            Map<String, String> propiedad = new HashMap<String, String>();
            propiedad.put(siguiente.getPropertyPath().toString(), siguiente.getMessage());
            errorList.add(propiedad);
         }
         log.info("createCatEstudioView() - Error de validacion al crear un nuevo estudio: {}", errorList);
         throw new EstudioException(HttpStatus.BAD_REQUEST, errorList.toString());
      } catch (DataIntegrityViolationException dive) {
         log.info("createCatEstudioView() - Error de integridad al crear un nuevo estudio: {}", dive);
         throw new EstudioException(HttpStatus.CONFLICT, dive.getCause().getCause().getMessage());
      } catch (Exception e) {
         log.info("createCatEstudioView() - Error de inesperado al crear un nuevo estudio: {}", e);
         throw new EstudioException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(EstudioException.SERVER_ERROR, "agregar un"));
      }
   }

   @Transactional(readOnly = false, rollbackFor = EstudioException.class)
   @Override
   public EstudioView updateEstudio(EstudioView estudioView) throws EstudioException {
      try {
         Estudio estudio = getByID(estudioView.getIdEstudio());
         List<Long> idNoExistentes = estudioConverter.detallesEliminado(estudio.getDetallesEstudioList(), estudioView.getDetallesEstudioList());
         for (Long id : idNoExistentes) {
            DetalleEstudio dEstudio = detalleEstudioRepository.findOne(id);
            dEstudio.setEstudio(null);
            log.info("updateEstudio() - Eliminado detalleEstudio con el id:{}", id);
            detalleEstudioRepository.delete(dEstudio);
         }
         Estudio entity = estudioConverter.toEntity(estudioView, estudio, true);
         log.info("updateEstudio() -  Actualizando estudio con el id: {}", estudioView.getIdEstudio());
         estudioRepository.saveAndFlush(entity);
         log.info("updateEstudio() - Se Actualizo exitosamente estudio: {}", entity);
         return estudioConverter.toView(entity);
      } catch (EstudioException ee) {
         throw ee;
      } catch (ConstraintViolationException cve) {
         final Set<ConstraintViolation<?>> violaciones = cve.getConstraintViolations();
         List<Map<String, String>> errorList = new ArrayList<>();
         for (Iterator<ConstraintViolation<?>> iterator = violaciones.iterator(); iterator.hasNext(); ) {
            ConstraintViolation<?> siguiente = iterator.next();
            Map<String, String> propiedad = new HashMap<String, String>();
            propiedad.put(siguiente.getPropertyPath().toString(), siguiente.getMessage());
            errorList.add(propiedad);
         }
         log.info("updateEstudio() - Error de validacion al actualizar un estudio - error: {}", errorList);
         throw new EstudioException(HttpStatus.BAD_REQUEST, errorList.toString());
      } catch (DataIntegrityViolationException dive) {
         log.info("updateEstudio() - Error de integridad al actualizar un estudio - error: {}", dive);
         throw new EstudioException(HttpStatus.CONFLICT, dive.getCause().getCause().getMessage());
      } catch (Exception e) {
         log.info("updateEstudio() - Error de inesperado al actualizar un estudio - error: {}", e);
         throw new EstudioException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(EstudioException.SERVER_ERROR, "actualizar un"));
      }
   }

   private Estudio getByID(UUID idEstudio) throws EstudioException {
      log.info("getByID() - Buscando estudio con el id:{}", idEstudio);
      Estudio estudio = estudioRepository.findOne(idEstudio);
      if (estudio == null) {
         log.info("getByID() - No se encontro Estudio con el id:{}", estudio);
         throw new EstudioException(HttpStatus.NOT_FOUND, String.format(EstudioException.NOT_FOUNT, idEstudio));
      }
      return estudio;
   }

   @Override
   public EstudioView getEstudioById(UUID idEstudio) throws EstudioException {
      try {
         Estudio estudio = getByID(idEstudio);
         EstudioView view = estudioConverter.toView(estudio);
         return view;
      } catch (EstudioException ee) {
         throw ee;
      } catch (Exception e) {
         log.info("getEstudioById() - Error de inesperado al obtener un estudio por id:{} - error: {}", idEstudio, e);
         throw new EstudioException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(EstudioException.SERVER_ERROR, "obtener un"));
      }

   }

   @Override
   public List<EstudioView> getAllEstudioByConsultaId(Long idConsulta, Boolean activo) throws EstudioException {
      try {
         List<Estudio> estudioList = estudioRepository.findByIdConsultaAndActivo(idConsulta, activo);
         if (estudioList == null || estudioList.isEmpty()) {
            log.info("getAllEstudioByConsultaId() - No se encontro Estudio con el id de consulta: {}", idConsulta);
            throw new EstudioException(HttpStatus.NOT_FOUND, String.format(EstudioException.NOT_FOUNT, idConsulta));
         }
         List<EstudioView> estudioViewList = new ArrayList<>();
         for (Estudio est : estudioList) {
            estudioViewList.add(estudioConverter.toView(est));
         }
         return estudioViewList;
      } catch (EstudioException ee) {
         throw ee;
      } catch (Exception e) {
         log.info("getAllEstudioByConsultaId() - Error de inesperado al obtener un estudio por id:{} - error: {}", idConsulta, e);
         throw new EstudioException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(EstudioException.SERVER_ERROR, "obtener por ConsultaID un"));
      }
   }

   @Transactional(readOnly = false, rollbackFor = EstudioException.class)
   @Override
   public void deleteEstudio(UUID idEstudio) throws EstudioException {
      try {

         Estudio estudio = getByID(idEstudio);
         estudio.setActivo(false);
         estudioRepository.saveAndFlush(estudio);
         log.info("deleteEstudio() - Se Actualizo exitosamente estudio: {}", estudio);

      } catch (EstudioException ee) {
         throw ee;
      } catch (Exception e) {
         log.info("deleteEstudio() - Error de inesperado al borrar un estudio - error: {}", e);
         throw new EstudioException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(EstudioException.SERVER_ERROR, "borrar un"));
      }
   }


   private Estudio getByFolio(Long folio) throws EstudioException {
      log.info("getByFolio() - Buscando estudio con el folio:{}", folio);
      Estudio estudio = estudioRepository.findByFolio(folio);
      if (estudio == null) {
         log.info("getByFolio() - No se encontro Estudio con el folio:{}", folio);
         throw new EstudioException(HttpStatus.NOT_FOUND, String.format(EstudioException.NOT_FOUNT, folio));
      }
      return estudio;
   }


   @Override
   public EstudioView getEstudioByFolio(Long folio) throws EstudioException {
      try {
         Estudio estudio = getByFolio(folio);
         EstudioView view = estudioConverter.toView(estudio);
         return view;
      } catch (EstudioException ee) {
         throw ee;
      } catch (Exception e) {
         log.info("getEstudioById() - Error de inesperado al obtener un estudio por id:{} - error: {}", folio, e);
         throw new EstudioException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(EstudioException.SERVER_ERROR, "obtener un"));
      }
   }
}