package net.amentum.niomedic.receta.rest;

import lombok.extern.slf4j.Slf4j;
import net.amentum.common.v2.RestBaseController;
import net.amentum.niomedic.receta.exception.EstudioException;
import net.amentum.niomedic.receta.service.EstudioService;
import net.amentum.niomedic.receta.views.EstudioView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("estudio")
@Slf4j
public class EstudioRest extends RestBaseController {

   private EstudioService estudioService;

   @Autowired
   public void setEstudioService(EstudioService estudioService) {
      this.estudioService = estudioService;
   }

   @RequestMapping(method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   public EstudioView createEstudio(@RequestBody @Validated EstudioView estudioView) throws EstudioException {
      log.info("POST - createEstudio() - Agregagando un nuevo Estudio");
      log.debug("POST - createEstudio() - Agregagando un nuevo Estudio: {}", estudioView);
      return estudioService.createEstudio(estudioView);
   }


   @RequestMapping(value = "{idEstudio}", method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.OK)
   public EstudioView updateEstudio(@PathVariable() UUID idEstudio, @RequestBody @Validated EstudioView estudioView) throws EstudioException {
      log.info("PUT - updateEstudio() - Actualizando Estudio con el id:{}", idEstudio);
      log.debug("PUT - updateEstudio() - Actualizando Estudio con el id: {}", idEstudio);
      estudioView.setIdEstudio(idEstudio);
      return estudioService.updateEstudio(estudioView);
   }


   @RequestMapping(value = "{idEstudio}", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public EstudioView getDetailsByIdEstudio(@PathVariable() UUID idEstudio) throws EstudioException {
      log.info("GET - getDetailsByIdEstudio() - Obtenido estudio por ID:{}", idEstudio);
      return estudioService.getEstudioById(idEstudio);
   }

   @RequestMapping(value = "{idEstudio}", method = RequestMethod.DELETE)
   @ResponseStatus(HttpStatus.OK)
   public void deleteEstudio(@PathVariable("idEstudio") UUID idEstudio) throws EstudioException {
      log.info("DELETE - deleteEstudio() - por ID: {}", idEstudio);
      estudioService.deleteEstudio(idEstudio);
   }

   @RequestMapping(value = "/por-consulta/{consultaId}", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public List<EstudioView> getAllEstudioByConsultaId(@PathVariable("consultaId") Long consultaId, @RequestParam(required = false) Boolean activo) throws EstudioException {
      log.info("GET - getAllEstudioByConsultaId() - Obtenido estudios por ID de Consulta: {} y activo: {}", consultaId, activo);
      if (activo == null) {
         activo = Boolean.TRUE;
      }
      return estudioService.getAllEstudioByConsultaId(consultaId, activo);
   }

   @RequestMapping(value = "/por-folio/{folio}", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public EstudioView getEstudioByFolio(@PathVariable("folio") Long folio) throws EstudioException {
      log.info("GET - getEstudioByFolio() - Obtenido estudio por folio:{}", folio);
      return estudioService.getEstudioByFolio(folio);
   }

}
