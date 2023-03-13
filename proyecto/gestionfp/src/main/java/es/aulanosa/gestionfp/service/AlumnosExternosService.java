package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.AlumnosExternos;

import java.util.List;
import java.util.Optional;

public interface AlumnosExternosService {

    List<AlumnosExternos> listarTodo();


    public void eliminar(Integer id);
    public AlumnosExternos guardar(AlumnosExternos alumnoExterno);
    public AlumnosExternos modificar(AlumnosExternos alumnoExterno);
    public Optional<AlumnosExternos> listarPorId(Integer id);

}