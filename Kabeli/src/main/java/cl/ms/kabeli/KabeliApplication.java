package cl.ms.kabeli;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cl.ms.kabeli.dto.Telefono;
import cl.ms.kabeli.dto.Usuario;
import cl.ms.kabeli.repositories.UsuarioRepo;

@SpringBootApplication
public class KabeliApplication implements CommandLineRunner{

	@Autowired
	private UsuarioRepo usuario;
	
	public static void main(String[] args) {
		SpringApplication.run(KabeliApplication.class, args);
	}
	
	

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setEmail("test@test.com");
		usuario.setName("Test User");
		usuario.setPassword("testPassword");
		
		List<Telefono> fonos = new ArrayList<>();
		Telefono fono = new Telefono();
		
		fono.setCitycode(22);
		fono.setCountrycode(56);
		fono.setNumber(222222);
		
		fonos.add(fono);
		usuario.setPhones(fonos);
		
		Telefono fono2 = new Telefono();
		
		fono2.setCitycode(22);
		fono2.setCountrycode(56);
		fono2.setNumber(222222);
		
		fonos.add(fono2);
		usuario.setPhones(fonos);
		
		this.usuario.save(usuario);
		
		
		
	}

}
