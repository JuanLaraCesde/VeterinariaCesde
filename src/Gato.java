public class Gato extends Paciente {
private int nivelEstres; // 0 a 10

public Gato(String nombre, double peso, int edad, String estado, int nivelEstres) {
	super(nombre, peso, edad, estado, 6.0);
	if (nivelEstres >= 0 && nivelEstres <= 10) {
		this.nivelEstres = nivelEstres;
	} else {
		this.nivelEstres = 0;
	}
}

public int getNivelEstres() {
	return nivelEstres;
}

public void setNivelEstres(int nivelEstres) {
	if (nivelEstres >= 0 && nivelEstres <= 10) {
		this.nivelEstres = nivelEstres;
	}
}

@Override
public String getTipo() {
	return "Gato";
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