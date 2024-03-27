package net.amentum.niomedic.receta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.validator.constraints.Email;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

//import javax.validation.constraints.Email;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "domicilio")
public class Domicilio implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idDomicilio;
   private UUID personaId;
   private Boolean tipoUsuario;
   @Size(max=250)
   private String calle;
   @Size(max=6)
   private String numeroExterior;
   @Size(max=6)
   private String numeroInterior;
   @Size(max=150)
   private String colonia;
   @Size(max=250)
   private String localidad;
   @Size(max=500)
   private String referencia;
   @Size(max=250)
   private String municipio;
   @Size(max=100)
   private String estado;
   @Size(max=100)
   private String pais;
   @Size(max=10)
   private String cp;
//   @Email(message = "Email no valido")
   private String email;
   //relaciones

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "receta_id", referencedColumnName = "id_receta")
   private Receta receta;

   @Override
   public String toString() {
      return "Domicilio{" +
         "idDomicilio=" + idDomicilio +
         ", personaId=" + personaId +
         ", tipoUsuario=" + tipoUsuario +
         ", calle='" + calle + '\'' +
         ", numeroExterior='" + numeroExterior + '\'' +
         ", numeroInterior='" + numeroInterior + '\'' +
         ", colonia='" + colonia + '\'' +
         ", localidad='" + localidad + '\'' +
         ", referencia='" + referencia + '\'' +
         ", municipio='" + municipio + '\'' +
         ", estado='" + estado + '\'' +
         ", pais='" + pais + '\'' +
         ", cp='" + cp + '\'' +
         ", email='" + email + '\'' +
         '}';
   }
}
