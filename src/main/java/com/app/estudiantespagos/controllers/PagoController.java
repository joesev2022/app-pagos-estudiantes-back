package com.app.estudiantespagos.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.estudiantespagos.entities.Estudiante;
import com.app.estudiantespagos.entities.Pago;
import com.app.estudiantespagos.enums.PagoStatus;
import com.app.estudiantespagos.enums.TypePago;
import com.app.estudiantespagos.repository.EstudianteRepository;
import com.app.estudiantespagos.repository.PagoRepository;
import com.app.estudiantespagos.services.PagoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PagoController {
  @Autowired
  private EstudianteRepository estudianteRepository;
  @Autowired
  private PagoRepository pagoRepository;
  @Autowired
  private PagoService pagoService;

  @GetMapping("/estudiantes")
  public List<Estudiante> getEstudiantes() {
    return estudianteRepository.findAll();
  }

  @GetMapping("/estudiante/{codigo}")
  public Estudiante getEstudianteByCodigo(String codigo) {
    return estudianteRepository.findByCodigo(codigo);
  }

  @GetMapping("/estudiantes/programa")
  public List<Estudiante> getEstudiantesByPrograma(@RequestParam String programaId) {
    return estudianteRepository.findByProgamaId(programaId);
  }

  @GetMapping("/pagos")
  public List<Pago> getPagos() {
    return pagoRepository.findAll();
  }

  @GetMapping("/pagos/{id}")
  public Pago getPagoById(@PathVariable Long id) {
    return pagoRepository.findById(id).get();
  }

  @GetMapping("/pagos/estudiante/{codigoEstudiante}")
  public List<Pago> getPagosByCodigoEstudiante(@PathVariable String codigoEstudiante) {
    return pagoRepository.findByEstudianteCodigo(codigoEstudiante);
  }

  @GetMapping("/pagos/status/{status}")
  public List<Pago> getPagosByStatus(@RequestParam PagoStatus status) {
    return pagoRepository.findByStatus(status);
  }

  public List<Pago> getPagosByType(@RequestParam TypePago type) {
    return pagoRepository.findByType(type);
  }

  @PutMapping("/pagos/{id}/status/{status}")
  public Pago updatePagoByStatus(@PathVariable Long id, @RequestParam PagoStatus status) {
    return pagoService.updatePagoByStatus(id, status);
  }

  @PostMapping(path = "/pagos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Pago savePago(MultipartFile file, double cantidad, TypePago type, LocalDate fecha, String codigoEstudiante) throws IOException {
    return pagoService.savePago(file, cantidad, type, fecha, codigoEstudiante);
  }

  @GetMapping(value = "/pagos/{id}/file", produces = MediaType.APPLICATION_PDF_VALUE)
  public byte[] getFileById(@PathVariable Long id) throws IOException {
    return pagoService.getFileById(id);
  }
}
