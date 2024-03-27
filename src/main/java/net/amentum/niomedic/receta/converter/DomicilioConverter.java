package net.amentum.niomedic.receta.converter;

import net.amentum.niomedic.receta.model.Domicilio;
import net.amentum.niomedic.receta.model.Receta;
import net.amentum.niomedic.receta.views.DomicilioView;
import net.amentum.niomedic.receta.views.RecetaView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class DomicilioConverter {
   private Logger logger = LoggerFactory.getLogger(DomicilioConverter.class);

   public Collection<Domicilio> toEntity(Collection<DomicilioView> domicilioViewArrayList, Collection<Domicilio> domicilioArrayList, Receta receta, Boolean update) {
      for (DomicilioView domV : domicilioViewArrayList) {
         Domicilio dom = new Domicilio();
         if (update) {
            dom.setIdDomicilio(domV.getIdDomicilio());
         }
         dom.setPersonaId(domV.getPersonaId());
         dom.setTipoUsuario(domV.getTipoUsuario());
         dom.setCalle(domV.getCalle());
         dom.setNumeroExterior(domV.getNumeroExterior());
         dom.setNumeroInterior(domV.getNumeroInterior());
         dom.setColonia(domV.getColonia());
         dom.setLocalidad(domV.getLocalidad());
         dom.setReferencia(domV.getReferencia());
         dom.setMunicipio(domV.getMunicipio());
         dom.setEstado(domV.getEstado());
         dom.setPais(domV.getPais());
         dom.setCp(domV.getCp());
         dom.setEmail(domV.getEmail());
         dom.setReceta(receta);
         domicilioArrayList.add(dom);
      }
      logger.debug("converter domicilioView to Entity: {}", domicilioArrayList);
      return domicilioArrayList;
   }

   public Collection<DomicilioView> toView(Collection<Domicilio> domicilioArrayList, Boolean completeConversion) {
      Collection<DomicilioView> domicilioViews = new ArrayList<>();
      for (Domicilio dom : domicilioArrayList) {
         DomicilioView domV = new DomicilioView();
         domV.setIdDomicilio(dom.getIdDomicilio());
         domV.setPersonaId(dom.getPersonaId());
         domV.setTipoUsuario(dom.getTipoUsuario());
         domV.setCalle(dom.getCalle());
         domV.setNumeroExterior(dom.getNumeroExterior());
         domV.setNumeroInterior(dom.getNumeroInterior());
         domV.setColonia(dom.getColonia());
         domV.setLocalidad(dom.getLocalidad());
         domV.setReferencia(dom.getReferencia());
         domV.setMunicipio(dom.getMunicipio());
         domV.setEstado(dom.getEstado());
         domV.setPais(dom.getPais());
         domV.setCp(dom.getCp());
         domV.setEmail(dom.getEmail());
         domicilioViews.add(domV);
      }
      logger.debug("converter domicilio to View: {}", domicilioViews);
      return domicilioViews;
   }

   public Collection<Long> obtenerIDNoExistentesDomicilio(Receta receta, RecetaView recetaView) {
//      IDs de DB
      Collection<Long> ids = new ArrayList<>();
      ids.addAll(
         receta.getDomicilioList().stream()
            .map(pv -> {
               Long id = pv.getIdDomicilio();
               return id;
            }).collect(Collectors.toList())
      );

//      IDs de View
      Collection<Long> idsView = new ArrayList<>();
      idsView.addAll(
         recetaView.getDomicilioViewList().stream()
            .map(pvV -> {
               Long idV = pvV.getIdDomicilio();
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
