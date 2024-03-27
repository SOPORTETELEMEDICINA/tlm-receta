package net.amentum.niomedic.receta.rest;

import net.amentum.common.BaseController;
import net.amentum.niomedic.receta.exception.RecetaException;
import net.amentum.niomedic.receta.service.RecetaService;
import net.amentum.niomedic.receta.views.RecetaView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("receta")
public class RecetaRest extends BaseController {
   private final Logger logger = LoggerFactory.getLogger(RecetaRest.class);

   private RecetaService recetaService;

   @Autowired
   public void setRecetaService(RecetaService recetaService) {
      this.recetaService = recetaService;
   }

   @RequestMapping(method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   public RecetaView createReceta(@RequestBody @Validated RecetaView recetaView) throws RecetaException {
      try {
         logger.info("===>>>Guardar nuevo Receta: {}", recetaView);
         return recetaService.createReceta(recetaView);
      } catch (RecetaException reE) {
         throw reE;
      } catch (Exception ex) {
         RecetaException reE = new RecetaException("No fue posible insertar  Receta", RecetaException.LAYER_REST, RecetaException.ACTION_INSERT);
         logger.error("===>>>Error al insertar  Receta- CODE: {} - ", reE.getExceptionCode(), ex);
         throw reE;
      }
   }

   @RequestMapping(value = "{idReceta}", method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.OK)
   public RecetaView updateReceta(@PathVariable() UUID idReceta, @RequestBody @Validated RecetaView recetaView) throws RecetaException {
      try {
         recetaView.setIdReceta(idReceta);
         logger.info("===>>>Editar receta: {}", recetaView);
         return recetaService.updateReceta(recetaView);
      } catch (RecetaException me) {
         throw me;
      } catch (Exception ex) {
         RecetaException me = new RecetaException("No fue posible modificar receta", RecetaException.LAYER_REST, RecetaException.ACTION_UPDATE);
         logger.error("===>>>Error al modificar receta- CODE: {} - ", me.getExceptionCode(), ex);
         throw me;
      }
   }

   @RequestMapping(value = "{idReceta}", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public RecetaView getDetailsByIdReceta(@PathVariable() UUID idReceta) throws RecetaException {
      try {
         logger.info("===>>>Obtener los detalles del receta por Id: {}", idReceta);
         return recetaService.getDetailsByIdReceta(idReceta);
      } catch (RecetaException me) {
         throw me;
      } catch (Exception ex) {
         RecetaException me = new RecetaException("No fue posible obtener los detalles del receta por Id", RecetaException.LAYER_REST, RecetaException.ACTION_SELECT);
         logger.error("===>>>Error al obtener los detalles del receta por Id- CODE: {} - ", me.getExceptionCode(), ex);
         throw me;
      }
   }

   @RequestMapping(value = "{idReceta}", method = RequestMethod.DELETE)
   @ResponseStatus(HttpStatus.OK)
   public void deleteReceta(@PathVariable() UUID idReceta) throws RecetaException {
      logger.info("Eliminar receta: {}", idReceta);
      recetaService.deleteReceta(idReceta);
   }


   @RequestMapping(value = "/por-consulta/{consultaId}", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public RecetaView getDetailsByConsultaId(@PathVariable() Long consultaId) throws RecetaException {
      try {
         logger.info("===>>>Obtener los detalles del receta por Id: {}", consultaId);
         return recetaService.getDetailsByConsultaId(consultaId);
      } catch (RecetaException me) {
         throw me;
      } catch (Exception ex) {
         RecetaException me = new RecetaException("No fue posible obtener los detalles del receta por consultaId", RecetaException.LAYER_REST, RecetaException.ACTION_SELECT);
         logger.error("===>>>Error al obtener los detalles del receta por consultaId- CODE: {} - ", me.getExceptionCode(), ex);
         throw me;
      }
   }


   
}
