package net.amentum.niomedic.receta.persistence;

import net.amentum.niomedic.receta.model.Estudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface EstudioRepository extends JpaRepository<Estudio, UUID>, JpaSpecificationExecutor<Estudio> {
   Estudio findByIdConsulta(@NotNull Long idConsulta);

   List<Estudio> findByIdConsultaAndActivo(@NotNull Long idConsulta, @NotNull Boolean activo);

   Estudio findByFolio(@NotNull Long folio);
}
