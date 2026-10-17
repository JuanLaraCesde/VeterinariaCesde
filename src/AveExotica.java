public class AveExotica extends Paciente {
private double envergadura;

public AveExotica(String nombre, double peso, int edad, String estado, double envergadura) {
	super(nombre, peso, edad, estado, 3.0);
	if (envergadura > 0) {
		this.envergadura = envergadura;
	} else {
		this.envergadura = 1.0;
	}
}

public double getEnvergadura() {
	return envergadura;
}

public void setEnvergadura(double envergadura) {
	if (envergadura > 0) {
		this.envergadura = envergadura;
	}
}

@Override
public String getTipo() {
	return "Ave";
}

@Override
public String getTamanoMinimoJaula() {
	// Regla R4: Si la envergadura > 60 cm, mínimo M aunque el peso indique S
	if (this.envergadura > 60.0) {
		if (getPeso() > 25.0) {
			return "L";
		}
		return "M";
	}

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
	return "B";
}
}