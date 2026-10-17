public abstract class Paciente {
private String nombre;
private double peso;
private int edad;
private String estado;
private double factorDosis;
private double dosisAcumulada;
private Jaula jaulaAsignada;

public Paciente(String nombre, double peso, int edad, String estado, double factorDosis) {
	this.nombre = nombre;
	this.peso = peso;
	this.edad = edad;
	this.estado = estado;
	this.factorDosis = factorDosis;
	this.dosisAcumulada = 0.0;
	this.jaulaAsignada = null;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	if (nombre != null && !nombre.trim().isEmpty()) {
		this.nombre = nombre;
	}
}

public double getPeso() {
	return peso;
}

public void setPeso(double peso) {
	if (peso > 0) {
		this.peso = peso;
	}
}

public int getEdad() {
	return edad;
}

public void setEdad(int edad) {
	if (edad >= 0 && edad <= 30) {
		this.edad = edad;
	}
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	if ("INGRESADO".equals(estado) || "CUARENTENA".equals(estado) || "EN_TRATAMIENTO".equals(estado)) {
		this.estado = estado;
	}
}

public double getFactorDosis() {
	return factorDosis;
}

public void setFactorDosis(double factorDosis) {
	if (factorDosis > 0) {
		this.factorDosis = factorDosis;
	}
}

public double getDosisAcumulada() {
	return dosisAcumulada;
}

public void setDosisAcumulada(double dosisAcumulada) {
	if (dosisAcumulada >= 0) {
		this.dosisAcumulada = dosisAcumulada;
	}
}

public Jaula getJaulaAsignada() {
	return jaulaAsignada;
}

public void setJaulaAsignada(Jaula jaulaAsignada) {
	this.jaulaAsignada = jaulaAsignada;
}

public abstract String getTipo();
public abstract String getTamanoMinimoJaula();
public abstract String getZonaRequerida();
}