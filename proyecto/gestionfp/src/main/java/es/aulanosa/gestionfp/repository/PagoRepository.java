package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public interface PagoRepository extends JpaRepository <Pago,Integer> {



}
