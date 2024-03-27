package net.amentum.niomedic.receta.converter;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.amentum.niomedic.receta.model.DetalleEstudio;
import net.amentum.niomedic.receta.model.Estudio;
import net.amentum.niomedic.receta.views.DetalleEstudioView;
import net.amentum.niomedic.receta.views.EstudioView;



@Component
@Slf4j
public class EstudioConverter {

	public Estudio toEntity(EstudioView view, Estudio entity, Boolean update) {
		log.info("toView() - Conversión de modelo vista a entidad");
		entity.setIdEstudio(view.getIdEstudio());
		entity.setIdMedico(view.getIdMedico());
		entity.setNombreMedico(view.getNombreMedico());
		entity.setCedulaMedico(view.getCedulaMedico());
		entity.setUniversidadMedico(view.getUniversidadMedico());
		entity.setEspecialidadMedico(view.getEspecialidadMedico());
		entity.setIdPaciente(view.getIdPaciente());
		entity.setNombrePaciente(view.getNombrePaciente());
		entity.setCorreoPaciente(view.getCorreoPaciente());
		entity.setEdadPaciente(view.getEdadPaciente());
		entity.setSexoPaciente(view.getSexoPaciente());
		entity.setCurpPaciente(view.getCurpPaciente());
		entity.setFolio(view.getFolio());
		entity.setNumeroExpediente(view.getNumeroExpediente());
		entity.setFechaCreacion(view.getFechaCreacion());
		entity.setIdConsulta(view.getIdConsulta());
		entity.setActivo(view.getActivo());
		//conversion de los detalles
		entity.setDetallesEstudioList(toEntityDetalle(view.getDetallesEstudioList(),new HashSet<DetalleEstudio>(),entity));
		return entity;
	}



	public EstudioView toView(Estudio entity) {
		log.info("toView() - Conversión Estudio, de modelo entidad a vista");
		EstudioView view = new EstudioView();
		view.setIdEstudio(entity.getIdEstudio());
		view.setIdMedico(entity.getIdMedico());
		view.setNombreMedico(entity.getNombreMedico());
		view.setCedulaMedico(entity.getCedulaMedico());
		view.setUniversidadMedico(entity.getUniversidadMedico());
		view.setEspecialidadMedico(entity.getEspecialidadMedico());
		view.setIdPaciente(entity.getIdPaciente());
		view.setNombrePaciente(entity.getNombrePaciente());
		view.setCorreoPaciente(entity.getCorreoPaciente());
		view.setEdadPaciente(entity.getEdadPaciente());
		view.setSexoPaciente(entity.getSexoPaciente());
		view.setCurpPaciente(entity.getCurpPaciente());
		view.setFolio(entity.getFolio());
		view.setNumeroExpediente(entity.getNumeroExpediente());
		view.setFechaCreacion(entity.getFechaCreacion());
		view.setIdConsulta(entity.getIdConsulta());
		view.setActivo(entity.getActivo());
		//conversion de los detalles
		view.setDetallesEstudioList(toViewDetalle(entity.getDetallesEstudioList()));
		return view;
	}


	private Set<DetalleEstudio> toEntityDetalle(Set<DetalleEstudioView> view, Set<DetalleEstudio> entity,Estudio estudio) {
		log.info("toView() - Conversión Estudio, de modelo vista a entidad");
		view.forEach(dView ->{
			DetalleEstudio dEntity= new DetalleEstudio();
			dEntity.setIdDetalleEstudio(dView.getIdDetalleEstudio());
			dEntity.setDescripcionEstudio(dView.getDescripcionEstudio());
			dEntity.setTipoEstudio(dView.getTipoEstudio());
			dEntity.setPreparacion(dView.getPreparacion());
			dEntity.setEstudio(estudio);
			entity.add(dEntity);
		});
		return entity;
	}
	private Set<DetalleEstudioView> toViewDetalle(Set<DetalleEstudio> entity) {
		log.info("toView() - Conversión DetalleEstudio, de modelo entidad a vista");
		Set<DetalleEstudioView> view = new HashSet<>();
		entity.forEach(dEntity ->{
			DetalleEstudioView dView= new DetalleEstudioView();
			dView.setIdDetalleEstudio(dEntity.getIdDetalleEstudio());
			dView.setDescripcionEstudio(dEntity.getDescripcionEstudio());
			dView.setTipoEstudio(dEntity.getTipoEstudio());
			dView.setPreparacion(dEntity.getPreparacion());
			dView.setIdEstudio(dEntity.getEstudio().getIdEstudio());
			view.add(dView);
		});
		return view;
	}

	public List<Long> detallesEliminado(Set<DetalleEstudio> entity, Set<DetalleEstudioView> view) {
		log.info("detallesEliminado() - Buscando detalles elimnados en la actualizacion");
		List<Long> idsEliminados = new ArrayList<>();
		List<Long> idsEntity = new ArrayList<>();
		List<Long> idsView = new ArrayList<>();
		entity.forEach(estudio->{
			idsEntity.add(estudio.getIdDetalleEstudio());
		});
		view.forEach(estudioView ->{
			idsView.add(estudioView.getIdDetalleEstudio());
		});
		idsEliminados = idsEntity;
		idsEliminados.removeAll(idsView);
		log.info("detallesEliminado() - ids de los detalles elimnados: {}",idsEliminados);
		return idsEliminados;
	}



}
