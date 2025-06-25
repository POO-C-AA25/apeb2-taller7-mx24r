
import java.util.ArrayList;

/**
 * Dibujad un diagrama de clases que muestre la estructura de un capítulo de libro;
 * un capítulo está compuesto por varias secciones, cada una de las cuales comprende
 * varios párrafos y figuras. Un párrafo incluye varias sentencias, cada una de las 
 * cuales contiene varias palabras.

Note

* Suponga que en un futuro se prevé que el sistema gestione además de párrafos y
* figuras otros componentes, como tablas, listas, viñetas, etc.
* Suponga además que una palabra puede aparecer en varias sentencias.
 * @author Mateo Rivera
 */
public class Problema1_CapituloLibro {
    public static void main(String[] args) {
        // Creación de palabras para el primer párrafo
        ArrayList<Palabra> palabras1 = new ArrayList<>();
        palabras1.add(new Palabra("El"));
        palabras1.add(new Palabra("señor"));
        palabras1.add(new Palabra("de"));
        palabras1.add(new Palabra("los"));
        palabras1.add(new Palabra("anillos"));

        ArrayList<Palabra> palabras2 = new ArrayList<>();
        palabras2.add(new Palabra("Cuando"));
        palabras2.add(new Palabra("el"));
        palabras2.add(new Palabra("señor"));
        palabras2.add(new Palabra("Bolsón"));
        palabras2.add(new Palabra("celebró"));
        palabras2.add(new Palabra("su"));
        palabras2.add(new Palabra("cumpleaños"));

        // Creación de sentencias
        ArrayList<Sentencia> sentencias1 = new ArrayList<>();
        sentencias1.add(new Sentencia(palabras1));
        sentencias1.add(new Sentencia(palabras2));

        // Componentes de la primera sección
        ArrayList<Componente> componentesSeccion1 = new ArrayList<>();
        Parrafo parrafoIntro = new Parrafo();
        parrafoIntro.contenido = "Introducción a la obra";
        parrafoIntro.sentencias = sentencias1;
        componentesSeccion1.add(parrafoIntro);

        Figura figura1 = new Figura();
        figura1.tipo = "Mapa de la Tierra Media";
        figura1.legenda = "Mapa inicial";
        componentesSeccion1.add(figura1);

        // Lista de personajes
        ArrayList<String> personajes = new ArrayList<>();
        personajes.add("Frodo");
        personajes.add("Gandalf");
        personajes.add("Aragorn");
        Lista listaPersonajes = new Lista();
        listaPersonajes.tipo = "Personajes principales";
        listaPersonajes.elementos = personajes;
        componentesSeccion1.add(listaPersonajes);

        // Creación de la primera sección
        ArrayList<Seccion> seccionesCapitulo1 = new ArrayList<>();
        Seccion seccion1 = new Seccion();
        seccion1.titulo = "1.1 Introducción";
        seccion1.componentes = componentesSeccion1;
        seccionesCapitulo1.add(seccion1);

        // Segunda sección
        ArrayList<Componente> componentesSeccion2 = new ArrayList<>();
        
        // Párrafo de desarrollo
        ArrayList<Palabra> palabras3 = new ArrayList<>();
        palabras3.add(new Palabra("El"));
        palabras3.add(new Palabra("anillo"));
        palabras3.add(new Palabra("único"));
        palabras3.add(new Palabra("fue"));
        palabras3.add(new Palabra("forjado"));
        palabras3.add(new Palabra("por"));
        palabras3.add(new Palabra("Sauron"));

        ArrayList<Sentencia> sentencias2 = new ArrayList<>();
        sentencias2.add(new Sentencia(palabras3));

        Parrafo parrafoDesarrollo = new Parrafo();
        parrafoDesarrollo.contenido = "Historia del anillo";
        parrafoDesarrollo.sentencias = sentencias2;
        componentesSeccion2.add(parrafoDesarrollo);

        // Tabla de razas
        Tabla tablaRazas = new Tabla();
        tablaRazas.filas = 4;
        tablaRazas.columnas = 2;
        tablaRazas.descripcion = "Razas de la Tierra Media";
        componentesSeccion2.add(tablaRazas);

        // Viñeta de lugares
        ArrayList<String> lugares = new ArrayList<>();
        lugares.add("La Comarca");
        lugares.add("Rivendel");
        lugares.add("Mordor");
        Vineta vinetaLugares = new Vineta();
        vinetaLugares.titulo = "Lugares importantes";
        vinetaLugares.puntos = lugares;
        componentesSeccion2.add(vinetaLugares);

        Seccion seccion2 = new Seccion();
        seccion2.titulo = "1.2 Desarrollo";
        seccion2.componentes = componentesSeccion2;
        seccionesCapitulo1.add(seccion2);

        // Creación del capítulo
        ArrayList<Capitulo> capitulosLibro = new ArrayList<>();
        Capitulo capitulo1 = new Capitulo();
        capitulo1.titulo = "La Comunidad del Anillo";
        capitulo1.secciones = seccionesCapitulo1;
        capitulosLibro.add(capitulo1);

        // Segundo capítulo
        ArrayList<Componente> componentesSeccion3 = new ArrayList<>();
        
        ArrayList<Palabra> palabras4 = new ArrayList<>();
        palabras4.add(new Palabra("La"));
        palabras4.add(new Palabra("comunidad"));
        palabras4.add(new Palabra("se"));
        palabras4.add(new Palabra("dispersa"));
        palabras4.add(new Palabra("en"));
        palabras4.add(new Palabra("su"));
        palabras4.add(new Palabra("misión"));

        ArrayList<Sentencia> sentencias3 = new ArrayList<>();
        sentencias3.add(new Sentencia(palabras4));

        Parrafo parrafoContinuacion = new Parrafo();
        parrafoContinuacion.contenido = "Continuación de la historia";
        parrafoContinuacion.sentencias = sentencias3;
        componentesSeccion3.add(parrafoContinuacion);

        Figura figura2 = new Figura();
        figura2.tipo = "Batalla de Helm's Deep";
        figura2.legenda = "Escena clave";
        componentesSeccion3.add(figura2);

        ArrayList<Seccion> seccionesCapitulo2 = new ArrayList<>();
        Seccion seccion3 = new Seccion();
        seccion3.titulo = "2.1 Las Dos Torres";
        seccion3.componentes = componentesSeccion3;
        seccionesCapitulo2.add(seccion3);

        Capitulo capitulo2 = new Capitulo();
        capitulo2.titulo = "Las Dos Torres";
        capitulo2.secciones = seccionesCapitulo2;
        capitulosLibro.add(capitulo2);

        // Creación del libro
        Libro libro = new Libro();
        libro.titulo = "El Señor de los Anillos";
        libro.autor = "J.R.R. Tolkien";
        libro.numPagina = 1216;
        libro.numCapitulo = 2;
        libro.capitulos = capitulosLibro;

        // Mostrar información del libro
        System.out.println("Libro: " + libro.titulo);
        System.out.println("Autor: " + libro.autor);
        System.out.println("Número de páginas: " + libro.numPagina);
        System.out.println("Capítulos:");
        
        for (Capitulo capitulo : libro.capitulos) {
            System.out.println("- Capítulo: " + capitulo.titulo);
            for (Seccion seccion : capitulo.secciones) {
                System.out.println("  Sección: " + seccion.titulo);

                for (Componente c : seccion.componentes) {

                    // Si el componente tiene sentencias, asumimos que es un Párrafo
                    if (c.getClass().getSimpleName().equals("Parrafo")) {
                        Parrafo p = (Parrafo) c;
                        System.out.println("    Párrafo: " + p.contenido);
                        for (Sentencia s : p.sentencias) {
                            System.out.print("      Sentencia: ");
                            for (Palabra palabra : s.palabras) {
                                System.out.print(palabra.texto + " ");
                            }
                            System.out.println();
                        }
                    }

                    // Si tiene tipo y legenda, es una Figura
                    else if (c.getClass().getSimpleName().equals("Figura")) {
                        Figura f = (Figura) c;
                        System.out.println("    Figura: " + f.tipo + " (Leyenda: " + f.legenda + ")");
                    }

                    // Si tiene tipo y elementos, es una Lista
                    else if (c.getClass().getSimpleName().equals("Lista")) {
                        Lista l = (Lista) c;
                        System.out.println("    Lista: " + l.tipo);
                        for (String e : l.elementos) {
                            System.out.println("      - " + e);
                        }
                    }

                    // Si tiene filas y columnas, es una Tabla
                    else if (c.getClass().getSimpleName().equals("Tabla")) {
                        Tabla t = (Tabla) c;
                        System.out.println("    Tabla: " + t.descripcion + " (" + t.filas + "x" + t.columnas + ")");
                    }

                    // Si tiene puntos, es una Viñeta
                    else if (c.getClass().getSimpleName().equals("Vineta")) {
                        Vineta v = (Vineta) c;
                        System.out.println("    Viñeta: " + v.titulo);
                        for (String punto : v.puntos) {
                            System.out.println("      * " + punto);
                        }
                    }
                }
            }
        }  
    }
}

class Libro {
    public String titulo;
    public ArrayList<Capitulo> capitulos = new ArrayList<>();
    public String autor;
    public int numPagina;
    public int numCapitulo;
    
    public Libro() {}
    
    public Libro(ArrayList<Capitulo> capitulos, String autor, int numPagina, int numCapitulo) {
        this.capitulos = capitulos;
        this.autor = autor;
        this.numPagina = numPagina;
        this.numCapitulo = numCapitulo;
    }
}

class Capitulo {
    public String titulo;
    public ArrayList<Seccion> secciones = new ArrayList<>();
    
    public Capitulo() {}
    
    public Capitulo(String titulo, ArrayList<Seccion> secciones) {
        this.titulo = titulo;
        this.secciones = secciones;
    }
}

class Seccion {
    public String titulo;
    public ArrayList<Componente> componentes = new ArrayList<>();
    
    public Seccion() {}
    
    public Seccion(String titulo, ArrayList<Componente> componentes) {
        this.titulo = titulo;
        this.componentes = componentes;
    }
}

class Componente {
    public String contenido;
    
    public Componente() {}
    
    public Componente(String contenido) {
        this.contenido = contenido;
    }
}

class Parrafo extends Componente {
    public ArrayList<Sentencia> sentencias = new ArrayList<>();
    
    public Parrafo() {}
}

class Figura extends Componente {
    public String tipo;
    public String legenda;
    
    public Figura() {}
}

class Tabla extends Componente {
    public int filas;
    public int columnas;
    public String descripcion;
    
    public Tabla() {}
}

class Lista extends Componente {
    public String tipo;
    public ArrayList<String> elementos = new ArrayList<>();
    
    public Lista() {}
}

class Vineta extends Componente {
    public String titulo;
    public ArrayList<String> puntos = new ArrayList<>();
    
    public Vineta() {}
}

class Sentencia {
    public ArrayList<Palabra> palabras = new ArrayList<>();
    
    public Sentencia() {}
    
    public Sentencia(ArrayList<Palabra> palabras) {
        this.palabras = palabras;
    }
}

class Palabra {
    public String texto;
    
    public Palabra() {}
    
    public Palabra(String texto) {
        this.texto = texto;
    }
}
