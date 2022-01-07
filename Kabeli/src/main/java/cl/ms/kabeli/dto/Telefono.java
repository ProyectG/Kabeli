package cl.ms.kabeli.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="telefono")
public class Telefono {
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private long id;
	private int number;
	private int citycode;
	private int countrycode;
	
	@JsonIgnore
	@ManyToOne(optional = true, cascade = CascadeType.REMOVE)
	private Usuario usuario;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getCitycode() {
		return citycode;
	}
	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}
	public int getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Telefono [number=" + number + ", citycode=" + citycode + ", countrycode=" + countrycode
				+ ", getNumber()=" + getNumber() + ", getCitycode()=" + getCitycode() + ", getCountrycode()="
				+ getCountrycode() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

}
