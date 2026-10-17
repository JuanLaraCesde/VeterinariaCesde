Aplicación de consola en Java puro (JDK 17+) para la gestión clínica y asignación de jaulas hospitalarias mediante 
Programación Orientada a Objetos (POO).
Principios y ArquitecturaEncapsulamiento estricto: Atributos private con validaciones en setters sin alterar el estado ante datos inválidos.  
Herencia y Polimorfismo: Superclase abstracta Paciente y subclases Perro, Gato y AveExotica que calculan de forma polimórfica sus 
requerimientos de jaula y factores de dosis usando super().   
Vectores de capacidad fija: Manejo de datos sin colecciones dinámicas (Paciente[10] y Jaula[6]), gestionados mediante contador, 
búsqueda secuencial y compactación al dar de alta.   Separación de responsabilidades: Las clases del modelo no leen ni imprimen; 
Scanner y System.out residen únicamente en Main.   Flujo de NegocioIngreso y Cuarentena: Valida límites fisiológicos y nombres únicos. 
Perros no vacunados pasan a CUARENTENA.   Asignación: Vincula a la primera jaula libre compatible en tamaño (S, M, L) y zona (A para perros/gatos, B para aves).   
Medicación: Exige jaula asignada. Valida dosis unitaria (peso × factorDosis) y tope acumulado (3× la dosis máxima). 
Controla el estrés en gatos (bloqueo al alcanzar 8 o más).   Alta: Solo para pacientes en EN_TRATAMIENTO. 
Libera la jaula y compacta el vector de pacientes desplazando las posiciones.   
