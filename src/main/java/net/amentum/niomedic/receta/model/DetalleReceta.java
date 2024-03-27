package net.amentum.niomedic.receta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalle_receta")
@TypeDef(
		name = "json", typeClass = JsonBinaryType.class
		)
public class DetalleReceta implements Serializable {
	
	private static final long serialVersionUID = 1507579249497992164L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalleReceta;
	@Size(max = 300)
	private String denominacionGenerica;
	@Size(max = 300)
	private String denominacionDistintiva;
	private Integer cantidad;
	@Size(max = 100)
	private String unidad;
	private String dosis;
	@Size(max = 7)
	private String frecuencia;
	@Size(max = 50)
	private String periodo;
	@Size(max = 50)
	private String viaAdministracion;
	@Column(columnDefinition = "TEXT")
	private String indicacionesMedicas;
	
	@Size(max=150)
	private String presentacion;
	
	@Type( type = "json" )
	@Column(columnDefinition = "json")
	//@NotNull(message="no puede ser nulo, por favor ingrese las substancias")
	private JsonNode substancias ;
	//relaciones

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "receta_id", referencedColumnName = "id_receta")
	private Receta receta;


	@Override
	public String toString() {
		return "DetalleReceta{" +
				"idDetalleReceta=" + idDetalleReceta +
				", denominacionGenerica='" + denominacionGenerica + '\'' +
				", denominacionDistintiva='" + denominacionDistintiva + '\'' +
				", cantidad=" + cantidad +
				", unidad='" + unidad + '\'' +
				", dosis='" + dosis + '\'' +
				", frecuencia='" + frecuencia + '\'' +
				", periodo='" + periodo + '\'' +
				", viaAdministracion='" + viaAdministracion + '\'' +
				", indicacionesMedicas='" + indicacionesMedicas + '\'' +
				'}';
	}
}
