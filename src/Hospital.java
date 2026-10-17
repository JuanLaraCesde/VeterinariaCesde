public class Hospital {
private Paciente[] pacientes;
private int contadorPacientes;
private Jaula[] jaulas;

public Hospital() {
	this.pacientes = new Paciente[10];
	this.contadorPacientes = 0;
	this.jaulas = new Jaula[6];
	cargarDatosIniciales();
}

public void cargarDatosIniciales() {
	// Inicialización de las 6 jaulas: 1 (A, S), 2 (A, M), 3 (A, M), 4 (A, L), 5 (B, S), 6 (B, M)
	jaulas[0] = new Jaula(1, "S", "A");
	jaulas[1] = new Jaula(2, "M", "A");
	jaulas[2] = new Jaula(3, "M", "A");
	jaulas[3] = new Jaula(4, "L", "A");
	jaulas[4] = new Jaula(5, "S", "B");
	jaulas[5] = new Jaula(6, "M", "B");

	// Pacientes iniciales sin jaula asignada
	pacientes[0] = new Perro("Rocky", 30.0, 4, "INGRESADO", "labrador", true);
	pacientes[1] = new Gato("Misifu", 4.0, 3, "INGRESADO", 0);
	pacientes[2] = new AveExotica("Kiwi", 0.8, 2, "INGRESADO", 70.0);
	contadorPacientes = 3;
}

// Búsqueda por nombre sin distinguir mayúsculas
public Paciente buscarPaciente(String nombre) {
	for (int i = 0; i < contadorPacientes; i++) {
		if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
			return pacientes[i];
		}
	}
	return null;
}

// COMANDO: ingresar perro
public String ingresarPerro(String nombre, double peso, int edad, String raza, String vacunadoStr) {
	// R1: Validación de datos
	if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 80.0 || edad < 0 || edad > 30 || raza == null || raza.trim().isEmpty()) {
		return "ERROR: datos invalidos";
	}
	boolean vacunado;
	if (vacunadoStr.equalsIgnoreCase("si")) {
		vacunado = true;
	} else if (vacunadoStr.equalsIgnoreCase("no")) {
		vacunado = false;
	} else {
		return "ERROR: datos invalidos";
	}

	// R2: Nombre duplicado
	if (buscarPaciente(nombre) != null) {
		return "ERROR: nombre duplicado";
	}

	// R2: Límite de capacidad
	if (contadorPacientes >= 10) {
		return "ERROR: hospital lleno";
	}

	// R3: Si no está vacunado entra en CUARENTENA
	String estadoInicial = vacunado ? "INGRESADO" : "CUARENTENA";
	Perro perro = new Perro(nombre, peso, edad, estadoInicial, raza, vacunado);
	pacientes[contadorPacientes++] = perro;

	if (!vacunado) {
		return "OK: " + nombre + " ingresado en CUARENTENA";
	}
	return "OK: " + nombre + " ingresado";
}

// COMANDO: ingresar gato
public String ingresarGato(String nombre, double peso, int edad) {
	// R1: Validación de datos
	if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 15.0 || edad < 0 || edad > 30) {
		return "ERROR: datos invalidos";
	}

	// R2: Nombre duplicado
	if (buscarPaciente(nombre) != null) {
		return "ERROR: nombre duplicado";
	}

	// R2: Límite de capacidad
	if (contadorPacientes >= 10) {
		return "ERROR: hospital lleno";
	}

	// Gato inicia con estrés 0 según la especificación
	Gato gato = new Gato(nombre, peso, edad, "INGRESADO", 0);
	pacientes[contadorPacientes++] = gato;
	return "OK: " + nombre + " ingresado";
}

// COMANDO: ingresar ave
public String ingresarAve(String nombre, double peso, int edad, double envergadura) {
	// R1: Validación de datos
	if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 15.0 || edad < 0 || edad > 30 || envergadura <= 0) {
		return "ERROR: datos invalidos";
	}

	// R2: Nombre duplicado
	if (buscarPaciente(nombre) != null) {
		return "ERROR: nombre duplicado";
	}

	// R2: Límite de capacidad
	if (contadorPacientes >= 10) {
		return "ERROR: hospital lleno";
	}

	AveExotica ave = new AveExotica(nombre, peso, edad, "INGRESADO", envergadura);
	pacientes[contadorPacientes++] = ave;
	return "OK: " + nombre + " ingresado";
}

// COMANDO: vacunar
public String vacunar(String nombre) {
	Paciente p = buscarPaciente(nombre);
	if (p == null) {
		return "ERROR: paciente no encontrado";
	}
	if (!(p instanceof Perro)) {
		return "ERROR: solo aplica a perros";
	}
	Perro perro = (Perro) p;
	if (perro.isVacunado()) {
		return "ERROR: ya vacunado";
	}

	perro.setVacunado(true);
	perro.setEstado("INGRESADO");
	return "OK: " + nombre + " vacunado";
}

// COMANDO: asignar
public String asignar(String nombre) {
	Paciente p = buscarPaciente(nombre);
	if (p == null) {
		return "ERROR: paciente no encontrado";
	}
	if ("CUARENTENA".equals(p.getEstado())) {
		return "ERROR: paciente en cuarentena";
	}
	if (p.getJaulaAsignada() != null) {
		return "ERROR: paciente ya tiene jaula";
	}

	String zonaReq = p.getZonaRequerida();
	String tamReq = p.getTamanoMinimoJaula();

	// R6: Primera jaula libre compatible con tamaño y zona
	for (int i = 0; i < jaulas.length; i++) {
		Jaula j = jaulas[i];
		if (j.estaLibre() && j.getZona().equals(zonaReq) && esTamanoCompatible(j.getTamano(), tamReq)) {
			j.setOcupante(p);
			p.setJaulaAsignada(j);
			return "OK: " + nombre + " asignado a jaula " + j.getNumero();
		}
	}
	return "ERROR: no hay jaula compatible";
}

// Compatibilidad de tamaños: una jaula mayor o igual sirve al tamaño requerido
private boolean esTamanoCompatible(String tamJaula, String tamReq) {
	int nivelJaula = valorTamano(tamJaula);
	int nivelReq = valorTamano(tamReq);
	return nivelJaula >= nivelReq;
}

private int valorTamano(String t) {
	if ("S".equals(t)) return 1;
	if ("M".equals(t)) return 2;
	if ("L".equals(t)) return 3;
	return 0;
}

// COMANDO: medicar
public String medicar(String nombre, double mg) {
	Paciente p = buscarPaciente(nombre);
	// Orden R8
	if (p == null) {
		return "ERROR: paciente no encontrado";
	}
	if (mg <= 0) {
		return "ERROR: datos invalidos";
	}
	if ("CUARENTENA".equals(p.getEstado())) {
		return "ERROR: paciente en cuarentena";
	}
	if (p.getJaulaAsignada() == null) {
		return "ERROR: paciente sin jaula";
	}
	if (p instanceof Gato) {
		Gato g = (Gato) p;
		if (g.getNivelEstres() >= 8) {
			return "ERROR: gato con estres alto";
		}
	}

	double dosisMaxima = p.getPeso() * p.getFactorDosis();
	if (mg > dosisMaxima) {
		return "ERROR: dosis excede el maximo";
	}
	if (p.getDosisAcumulada() + mg > 3 * dosisMaxima) {
		return "ERROR: tope acumulado superado";
	}

	// R9: Aplicación de medicación
	p.setDosisAcumulada(p.getDosisAcumulada() + mg);
	p.setEstado("EN_TRATAMIENTO");

	if (p instanceof Gato) {
		Gato g = (Gato) p;
		int nuevoEstres = Math.min(10, g.getNivelEstres() + 2);
		g.setNivelEstres(nuevoEstres);
	}

	// Formato para mostrar mg sin decimal innecesario si es entero
	String mgStr = (mg == (long) mg) ? String.format("%d", (long) mg) : String.valueOf(mg);
	return "OK: " + nombre + " medicado con " + mgStr + " mg";
}

// COMANDO: alta
public String alta(String nombre) {
	int indice = -1;
	for (int i = 0; i < contadorPacientes; i++) {
		if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
			indice = i;
			break;
		}
	}
	if (indice == -1) {
		return "ERROR: paciente no encontrado";
	}

	Paciente p = pacientes[indice];
	if (!"EN_TRATAMIENTO".equals(p.getEstado())) {
		return "ERROR: paciente no apto para alta";
	}

	// R10: Liberar jaula
	if (p.getJaulaAsignada() != null) {
		p.getJaulaAsignada().setOcupante(null);
		p.setJaulaAsignada(null);
	}

	// Compactación del vector
	for (int i = indice; i < contadorPacientes - 1; i++) {
		pacientes[i] = pacientes[i + 1];
	}
	pacientes[contadorPacientes - 1] = null;
	contadorPacientes--;

	return "OK: " + nombre + " dado de alta";
}

// COMANDO: reporte
public String generarReporte() {
	StringBuilder sb = new StringBuilder();
	sb.append("--- REPORTE ---\n");
	sb.append("Pacientes: ").append(contadorPacientes).append("/10\n");
	for (int i = 0; i < contadorPacientes; i++) {
		Paciente p = pacientes[i];
		String jaulaStr = (p.getJaulaAsignada() != null) ? String.valueOf(p.getJaulaAsignada().getNumero()) : "-";
		sb.append(p.getNombre()).append(" | ")
				.append(p.getTipo()).append(" | ")
				.append(String.format(java.util.Locale.US, "%.1f", p.getPeso())).append(" kg | ")
				.append(p.getEstado()).append(" | ")
				.append("jaula: ").append(jaulaStr).append("\n");
	}

	int libres = 0;
	for (int i = 0; i < jaulas.length; i++) {
		if (jaulas[i].estaLibre()) {
			libres++;
		}
	}
	sb.append("Jaulas libres: ").append(libres);
	return sb.toString();
}
}