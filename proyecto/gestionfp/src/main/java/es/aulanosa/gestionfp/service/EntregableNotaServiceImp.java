package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.EntregableNota;
import es.aulanosa.gestionfp.repository.EntregableNotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntregableNotaServiceImp implements EntregableNotaService {

    @Autowired
    private EntregableNotasRepository repository;

    @Override
    @Transactional
    //Insertar EntregableNotas
    public EntregableNota insertarEntregableNota(EntregableNota entregableNota) {
        return repository.save(entregableNota);
    }
    @Override
    @Transactional(readOnly = true)
    //Consultar EntregableNotas por id
    public Optional<EntregableNota> buscarEntregableNotaPorId(Integer id) {
        return repository.findById(id);
    }
    @Override
    @Transactional
    //Modificar EntregableNotas
    public EntregableNota modificarEntregableNota(EntregableNota entregableNota) {
        if (repository.findById(entregableNota.getId()) == null) {
            return repository.save(entregableNota);
        } else {
            return null;
        }
    }
    @Override
    @Transactional
    //Eliminar EntregableNotas
    public void eliminarEntregableNota(EntregableNota entregableNota) {
        repository.delete(entregableNota);
    }
    @Override
    @Transactional(readOnly = true)
    //Consultar todas las EntregableNotas
    public List<EntregableNota> buscarEntregableNota() {
        return repository.findAll();
    }




}
