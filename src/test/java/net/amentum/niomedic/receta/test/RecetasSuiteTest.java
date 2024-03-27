package net.amentum.niomedic.receta.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.amentum.niomedic.receta.ConfigurationTest;
import net.amentum.niomedic.receta.views.DetalleRecetaView;
import net.amentum.niomedic.receta.views.DomicilioView;
import net.amentum.niomedic.receta.views.RecetaView;

public class RecetasSuiteTest extends ConfigurationTest {
	Random r = new Random();
	@Test
	public void createReceta() throws Exception{
		Integer id= r.nextInt(9999);
		Long consultaId = id.longValue();
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
		recetaView.setSexoPaciente("Masculino");
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
		//	      domicilioView.setIdDomicilio(dom.getIdDomicilio());
		domicilioView.setPersonaId(uid);
		domicilioView.setTipoUsuario(Boolean.TRUE);
		domicilioView.setCalle("setCalle");
		domicilioView.setNumeroExterior("a1");
		domicilioView.setNumeroInterior("a2");
		domicilioView.setColonia("setColonia");
		domicilioView.setLocalidad("setLocalidad");
		domicilioView.setReferencia("setReferencia");
		domicilioView.setMunicipio("setMunicipio");
		domicilioView.setEstado("setEstado");
		domicilioView.setPais("setPais");
		domicilioView.setCp("setCp");
		domicilioView.setEmail("setEmail");

		DomicilioView domicilioView2 = new DomicilioView();
		//	      domicilioView.setIdDomicilio(dom.getIdDomicilio());
		domicilioView2.setPersonaId(uid);
		domicilioView2.setTipoUsuario(Boolean.TRUE);
		domicilioView2.setCalle("setCalle2");
		domicilioView2.setNumeroExterior("a2");
		domicilioView2.setNumeroInterior("a1");
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
		//	      detalleRecetaView.setIdDetalleReceta(deRe.getIdDetalleReceta());
		detalleRecetaView.setDenominacionGenerica("setDenominacionGenerica");
		detalleRecetaView.setDenominacionDistintiva("setDenominacionDistintiva");
		detalleRecetaView.setCantidad(99);
		detalleRecetaView.setUnidad("setUnidad");
		detalleRecetaView.setDosis("setDosis");
		detalleRecetaView.setViaAdministracion("setViaAdministracion");
		detalleRecetaView.setIndicacionesMedicas("setIndicacionesMedicas");
		detalleRecetaView.setPresentacion("1 Caja, 1 Frasco gotero, 5 ml, 5 mg/ml");
		Collection<Map<String, Object>> substancias= new ArrayList<>();
		Map<String, Object> una= new HashMap<String,Object>();
		Map<String, Object> dos= new HashMap<String,Object>();
		una.put("Active", true);
		una.put("ActiveSubstanceId", 4099);
		una.put("Description" , "KETOROLACO TROMETAMINA");
		una.put("EnglishDescription","Ketorolac tromethamine");
		una.put("Enunciative", false);
		dos.put("Active", true);
		dos.put("ActiveSubstanceId", 4483);
		dos.put("Description" , "ÁCIDO ACETILSALICÍLICO");
		dos.put("EnglishDescription","Aspirin");
		dos.put("Enunciative", false);
		substancias.add(una);
		substancias.add(dos);
		detalleRecetaView.setSubstancias(substancias);



		DetalleRecetaView detalleRecetaView2 = new DetalleRecetaView();
		//	      detalleRecetaView2.setIdDetalleReceta(deRe.getIdDetalleReceta());
		detalleRecetaView2.setDenominacionGenerica("setDenominacionGenerica2");
		detalleRecetaView2.setDenominacionDistintiva("setDenominacionDistintiva2");
		detalleRecetaView2.setCantidad(99);
		detalleRecetaView2.setUnidad("setUnidad2");
		detalleRecetaView2.setDosis("setDosis");
		detalleRecetaView2.setViaAdministracion("setViaAdministracion2");
		detalleRecetaView2.setIndicacionesMedicas("setIndicacionesMedicas2");
		detalleRecetaView2.setPresentacion("1 Caja, 1 Frasco gotero, 5 ml, 5 mg/ml");
		detalleRecetaView2.setSubstancias(substancias);
		
		ArrayList<DetalleRecetaView> detalleRecetaViewArrayList = new ArrayList<>();
		detalleRecetaViewArrayList.add(detalleRecetaView);
		detalleRecetaViewArrayList.add(detalleRecetaView2);
		recetaView.setDetalleRecetaViewList(detalleRecetaViewArrayList);

		System.out.println(MAPPER.writeValueAsString(recetaView));

		mockMvc.perform(MockMvcRequestBuilders.post("/receta")
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(recetaView)))
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andDo(MockMvcResultHandlers.print());

	}




	@Test
	public void createRecetaBadRequest() throws Exception{
		Integer id= r.nextInt(9999);
		Long consultaId = id.longValue();
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
		recetaView.setSexoPaciente("Masculino");
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
		//	      domicilioView.setIdDomicilio(dom.getIdDomicilio());
		domicilioView.setPersonaId(uid);
		domicilioView.setTipoUsuario(Boolean.TRUE);
		domicilioView.setCalle("setCalle");
		domicilioView.setNumeroExterior("a1");
		domicilioView.setNumeroInterior("a2");
		domicilioView.setColonia("setColonia");
		domicilioView.setLocalidad("setLocalidad");
		domicilioView.setReferencia("setReferencia");
		domicilioView.setMunicipio("setMunicipio");
		domicilioView.setEstado("setEstado");
		domicilioView.setPais("setPais");
		domicilioView.setCp("setCp");
		domicilioView.setEmail("setEmail");

		DomicilioView domicilioView2 = new DomicilioView();
		//	      domicilioView.setIdDomicilio(dom.getIdDomicilio());
		domicilioView2.setPersonaId(uid);
		domicilioView2.setTipoUsuario(Boolean.TRUE);
		domicilioView2.setCalle("setCalle2");
		domicilioView2.setNumeroExterior("a2");
		domicilioView2.setNumeroInterior("a1");
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
		//	      detalleRecetaView.setIdDetalleReceta(deRe.getIdDetalleReceta());
		detalleRecetaView.setDenominacionGenerica("setDenominacionGenerica");
		detalleRecetaView.setDenominacionDistintiva("setDenominacionDistintiva");
		detalleRecetaView.setCantidad(99);
		detalleRecetaView.setUnidad("setUnidad");
		detalleRecetaView.setDosis("setDosis");
		detalleRecetaView.setViaAdministracion("setViaAdministracion");
		detalleRecetaView.setIndicacionesMedicas("setIndicacionesMedicas");
		detalleRecetaView.setPresentacion("1 Caja, 1 Frasco gotero, 5 ml, 5 mg/ml");
		Collection<Map<String, Object>> substancias= new ArrayList<>();
		Map<String, Object> una= new HashMap<String,Object>();
		Map<String, Object> dos= new HashMap<String,Object>();
		una.put("Active", true);
		una.put("ActiveSubstanceId", 4099);
		una.put("Description" , "KETOROLACO TROMETAMINA");
		una.put("EnglishDescription","Ketorolac tromethamine");
		una.put("Enunciative", false);
		dos.put("Active", true);
		dos.put("ActiveSubstanceId", 4483);
		dos.put("Description" , "ÁCIDO ACETILSALICÍLICO");
		dos.put("EnglishDescription","Aspirin");
		dos.put("Enunciative", false);
		substancias.add(una);
		substancias.add(dos);
		//detalleRecetaView.setSubstancias(substancias);

		DetalleRecetaView detalleRecetaView2 = new DetalleRecetaView();
		//	      detalleRecetaView2.setIdDetalleReceta(deRe.getIdDetalleReceta());
		detalleRecetaView2.setDenominacionGenerica("setDenominacionGenerica2");
		detalleRecetaView2.setDenominacionDistintiva("setDenominacionDistintiva2");
		detalleRecetaView2.setCantidad(99);
		detalleRecetaView2.setUnidad("setUnidad2");
		detalleRecetaView2.setDosis("setDosis");

		detalleRecetaView2.setViaAdministracion("setViaAdministracion2");
		detalleRecetaView2.setIndicacionesMedicas("setIndicacionesMedicas2");
		detalleRecetaView2.setPresentacion("1 Caja, 1 Frasco gotero, 5 ml, 5 mg/ml");
		//detalleRecetaView2.setSubstancias(substancias);

		ArrayList<DetalleRecetaView> detalleRecetaViewArrayList = new ArrayList<>();
		detalleRecetaViewArrayList.add(detalleRecetaView);
		detalleRecetaViewArrayList.add(detalleRecetaView2);
		recetaView.setDetalleRecetaViewList(detalleRecetaViewArrayList);

		System.out.println(MAPPER.writeValueAsString(recetaView));

		mockMvc.perform(MockMvcRequestBuilders.post("/receta")
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(recetaView)))
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andDo(MockMvcResultHandlers.print());

	}


	@Test
	public void detallesRecetaBarRequest() throws Exception{
		Integer id= r.nextInt(9999);
		Long consultaId = id.longValue();
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
		recetaView.setSexoPaciente("Masculino");
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
		domicilioView.setPersonaId(uid);
		domicilioView.setTipoUsuario(Boolean.TRUE);
		domicilioView.setCalle("setCalle");
		domicilioView.setNumeroExterior("a1");
		domicilioView.setNumeroInterior("a2");
		domicilioView.setColonia("setColonia");
		domicilioView.setLocalidad("setLocalidad");
		domicilioView.setReferencia("setReferencia");
		domicilioView.setMunicipio("setMunicipio");
		domicilioView.setEstado("setEstado");
		domicilioView.setPais("setPais");
		domicilioView.setCp("setCp");
		domicilioView.setEmail("setEmail");

		DomicilioView domicilioView2 = new DomicilioView();
		domicilioView2.setPersonaId(uid);
		domicilioView2.setTipoUsuario(Boolean.TRUE);
		domicilioView2.setCalle("setCalle2");
		domicilioView2.setNumeroExterior("a2");
		domicilioView2.setNumeroInterior("a1");
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
		detalleRecetaView.setDenominacionGenerica("setDenominacionGenerica");
		detalleRecetaView.setDenominacionDistintiva("setDenominacionDistintiva");
		detalleRecetaView.setCantidad(99);
		detalleRecetaView.setUnidad("setUnidad");
		detalleRecetaView.setDosis("setDosis");
		detalleRecetaView.setViaAdministracion("setViaAdministracion");
		detalleRecetaView.setIndicacionesMedicas("setIndicacionesMedicas");
		detalleRecetaView.setPresentacion("1 Caja, 1 Frasco gotero, 5 ml, 5 mg/ml");
		Collection<Map<String, Object>> substancias= new ArrayList<>();
		Map<String, Object> una= new HashMap<String,Object>();
		Map<String, Object> dos= new HashMap<String,Object>();
		una.put("Active", true);
		una.put("ActiveSubstanceId", 4099);
		una.put("Description" , "KETOROLACO TROMETAMINA");
		una.put("EnglishDescription","Ketorolac tromethamine");
		una.put("Enunciative", false);
		dos.put("Active", true);
		dos.put("ActiveSubstanceId", 4483);
		dos.put("Description" , "ÁCIDO ACETILSALICÍLICO");
		dos.put("EnglishDescription","Aspirin");
		dos.put("Enunciative", false);
		substancias.add(una);
		substancias.add(dos);
		detalleRecetaView.setSubstancias(substancias);


		DetalleRecetaView detalleRecetaView2 = new DetalleRecetaView();
		detalleRecetaView2.setDenominacionGenerica("setDenominacionGenerica2");
		detalleRecetaView2.setDenominacionDistintiva("setDenominacionDistintiva2");
		detalleRecetaView2.setCantidad(99);
		detalleRecetaView2.setUnidad("setUnidad2");
		detalleRecetaView2.setDosis("setDosis");
		detalleRecetaView2.setViaAdministracion("setViaAdministracion2");
		detalleRecetaView2.setIndicacionesMedicas("setIndicacionesMedicas2");
		detalleRecetaView2.setPresentacion("1 Caja, 1 Frasco gotero, 5 ml, 5 mg/ml");
		detalleRecetaView2.setSubstancias(substancias);

		ArrayList<DetalleRecetaView> detalleRecetaViewArrayList = new ArrayList<>();
		detalleRecetaViewArrayList.add(detalleRecetaView);
		detalleRecetaViewArrayList.add(detalleRecetaView2);
		recetaView.setDetalleRecetaViewList(detalleRecetaViewArrayList);

		System.out.println(MAPPER.writeValueAsString(recetaView));

		mockMvc.perform(MockMvcRequestBuilders.post("/receta")
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(recetaView)))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError())
		.andDo(MockMvcResultHandlers.print());

	}


}
