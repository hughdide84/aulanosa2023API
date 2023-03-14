package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnoExterno;
import es.aulanosa.gestionfp.service.AlumnoExternoServiceImp;
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
class TestAlumnoExternoV1 {

	@Autowired
	private AlumnoExternoServiceImp service;

	@Test
	@Order(1)
	void guardar(){
		AlumnoExterno alumnosExternos = new AlumnoExterno();
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
		System.out.println("Alumno externo eliminado");
	}
	@Test
	@Order(2)
	void modificar() throws NoSeHaEncontradoException {
		Optional<AlumnoExterno> alumnosExternos = service.listarPorId(2);

		if(alumnosExternos.isPresent()){
			alumnosExternos.get().setNombre("Paco");
			AlumnoExterno alumnosExternos1 = service.modificar(alumnosExternos.get());

			System.out.println("Alumno modificado a: " + alumnosExternos1);
		}else{
			throw new NoSeHaEncontradoException("No se ha encontrado el alumno especificado");
		}
	}

}
