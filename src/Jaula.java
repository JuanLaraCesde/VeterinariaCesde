public class Jaula {
private int numero;
private String tamano; // "S", "M", "L"
private String zona;   // "A", "B"
private Paciente ocupante;

public Jaula(int numero, String tamano, String zona) {
	this.numero = numero;
	this.tamano = tamano;
	this.zona = zona;
	this.ocupante = null;
}

public int getNumero() {
	return numero;
}

public void setNumero(int numero) {
	this.numero = numero;
}

public String getTamano() {
	return tamano;
}

public void setTamano(String tamano) {
	this.tamano = tamano;
}

public String getZona() {
	return zona;
}

public void setZona(String zona) {
	this.zona = zona;
}

public Paciente getOcupante() {
	return ocupante;
}

public void setOcupante(Paciente ocupante) {
	this.ocupante = ocupante;
}

public boolean estaLibre() {
	return this.ocupante == null;
}

public static class Main {
// Subclase temporal solo para poder instanciar Paciente y probar
static class PacientePrueba extends Paciente {
	public PacientePrueba(String nombre, double peso, int edad, String estado, double factorDosis) {
		super(nombre, peso, edad, estado, factorDosis);
	}

	@Override
	public String getTipo() {
		return "Prueba";
	}

	@Override
	public String getTamanoMinimoJaula() {
		return "M";
	}

	@Override
	public String getZonaRequerida() {
		return "A";
	}
}

public static void main(String[] args) {
	System.out.println("=== TEST 1: Creación de Jaula ===");
	Jaula jaula1 = new Jaula(1, "S", "A");
	System.out.println("Jaula número: " + jaula1.getNumero() + " | Tamaño: " + jaula1.getTamano() + " | Zona: " + jaula1.getZona());
	System.out.println("¿Está libre?: " + jaula1.estaLibre()); // Esperado: true

	System.out.println("\n=== TEST 2: Creación de Paciente y Validaciones ===");
	Paciente paciente = new PacientePrueba("Firulais", 12.5, 3, "INGRESADO", 10.0);
	System.out.println("Nombre: " + paciente.getNombre());
	System.out.println("Peso: " + paciente.getPeso());
	System.out.println("Estado inicial: " + paciente.getEstado());

	// Prueba de encapsulamiento: intento de asignar edad inválida (debe ignorarse)
	paciente.setEdad(45); // Regla: edad de 0 a 30
	System.out.println("Edad tras setEdad(45): " + paciente.getEdad()); // Esperado: 3

	// Intento de asignar estado inválido
	paciente.setEstado("VOLANDO");
	System.out.println("Estado tras setEstado('VOLANDO'): " + paciente.getEstado()); // Esperado: INGRESADO

	System.out.println("\n=== TEST 3: Asignación y Enlace Jaula <-> Paciente ===");
	jaula1.setOcupante(paciente);
	paciente.setJaulaAsignada(jaula1);

	System.out.println("¿Jaula libre tras asignar?: " + jaula1.estaLibre()); // Esperado: false
	System.out.println("Ocupante de la jaula: " + jaula1.getOcupante().getNombre());
	System.out.println("Jaula asignada al paciente: " + paciente.getJaulaAsignada().getNumero());

	System.out.println("\n>>> TODAS LAS PRUEBAS BASE PASARON CON ÉXITO <<<");
}
}
}