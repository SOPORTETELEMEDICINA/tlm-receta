package net.amentum.niomedic.receta;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.amentum.niomedic.receta.model.Receta;
import net.amentum.niomedic.receta.views.DetalleRecetaView;
import net.amentum.niomedic.receta.views.DomicilioView;
import net.amentum.niomedic.receta.views.RecetaView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {NioRecetaApplication.class})
public class RecetaTest {
   private final MediaType jsonType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf-8"));
   public MockMvc mockMvc;
   @Autowired
   private WebApplicationContext webApplicationContext;
   @Autowired
   private ObjectMapper objectMapper;

   @Before
   public void setup() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }

   @Test
   public void createReceta() throws Exception{
      Long consultaId = 1L;
      RecetaView recetaView = new RecetaView();
      UUID uid = UUID.fromString("d3d97ede-15ff-47e7-9411-e92feef7958b");
      recetaView.setMedicoId(uid);
      recetaView.setNombreMedico("setNombreMedico");
      recetaView.setCedulaMedico("setCedulaMedico");
      recetaView.setUniversidadMedico("setUniversidadMedico");
      recetaView.setEspecialidadMedico("setEspecialidadMedico");
      recetaView.setPacienteId(uid);
      recetaView.setNombrePaciente("setNombrePaciente");
      recetaView.setEdadPaciente(99);
      recetaView.setSexoPaciente("setSexoPaciente");
      recetaView.setCurpPaciente("setCurpPaciente");
      recetaView.setDiagnosticoPaciente("setDiagnosticoPaciente");
      recetaView.setNumeroFolio(1L);
      recetaView.setNumeroExpediente(1L);
      recetaView.setCuidadosGenerales("setCuidadosGenerales");
      recetaView.setFechaCreacion(new Date());
      recetaView.setSello("setSello");
      recetaView.setCadenaOriginal("setCadenaOriginal");
      recetaView.setActivo(Boolean.TRUE);
      recetaView.setConsultaId(consultaId);

      DomicilioView domicilioView = new DomicilioView();
//      domicilioView.setIdDomicilio(dom.getIdDomicilio());
      domicilioView.setPersonaId(uid);
      domicilioView.setTipoUsuario(Boolean.TRUE);
      domicilioView.setCalle("setCalle");
      domicilioView.setNumeroExterior("setNumeroExterior");
      domicilioView.setNumeroInterior("setNumeroInterior");
      domicilioView.setColonia("setColonia");
      domicilioView.setLocalidad("setLocalidad");
      domicilioView.setReferencia("setReferencia");
      domicilioView.setMunicipio("setMunicipio");
      domicilioView.setEstado("setEstado");
      domicilioView.setPais("setPais");
      domicilioView.setCp("setCp");
      domicilioView.setEmail("setEmail");

      DomicilioView domicilioView2 = new DomicilioView();
//      domicilioView.setIdDomicilio(dom.getIdDomicilio());
      domicilioView2.setPersonaId(uid);
      domicilioView2.setTipoUsuario(Boolean.TRUE);
      domicilioView2.setCalle("setCalle2");
      domicilioView2.setNumeroExterior("setNumeroExterior2");
      domicilioView2.setNumeroInterior("setNumeroInterior2");
      domicilioView2.setColonia("setColonia2");
      domicilioView2.setLocalidad("setLocalidad2");
      domicilioView2.setReferencia("setReferencia2");
      domicilioView2.setMunicipio("setMunicipio2");
      domicilioView2.setEstado("setEstado2");
      domicilioView2.setPais("setPais2");
      domicilioView2.setCp("setCp2");
      domicilioView2.setEmail("setEmail2");

      ArrayList<DomicilioView> domicilioViewArrayList = new ArrayList<>();
      domicilioViewArrayList.add(domicilioView);
      domicilioViewArrayList.add(domicilioView2);
      recetaView.setDomicilioViewList(domicilioViewArrayList);
      
      DetalleRecetaView detalleRecetaView = new DetalleRecetaView();
//      detalleRecetaView.setIdDetalleReceta(deRe.getIdDetalleReceta());
      detalleRecetaView.setDenominacionGenerica("setDenominacionGenerica");
      detalleRecetaView.setDenominacionDistintiva("setDenominacionDistintiva");
      detalleRecetaView.setCantidad(99);
      detalleRecetaView.setUnidad("setUnidad");
      detalleRecetaView.setDosis("setDosis");
      detalleRecetaView.setViaAdministracion("setViaAdministracion");
      detalleRecetaView.setIndicacionesMedicas("setIndicacionesMedicas");

      DetalleRecetaView detalleRecetaView2 = new DetalleRecetaView();
//      detalleRecetaView2.setIdDetalleReceta(deRe.getIdDetalleReceta());
      detalleRecetaView2.setDenominacionGenerica("setDenominacionGenerica2");
      detalleRecetaView2.setDenominacionDistintiva("setDenominacionDistintiva2");
      detalleRecetaView2.setCantidad(99);
      detalleRecetaView2.setUnidad("setUnidad2");
      detalleRecetaView2.setDosis("setDosis");
      detalleRecetaView2.setViaAdministracion("setViaAdministracion2");
      detalleRecetaView2.setIndicacionesMedicas("setIndicacionesMedicas2");



      ArrayList<DetalleRecetaView> detalleRecetaViewArrayList = new ArrayList<>();
      detalleRecetaViewArrayList.add(detalleRecetaView);
      detalleRecetaViewArrayList.add(detalleRecetaView2);
      recetaView.setDetalleRecetaViewList(detalleRecetaViewArrayList);



      System.out.println(objectMapper.writeValueAsString(recetaView));

      mockMvc.perform(MockMvcRequestBuilders.post("/receta")
         .contentType(jsonType)
         .content(objectMapper.writeValueAsString(recetaView)))
         .andExpect(MockMvcResultMatchers.status().isCreated())
         .andDo(MockMvcResultHandlers.print());
      
   }

   @Test
   public void updateReceta() throws Exception{
      String idReceta = "cb7a064d-52fa-4dc3-900c-bfc36adf4d2a";

      RecetaView recetaView = new RecetaView();
      UUID uid = UUID.fromString("d3d97ede-15ff-47e7-9411-e92feef7958b");
      recetaView.setMedicoId(uid);
      recetaView.setNombreMedico("updNombreMedico");
      recetaView.setCedulaMedico("updCedulaMedico");
      recetaView.setUniversidadMedico("updUniversidadMedico");
      recetaView.setEspecialidadMedico("updEspecialidadMedico");
      recetaView.setPacienteId(uid);
      recetaView.setNombrePaciente("updNombrePaciente");
      recetaView.setEdadPaciente(99);
      recetaView.setSexoPaciente("updSexoPaciente");
      recetaView.setCurpPaciente("updCurpPaciente");
      recetaView.setDiagnosticoPaciente("updDiagnosticoPaciente");
      recetaView.setNumeroFolio(1L);
      recetaView.setNumeroExpediente(1L);
      recetaView.setCuidadosGenerales("updCuidadosGenerales");
      recetaView.setFechaCreacion(new Date());
      recetaView.setSello("updSello");
      recetaView.setCadenaOriginal("updCadenaOriginal");
      recetaView.setActivo(Boolean.TRUE);

      DomicilioView domicilioView = new DomicilioView();
//      domicilioView.setIdDomicilio(dom.getIdDomicilio());
      domicilioView.setPersonaId(uid);
      domicilioView.setTipoUsuario(Boolean.TRUE);
      domicilioView.setCalle("updCalle");
      domicilioView.setNumeroExterior("updNumeroExterior");
      domicilioView.setNumeroInterior("updNumeroInterior");
      domicilioView.setColonia("updColonia");
      domicilioView.setLocalidad("updLocalidad");
      domicilioView.setReferencia("updReferencia");
      domicilioView.setMunicipio("updMunicipio");
      domicilioView.setEstado("updEstado");
      domicilioView.setPais("updPais");
      domicilioView.setCp("updCp");
      domicilioView.setEmail("updEmail");

      DomicilioView domicilioView2 = new DomicilioView();
//      domicilioView.setIdDomicilio(dom.getIdDomicilio());
      domicilioView2.setPersonaId(uid);
      domicilioView2.setTipoUsuario(Boolean.TRUE);
      domicilioView2.setCalle("updCalle2");
      domicilioView2.setNumeroExterior("updNumeroExterior2");
      domicilioView2.setNumeroInterior("updNumeroInterior2");
      domicilioView2.setColonia("updColonia2");
      domicilioView2.setLocalidad("updLocalidad2");
      domicilioView2.setReferencia("updReferencia2");
      domicilioView2.setMunicipio("updMunicipio2");
      domicilioView2.setEstado("updEstado2");
      domicilioView2.setPais("updPais2");
      domicilioView2.setCp("updCp2");
      domicilioView2.setEmail("updEmail2");

      ArrayList<DomicilioView> domicilioViewArrayList = new ArrayList<>();
      domicilioViewArrayList.add(domicilioView);
      domicilioViewArrayList.add(domicilioView2);
      recetaView.setDomicilioViewList(domicilioViewArrayList);

      DetalleRecetaView detalleRecetaView = new DetalleRecetaView();
//      detalleRecetaView.setIdDetalleReceta(deRe.getIdDetalleReceta());
      detalleRecetaView.setDenominacionGenerica("updDenominacionGenerica");
      detalleRecetaView.setDenominacionDistintiva("updDenominacionDistintiva");
      detalleRecetaView.setCantidad(99);
      detalleRecetaView.setUnidad("updUnidad");
      detalleRecetaView.setDosis("setDosis");
      detalleRecetaView.setViaAdministracion("updViaAdministracion");
      detalleRecetaView.setIndicacionesMedicas("updIndicacionesMedicas");

      DetalleRecetaView detalleRecetaView2 = new DetalleRecetaView();
//      detalleRecetaView2.setIdDetalleReceta(deRe.getIdDetalleReceta());
      detalleRecetaView2.setDenominacionGenerica("updDenominacionGenerica2");
      detalleRecetaView2.setDenominacionDistintiva("updDenominacionDistintiva2");
      detalleRecetaView2.setCantidad(99);
      detalleRecetaView2.setUnidad("updUnidad2");
      detalleRecetaView2.setDosis("setDosis");
      detalleRecetaView2.setViaAdministracion("updViaAdministracion2");
      detalleRecetaView2.setIndicacionesMedicas("updIndicacionesMedicas2");



      ArrayList<DetalleRecetaView> detalleRecetaViewArrayList = new ArrayList<>();
      detalleRecetaViewArrayList.add(detalleRecetaView);
      detalleRecetaViewArrayList.add(detalleRecetaView2);
      recetaView.setDetalleRecetaViewList(detalleRecetaViewArrayList);



      System.out.println(objectMapper.writeValueAsString(recetaView));

//      mockMvc.perform(MockMvcRequestBuilders.put("/receta/"+idReceta)
//         .contentType(jsonType)
//         .content(objectMapper.writeValueAsString(recetaView)))
//         .andExpect(MockMvcResultMatchers.status().isOk())
//         .andDo(MockMvcResultHandlers.print());

   }

   @Test
   public void getDetailsByIdReceta() throws Exception {
      String idReceta = "21771cfe-24aa-41a0-9000-8e46d5a1518c";
      mockMvc.perform(MockMvcRequestBuilders.get("/receta/"+idReceta))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void deleteReceta() throws Exception {
      String idReceta = "21771cfe-24aa-41a0-9000-8e46d5a1518";
      mockMvc.perform(MockMvcRequestBuilders.delete("/receta/"+idReceta))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void getDetailsByConsultaId() throws Exception {
      Long consultaId = 5L;
      mockMvc.perform(MockMvcRequestBuilders.get("/receta/por-consulta/"+consultaId))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());
   }

}
