package net.amentum.niomedic.receta.medicamentos;

import java.net.HttpURLConnection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import lombok.extern.slf4j.Slf4j;
import net.amentum.niomedic.receta.service.impl.MedicamentoServiceImpl;
@Slf4j
public class MedicamentosExceptionTest {
	@Mock
	MedicamentoServiceImpl MedicamentoSerivce;
	
	@Mock
	HttpURLConnection conn;

	private MedicamentoServiceImpl  medicamentoServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		medicamentoServiceImpl =new MedicamentoServiceImpl();
	}
	@Test
	public void getMedicamentosCustomException() throws Exception{
		Mockito.when(conn.getResponseCode()).thenReturn(500);
		
		medicamentoServiceImpl.getMedicamentos("aspi");
		log.debug("entro");
	}
}
