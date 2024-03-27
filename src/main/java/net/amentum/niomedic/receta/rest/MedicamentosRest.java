package net.amentum.niomedic.receta.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import net.amentum.common.v2.RestBaseController;
import net.amentum.niomedic.receta.exception.MedicamentosException;
import net.amentum.niomedic.receta.service.MedicamentoService;

@RestController
@RequestMapping("medicamento")
@Slf4j
public class MedicamentosRest extends RestBaseController{
	@Autowired
	MedicamentoService  medicamentoService;

	@RequestMapping(value="/search",method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public String getMedicamentos(@RequestParam(required=true) String textoBusqueda)  throws MedicamentosException{
		log.info("getMedicamentos() - Obteniendo medicamento/sustancio por el texto: {}",textoBusqueda);
		return medicamentoService.getMedicamentos(textoBusqueda);
	}

	@RequestMapping(value="/sustancia/{substanceId}",method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public String getMedicamentoBySustanceId(@PathVariable() Long substanceId)  throws MedicamentosException{
		log.info("getMedicamentoBySustanceId() - Obteniendo medicamentos por substanceId: {}",substanceId);
		return medicamentoService.getMedicamentoBySustanceId(substanceId);
	}

	@RequestMapping(value="/{ProductId}",method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public String getDetailsMedicamento(@PathVariable() Long ProductId, @RequestParam(required=true) Long categoryId ,@RequestParam(required=true) Long pharmaFormId,@RequestParam(required=true) Long divisionId )  throws MedicamentosException{
		log.info("getDetailsMedicamento() - Obteniendo detalles del medicamentos, ProductId: {}, categoryId: {}, pharmaFormId: {} ",ProductId, categoryId, pharmaFormId);
		return medicamentoService.getDetailsMedicamento(ProductId, categoryId, pharmaFormId, divisionId);
	}


}
