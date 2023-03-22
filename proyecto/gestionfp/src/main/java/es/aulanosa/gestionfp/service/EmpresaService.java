package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Empresa;

import java.util.List;

public interface EmpresaService {
    /**
     * Busca una empresa por su ID.
     * @param id El ID de la empresa a buscar.
     * @return La empresa encontrada.
     */
    Empresa findById(int id);
    /**
     * Guarda una empresa.
     * @param empresa La empresa a guardar.
     * @return La empresa guardada.
     */
    Empresa save(Empresa empresa);
    /**
     * Actualiza una empresa existente.
     * @param empresa La empresa a actualizar.
     * @return La empresa actualizada.
     * @throws NoSeHaEncontradoException si la empresa no existe.
     */
    Empresa update(Empresa empresa) throws NoSeHaEncontradoException;
    /**
     * Elimina una empresa por su ID.
     * @param id El ID de la empresa a eliminar.
     */
    void deleteById(int id);
    /**
     * Obtiene una lista de todas las empresas.
     * @return Una lista de todas las empresas.
     */
    List<Empresa> findAll();

    /**
     * Obtiene una lista de empresas filtradas por nombre.
     * @param nombre El nombre a buscar.
     * @return Una lista de empresas que coincidan con el nombre a buscar
     */
    List<Empresa> findAllByNombre(String nombre);
}
