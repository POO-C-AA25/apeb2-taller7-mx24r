
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. 
 * Los datos personales de los trabajadores son nombre y apellidos, dirección y DNI. 
 * Además, existen diferentes tipos de trabajadores:
 * Fijos Mensuales: que cobran una cantidad fija al mes.
 * Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
 * Por Horas: cobran un precio por cada una de las horas que han realizado durante 
 * el mes. El precio es fijo para las primeras 40 horas y es otro para las horas realizadas 
 * a partir de la 40 hora mensual.
 * Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene obligatoriamente 
 * un jefe (exceptuando los jefes que no tienen ninguno). El programa debe permitir dar
 * de alta a trabajadores, así como fijar horas o ventas realizadas e imprimir la
 * nómina correspondiente al final de mes.

Note

* Diseñe la jerarquia de clases UML basado en herencia, que defina de mejor 
* forma el escenario planteado.
* Para probar el diseño de clases, instancia en el clase de prueba Ejecutor 
* (la-s clase-s respectiva-s), con datos aleatorios.
* En los escenarios de prueba verifique su solución con al menos 2 tipos de trabajadores.
 * @author Mateo Rivera
 */
public class Problema4_Trabajador {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        
        // Datos aleatorios
        String[] nombres = {"Mateo", "Valentina", "Sebastián", "Camila", "Alejandro", "Isabella", "Daniel", "Gabriela"};
        String[] apellidos = {"García", "Martínez", "López", "González", "Rodríguez", "Fernández", "Pérez", "Díaz"};
        String[] direcciones = {"Calle 1 #23-4", "Av. 2 #45-6", "Carrera 3 #67-8", "Diagonal 4 #89-0"};
        
        ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();
        ArrayList<Jefe> listaJefes = new ArrayList<>();
        
        String continuarJefes;
        do {
            // Crear un nuevo jefe
            String nombrej = nombres[random.nextInt(nombres.length)];
            String apellidoj = apellidos[random.nextInt(apellidos.length)] + " " + apellidos[random.nextInt(apellidos.length)];
            String direccionj = direcciones[random.nextInt(direcciones.length)];
            String dnij = String.valueOf(10000000 + random.nextInt(90000000));
            double sueldoJefe = 3000 + random.nextDouble() * 2000;

            Jefe jefe = new Jefe(nombrej, apellidoj, direccionj, dnij, sueldoJefe);
            listaJefes.add(jefe);
            listaTrabajadores.add(jefe);

            System.out.println("\nJefe añadido: ");
            jefe.imprimirNomina();

            String continuarTrabajadores;
            do {
                System.out.print("\n¿Deseas añadir un trabajador para este jefe? (S/N): ");
                continuarTrabajadores = sc.nextLine().toUpperCase();

                if (continuarTrabajadores.equals("S")) {
                    String nombre = nombres[random.nextInt(nombres.length)];
                    String apellido = apellidos[random.nextInt(apellidos.length)] + " " + apellidos[random.nextInt(apellidos.length)];
                    String direccion = direcciones[random.nextInt(direcciones.length)];
                    String dni = String.valueOf(10000000 + random.nextInt(90000000));

                    System.out.println("\nSeleccione el tipo de trabajador:");
                    System.out.println("1. Fijo Mensual");
                    System.out.println("2. Comisionista");
                    System.out.println("3. Por Horas");
                    System.out.print("Opción: ");
                    int tipoTrabajador = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer

                    switch (tipoTrabajador) {
                        case 1: {
                            double salario = 1500 + random.nextDouble() * 1500;
                            FijoMensual fm = new FijoMensual(nombre, apellido, direccion, dni, jefe, salario);
                            listaTrabajadores.add(fm);
                            System.out.println("\nTrabajador Fijo Mensual añadido:");
                            fm.imprimirNomina();
                            break;
                        }
                        case 2: {
                            double ventas = 5000 + random.nextDouble() * 15000;
                            double porcentaje = 5 + random.nextDouble() * 10;
                            Comisionista com = new Comisionista(nombre, apellido, direccion, dni, jefe, ventas, porcentaje);
                            com.calcularNomina();
                            listaTrabajadores.add(com);
                            System.out.println("\nTrabajador Comisionista añadido:");
                            com.imprimirNomina();
                            break;
                        }
                        case 3: {
                            int horas = 30 + random.nextInt(50);
                            double precioNormal = 10 + random.nextDouble() * 15;
                            double precioExtra = precioNormal * 1.5;
                            PorHoras ph = new PorHoras(nombre, apellido, direccion, dni, jefe, horas, precioNormal, precioExtra);
                            ph.calcularNomina();
                            listaTrabajadores.add(ph);
                            System.out.println("\nTrabajador por Horas añadido:");
                            ph.imprimirNomina();
                            break;
                        }
                        default:
                            System.out.println("Opción no válida.");
                    }
                }
            } while (continuarTrabajadores.equals("S"));

            System.out.print("\n¿Deseas añadir otro jefe? (S/N): ");
            continuarJefes = sc.nextLine().toUpperCase();

        } while (continuarJefes.equals("S"));

        // Mostrar resumen final
        System.out.println("\n============ RESUMEN FINAL ============");
        System.out.println("\nTotal Jefes: " + listaJefes.size());
        System.out.println("Total Trabajadores: " + (listaTrabajadores.size() - listaJefes.size()));
        
        System.out.println("\n=== NÓMINAS DEL MES ===");
        for(Trabajador t : listaTrabajadores) {
            t.imprimirNomina();
        }
    }
}

class Trabajador {
    public String nombre;
    public String apellidos;
    public String direccion;
    public String dni;
    public Jefe jefe;
    public double salario;
    
    public Trabajador() {
        // Constructor vacío
    }

    public Trabajador(String nombre, String apellidos, String direccion, String dni, Jefe jefe) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }
    
    public void calcularNomina() {
        // Cada trabajador tiene su atributo nómina y modifica este metodo
    }

    public void imprimirNomina() {
        System.out.println("Nómina de " + nombre + " " + apellidos);
        System.out.println("DNI: " + dni);
        System.out.println("Dirección: " + direccion);
        if(jefe != null) {
            System.out.println("Jefe: " + jefe.nombre + " " + jefe.apellidos);
        }
    }
}

class FijoMensual extends Trabajador {

    public FijoMensual() {
        // Constructor vacío
    }   

    public FijoMensual(String nombre, String apellidos, String direccion, String dni, Jefe jefe, double salarioMensual) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.salario = salarioMensual;
    }

    @Override
    public void imprimirNomina() {
        super.imprimirNomina();
        System.out.println("Tipo: Trabajador Fijo Mensual");
        System.out.printf("Salario: $%.2f", salario);
        System.out.println("\n--------------------------");
    }
}

class Comisionista extends Trabajador {
    public double ventasRealizadas;
    public double porcentajeComision;

    public Comisionista() {
        // Constructor vacío
    }

    public Comisionista(String nombre, String apellidos, String direccion, String dni, Jefe jefe, double ventasRealizadas, double porcentajeComision) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.ventasRealizadas = ventasRealizadas;
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public void calcularNomina() {
        this.salario = ventasRealizadas * (porcentajeComision / 100);
    }

    @Override
    public void imprimirNomina() {
        super.imprimirNomina();
        System.out.println("Tipo: Trabajador Comisionista");
        System.out.printf("Ventas realizadas: $%.2f", ventasRealizadas);
        System.out.printf("\nPorcentaje comisión: %.2f (por ciento)", porcentajeComision);
        System.out.printf("\nTotal a pagar: $%.2f", salario);
        System.out.println("\n--------------------------");
    }
}

class PorHoras extends Trabajador {
    public int horasTrabajadas;
    public double precioHoraNormal;
    public double precioHoraExtra;

    public PorHoras() {
        // Constructor vacío
    }
    
    public PorHoras(String nombre, String apellidos, String direccion, String dni, Jefe jefe, int horasTrabajadas, double precioHoraNormal, double precioHoraExtra) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.horasTrabajadas = horasTrabajadas;
        this.precioHoraNormal = precioHoraNormal;
        this.precioHoraExtra = precioHoraExtra;
    }

    @Override
    public void calcularNomina() {
        if(horasTrabajadas <= 40) {
            this.salario = horasTrabajadas * precioHoraNormal;
        } else {
            int horasExtra = horasTrabajadas - 40;
            this.salario = (40 * precioHoraNormal) + (horasExtra * precioHoraExtra);
        }
    }

    @Override
    public void imprimirNomina() {
        super.imprimirNomina();
        System.out.println("Tipo: Trabajador Por Horas");
        System.out.println("Horas trabajadas: " + horasTrabajadas);
        System.out.printf("\nPrecio hora normal: $%.2f", precioHoraNormal);
        System.out.printf("\nPrecio hora extra: $%.2f", precioHoraExtra);
        System.out.printf("\nTotal a pagar: $%.2f%n", salario);
        System.out.println("--------------------------");
    }
}

class Jefe extends Trabajador {
    
    public Jefe() {
        // Constructor vacío
    }

    public Jefe(String nombre, String apellidos, String direccion, String dni, double salarioFijo) {
        super(nombre, apellidos, direccion, dni, null);
        this.salario = salarioFijo;
    }

    @Override
    public void imprimirNomina() {
        super.imprimirNomina();
        System.out.println("Tipo: Jefe");
        System.out.printf("Salario fijo: $%.2f", salario);
        System.out.println("\n--------------------------");
    }
}