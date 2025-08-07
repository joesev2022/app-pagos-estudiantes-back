package com.app.estudiantespagos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.estudiantespagos.entities.Pago;
import com.app.estudiantespagos.enums.PagoStatus;
import com.app.estudiantespagos.enums.TypePago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
  
  List<Pago> findByEstudianteCodigo(String codigo);

  List<Pago> findByStatus(PagoStatus status);

  List<Pago> findByType(TypePago type);

}
