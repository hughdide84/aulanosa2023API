package es.aulanosa.gestionfp.service;

import es.aulanosa.gestionfp.model.EntregableNota;
import es.aulanosa.gestionfp.repository.EntregableNotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntregablesNotaServiceImp implements EntregableNotaService {

    @Autowired
    private EntregableNotasRepository repository;

    @Override
    @Transactional
    //Insertar EntregableNotas
    public EntregableNota insertarEntregablesNotas(EntregableNota entregableNotas) {
        return repository.save(entregableNotas);
    }
    @Override
    @Transactional(readOnly = true)
    //Consultar EntregableNotas por id
    public Optional<EntregableNota> buscarEntregablesNotasPorId(Integer id) {
        return repository.findById(id);
    }
    @Override
    @Transactional
    //Modificar EntregableNotas
    public EntregableNota modificarEntregablesNotas(EntregableNota entregableNotas) {
        if (repository.findById(entregableNotas.getId()) == null) {
            return repository.save(entregableNotas);
        } else {
            return null;
        }
    }
    @Override
    @Transactional
    //Eliminar EntregableNotas
    public void eliminarEntregablesNotas(EntregableNota entregableNotas) {
        repository.delete(entregableNotas);
    }
    @Override
    @Transactional(readOnly = true)
    //Consultar todas las EntregableNotas
    public List<EntregableNota> buscarEntregablesNotas() {
        return repository.findAll();
    }




}
