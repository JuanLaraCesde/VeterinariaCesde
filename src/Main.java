import java.util.Scanner;

public class Main {
public static void main(String[] args) {
	Hospital hospital = new Hospital();
	Scanner scanner = new Scanner(System.in);

	while (scanner.hasNextLine()) {
		String linea = scanner.nextLine().trim();
		if (linea.isEmpty()) {
			continue;
		}

		if (linea.equalsIgnoreCase("salir")) {
			break;
		}

		String[] partes = linea.split("\\s+");
		String comando = partes[0].toLowerCase();

		try {
			if (comando.equals("ingresar")) {
				if (partes.length < 2) {
					System.out.println("ERROR: comando desconocido");
					continue;
				}

				String subcomando = partes[1].toLowerCase();
				if (subcomando.equals("perro")) {
					// Formato: ingresar perro <nombre> <peso> <edad> <raza> <si/no>
					if (partes.length == 7) {
						String nombre = partes[2];
						double peso = Double.parseDouble(partes[3]);
						int edad = Integer.parseInt(partes[4]);
						String raza = partes[5];
						String vacunadoStr = partes[6];
						System.out.println(hospital.ingresarPerro(nombre, peso, edad, raza, vacunadoStr));
					} else {
						System.out.println("ERROR: datos invalidos");
					}
				} else if (subcomando.equals("gato")) {
					// Formato: ingresar gato <nombre> <peso> <edad>
					if (partes.length == 5) {
						String nombre = partes[2];
						double peso = Double.parseDouble(partes[3]);
						int edad = Integer.parseInt(partes[4]);
						System.out.println(hospital.ingresarGato(nombre, peso, edad));
					} else {
						System.out.println("ERROR: datos invalidos");
					}
				} else if (subcomando.equals("ave")) {
					// Formato: ingresar ave <nombre> <peso> <edad> <envergadura>
					if (partes.length == 6) {
						String nombre = partes[2];
						double peso = Double.parseDouble(partes[3]);
						int edad = Integer.parseInt(partes[4]);
						double envergadura = Double.parseDouble(partes[5]);
						System.out.println(hospital.ingresarAve(nombre, peso, edad, envergadura));
					} else {
						System.out.println("ERROR: datos invalidos");
					}
				} else {
					System.out.println("ERROR: comando desconocido");
				}

			} else if (comando.equals("vacunar")) {
				// Formato: vacunar <nombre>
				if (partes.length == 2) {
					System.out.println(hospital.vacunar(partes[1]));
				} else {
					System.out.println("ERROR: comando desconocido");
				}

			} else if (comando.equals("asignar")) {
				// Formato: asignar <nombre>
				if (partes.length == 2) {
					System.out.println(hospital.asignar(partes[1]));
				} else {
					System.out.println("ERROR: comando desconocido");
				}

			} else if (comando.equals("medicar")) {
				// Formato: medicar <nombre> <mg>
				if (partes.length == 3) {
					String nombre = partes[1];
					double mg = Double.parseDouble(partes[2]);
					System.out.println(hospital.medicar(nombre, mg));
				} else {
					System.out.println("ERROR: datos invalidos");
				}

			} else if (comando.equals("alta")) {
				// Formato: alta <nombre>
				if (partes.length == 2) {
					System.out.println(hospital.alta(partes[1]));
				} else {
					System.out.println("ERROR: comando desconocido");
				}

			} else if (comando.equals("reporte")) {
				if (partes.length == 1) {
					System.out.println(hospital.generarReporte());
				} else {
					System.out.println("ERROR: comando desconocido");
				}

			} else {
				System.out.println("ERROR: comando desconocido");
			}

		} catch (NumberFormatException e) {
			// Si escriben texto en lugar de números para peso, edad, mg, etc.
			System.out.println("ERROR: datos invalidos");
		}
	}

	scanner.close();
}
}