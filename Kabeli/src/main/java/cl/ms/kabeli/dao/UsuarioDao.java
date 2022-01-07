package cl.ms.kabeli.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cl.ms.kabeli.dto.AddUser;
import cl.ms.kabeli.dto.Login;
import cl.ms.kabeli.dto.Respuesta;
import cl.ms.kabeli.dto.Usuario;
import cl.ms.kabeli.repositories.UsuarioRepo;
import cl.ms.kabeli.utilitarios.CustomException;
import cl.ms.kabeli.utilitarios.JwtUtils;

@Component
public class UsuarioDao {

	@Autowired
	private UsuarioRepo dataUsuario;
	private final JwtUtils JWT = new JwtUtils();
	final Logger LOGGER = LoggerFactory.logger(UsuarioDao.class);

	public ResponseEntity<Object> insertarUsuario(AddUser usuario) {
		Respuesta resp = new Respuesta();
		try {
			usuario.validarClase();
			Usuario busqueda = dataUsuario.findByEmail(usuario.getEmail());
			if (busqueda == null) {
				Usuario user = new Usuario();
				user.setEmail(usuario.getEmail());
				user.setPassword(usuario.getPassword());
				user.setName(usuario.getName());
				user.setCreated(new java.sql.Date(System.currentTimeMillis()));
				user.setPhones(usuario.getPhones());
				dataUsuario.save(user);
				resp.setMensaje("Usuario añadido exitosamente");
			} else {
				resp.setMensaje("Usuario ya existe en bd");
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error ", e);
			resp.setMensaje("Hubo un problema al insertar el usuarios");
		} catch (CustomException f) {
			resp.setMensaje(f.getMessage());
		}
		return ResponseEntity.ok(resp);
	}

	public ResponseEntity<Object> usuarios() {
		try {
			List<Usuario> respuesta = new ArrayList<>();
			dataUsuario.findAll().forEach(respuesta::add);
			for (Usuario res : respuesta) {
				LOGGER.info(respuesta.toString());
				if (res.getToken() != null) {
					LOGGER.info("Validanto token " + JWT.validateJWT(res.getToken()) + " -- " + res.getToken());
					res.setIsactive(JWT.validateJWT(res.getToken()));
				}
			}

			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			LOGGER.error("Error ", e);
			Respuesta resp = new Respuesta();
			resp.setMensaje("Hubo un problema al listar los usuarios.");
			return ResponseEntity.ok(resp);
		}
	}

	public ResponseEntity<Object> usuario(String id) {
		Respuesta resp = new Respuesta();
		resp.setMensaje("Hubo un problema al listar el usuario.");
		try {
			Optional<Usuario> respuesta = dataUsuario.findById(UUID.fromString(id));
			if (respuesta.isPresent()) {
				return ResponseEntity.ok(respuesta);
			} else {
				return ResponseEntity.ok(resp);
			}
		} catch (Exception e) {
			LOGGER.error("Error ", e);
			return ResponseEntity.ok(resp);
		}
	}

	public ResponseEntity<Object> eliminarUsuario(UUID id) {
		Respuesta resp = new Respuesta();
		try {
			dataUsuario.deleteById(id);
			resp.setMensaje("Usuario eliminado exitosamente");
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error ", e);
			resp.setMensaje("Hubo un problema al eliminar el usuario.");
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error ", e);
			resp.setMensaje("Usuario no existente o eliminado anteriormente.");
		}
		return ResponseEntity.ok(resp);
	}

	public ResponseEntity<Object> logear(Login login) {
		Respuesta resp = new Respuesta();
		try {
			Usuario respuesta = dataUsuario.findByEmailAndPassword(login.getUser(), login.getPass());
			if (respuesta != null) {
				String token = JWT.generateJWT(respuesta.getEmail());
				respuesta.setToken(token);
				respuesta.setLastLogin(new java.sql.Date(System.currentTimeMillis()));
				dataUsuario.save(respuesta);
				resp.setMensaje(token);
			} else {
				resp.setMensaje("Usuario / Contraseña incorrectos.");
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error ", e);
			resp.setMensaje("Vuelve a intentarlo mas tarde.");
		}
		return ResponseEntity.ok(resp);
	}

	public ResponseEntity<Object> updateUsuario(String jwt, AddUser usuario) {
		Respuesta resp = new Respuesta();
		try {
			if (JWT.validateJWT(jwt)) {
				Usuario respuesta = dataUsuario.findByEmail(usuario.getEmail());
				if (respuesta != null) {
					respuesta.setPassword(usuario.getPassword());
					respuesta.setName(usuario.getName());
					respuesta.setModified(new java.sql.Date(System.currentTimeMillis()));
					respuesta.setPhones(usuario.getPhones());
					dataUsuario.save(respuesta);
					resp.setMensaje("Usuario Modificado");
				} else {
					resp.setMensaje("Usuario no encontrado.");
				}
			} else {
				resp.setMensaje("Token no valido.");
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error ", e);
			resp.setMensaje("Vuelve a intentarlo mas tarde.");
		}
		return ResponseEntity.ok(resp);
	}
}
