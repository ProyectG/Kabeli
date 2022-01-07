package cl.ms.kabeli.dto;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cl.ms.kabeli.utilitarios.CustomException;

public class AddUser {
	
	private String name;
	private String email;
	private String password;
	private List<Telefono> phones;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Telefono> getPhones() {
		return phones;
	}
	public void setPhones(List<Telefono> phones) {
		this.phones = phones;
	}
	
	public void validarClase() throws CustomException{
		
		Pattern patronNombre = Pattern.compile(new String("^[\\p{L} .'-]+$"));
		Matcher match = patronNombre.matcher(this.name);
		
		Pattern patronCorreo = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matchc = patronCorreo.matcher(this.email.toUpperCase());
		
		Pattern patronPassword = Pattern.compile(new String("^((?=.*\\d){2})((?=.*[a-z]){1})((?=.*[A-Z]){1}).{1,}$"));
		Matcher matchp = patronPassword.matcher(this.password);
		
		if(name.length() <= 0 || !match.matches())
			throw new CustomException("Revise el nombre.");
		if(email.length() <= 0 || !matchc.matches())
			throw new CustomException("Revise el correo");
		if(password.length() <= 0 || !matchp.matches())
			throw new CustomException("La password no cumple con el minimo de complejidad");
		
	}

}
