package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.EntregableNotas;
import es.aulanosa.gestionfp.repository.EntregableNotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntregablesNotasServiceImp implements EntregableNotasService {

    @Autowired
    private EntregableNotasRepository repository;

    @Override
    @Transactional
    //Insertar EntregableNotas
    public EntregableNotas insertarEntregablesNotas(EntregableNotas entregableNotas) {
        return repository.save(entregableNotas);
    }
    @Override
    @Transactional(readOnly = true)
    //Consultar EntregableNotas por id
    public Optional<EntregableNotas> buscarEntregablesNotasPorId(Integer id) {
        return repository.findById(id);
    }
    @Override
    @Transactional
    //Modificar EntregableNotas
    public EntregableNotas modificarEntregablesNotas(EntregableNotas entregableNotas) {
        if (repository.findById(entregableNotas.getId()) == null) {
            return repository.save(entregableNotas);
        } else {
            return null;
        }
    }
    @Override
    @Transactional
    //Eliminar EntregableNotas
    public void eliminarEntregablesNotas(EntregableNotas entregableNotas) {
        repository.delete(entregableNotas);
    }
    @Override
    @Transactional(readOnly = true)
    //Consultar todas las EntregableNotas
    public List<EntregableNotas> buscarEntregablesNotas() {
        return repository.findAll();
    }




}
