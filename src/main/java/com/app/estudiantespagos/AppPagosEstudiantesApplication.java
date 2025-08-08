package com.app.estudiantespagos;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.estudiantespagos.entities.Estudiante;
import com.app.estudiantespagos.entities.Pago;
import com.app.estudiantespagos.enums.PagoStatus;
import com.app.estudiantespagos.enums.TypePago;
import com.app.estudiantespagos.repository.EstudianteRepository;
import com.app.estudiantespagos.repository.PagoRepository;

@SpringBootApplication
public class AppPagosEstudiantesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppPagosEstudiantesApplication.class, args);
	}

	/* @Bean
	CommandLineRunner commandLineRunner(EstudianteRepository estudianteRepository, PagoRepository pagoRepository) {
		return args -> {
			estudianteRepository.save(Estudiante.builder()
				.id(UUID.randomUUID().toString())
				.nombre("Manuel")
				.apellido("Lopez")
				.codigo("123456")
				.progamaId("LTA1")
				.foto("https://via.placeholder.com/150")
				.build());
			
			estudianteRepository.save(Estudiante.builder()
				.id(UUID.randomUUID().toString())
				.nombre("Juan")
				.apellido("Marquez")
				.codigo("654321")
				.progamaId("LTA2")
				.foto("https://via.placeholder.com/150")
				.build());

			TypePago tiposPago[] = TypePago.values();
			Random random = new Random();
			estudianteRepository.findAll().forEach(estudiante -> {
				for (int i = 0; i < 10; i++) {
					int index = random.nextInt(tiposPago.length);
					Pago pago = Pago.builder()
						.cantidad(random.nextDouble() * 100)
						.type(tiposPago[index])
						.status(PagoStatus.PENDIENTE)
						.fecha(LocalDate.now())
						.estudiante(estudiante)
						.build();
					pagoRepository.save(pago);
				}
			});
		};
	} */
}
