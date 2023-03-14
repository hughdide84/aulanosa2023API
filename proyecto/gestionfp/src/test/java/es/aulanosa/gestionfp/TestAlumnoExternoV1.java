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
import java.util.List;
import java.util.Optional;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
class TestAlumnoExternoV1 {

	@Autowired
	private AlumnoExternoServiceImp service;

	@Test
	@Order(1)
	void insertarAlumnoExterno(){
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
	@Order(2)
	void listarTodoAlumnoExterno(){
		List<AlumnoExterno> lista = service.listarTodo();

		for (AlumnoExterno alumno :
				lista) {
			System.out.println(alumno);
		}
	}
	@Test
	@Order(3)
	void listarAlumnoExterno(){
		try{
			Optional<AlumnoExterno> alumno = service.listarPorId(1);
		}catch (NoSeHaEncontradoException e){
			System.out.println(e);
		}
	}
	@Test
	@Order(4)
	void modificarAlumnoExterno() throws NoSeHaEncontradoException {
		Optional<AlumnoExterno> alumnosExternos = service.listarPorId(2);

		if(alumnosExternos.isPresent()){
			alumnosExternos.get().setNombre("Paco");
			AlumnoExterno alumnosExternos1 = service.modificar(alumnosExternos.get());

			System.out.println("Alumno modificado a: " + alumnosExternos1);
		}else{
			throw new NoSeHaEncontradoException("No se ha encontrado el alumno especificado");
		}
	}
	@Test
	@Order(5)
	void eliminarAlumnoExterno(){
		int id = 14;

		try {
			service.eliminar(id);
			System.out.println("Se ha eliminado el registro especificado de la base de datos");
		}catch (NoSeHaEncontradoException e){
			System.out.println(e);
		}

	}
}
