package net.amentum.niomedic.receta.service.impl;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.amentum.niomedic.receta.exception.MedicamentosException;
import net.amentum.niomedic.receta.service.MedicamentoService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.http.HttpStatus;

@Component
@Slf4j
public class MedicamentoServiceImpl implements MedicamentoService {
	private ObjectMapper mapp= new ObjectMapper();

	@Override
	public String getMedicamentos(String textoBusqueda) throws MedicamentosException {
		try {
			BufferedReader  br=null;
			log.info("getMedicamentos() - Consumiendo endpoint de plm");
            // Sre20042020 Inicia los espacios no le gustan a plm así que los cambio por &nbsp;
            String textoLimpio = (textoBusqueda == null) ? "" : textoBusqueda.replaceAll("[ ]", "&nbsp;");
			final String endPoint="http://www.plmconnection.com/plmservices/RestPharmaSearchEngine/RestPharmaSearchEngine.svc/getResultsDetail?code=ZP91XlS0PN8T772GCf50u0H2UPIJ&countryId=11&editionId=211&searchText="+textoLimpio;
            // Sre20042020 Termina
			log.info("getMedicamentos() - {}",endPoint);
			URL url = new URL(endPoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			Integer responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				StringBuilder response = new StringBuilder();
				String currentLine;
				while ((currentLine = br.readLine()) != null) {
					response.append(currentLine);
				}
				br.close();
				Map<String, Object> JsonResponse= mapp.readValue(response.toString(), Map.class);
				String code = (String) JsonResponse.get("Folio");
				String message = (String) JsonResponse.get("Message");	
				log.error("getMedicamentos() - oncurrio un error al consumir el endpoint, mensaje que regresa: {}",message);
				throw new MedicamentosException(HttpStatus.CONFLICT,"PLM responde: "+"Folio: "+code+", Message: "+message );
			}
			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			String body="";
			while ((output = br.readLine()) != null) {
				body = body + output;
			}
			conn.disconnect();
			return body;
		}catch(MedicamentosException me) {
			throw me;
		}catch(Exception e) {
			throw new MedicamentosException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(MedicamentosException.SERVER_ERROR, "obtener"));
		}
	}

	@Override
	public String getMedicamentoBySustanceId(Long substanceId) throws MedicamentosException {
		try {
			BufferedReader  br=null;
			log.info("getMedicamentoBySustanceId() - Consumiendo endpoint de plm");
			final String endPoint="http://www.plmconnection.com/plmservices/RestPharmaSearchEngine/RestPharmaSearchEngine.svc/getDrugsShortDetailBySubstance?code=ZP1L733PUE&countryId=11&editionId=211&substanceId="+substanceId;
			log.info("getMedicamentoBySustanceId() - {}",endPoint);
			URL url = new URL(endPoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {

				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				StringBuilder response = new StringBuilder();
				String currentLine;
				while ((currentLine = br.readLine()) != null) {
					response.append(currentLine);
				}
				br.close();
				Map<String, Object> JsonResponse= mapp.readValue(response.toString(), Map.class);
				String code = (String) JsonResponse.get("Folio");
				String message = (String) JsonResponse.get("Message");	
				log.error("getMedicamentoBySustanceId() - oncurrio un error al consumir el endpoint, mensaje que regresa: {}",message);
				throw new MedicamentosException(HttpStatus.CONFLICT,"PLM responde: "+"Folio: "+code+", Message: "+message );
			}
			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			String body="";
			while ((output = br.readLine()) != null) {
				body = body + output;
			}
			conn.disconnect();
			log.info("getMedicamentos() - respondio");
			return body;
		}catch(MedicamentosException me) {
			throw me;
		}catch(Exception e) {
			throw new MedicamentosException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(MedicamentosException.SERVER_ERROR, "obtener"));
		}
	}

	@Override
	public String getDetailsMedicamento(Long ProductId, Long categoryId, Long pharmaFormId, Long divisionId) throws MedicamentosException{
		try {
			BufferedReader  br=null;
			log.info("getDetailsMedicamento() - Consumiendo endpoint de plm");
			final String endPoint="http://www.plmconnection.com/plmservices/RestPharmaSearchEngine/RestPharmaSearchEngine.svc/getDrugShortInformation?code=ZP1L733PUE&countryId=11&editionId=211&"
					+ "divisionId="+divisionId+"&categoryId="+categoryId+"&pharmaFormId="+pharmaFormId+"&drugId="+ProductId;
			log.info("getDetailsMedicamento() - {}",endPoint);
			URL url = new URL(endPoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			Integer responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				StringBuilder response = new StringBuilder();
				String currentLine;
				while ((currentLine = br.readLine()) != null) {
					response.append(currentLine);
				}
				br.close();
				Map<String, Object> JsonResponse= mapp.readValue(response.toString(), Map.class);
				String code = (String) JsonResponse.get("Folio");
				String message = (String) JsonResponse.get("Message");	
				log.error("getMedicamentos() - oncurrio un error al consumir el endpoint, mensaje que regresa: {}",message);
				throw new MedicamentosException(HttpStatus.CONFLICT,"PLM responde: "+"Folio: "+code+", Message: "+message );
			}
			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			String body="";
			while ((output = br.readLine()) != null) {
				body = body + output;
			}
			conn.disconnect();
			return body;
		}catch(MedicamentosException me) {
			throw me;
		}catch(Exception e) {
			throw new MedicamentosException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(MedicamentosException.SERVER_ERROR, "obtener"));
		}
	}



}
