/**
 * Un videoclub dispone de una serie de películas que pueden estar en DVD 
 * (con capacidad en Gb.) o en VHS (una sola cinta por película y con cierto 
 * tipo de cinta magnetica). De las películas interesa guardar el título, el 
 * autor, el año de edición y el idioma (o los idiomas, en caso de DVD). 
 * El precio de alquiler de las películas varía en función del tipo de película. 
 * Las DVD siempre son 10% mas caras que las de VHS.

Note
    * Analice los tipos de relación de las siguientes posibles clases: Pelicula, 
    * Dvd, Vhs, Soporte, etc, y justifique su diseño.
    
    * Para probar el diseño jerarquico de clases, instancia en el clase de prueba 
    * Ejecutor (la-s clase-s respectiva-s), con datos aleatorios.
    
    * Los escenarios de prueba pueden darse para el alquiler de una o varias 
    * peliculas según la preferencia del usuario.

 * @author Mateo Rivera
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Problema2_AlquilerPelicula {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        
        String[] titulos = {"IT", "Angeles y Demonios", "El Padrino", 
                           "Jurassic Park", "El Señor de los Anillos: La Comunidad del Anillo"};
        String[] autores = {"Stephen King", "Dan Brown", "Mario Puzo", 
                          "Michael Crichton", "J.R.R. Tolkien"};
        String[] años = {"2017", "2009", "1972", "1993", "2001"};
        String[] idiomas = {"Inglés", "Alemán", "Español", "Árabe", "Ruso", "Portugués", "Francés"};
        double[] precios = {2.0, 3.5, 10.0, 5.55, 6.25};
        
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        ArrayList<SoportePelicula> soportesAlquilados = new ArrayList<>();
        
        // Inicializar películas disponibles
        for(int i = 0; i < titulos.length; i++) {
            listaPeliculas.add(new Pelicula(titulos[i], autores[i], años[i]));            
        }
        
        char opcion;
        do {
            System.out.println("\n--- VIDEOCLUB MATEO ---");
            System.out.println("1. Mostrar películas disponibles");
            System.out.println("2. Alquilar película");
            System.out.println("3. Ver mis alquileres");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.next().charAt(0);
            sc.nextLine(); 
            
            switch(opcion) {
                case '1':
                    mostrarPeliculasDisponibles(listaPeliculas, precios);
                    break;
                    
                case '2':
                    alquilarPelicula(listaPeliculas, precios, idiomas, soportesAlquilados);
                    break;
                    
                case '3':
                    verAlquileres(soportesAlquilados);
                    break;
                    
                case '4':
                    System.out.println("\nGracias por usar nuestros servicios.");
                    break;
                    
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            
        } while(opcion != '4');
    }
    
    public static void mostrarPeliculasDisponibles(ArrayList<Pelicula> peliculas, double[] precios) {
        System.out.println("\n--- PELÍCULAS DISPONIBLES ---");
        for(int i = 0; i < peliculas.size(); i++) {
            System.out.println((i+1) + ". " + peliculas.get(i));
            System.out.println("  Precio base: $" + String.format("%.2f", precios[i]));
        }
    }
    
    public static void alquilarPelicula(ArrayList<Pelicula> peliculas, double[] precios, 
                                       String[] idiomas, ArrayList<SoportePelicula> alquileres) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        if(peliculas.isEmpty()) {
            System.out.println("No hay películas disponibles para alquilar.");
            return;
        }
        
        char continuar;
        do {
            mostrarPeliculasDisponibles(peliculas, precios);
            
            System.out.print("\nSeleccione el número de película que desea alquiler: ");
            int seleccion = sc.nextInt() - 1;
            
            if(seleccion >= 0 && seleccion < peliculas.size()) {
                Pelicula pelicula = peliculas.get(seleccion);
                double precioBase = precios[seleccion];
                
                System.out.println("\nSeleccione el tipo de soporte:");
                System.out.println("1. VHS");
                System.out.println("2. DVD");
                System.out.print("Opción: ");
                int tipoSoporte = sc.nextInt();
                
                if(tipoSoporte == 1) {
                    String idioma = idiomas[random.nextInt(idiomas.length)];
                    VHS vhs = new VHS(idioma, pelicula, precioBase);
                    alquileres.add(vhs);
                    System.out.println("\nHas alquilado: " + vhs);
                } 
                else if(tipoSoporte == 2) {
                    int cantIdiomas = 1 + random.nextInt(3);
                    String[] idiomasDVD = new String[cantIdiomas];
                    for(int i = 0; i < cantIdiomas; i++) {
                        idiomasDVD[i] = idiomas[random.nextInt(idiomas.length)];
                    }
                    
                    ArrayList<Pelicula> peliculasDVD = new ArrayList<>();
                    peliculasDVD.add(pelicula);
                    
                    if(peliculas.size() > 1) {
                            int extras = random.nextInt(2) + 1; 
                            for(int i = 0; i < extras && i < peliculas.size() - 1; i++) {
                                peliculasDVD.add(peliculas.get(random.nextInt(peliculas.size() - 1)));
                            }
                        }
                    
                    DVD dvd = new DVD(idiomasDVD, peliculasDVD, precioBase);
                    dvd.calcularPrecioAlq();
                    alquileres.add(dvd);
                    System.out.println("\nHas alquilado: " + dvd);
                } 
                else {
                    System.out.println("Opción no válida.");
                }
            } 
            else {
                System.out.println("Selección inválida.");
            }
            
            System.out.print("\n¿Desea alquilar otra película? (s/n): ");
            continuar = sc.next().charAt(0);
            sc.nextLine();
            
        } while(continuar == 's' || continuar == 'S');
    }
    
    public static void verAlquileres(ArrayList<SoportePelicula> alquileres) {
        if(alquileres.isEmpty()) {
            System.out.println("\nNo tienes ningún alquiler registrado.");
            return;
        }
        
        System.out.println("\n=== TUS ALQUILERES ===");
        double total = 0;
        
        for(int i = 0; i < alquileres.size(); i++) {
            System.out.println((i+1) + ". " + alquileres.get(i));
            total += alquileres.get(i).precioAlq;
        }
        
        System.out.println("\nTOTAL A PAGAR: $" + String.format("%.2f", total));
        System.out.println("=====================");
    }
}

class SoportePelicula {
    public double precioAlq;
    
    public SoportePelicula() {
        // Constructor vacío
    }
    
    public SoportePelicula(double precioAlq) {
        this.precioAlq = precioAlq;
    }
    
    @Override
    public String toString() {
        return String.format(" Soporte Pelicula{Precio Alquiler: $%.2f}", precioAlq);
    }
}

class DVD extends SoportePelicula{
    public String idioma[];
    public ArrayList<Pelicula> pelicula;

    public DVD() {
        // Constructor vacío
    }   

    public DVD(String[] idioma, ArrayList<Pelicula> pelicula, double precioAlq) {
        super(precioAlq);
        this.idioma = idioma;
        this.pelicula = pelicula;
    }
    
    public void calcularPrecioAlq() {
        this.precioAlq += this.precioAlq * 0.1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n=== DVD ===\n");
        sb.append("Idiomas disponibles: ");
        sb.append(Arrays.toString(idioma)).append("\n");
        sb.append("Peliculas incluidas:\n");

        for (Pelicula p : pelicula) {
            sb.append("  * ").append(p + "\n");
        }
        sb.append(String.format(" %s", super.toString()));
        sb.append("\n===================");

        return sb.toString();
    }
}

class VHS extends SoportePelicula{
    public String idioma;
    public Pelicula pelicula;

    public VHS() {
        // Constructor vacío
    }
    
    public VHS(String idioma, Pelicula pelicula, double precioAlq) {
        super(precioAlq);
        this.idioma = idioma;
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return String.format("VHS:\n" +
                           "  Pelicula: %s\n" +
                           "  Idioma:   %s\n" +
                           " %s", 
                           pelicula, idioma, super.toString());
    }
}

class Pelicula {
    public String titulo;
    public String autor;
    public String anioEdicion;

    public Pelicula() {
        // Constructor vacío
    }   

    public Pelicula(String titulo, String autor, String anioEdicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioEdicion = anioEdicion;
    }
    
    @Override
    public String toString() {
        return String.format("Pelicula:\n" +
                           "  Titulo:   %s\n" +
                           "  Autor:    %s\n" +
                           "  Año:      %s", 
                           titulo, autor, anioEdicion);
    }
}