package com.app.estudiantespagos.entities;

import java.time.LocalDate;

import com.app.estudiantespagos.enums.PagoStatus;
import com.app.estudiantespagos.enums.TypePago;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;
  
  private LocalDate fecha;
  
  private double cantidad;

  private TypePago type;

  private PagoStatus status;

  private String file;

  @ManyToOne
  private Estudiante estudiante;
}
