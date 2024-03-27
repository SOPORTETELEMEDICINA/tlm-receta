package net.amentum.niomedic.receta.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalle_estudio")
public class DetalleEstudio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5859130103298193118L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle_estudio")
	Long idDetalleEstudio;
	@Size(max=100, message="Maximo 100 caracteres.")
	@NotEmpty(message="No puede ser nulo o vacío.")
	String descripcionEstudio;
	@Size(max=100, message="Maximo 100 caracteres.")
	@NotEmpty(message="No puede ser nulo o vacío.")
	String tipoEstudio;
	@Column(columnDefinition="TEXT")
	String preparacion;
	//relaciones
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="id_estudio", referencedColumnName = "id_estudio",nullable=false, updatable=false)
	private Estudio estudio;
	@Override
	public String toString() {
		return "DetalleEstudio {idDetalleEstudio=" + idDetalleEstudio + ", descripcionEstudio=" + descripcionEstudio
				+ ", tipoEstudio=" + tipoEstudio + ", preparacion=" + preparacion + "}";
	} 
	
	
}
