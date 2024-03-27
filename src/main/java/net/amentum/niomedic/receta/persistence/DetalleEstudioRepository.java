package net.amentum.niomedic.receta.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import net.amentum.niomedic.receta.model.DetalleEstudio;

public interface DetalleEstudioRepository extends JpaRepository<DetalleEstudio,Long>, JpaSpecificationExecutor<DetalleEstudio> {

}
