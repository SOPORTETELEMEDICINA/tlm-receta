package net.amentum.niomedic.receta.persistence;

import net.amentum.niomedic.receta.model.DetalleReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long>, JpaSpecificationExecutor {
}
