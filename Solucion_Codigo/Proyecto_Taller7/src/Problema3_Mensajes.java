/**
 *Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de 
 * mensajes que se pueden enviar entre móviles, mensajes de texto (SMS) y 
 * mensajes que contienen imágenes (MMS). Por un lado, los mensajes de texto 
 * contienen un mensaje en caracteres que se desea enviar de un móvil a otro. 
 * Por otro lado, los mensajes que contienen imágenes almacenan información 
 * sobre la imagen a enviar, la cual se representará por el nombre del fichero 
 * que la contiene. Independientemente del tipo de mensaje, cada mensaje tendrá 
 * asociado un remitente de dicho mensaje y un destinatario. Ambos estarán definidos 
 * obligatoriamente por un número de móvil, y opcionalmente se podrá guardar 
 * información sobre su nombre. Además, los métodos enviarMensaje y visualizarMensaje 
 * deben estar definidos.

Note
    * Para probar el diseño jerarquico de clases, instancia en el clase de 
    * prueba Ejecutor, con datos ficticios.
    
 * @author Mateo Rivera
 */


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Problema3_Mensajes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        // Nombres de contactos 
        String[] nombres = {"Mateo", "Valentina", "Sebastián", "Camila", "Alejandro",
            "Isabella", "Daniel", "Gabriela"};

        // Números de teléfono para remitentes
        String[] numRemitente = {"3101234567", "3152345678", "3203456789", "3004567890", 
                                 "3115678901", "3016789012", "3137890123", "3148901234"};

        // Números de teléfono para destinatarios
        String[] numDestinatario = {"3189012345", "3120123456", "3171234567", "3042345678",
                                   "3193456789", "3054567890", "3145678901", "3166789012"};

        // Mensajes de texto básicos
        String[] mensajesTexto = {"¿Nos vemos hoy?", "Te llamo en 10 min", "Confirmo la cita",
            "Llego tarde", "Urgente: necesito tu ayuda", "Gracias por todo","¿Recibiste mi mensaje?",};

        // Archivos para MMS
        String[] imagenes = {"foto_familiar.jpg", "captura_pantalla.png",
            "contrato_firmado.jpg", "factura_mayo.png", "diseño_final.jpg"};
        
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        char opcion;
        do {
            System.out.println("\n--- SISTEMA DE MENSAJERÍA MOVIL MYK ---");
            System.out.println("1. Enviar mensaje de texto (SMS)");
            System.out.println("2. Enviar mensaje con imagen (MMS)");
            System.out.println("3. Visualizar todos los mensajes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.next().charAt(0);
            sc.nextLine(); 
            
            switch(opcion) {
                case '1':
                    System.out.print("¿Enviar mensaje con nombres de Remitente/Destinatario? (s/n): ");
                    char decidir = sc.nextLine().charAt(0);
                    if(decidir == 's') {
                        // Generar datos aleatorios para el SMS con el constructor completo
                        String numRemSMS = numRemitente[random.nextInt(numRemitente.length)];
                        String numDestSMS = numDestinatario[random.nextInt(numDestinatario.length)];
                        String nombreRemSMS = nombres[random.nextInt(nombres.length)];
                        String nombreDestSMS = nombres[random.nextInt(nombres.length)];
                        String texto = mensajesTexto[random.nextInt(mensajesTexto.length)];

                        SMS sms = new SMS(texto, numRemSMS, numDestSMS, nombreRemSMS, nombreDestSMS);                        
                        sms.enviarMensaje();
                        listaMensajes.add(sms);
                        System.out.println("\nMensaje de texto enviado: ");
                        sms.visualizarMensaje();
                    } else {
                        // Generar datos aleatorios para el SMS con el constructor semicompleto
                        String numRemSMS = numRemitente[random.nextInt(numRemitente.length)];
                        String numDestSMS = numDestinatario[random.nextInt(numDestinatario.length)];                        
                        String texto = mensajesTexto[random.nextInt(mensajesTexto.length)];

                        SMS sms = new SMS(texto, numRemSMS, numDestSMS);                        
                        sms.enviarMensaje();
                        listaMensajes.add(sms);
                        System.out.println("\nMensaje de texto enviado: ");
                        sms.visualizarMensaje();
                    }                                       
                    break;
                    
                case '2':
                    System.out.print("¿Enviar mensaje con nombres de Remitente/Destinatario? (s/n): ");
                    char decidir2 = sc.nextLine().charAt(0);
                    if(decidir2 == 's') {
                        // Generar datos aleatorios para el SMS con el constructor completo
                        String numRemMMS = numRemitente[random.nextInt(numRemitente.length)];
                        String numDestMMS = numDestinatario[random.nextInt(numDestinatario.length)];
                        String nombreRemMMS = nombres[random.nextInt(nombres.length)];
                        String nombreDestMMS = nombres[random.nextInt(nombres.length)];
                        String nombreFicherMMS = imagenes[random.nextInt(imagenes.length)];

                        MMS mms = new MMS(nombreFicherMMS, numRemMMS, numDestMMS, nombreRemMMS, nombreDestMMS);                        
                        mms.enviarMensaje();
                        listaMensajes.add(mms);
                        System.out.println("\nMensaje de texto enviado: ");
                        mms.visualizarMensaje();
                    } else {
                        // Generar datos aleatorios para el SMS con el constructor completo
                        String numRemMMS = numRemitente[random.nextInt(numRemitente.length)];
                        String numDestMMS = numDestinatario[random.nextInt(numDestinatario.length)];                        
                        String nombreFicherMMS = imagenes[random.nextInt(imagenes.length)];

                        MMS mms = new MMS(nombreFicherMMS, numRemMMS, numDestMMS);                        
                        mms.enviarMensaje();
                        listaMensajes.add(mms);
                        System.out.println("\nMensaje de texto enviado: ");
                        mms.visualizarMensaje();
                    }   
                    break;
                    
                case '3':
                    System.out.println("\n--- Lista de Mensajes ---");
                    for(Mensaje m : listaMensajes) {
                        m.visualizarMensaje();
                        System.out.println();
                    }
                    break;
                    
                case '4':
                    System.out.println("Gracias por usar nuestro sistema de mensajería!");
                    break;
                    
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            
        } while(opcion != '4');
    }        
}

class Mensaje {
    // Atributos públicos como indica el UML
    public String numRemitente;
    public String numDestinatario;
    public String nombreRemitente;
    public String nombreDestinatario;

    // Constructores
    public Mensaje() {
    }

    public Mensaje(String numRemitente, String numDestinatario) {
        this.numRemitente = numRemitente;
        this.numDestinatario = numDestinatario;
    }

    public Mensaje(String numRemitente, String numDestinatario, 
                  String nombreRemitente, String nombreDestinatario) {
        this.numRemitente = numRemitente;
        this.numDestinatario = numDestinatario;
        this.nombreRemitente = nombreRemitente;
        this.nombreDestinatario = nombreDestinatario;
    }

    // Métodos
    public void enviarMensaje() {
        System.out.println("Enviando mensaje genérico...");
    }

    public void visualizarMensaje() {
        System.out.println("Remitente: " + (nombreRemitente != null ? nombreRemitente + " " : "") + numRemitente);
        System.out.println("Destinatario: " + (nombreDestinatario != null ? nombreDestinatario + " " : "") + numDestinatario);
    }    
}

class SMS extends Mensaje {
    // Atributo adicional para SMS
    public String texto;

    // Constructores
    public SMS() {
    }

    public SMS(String texto, String numRemitente, String numDestinatario) {
        super(numRemitente, numDestinatario);
        this.texto = texto;
    }

    public SMS(String texto, String numRemitente, String numDestinatario,
              String nombreRemitente, String nombreDestinatario) {
        super(numRemitente, numDestinatario, nombreRemitente, nombreDestinatario);
        this.texto = texto;
    }

    // Implementación de métodos
    @Override
    public void enviarMensaje() {        
        System.out.println("Enviando SMS...");
    }

    @Override
    public void visualizarMensaje() {
        super.visualizarMensaje();
        System.out.println("Tipo: SMS");
        System.out.println("Texto: " + texto);
    }
}

class MMS extends Mensaje {
    // Atributo adicional para MMS
    public String nombreFichero;

    // Constructores
    public MMS() {
    }

    public MMS(String nombreFichero, String numRemitente, String numDestinatario) {
        super(numRemitente, numDestinatario);
        this.nombreFichero = nombreFichero;
    }

    public MMS(String nombreFichero, String numRemitente, String numDestinatario,
              String nombreRemitente, String nombreDestinatario) {
        super(numRemitente, numDestinatario, nombreRemitente, nombreDestinatario);
        this.nombreFichero = nombreFichero;
    }

    // Implementación de métodos
    @Override
    public void enviarMensaje() {
        System.out.println("Enviando MMS...");
    }

    @Override
    public void visualizarMensaje() {
        super.visualizarMensaje();
        System.out.println("Tipo: MMS");
        System.out.println("Archivo: " + nombreFichero);
    }
}