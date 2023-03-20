package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Matricula;
import es.aulanosa.gestionfp.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface PagoRepository extends JpaRepository <Pago,Integer> {

    public List<Pago> findAllByIdMatricula (Integer id);

}
