package net.amentum.niomedic.receta.medicamentos;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.amentum.niomedic.receta.ConfigurationTest;


public class MedicamentosSuiteTest extends ConfigurationTest{
	@Test
	public void guetMeciamentos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/medicamento/search?textoBusqueda=aspi")
				//.param("textoBusqueda", "aspi")
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
}
