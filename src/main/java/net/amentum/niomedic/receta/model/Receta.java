package net.amentum.niomedic.receta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receta")
public class Receta implements Serializable {
   @Id
   @Column(name = "id_receta")
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
   private UUID idReceta;
   private UUID medicoId;
   @Size(max = 60)
   private String nombreMedico;
   @Size(max = 15)
   private String cedulaMedico;
   @Size(max = 100)
   private String universidadMedico;
   @Size(max = 150)
   private String especialidadMedico;
   private UUID pacienteId;
   @Size(max = 60)
   private String nombrePaciente;
   private Integer edadPaciente;
   @Size(max = 10)
   private String sexoPaciente;
   @Size(max = 20)
   private String curpPaciente;
   @Column(columnDefinition = "TEXT")
   private String diagnosticoPaciente;
   @Column(name = "numero_folio", columnDefinition = "serial")
   @Generated(GenerationTime.INSERT)
   private Long numeroFolio;
   private Long numeroExpediente; // asi se maneja en MS paciente
   @Column(columnDefinition = "TEXT")
   private String  cuidadosGenerales;
   @Temporal(TemporalType.TIMESTAMP)
   private Date fechaCreacion;
   @Size(max = 100)
   private String sello;
   @Size(max = 100)
   private String cadenaOriginal;
//   private String datosBusqueda;
   private Boolean activo;
   @Column(unique = true, nullable = false)
   private Long consultaId;


   //relaciones

   @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "receta")
   private Collection<DetalleReceta> detalleRecetaList = new ArrayList<>();

   @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "receta")
   private Collection<Domicilio> domicilioList = new ArrayList<>();


   @Override
   public String toString() {
      return "Receta{" +
         "idReceta=" + idReceta +
         ", medicoId=" + medicoId +
         ", nombreMedico='" + nombreMedico + '\'' +
         ", cedulaMedico='" + cedulaMedico + '\'' +
         ", universidadMedico='" + universidadMedico + '\'' +
         ", especialidadMedico='" + especialidadMedico + '\'' +
         ", pacienteId=" + pacienteId +
         ", nombrePaciente='" + nombrePaciente + '\'' +
         ", edadPaciente=" + edadPaciente +
         ", sexoPaciente='" + sexoPaciente + '\'' +
         ", curpPaciente='" + curpPaciente + '\'' +
         ", diagnosticoPaciente='" + diagnosticoPaciente + '\'' +
         ", numeroFolio=" + numeroFolio +
         ", numeroExpediente=" + numeroExpediente +
         ", cuidadosGenerales='" + cuidadosGenerales + '\'' +
         ", fechaCreacion=" + fechaCreacion +
         ", sello='" + sello + '\'' +
         ", cadenaOriginal='" + cadenaOriginal + '\'' +
         '}';
   }
}
