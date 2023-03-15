package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Empresa;

import java.util.List;

public interface AlumnoEmpresaService {

    List<AlumnoEmpresa> buscarTodo();
    AlumnoEmpresa buscarPorId(int id);
    AlumnoEmpresa guardar(AlumnoEmpresa alumnosEmpresas);
    void borrarPorId(int id);
    AlumnoEmpresa modificar (AlumnoEmpresa alumnosEmpresas) throws NoSeHaEncontradoException;

    List<Alumno> buscarTodosAlumnosPorIdEmpresa(int EmpresaId);
    List<Empresa> buscarTodasEmpresasPorIdAlumno(int AlumnoId);


}
