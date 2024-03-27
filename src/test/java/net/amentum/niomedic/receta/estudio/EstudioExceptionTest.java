package net.amentum.niomedic.receta.estudio;

import net.amentum.niomedic.receta.converter.EstudioConverter;
import net.amentum.niomedic.receta.exception.EstudioException;
import net.amentum.niomedic.receta.model.DetalleEstudio;
import net.amentum.niomedic.receta.model.Estudio;
import net.amentum.niomedic.receta.persistence.EstudioRepository;
import net.amentum.niomedic.receta.service.impl.EstudioServiceImpl;
import net.amentum.niomedic.receta.views.DetalleEstudioView;
import net.amentum.niomedic.receta.views.EstudioView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class EstudioExceptionTest {
   private EstudioServiceImpl estudioServiceImpl;
   @Mock
   private EstudioConverter estudioConverter;
   @Mock
   private EstudioRepository estudioRepository;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      estudioServiceImpl = new EstudioServiceImpl();
      estudioServiceImpl.setEstudioCoverter(estudioConverter);
      estudioServiceImpl.setEstudioRepository(estudioRepository);
   }

   @Test(expected = EstudioException.class)
   public void postEstudioException() throws Exception {
      Mockito.when(estudioConverter.toEntity(Matchers.any(EstudioView.class), Matchers.any(Estudio.class), Matchers.anyBoolean()))
         .thenThrow(new IllegalArgumentException());

      estudioServiceImpl.createEstudio(new EstudioView());
   }


   @Test(expected = EstudioException.class)
   public void postEstudioConstraint() throws Exception {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();
      Estudio entity = new Estudio();
      Set<ConstraintViolation<Estudio>> violations = validator.validate(entity);

      Mockito.when(estudioConverter.toEntity(Matchers.any(EstudioView.class), Matchers.any(Estudio.class), Matchers.anyBoolean()))
         .thenReturn(new Estudio());

      Mockito.when(estudioRepository.saveAndFlush(Matchers.any(Estudio.class)))
         .thenThrow(new ConstraintViolationException("Error de validacion", violations));
      estudioServiceImpl.createEstudio(new EstudioView());
   }


   @Test(expected = EstudioException.class)
   public void postEstudioDataIntegrity() throws Exception {
      Mockito.when(estudioConverter.toEntity(Matchers.any(EstudioView.class), Matchers.any(Estudio.class), Matchers.anyBoolean()))
         .thenReturn(new Estudio());

      Mockito.when(estudioRepository.saveAndFlush(Matchers.any(Estudio.class)))
         .thenThrow(new DataIntegrityViolationException("Error de Integridad", new PSQLException("org.postgresql.util.PSQLException: ERROR:", new PSQLState("324351453"), new Throwable(" Detail: ID duplicado"))));
      estudioServiceImpl.createEstudio(new EstudioView());
   }


   @Test(expected = EstudioException.class)
   public void putEstudioException() throws Exception {
      Mockito.when(estudioRepository.findOne(Matchers.any(UUID.class)))
         .thenReturn(new Estudio());
      Mockito.when(estudioConverter.detallesEliminado(Matchers.anySetOf(DetalleEstudio.class), Matchers.anySetOf(DetalleEstudioView.class)))
         .thenThrow(new IllegalArgumentException());
      estudioServiceImpl.updateEstudio(new EstudioView());
   }


   @Test(expected = EstudioException.class)
   public void putEstudioConstraint() throws Exception {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();
      Estudio entity = new Estudio();
      Set<ConstraintViolation<Estudio>> violations = validator.validate(entity);

      Mockito.when(estudioRepository.findOne(Matchers.any(UUID.class)))
         .thenReturn(new Estudio());
      Mockito.when(estudioConverter.detallesEliminado(Matchers.anySetOf(DetalleEstudio.class), Matchers.anySetOf(DetalleEstudioView.class)))
         .thenReturn(new ArrayList<Long>());

      Mockito.when(estudioConverter.toEntity(Matchers.any(EstudioView.class), Matchers.any(Estudio.class), Matchers.anyBoolean()))
         .thenReturn(new Estudio());

      Mockito.when(estudioRepository.saveAndFlush(Matchers.any(Estudio.class)))
         .thenThrow(new ConstraintViolationException("Error de validacion", violations));
      estudioServiceImpl.updateEstudio(new EstudioView());
   }


   @Test(expected = EstudioException.class)
   public void putEstudioDataIntegrity() throws Exception {
      Mockito.when(estudioRepository.findOne(Matchers.any(UUID.class)))
         .thenReturn(new Estudio());
      Mockito.when(estudioConverter.detallesEliminado(Matchers.anySetOf(DetalleEstudio.class), Matchers.anySetOf(DetalleEstudioView.class)))
         .thenReturn(new ArrayList<Long>());

      Mockito.when(estudioConverter.toEntity(Matchers.any(EstudioView.class), Matchers.any(Estudio.class), Matchers.anyBoolean()))
         .thenReturn(new Estudio());

      Mockito.when(estudioRepository.saveAndFlush(Matchers.any(Estudio.class)))
         .thenThrow(new DataIntegrityViolationException("Error de Integridad", new PSQLException("org.postgresql.util.PSQLException: ERROR:", new PSQLState("324351453"), new Throwable(" Detail: ID duplicado"))));
      estudioServiceImpl.updateEstudio(new EstudioView());
   }

   @Test(expected = EstudioException.class)
   public void getEstudioException() throws Exception {
      Mockito.when(estudioRepository.findOne(Matchers.any(UUID.class)))
         .thenThrow(new DataIntegrityViolationException("Error de Integridad", new PSQLException("org.postgresql.util.PSQLException: ERROR:", new PSQLState("324351453"), new Throwable(" Detail: ID duplicado"))));

      estudioServiceImpl.getEstudioById(UUID.randomUUID());
   }

   /////////////////////////////////////////////////////////////////////////////////////////////////////GET-por ID consulta
   @Test(expected = EstudioException.class)
   public void getAllEstudioByConsultaIdEstudioException() throws Exception {
//      mvn test -Dtest=EstudioExceptionTest#getAllEstudioByConsultaIdEstudioException test
      Mockito.when(estudioRepository.findByIdConsultaAndActivo(Matchers.anyLong(),Matchers.anyBoolean()))
         .thenReturn(null)
         .thenThrow(new NullPointerException());

      estudioServiceImpl.getAllEstudioByConsultaId(Matchers.anyLong(),Matchers.anyBoolean());
   }


   @Test(expected = EstudioException.class)
   public void getAllEstudioByConsultaIdException() throws Exception {
//      mvn test -Dtest=EstudioExceptionTest#getAllEstudioByConsultaIdException test
      Mockito.when(estudioRepository.findByIdConsultaAndActivo(Matchers.anyLong(),Matchers.anyBoolean()))
         .thenThrow(new NullPointerException());

      estudioServiceImpl.getAllEstudioByConsultaId(Matchers.anyLong(),Matchers.anyBoolean());
   }

   /////////////////////////////////////////////////////////////////////////////////////////////////////DELETE
   @Test(expected = EstudioException.class)
   public void deleteEstudioException() throws Exception{
//      mvn test -Dtest=EstudioExceptionTest#deleteEstudioException test
      Mockito.when(estudioRepository.findOne(Matchers.any(UUID.class)))
         .thenThrow(new NullPointerException());

      estudioServiceImpl.deleteEstudio(Matchers.any(UUID.class));
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////por FOLIO
   @Test(expected = EstudioException.class)
   public void getEstudioByFolioException() throws Exception {
      //      mvn test -Dtest=EstudioExceptionTest#getEstudioByFolioException test
      Mockito.when(estudioRepository.findByFolio(Matchers.anyLong()))
         .thenThrow(new NullPointerException());

      estudioServiceImpl.getEstudioByFolio(Matchers.anyLong());
   }



}
