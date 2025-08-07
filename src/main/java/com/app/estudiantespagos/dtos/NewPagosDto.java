package com.app.estudiantespagos.dtos;

import java.time.LocalDate;

import com.app.estudiantespagos.enums.TypePago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPagosDto {
  

  private double cantidad;
  private TypePago type;
  private LocalDate fecha;
  private String codigoEstudiante;
}
