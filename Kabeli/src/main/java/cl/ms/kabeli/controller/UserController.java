package cl.ms.kabeli.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.ms.kabeli.dao.UsuarioDao;
import cl.ms.kabeli.dto.AddUser;
import cl.ms.kabeli.dto.Login;

@RestController
@RequestMapping("/api/v1/kabeli/user/")
public class UserController extends UsuarioDao {
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ResponseEntity<Object> listarUsuarios()
	{
		return usuarios();
	}
	
	@RequestMapping(value="add", method=RequestMethod.PUT)
	public ResponseEntity<Object> agregarUsuario(@RequestBody AddUser usuario)
	{
		return insertarUsuario(usuario);
	}
	
	@RequestMapping(value="delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> eliminar(@PathVariable("id") UUID id)
	{
		return eliminarUsuario(id);
	}
	
	@RequestMapping(value="list/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> listarUsuario(@PathVariable("id") String id)
	{
		return usuario(id);
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Login login)
	{
		return logear(login);
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public ResponseEntity<Object> update(@RequestHeader("Authorization") String jwt,@RequestBody AddUser usuario)
	{
		return updateUsuario(jwt,usuario);
	}
	

}
