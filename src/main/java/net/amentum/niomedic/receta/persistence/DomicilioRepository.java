package net.amentum.niomedic.receta.persistence;

import net.amentum.niomedic.receta.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long>, JpaSpecificationExecutor {
}
