package net.amentum.niomedic.receta.persistence;

import net.amentum.niomedic.receta.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public interface RecetaRepository extends JpaRepository<Receta, UUID>, JpaSpecificationExecutor {
   Receta findByConsultaId(@NotNull Long consultaId);
}
