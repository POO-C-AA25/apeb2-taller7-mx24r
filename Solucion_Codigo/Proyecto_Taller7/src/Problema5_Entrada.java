import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:

* Se desea gestionar la venta de entradas para un espectáculo en un teatro. 
* El patio de butacas del teatro se divide en varias zonas, cada una identificada 
* por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:

* NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
* Principal	200	25$	17.5$
* PalcoB	40	70$	40$
* Central	400	20$	14$
* Lateral	100	15.5$	10$
* Para realizar la compra de una entrada, un espectador debe indicar la zona que
* desea y presentar al vendedor el documento que justifique que tiene algún tipo
* de descuento (estudiante, abonado o pensionista). El vendedor sacará la entrada
* del tipo apropiado y de la zona indicada, en el momento de la compra se asignará 
* a la entrada un identificador (un número entero) que permitirá la identificación
* de la entrada en todas las operaciones que posteriormente se desee realizar con ella.

* Una entrada tiene como datos asociados su identificador, la zona a la que pertenece 
* y el nombre del comprador.

* Los precios de las entradas dependen de la zona y del tipo de entrada según 
* lo explicado a continuación:

* Entradas normales: su precio es el precio normal de la zona elegida sin ningún
* tipo de descuento.
* Entradas reducidas (para estudiantes o pensionistas): su precio tiene una 
* rebaja del 15% sobre el precio normal de la zona elegida.
* Entradas abonado: su precio es el precio para abonados de la zona elegida.
* La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.

Note

* Caso de uso “Vende entrada”:

* El vendedor elige la opción “vende entrada” e introduce la zona deseada,
* el nombre del espectador y el tipo (normal, abonado o beneficiario de entrada 
* reducida).
Si la zona elegida no está completa, la aplicación genera una nueva entrada con los datos facilitados.
Si no existe ninguna zona con ese nombre, se notifica y finaliza el caso de uso sin generar la entrada.
* Si la zona elegida está completa lo notifica y finaliza el caso de uno sin generar la entrada.
* La aplicación muestra el identificador y el precio de la entrada.
* Caso de uso “Consulta entrada”:

* El vendedor elige la opción “consulta entrada” e introduce el identificador de la entrada.
* La aplicación muestra los datos de la entrada: nombre del espectador, precio y nombre de la zona. Si no existe ninguna entrada con ese identificador, lo notifica y finaliza el caso de uso
 * @author Usuario
 */
public class Problema5_Entrada {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        ArrayList<Zona> zonas = new ArrayList<>();
        zonas.add(new Zona("Principal", 200, 25, 17.5));
        zonas.add(new Zona("PalcoB", 40, 70, 40));
        zonas.add(new Zona("Central", 400, 20, 14));
        zonas.add(new Zona("Lateral", 100, 15.5, 10));
        
        String[] nombres = {"Lucas", "Emma", "Tomás", "Sofía", "Andrés", "Mía", "Julián", "Renata"};
        String[] apellidos = {"Castillo", "Moreno", "Vega", "Ramírez", "Silva", "Torres", "Herrera", "Mendoza"};
        String[] tiposEntrada = {"normal", "abonado", "reducida"};
        String[] tiposZona = {"Principal", "PalcoB", "Central", "Lateral", " ", "Lat", "PalcoC"};
        String[] documento = {"estudiante", "abonado", "pensionista", "otro"};
        
        ArrayList<Entrada> entradas = new ArrayList<>();
        int opcion;
        
        do {
            System.out.println("\n--- TEATRO ---");
            System.out.println("1. Vender entrada");
            System.out.println("2. Consultar entrada");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    venderEntrada(nombres, apellidos, tiposEntrada, entradas, zonas, tiposZona, documento);
                    break;
                case 2:
                    consultarEntrada(entradas);
                    break;
                case 0:
                    System.out.println("\nGracias por usar el sistema.");
                    System.exit(0);
                default:
                    System.err.println("\nOpción inválida.");
            }
        } while (opcion != '0');
    }

    public static void venderEntrada(String[] nombres, String[] apellidos, String[] tiposEntrada, 
            ArrayList<Entrada> entradas, ArrayList<Zona> zonas, String[] tiposZona, String[] documento) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
       
        Zona zona = zonas.get(random.nextInt(zonas.size()));
        String tipoZona = tiposZona[random.nextInt(tiposZona.length)]; // Puede ser una zona que no existe
    
        // Buscar si la zona existe en la lista de zonas disponibles
        Zona zonaSeleccionada = null;
        for (Zona zonaAct : zonas) {
            if (zonaAct.nombre.equals(tipoZona)) {
                zonaSeleccionada = zona;
                break;
            }
        }

        if (zonaSeleccionada == null) {
            System.out.println("\nLa zona " + tipoZona + " no existe");
            return; // Salir del método si la zona no existe
        }
        
        if(!zona.hayDisponibilidad()) {
            return; // Salir del método si no hay disponibilidad
        }
        
        String nombre = nombres[random.nextInt(nombres.length)];
        String apellido = apellidos[random.nextInt(apellidos.length)];
        String entrada = tiposEntrada[random.nextInt(tiposEntrada.length)];
        String id = String.valueOf(10000000 + random.nextInt(90000000));
        String doc = documento[random.nextInt(documento.length)];

        if (entrada.equals("normal") && doc.equals("otro")) {
            EntradaNormal normal = new EntradaNormal(id, zonaSeleccionada, nombre + " " + apellido);
            normal.calcularPrecio();
            zonaSeleccionada.registrarVenta();
            entradas.add(normal);
            System.out.println(normal.toString());
        } else if(entrada.equals("abonado")) {
            if(doc.equals("abonado")) {
                EntradaAbonado abonado = new EntradaAbonado(id, zona, nombre + " " + apellido);
                abonado.calcularPrecio();
                zonaSeleccionada.registrarVenta();
                entradas.add(abonado);
                System.out.println(abonado.toString());
            } else {
                System.out.println("Usted no tiene un documento de abonado.");
                return;
            }            
        } else if(entrada.equals("reducida")) {
            if(doc.equals("estudiante") || doc.equals("pensionista")) {
                EntradaReducida reducida = new EntradaReducida(id, zona, nombre + " " + apellido);
                reducida.calcularPrecio();
                zonaSeleccionada.registrarVenta();
                entradas.add(reducida);
                System.out.println(reducida.toString());                
            } else {
                System.out.println("Usted no tiene un documento de estudiante o pensionista.");
                return;
            } 
        }
    }
    
    public static void consultarEntrada(ArrayList<Entrada> entradas) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("\nIngrese el identificador de la entrada: ");
        String id = sc.nextLine();
        for(Entrada e: entradas) {
            if(e.id.equals(id)) {
                System.out.println(e.toString());
                return;
            }
        }
        System.out.println("No se encontró la idnentificación: " + id);
    }
}

class Zona {
    public String nombre;
    public int capacidad;
    public double precioNormal;
    public double precioAbonado;
    public int vendidas;
    
    public Zona() {
        // Constructor vacío
    }

    public Zona(String nombre, int capacidad, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.vendidas = 0;
    }

    public boolean hayDisponibilidad() {
        return this.vendidas < this.capacidad;
    }

    public void registrarVenta() {
        this.vendidas++;
    }    
}

class Entrada {
    public String id;
    public Zona zona;
    public String comprador;
    public double precio;
    
    public Entrada() {
        // Constructor vacío
    }

    public Entrada(String id, Zona zona, String comprador) {
        this.id = id;
        this.zona = zona;
        this.comprador = comprador;
    }

    public void calcularPrecio() {
        // Vacío
    }
    
    @Override
    public String toString() {
        return String.format("[ID: %s, Zona: %s, Comprador: %s]",
                            id, zona.nombre, comprador);
    }
}

class EntradaNormal extends Entrada {
    
    public EntradaNormal() {
        // Constructor vacío
    }

    public EntradaNormal(String id, Zona zona, String comprador) {
        super(id, zona, comprador);
    }

    @Override
    public void calcularPrecio() {
        this.precio = zona.precioNormal;
    }
    
    @Override
    public String toString() {
        return String.format("Entrada Normal [%s, Precio: $%.2f]",
                            super.toString(), precio);
    }
}

class EntradaReducida extends Entrada {

    public EntradaReducida() {
        // Constructor vacío
    }

    public EntradaReducida(String id, Zona zona, String comprador) {
        super(id, zona, comprador);
    }

    @Override
    public void calcularPrecio() {
        this.precio = zona.precioNormal * 0.85;
    }
    
    @Override
    public String toString() {
        return String.format("Entrada Reducida [%s, Precio: $%.2f]",
                            super.toString(), precio);
    }
}

class EntradaAbonado extends Entrada {
    
    public EntradaAbonado() {
        // Constructor vacío
    }

    public EntradaAbonado(String id, Zona zona, String comprador) {
        super(id, zona, comprador);
    }

    @Override
    public void calcularPrecio() {
        this.precio = zona.precioAbonado;
    }
    
    @Override
    public String toString() {
        return String.format("Entrada Abonado [%s, Precio: $%.2f]",
                            super.toString(), precio);
    }
}