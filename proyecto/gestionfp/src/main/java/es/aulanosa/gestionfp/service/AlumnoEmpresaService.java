package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.dto.AlumnoEmpresaDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Empresa;

import java.util.List;

public interface AlumnoEmpresaService {

    List<AlumnoEmpresa> buscarTodoAlumnoEmpresa();
    AlumnoEmpresa buscarPorIdAlumnoEmpresa(int id);
    AlumnoEmpresa guardarAlumnoEmpresa(AlumnoEmpresa alumnosEmpresas);
    void borrarPorIdAlumnoEmpresa(int id);
    AlumnoEmpresa modificarAlumnoEmpresa (AlumnoEmpresa alumnosEmpresas) throws NoSeHaEncontradoException;

    List<Alumno> buscarTodosAlumnosPorIdEmpresa(int EmpresaId);
    List<Empresa> buscarTodasEmpresasPorIdAlumno(int AlumnoId);

    List<AlumnoEmpresa> buscarAlumnosEmpresa(int idCurso, int idEstudio);
    List<AlumnoEmpresa> buscarEmpresasAlumnos(int idCurso, int idEstudio);
}
