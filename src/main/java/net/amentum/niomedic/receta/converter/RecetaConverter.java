package net.amentum.niomedic.receta.converter;

import net.amentum.niomedic.receta.model.Receta;
import net.amentum.niomedic.receta.views.RecetaView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RecetaConverter {
   private Logger logger = LoggerFactory.getLogger(RecetaConverter.class);

   @Autowired
   private DomicilioConverter domicilioConverter;

   @Autowired
   private DetalleRecetaConverter detalleRecetaConverter;

   public Receta toEntity(RecetaView recetaView, Receta receta, Boolean update) {
      receta.setMedicoId(recetaView.getMedicoId());
      receta.setNombreMedico(recetaView.getNombreMedico());
      receta.setCedulaMedico(recetaView.getCedulaMedico());
      receta.setUniversidadMedico(recetaView.getUniversidadMedico());
      receta.setEspecialidadMedico(recetaView.getEspecialidadMedico());
      receta.setPacienteId(recetaView.getPacienteId());
      receta.setNombrePaciente(recetaView.getNombrePaciente());
      receta.setEdadPaciente(recetaView.getEdadPaciente());
      receta.setSexoPaciente(recetaView.getSexoPaciente());
      receta.setCurpPaciente(recetaView.getCurpPaciente());
      receta.setDiagnosticoPaciente(recetaView.getDiagnosticoPaciente());
      receta.setNumeroFolio(recetaView.getNumeroFolio());
      receta.setNumeroExpediente(recetaView.getNumeroExpediente());
      receta.setCuidadosGenerales(recetaView.getCuidadosGenerales());
      receta.setFechaCreacion((update) ? receta.getFechaCreacion() : new Date());
      receta.setSello(recetaView.getSello());
      receta.setCadenaOriginal(recetaView.getCadenaOriginal());
      receta.setActivo(recetaView.getActivo());
      receta.setConsultaId(recetaView.getConsultaId());
//      String datosBusqueda = recetaView.getNombreMedico() + " " + recetaView.getNombrePaciente() + " " + recetaView.getEspecialidadMedico();
//      receta.setDatosBusqueda(datosBusqueda);

//      CUIDAR EL USO DEL  BOOLEAN  "update" CON ESE CONTROLAMOS  LOS CREATE O UPDATES!!!

      if (recetaView.getDomicilioViewList() != null && !recetaView.getDomicilioViewList().isEmpty()) {
         receta.setDomicilioList(domicilioConverter.toEntity(
            recetaView.getDomicilioViewList(),
            receta.getDomicilioList(),
            receta,
            update
         ));
      }

      if (recetaView.getDetalleRecetaViewList() != null && !recetaView.getDetalleRecetaViewList().isEmpty()) {
         receta.setDetalleRecetaList(detalleRecetaConverter.toEntity(
            recetaView.getDetalleRecetaViewList(),
            receta.getDetalleRecetaList(),
            receta,
            update
         ));
      }

      logger.debug("convertir recetaView to Entity: {}",receta);
      return receta;
   }

   public RecetaView toView(Receta receta, Boolean completeConversion){
      RecetaView recetaView = new RecetaView();
      recetaView.setIdReceta(receta.getIdReceta());
      recetaView.setMedicoId(receta.getMedicoId());
      recetaView.setNombreMedico(receta.getNombreMedico());
      recetaView.setCedulaMedico(receta.getCedulaMedico());
      recetaView.setUniversidadMedico(receta.getUniversidadMedico());
      recetaView.setEspecialidadMedico(receta.getEspecialidadMedico());
      recetaView.setPacienteId(receta.getPacienteId());
      recetaView.setNombrePaciente(receta.getNombrePaciente());
      recetaView.setEdadPaciente(receta.getEdadPaciente());
      recetaView.setSexoPaciente(receta.getSexoPaciente());
      recetaView.setCurpPaciente(receta.getCurpPaciente());
      recetaView.setDiagnosticoPaciente(receta.getDiagnosticoPaciente());
      recetaView.setNumeroFolio(receta.getNumeroFolio());
      recetaView.setNumeroExpediente(receta.getNumeroExpediente());
      recetaView.setCuidadosGenerales(receta.getCuidadosGenerales());
      recetaView.setFechaCreacion(receta.getFechaCreacion());
      recetaView.setSello(receta.getSello());
      recetaView.setCadenaOriginal(receta.getCadenaOriginal());
      recetaView.setActivo(receta.getActivo());
      recetaView.setConsultaId(receta.getConsultaId());

      if(completeConversion){
         if(receta.getDomicilioList()!=null && !receta.getDomicilioList().isEmpty()){
            recetaView.setDomicilioViewList(domicilioConverter.toView(receta.getDomicilioList(),true));
         }
         if(receta.getDetalleRecetaList()!=null && !receta.getDetalleRecetaList().isEmpty()){
            recetaView.setDetalleRecetaViewList(detalleRecetaConverter.toView(receta.getDetalleRecetaList(),true));
         }
      }

      logger.debug("convertir receta to View: {}", recetaView);
      return recetaView;
   }

}
