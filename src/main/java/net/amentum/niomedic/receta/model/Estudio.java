package net.amentum.niomedic.receta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudio")
public class Estudio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7614452693738176726L;
	@Id
	@Column(name = "id_estudio")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID idEstudio;
	@NotNull(message="No puede ser nulo.")
	@Column(updatable=false)
	private UUID idMedico;
	@Size(max=100, message="Máximo 100 caracteres.")
	@NotEmpty(message="No puede ser nulo o vacío.")
	@Column(updatable=false)
	private String nombreMedico;
	@Size(max=20, message="Máximo 20 caracteres.")
	@Column(updatable=false)
	private String cedulaMedico;
	@Size(max=100, message="Máximo 100 caracteres.")
	private String universidadMedico;
	@Size(max=100, message="Máximo 150 caracteres.")
	@NotEmpty(message="No peude ser nulo o vacío")
	private String especialidadMedico;
	@NotNull(message="no puede ser nulo.")
	private UUID idPaciente;
	@Size(max=100, message="Máximo 100 caracteres.")
	@NotEmpty(message="No puede ser nulo o vacío.")
	@Column(updatable=false)
	private String nombrePaciente;
	@Email(message="No contiene un formato correcto de corro.")
	private String correoPaciente;
	private Integer edadPaciente;
	private String sexoPaciente;
	@Size(max=25, message="Máximo 25 caracteres.")
	@NotEmpty(message="No puede ser nulo o vacío.")
	private String curpPaciente;
	@Column(name = "folio", columnDefinition = "bigserial")
	@Generated(GenerationTime.INSERT)
	private Long folio;
	@NotNull(message="No puede ser nulo.")
	private Long numeroExpediente;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false)
	private Date fechaCreacion;
	@NotNull(message="No puede ser nulo.")
	@Column(updatable=false)
	private Long idConsulta;
	//usado solo para el borrado logico
	@NotNull(message="No puede ser nulo.")
	private Boolean activo;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "estudio")
	private Set<DetalleEstudio> detallesEstudioList= new HashSet<DetalleEstudio>(); 

	@Override
	public int hashCode() {
		return Objects.hash(idEstudio);
	}

	@Override
	public String toString() {
		return "Estudio {idEstudio=" + idEstudio + ", idMedico=" + idMedico + ", nombreMedico=" + nombreMedico
				+ ", cedulaMedico=" + cedulaMedico + ", universidadMedico=" + universidadMedico
				+ ", especialidadMedico=" + especialidadMedico + ", idPaciente=" + idPaciente + ", nombrePaciente="
				+ nombrePaciente + ", correoPaciente=" + correoPaciente + ", edadPaciente=" + edadPaciente
				+ ", sexoPaciente=" + sexoPaciente + ", curpPaciente=" + curpPaciente + ", folio=" + folio
				+ ", numeroExpediente=" + numeroExpediente + ", fechaCreacion=" + fechaCreacion + ", idConsulta="
				+ idConsulta + ", activo=" + activo + "}";
	}



}
