public class Perro extends Paciente {
private String raza;
private boolean vacunado;

public Perro(String nombre, double peso, int edad, String estado, String raza, boolean vacunado) {
	super(nombre, peso, edad, estado, 10.0);
	this.raza = raza;
	this.vacunado = vacunado;
}

public String getRaza() {
	return raza;
}

public void setRaza(String raza) {
	if (raza != null && !raza.trim().isEmpty()) {
		this.raza = raza;
	}
}

public boolean isVacunado() {
	return vacunado;
}

public void setVacunado(boolean vacunado) {
	this.vacunado = vacunado;
}

@Override
public String getTipo() {
	return "Perro";
}

@Override
public String getTamanoMinimoJaula() {
	if (getPeso() < 5.0) {
		return "S";
	} else if (getPeso() <= 25.0) {
		return "M";
	} else {
		return "L";
	}
}

@Override
public String getZonaRequerida() {
	return "A";
}
}