package com.app.estudiantespagos.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.estudiantespagos.entities.Estudiante;
import com.app.estudiantespagos.entities.Pago;
import com.app.estudiantespagos.enums.PagoStatus;
import com.app.estudiantespagos.enums.TypePago;
import com.app.estudiantespagos.repository.EstudianteRepository;
import com.app.estudiantespagos.repository.PagoRepository;

@Service
@Transactional
public class PagoService {

  @Autowired
  private PagoRepository pagoRepository;
  @Autowired
  private EstudianteRepository estudianteRepository;

  public Pago savePago(MultipartFile file, double cantidad, TypePago type, LocalDate fecha, String codigoEstudiante) throws IOException {

    Path folderPath = Path.of("uploads");

    String fileName = file.getOriginalFilename();

    String filePath = folderPath + "/" + fileName;

    if(!Files.exists(folderPath)) {
      Files.createDirectories(folderPath);
    }

    try {
      Files.deleteIfExists(Path.of(filePath));
      Files.copy(file.getInputStream(), Path.of(filePath));
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    Estudiante estudiante = estudianteRepository.findByCodigo(codigoEstudiante);

    Pago pago = Pago.builder()
      .cantidad(cantidad)
      .type(type)
      .fecha(fecha)
      .file(filePath)
      .estudiante(estudiante)
      .build();    
    pagoRepository.save(pago);

    return pago;
  }

  public byte[] getFileById(Long id) throws IOException {
    Pago pago = pagoRepository.findById(id).get();
    File file = new File(pago.getFile());
    return Files.readAllBytes(file.toPath());
  }

  public Pago updatePagoByStatus(Long id, PagoStatus status) {
    Pago pago = pagoRepository.findById(id).get();
    pago.setStatus(status);
    return pagoRepository.save(pago);
  }
}
