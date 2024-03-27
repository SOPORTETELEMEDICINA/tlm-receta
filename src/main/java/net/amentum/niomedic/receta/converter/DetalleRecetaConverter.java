package net.amentum.niomedic.receta.converter;

import net.amentum.niomedic.receta.model.DetalleReceta;
import net.amentum.niomedic.receta.model.Receta;
import net.amentum.niomedic.receta.views.DetalleRecetaView;
import net.amentum.niomedic.receta.views.RecetaView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DetalleRecetaConverter {
   private Logger logger = LoggerFactory.getLogger(DetalleRecetaConverter.class);
   ObjectMapper mapper= new ObjectMapper();
   public Collection<DetalleReceta> toEntity(Collection<DetalleRecetaView> detalleRecetaViewArrayList, Collection<DetalleReceta> detalleRecetaArrayList, Receta receta, Boolean update) {
      for (DetalleRecetaView deReV : detalleRecetaViewArrayList) {
         DetalleReceta deRe = new DetalleReceta();
         if (update) {
            deRe.setIdDetalleReceta(deReV.getIdDetalleReceta());
         }
         deRe.setDenominacionGenerica(deReV.getDenominacionGenerica());
         deRe.setDenominacionDistintiva(deReV.getDenominacionDistintiva());
         deRe.setCantidad(deReV.getCantidad());
         deRe.setUnidad(deReV.getUnidad());
         deRe.setDosis(deReV.getDosis());
         deRe.setFrecuencia(deReV.getFrecuencia());
         deRe.setPeriodo(deReV.getPeriodo());
         deRe.setViaAdministracion(deReV.getViaAdministracion());
         deRe.setIndicacionesMedicas(deReV.getIndicacionesMedicas());
         deRe.setReceta(receta);
         //nuevos campos
         deRe.setPresentacion(deReV.getPresentacion());
         JsonNode subtancias= mapper.convertValue(deReV.getSubstancias(), JsonNode.class);
         deRe.setSubstancias(subtancias);
         detalleRecetaArrayList.add(deRe);
      }
      logger.debug("converter detalleRecetaView to Entity: {}", detalleRecetaArrayList);
      return detalleRecetaArrayList;
   }

   public Collection<DetalleRecetaView> toView(Collection<DetalleReceta> detalleRecetaArrayList, Boolean completeConversion) {
      Collection<DetalleRecetaView> detalleRecetaViews = new ArrayList<>();
      for (DetalleReceta deRe : detalleRecetaArrayList) {
         DetalleRecetaView deReV = new DetalleRecetaView();
         deReV.setIdDetalleReceta(deRe.getIdDetalleReceta());
         deReV.setDenominacionGenerica(deRe.getDenominacionGenerica());
         deReV.setDenominacionDistintiva(deRe.getDenominacionDistintiva());
         deReV.setCantidad(deRe.getCantidad());
         deReV.setUnidad(deRe.getUnidad());
         deReV.setDosis(deRe.getDosis());
         deReV.setFrecuencia(deRe.getFrecuencia());
         deReV.setPeriodo(deRe.getPeriodo());
         deReV.setViaAdministracion(deRe.getViaAdministracion());
         deReV.setIndicacionesMedicas(deRe.getIndicacionesMedicas());
       //nuevos campos
         deReV.setPresentacion(deRe.getPresentacion());
         Collection<Map<String, Object>> subtancias= mapper.convertValue(deRe.getSubstancias(), Collection.class);
         deReV.setSubstancias(subtancias);
         detalleRecetaViews.add(deReV);
      }
      logger.debug("converter detalleReceta to View: {}", detalleRecetaViews);
      return detalleRecetaViews;
   }

   public Collection<Long> obtenerIDNoExistentesDetalleReceta(Receta receta, RecetaView recetaView) {
//      IDs de DB
      Collection<Long> ids = new ArrayList<>();
      ids.addAll(
         receta.getDetalleRecetaList().stream()
            .map(pv -> {
               Long id = pv.getIdDetalleReceta();
               return id;
            }).collect(Collectors.toList())
      );

//      IDs de View
      Collection<Long> idsView = new ArrayList<>();
      idsView.addAll(
         recetaView.getDetalleRecetaViewList().stream()
            .map(pvV -> {
               Long idV = pvV.getIdDetalleReceta();
               return idV;
            }).collect(Collectors.toList())
      );

//      Obtener los no existentes
      Collection<Long> noExisten = new ArrayList<>(ids);
      noExisten.removeAll(idsView);

//      logger.info("--->ids--->{}", ids);
//      logger.info("--->idsView--->{}", idsView);
//      logger.info("--->noExisten--->{}", noExisten);

      return noExisten;
   }
}
