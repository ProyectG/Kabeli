package cl.ms.kabeli.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import cl.ms.kabeli.dto.Usuario;

public interface UsuarioRepo extends CrudRepository<Usuario,UUID>{
	
	public Usuario findByEmailAndPassword(String email, String password);
	public Usuario findByEmail(String email);

}
