package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnosExternos;
import es.aulanosa.gestionfp.service.AlumnosExternosServiceImp;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Optional;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
class TestAlumnosExternosV1 {

	@Autowired
	private AlumnosExternosServiceImp service;

	@Test
	@Order(1)
	void guardar(){
		AlumnosExternos alumnosExternos = new AlumnosExternos();
		alumnosExternos.setCv('a');
		alumnosExternos.setNombre("nose");
		alumnosExternos.setEmail("a");
		alumnosExternos.setFin(new Timestamp(new GregorianCalendar(2022, 3, 23).getTimeInMillis()));
		alumnosExternos.setInicio(new Timestamp(new GregorianCalendar(2022, 3, 23).getTimeInMillis()));
		alumnosExternos.setConvenio('a');
		alumnosExternos.setEspecialidad("s");
		alumnosExternos.setIdCurso(1);
		alumnosExternos.setHorario('a');
		alumnosExternos.setTelefono("1");
		alumnosExternos.setEvaluacion('a');
		alumnosExternos.setTipo('a');
		alumnosExternos.setTitulacion("a");
		alumnosExternos.setUniversidad("a");

		var i = service.guardar(alumnosExternos);

		System.out.println(i);
	}
	@Test
	@Order(3)
	void eliminar(){
		service.eliminar(2);
	}
	@Test
	@Order(2)
	void modificar() throws NoSeHaEncontradoException {
		Optional<AlumnosExternos> alumnosExternos = service.listarPorId(2);

		if(alumnosExternos.isPresent()){
			alumnosExternos.get().setNombre("Paco");
			AlumnosExternos alumnosExternos1 = service.modificar(alumnosExternos.get());

			System.out.println("Alumno modificado a: " + alumnosExternos1);
		}else{
			throw new NoSeHaEncontradoException("No se ha encontrado el alumno especificado");
		}
	}

}
