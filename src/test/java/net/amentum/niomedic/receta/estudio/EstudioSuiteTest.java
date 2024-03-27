package net.amentum.niomedic.receta.estudio;


import com.fasterxml.jackson.core.type.TypeReference;
import net.amentum.niomedic.receta.ConfigurationTest;
import net.amentum.niomedic.receta.views.DetalleEstudioView;
import net.amentum.niomedic.receta.views.EstudioView;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class EstudioSuiteTest extends ConfigurationTest {

   @Test
   public void postEstudios400() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.post("/estudio")
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(new EstudioView())))
         .andExpect(MockMvcResultMatchers.status().isBadRequest())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void postEstudios400Eamil() throws Exception {
      EstudioView view = estudioView();
      view.setCorreoPaciente("adalkdjalksjdks");
      //property":"correoPaciente","message":"No contiene un formato correcto de corro."}]}
      mockMvc.perform(MockMvcRequestBuilders.post("/estudio")
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isBadRequest())
         .andDo(MockMvcResultHandlers.print());
      //        .andExpect(jsonPath("idTicketType").exists())
      //        .andExpect(jsonPath("ticketType").value("Prueba");
   }

   @Test
   public void postEstudios400Detalles() throws Exception {
      EstudioView view = estudioView();
      //nulo
      view.setDetallesEstudioList(null);
      mockMvc.perform(MockMvcRequestBuilders.post("/estudio")
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isBadRequest())
         .andDo(MockMvcResultHandlers.print());
      //vacion
      view.setDetallesEstudioList(new HashSet<>());

      mockMvc.perform(MockMvcRequestBuilders.post("/estudio")
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isBadRequest())
         .andDo(MockMvcResultHandlers.print());

      //bean no valido
      Set<DetalleEstudioView> detallesEstudioList = new HashSet<DetalleEstudioView>();
      DetalleEstudioView uno = new DetalleEstudioView();
      uno.setTipoEstudio("RESONANCIA MAGNETICA");
      uno.setPreparacion("Ayuno de 6 hrs. Todo paciente deberá contar con un estudio complementario de Creatinina Sérica no mayor a 30 días. Este estudio se realiza sin costo al adquirir su Resonancia contrastada; es recomendable presentarse para su realización uno o dos días previos a su Resonancia. No ser portador de marcapasos cardiaco. Acudir con ropa cómoda. Es recomendable acudir con un acompañante. Evitar acudir con tarjetas de crédito, sortijas, objetos metálicos y de valor. Se requiere orden médica. \",\n" +
         "\"Estudio\":\"RESONANCIA MAGNETICA");
      detallesEstudioList.add(uno);

      view.setDetallesEstudioList(detallesEstudioList);

      mockMvc.perform(MockMvcRequestBuilders.post("/estudio")
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isBadRequest())
         .andDo(MockMvcResultHandlers.print());


   }


   @Test
   public void postEstudios201() throws Exception {
      EstudioView view = estudioView();
      mockMvc.perform(MockMvcRequestBuilders.post("/estudio")
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isCreated())
         .andDo(MockMvcResultHandlers.print());

      view = estudioView();
      view.setFechaCreacion(null);
      mockMvc.perform(MockMvcRequestBuilders.post("/estudio")
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isCreated())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void putEstudios200() throws Exception {
      EstudioView view = postEstudioTest();
      view.setIdMedico(UUID.randomUUID());
      view.setFechaCreacion(new Date());
      view.setNombrePaciente("Olegario Andrare");
      view.setCorreoPaciente("Ourtado@actualizado.com");
      mockMvc.perform(MockMvcRequestBuilders.put("/estudio/{idEstudio}", view.getIdEstudio())
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());
      //campos nulos
      view.setCorreoPaciente(null);
      view.setUniversidadMedico(null);
      view.setEdadPaciente(null);
      view.setSexoPaciente(null);
      mockMvc.perform(MockMvcRequestBuilders.put("/estudio/{idEstudio}", view.getIdEstudio())
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());

      //nueva infromacion
      view.setCorreoPaciente("Ourtado@actualizado2.com");
      view.setUniversidadMedico("UNAM");
      view.setEspecialidadMedico("ANESTESIOLOGÍA");
      view.setEdadPaciente(40);
      view.setSexoPaciente("IDEFINIDO");
      view.setCurpPaciente("NUEVA654DAS25S4DA");

      mockMvc.perform(MockMvcRequestBuilders.put("/estudio/{idEstudio}", view.getIdEstudio())
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());

      Set<DetalleEstudioView> detallesEstudioList = view.getDetallesEstudioList();
      List<DetalleEstudioView> list = new ArrayList<DetalleEstudioView>(detallesEstudioList);
      detallesEstudioList.remove(list.get(0));

      DetalleEstudioView uno = new DetalleEstudioView();
      uno.setDescripcionEstudio("LAMOTRIGINA");
      uno.setTipoEstudio("LABORATORIO");
      uno.setPreparacion("AYUNO DE 4 HRS. EL TIEMPO OPTIMO PARA LA TOMA DE MUESTRA ES 1 HRA. ANTES DE LA SIGUIENTE DOSIS.");
      detallesEstudioList.add(uno);


      mockMvc.perform(MockMvcRequestBuilders.put("/estudio/{idEstudio}", view.getIdEstudio())
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void putEstudio404() throws Exception {
      EstudioView view = estudioView();
      mockMvc.perform(MockMvcRequestBuilders.put("/estudio/{idEstudio}", UUID.randomUUID())
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isNotFound())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void getEstudio200() throws Exception {
      EstudioView view = postEstudioTest();
      mockMvc.perform(MockMvcRequestBuilders.get("/estudio/{idEstudio}", view.getIdEstudio()))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void getEstudio404() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/estudio/{idEstudio}", UUID.randomUUID()))
         .andExpect(MockMvcResultMatchers.status().isNotFound())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void getAllEstudioByConsultaId() throws Exception {
//	   mvn clean test -Dtest=EstudioSuiteTest#getAllEstudioByConsultaId test
      EstudioView view = postEstudioTest();
      mockMvc.perform(MockMvcRequestBuilders.get("/estudio/por-consulta/{consultaId}", view.getIdConsulta()))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());

      mockMvc.perform(MockMvcRequestBuilders.get("/estudio/por-consulta/{consultaId}?activo=false", view.getIdConsulta()))
         .andExpect(MockMvcResultMatchers.status().isNotFound())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void getAllEstudioByConsultaIdBAD() throws Exception {
//	   mvn clean test -Dtest=EstudioSuiteTest#getAllEstudioByConsultaIdBAD test
      Random r = new Random();
      Long numero = r.nextLong();
      mockMvc.perform(MockMvcRequestBuilders.get("/estudio/por-consulta/{consultaId}", numero))
         .andExpect(MockMvcResultMatchers.status().isNotFound())
         .andDo(MockMvcResultHandlers.print());

      mockMvc.perform(MockMvcRequestBuilders.get("/estudio/por-consulta/{consultaId}?activo=false", numero))
         .andExpect(MockMvcResultMatchers.status().isNotFound())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void deleteEstudio() throws Exception {
//	   mvn clean test -Dtest=EstudioSuiteTest#deleteEstudio test
      EstudioView view = postEstudioTest();

      mockMvc.perform(MockMvcRequestBuilders.delete("/estudio/{idEstudio}", view.getIdEstudio()))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void deleteEstudioBAD() throws Exception {
//	   mvn clean test -Dtest=EstudioSuiteTest#deleteEstudioBAD test

      mockMvc.perform(MockMvcRequestBuilders.delete("/estudio/{idEstudio}", UUID.randomUUID()))
         .andExpect(MockMvcResultMatchers.status().isNotFound())
         .andDo(MockMvcResultHandlers.print());
   }


   @Test
   public void getEstudioByFolio() throws Exception {
//	   mvn clean test -Dtest=EstudioSuiteTest#getEstudioByFolio test
      EstudioView view = postEstudioTest();
      mockMvc.perform(MockMvcRequestBuilders.get("/estudio/por-folio/{folio}", view.getFolio()))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());

   }

   @Test
   public void getEstudioByFolioBAD() throws Exception {
//	   mvn clean test -Dtest=EstudioSuiteTest#getEstudioByFolioBAD test
      Random r = new Random();
      Long numero = r.nextLong();
      mockMvc.perform(MockMvcRequestBuilders.get("/estudio/por-folio/{folio}", numero))
         .andExpect(MockMvcResultMatchers.status().isNotFound())
         .andDo(MockMvcResultHandlers.print());

   }
   

   private EstudioView postEstudioTest() throws Exception {
      EstudioView view = estudioView();
      String response = mockMvc.perform(MockMvcRequestBuilders.post("/estudio")
         .contentType(JSON)
         .content(MAPPER.writeValueAsString(view)))
         .andExpect(MockMvcResultMatchers.status().isCreated())
         .andReturn().getResponse().getContentAsString();
      view = MAPPER.readValue(response, new TypeReference<EstudioView>() {
      });
      return view;
   }

   private EstudioView estudioView() {
      Random r = new Random();
      EstudioView view = new EstudioView();
      view.setIdMedico(UUID.randomUUID());
      view.setNombreMedico("Carlos Marquines");
      view.setCedulaMedico("98a7dfa9df9");
      view.setUniversidadMedico("UDG");
      view.setEspecialidadMedico("Medico General");
      view.setIdPaciente(UUID.randomUUID());
      view.setNombrePaciente("Roberto Urtado");
      view.setCorreoPaciente("rurtado@gmail.com");
      view.setEdadPaciente(28);
      view.setSexoPaciente("Hombre");
      view.setCurpPaciente("JHKJ63546KJHKÑ56K");
      view.setFolio(4l);
      Integer numero = r.nextInt(999999999);
      view.setNumeroExpediente(numero.longValue());
      view.setFechaCreacion(new Date());
      numero = r.nextInt(999999999);
      view.setIdConsulta(numero.longValue());

      //detalle
      Set<DetalleEstudioView> detallesEstudioList = new HashSet<DetalleEstudioView>();
      DetalleEstudioView uno = new DetalleEstudioView();
      uno.setDescripcionEstudio("RM MANO SIMPLE");
      uno.setTipoEstudio("RESONANCIA MAGNETICA");
      uno.setPreparacion("Ayuno de 6 hrs. Todo paciente deberá contar con un estudio complementario de Creatinina Sérica no mayor a 30 días. Este estudio se realiza sin costo al adquirir su Resonancia contrastada; es recomendable presentarse para su realización uno o dos días previos a su Resonancia. No ser portador de marcapasos cardiaco. Acudir con ropa cómoda. Es recomendable acudir con un acompañante. Evitar acudir con tarjetas de crédito, sortijas, objetos metálicos y de valor. Se requiere orden médica. \",\n" +
         "\"Estudio\":\"RESONANCIA MAGNETICA");
      detallesEstudioList.add(uno);
      DetalleEstudioView dos = new DetalleEstudioView();
      dos.setDescripcionEstudio("MUÑECA AP Y OBLICUA");
      dos.setTipoEstudio("RAYOS X");
      dos.setPreparacion(null);
      detallesEstudioList.add(dos);
      view.setDetallesEstudioList(detallesEstudioList);
      return view;
   }

}
