
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Problema6_CuentaBancaria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        ArrayList<CuentaBancaria> listaCuentas = new ArrayList<>();

        // Datos quemados
        String[] nombres = {"Alejandro", "Beatriz", "Camila", "Diego", "Elena", 
                           "Fernando", "Gabriela", "Héctor", "Isabel", "Javier"};
        String[] apellidos = {"García", "Rodríguez", "Martínez", "López", "Pérez", 
                             "Gómez", "Sánchez", "Romero", "Díaz", "Torres"};

        String continuar;

        do {
            // Generar datos aleatorios para la cuenta
            String numeroCuenta = "C" + (10000000 + random.nextInt(90000000));
            String nombreCliente = nombres[random.nextInt(nombres.length)] + " " + 
                                 apellidos[random.nextInt(apellidos.length)];
            double balanceInicial = 100 + random.nextDouble() * 30000;

            System.out.println("\nSeleccione el tipo de cuenta:");
            System.out.println("1. Cuenta de Ahorro");
            System.out.println("2. Cuenta de Cheque");
            System.out.println("3. Cuenta Platino");
            System.out.print("Opción: ");
            int tipoCuenta = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (tipoCuenta) {
                case 1 -> {
                    CuentaAhorros ca = new CuentaAhorros(numeroCuenta, nombreCliente, balanceInicial);
                    listaCuentas.add(ca);
                    System.out.println("\nCuenta de Ahorro creada: " + ca.toString());
                }
                case 2 -> {
                    CuentaCheques cc = new CuentaCheques(numeroCuenta, nombreCliente, balanceInicial);
                    listaCuentas.add(cc);
                    System.out.println("\nCuenta de Cheque creada: " + cc);
                }
                case 3 -> {
                    CuentaPlatino cp = new CuentaPlatino(numeroCuenta, nombreCliente, balanceInicial);
                    listaCuentas.add(cp);
                    System.out.println("\nCuenta Platino creada: " + cp);
                }
                default -> System.out.println("Opción no válida.");
            }

            // Operaciones con la cuenta recién creada
            if (!listaCuentas.isEmpty()) {
                CuentaBancaria cuentaActual = listaCuentas.get(listaCuentas.size() - 1);
                
                System.out.print("\n¿Desea realizar operaciones con esta cuenta? (S/N): ");
                String operar = sc.nextLine().toUpperCase();
                
                while (operar.equals("S")) {
                    System.out.println("\nOperaciones disponibles:");
                    System.out.println("1. Depositar");
                    System.out.println("2. Retirar");
                    System.out.println("3. Mostrar información");
                    if (tipoCuenta == 1 || tipoCuenta == 3) {
                        System.out.println("4. Calcular interés");
                    }
                    System.out.print("Opción: ");
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    
                    switch (opcion) {
                        case 1 -> {
                            System.out.print("Ingrese monto a depositar: $");
                            double montoDeposito = sc.nextDouble();
                            sc.nextLine();
                            cuentaActual.depositar(montoDeposito);
                        }
                        case 2 -> {
                            System.out.print("Ingrese monto a retirar: $");
                            double montoRetiro = sc.nextDouble();
                            sc.nextLine();
                            cuentaActual.retirar(montoRetiro);
                        }
                        case 3 -> System.out.println(cuentaActual.toString());
                        case 4 -> {
                            if (tipoCuenta == 1) {  // 1 representa CuentaAhorro
                                ((CuentaAhorros)cuentaActual).calcularInteres();
                            } else if (tipoCuenta == 3) {  // 3 representa CuentaPlatino
                                ((CuentaPlatino)cuentaActual).calcularInteres();
                            } else {
                                System.out.println("Opción no válida para este tipo de cuenta");
                            }
                        }
                        default -> System.out.println("Opción no válida.");
                    }
                    
                    System.out.print("\n¿Desea realizar otra operación con esta cuenta? (S/N): ");
                    operar = sc.nextLine().toUpperCase();
                }
            }

            System.out.print("\n¿Desea crear otra cuenta? (S/N): ");
            continuar = sc.nextLine().toUpperCase();

        } while (continuar.equals("S"));

        System.out.println("\n--- Lista Final de Cuentas ---");
        for (CuentaBancaria cuenta : listaCuentas) {
            System.out.println(cuenta);
        }

        System.out.println("\nPrograma finalizado.");
    }
    
}

class CuentaBancaria {
    public String numeroCuenta;
    public String nombreCliente;
    public double balance;
    
    public CuentaBancaria() {
        // Constructor vacío
    }
    
    public CuentaBancaria(String numeroCuenta, String nombreCliente, double balance) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = balance;
    }
    
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            balance += cantidad;
            System.out.printf("\nDepósito exitoso. Nuevo balance: $%.2f%n", getBalance());
        } else {
            System.out.println("Error: Cantidad a depositar debe ser positiva");
        }
    }
    
    public void retirar(double cantidad) {
        if (cantidad > 0 && getBalance() >= cantidad) {
            balance -= cantidad;
            System.out.printf("\nRetiro exitoso. Nuevo balance: $%.2f%n", getBalance());
        } else if(getBalance() < cantidad){
            System.out.println("Error: Fondos insuficientes");
        } else {
             System.err.println("Error: Cantidad a retirar debe ser positiva");
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    @Override
    public String toString() {
        return String.format("Cuenta: %s%n"
                + "Cliente: %s%n"
                + "Balance: $%.2f%n", numeroCuenta, nombreCliente, balance);
    }
}

// Cuenta de Cheques (puede sobregirarse)
class CuentaCheques extends CuentaBancaria {

    public CuentaCheques() {
        // Constructor vacío
    }   
    
    public CuentaCheques(String numeroCuenta, String nombreCliente, double balanceInicial) {
        super(numeroCuenta, nombreCliente, balanceInicial);
    }
    
    @Override
    public void retirar(double cantidad) {
        if (cantidad > 0) {
            balance -= cantidad;
            System.out.printf("\nRetiro exitoso. Nuevo balance: $%.2f%n", getBalance());
        } else if(cantidad <= 0) {
             System.err.println("Error: Cantidad a retirar debe ser positiva");
        }
    }
    
    
    @Override
    public String toString() {
        return "Cuenta Cheques - " + super.toString();
    }
}

class CuentaAhorros extends CuentaBancaria {
    public double TASA_INTERES = 0.05; // 5% de interés

    public CuentaAhorros() {
        // Constructor vacío
    }
    
    public CuentaAhorros(String numeroCuenta, String nombreCliente, double balanceInicial) {
        super(numeroCuenta, nombreCliente, balanceInicial);
    }
    
    public void calcularInteres() {
        double interes = balance * TASA_INTERES;
        balance += interes;
        System.out.printf("\nInterés calculado: $%.2f" + 
                         ". Nuevo balance: $%.2f%n", interes, super.getBalance());
    }
    
    @Override
    public String toString() {
        return "Cuenta Ahorros - " + super.toString();
    }
}

// Cuenta Platino (interés 10%, sin restricciones de sobregiro)
class CuentaPlatino extends CuentaBancaria {
    public double TASA_INTERES = 0.10; // 10% de interés

    public CuentaPlatino() {
        // Constructor vacío
    }
    
    public CuentaPlatino(String numeroCuenta, String nombreCliente, double balanceInicial) {
        super(numeroCuenta, nombreCliente, balanceInicial);
    }
    
    @Override
    public void retirar(double cantidad) {
        if (cantidad > 0) {
            balance -= cantidad;
            System.out.printf("\nRetiro exitoso. Nuevo balance: $%.2f%n", getBalance());
        } else {
            System.err.println("Error: Cantidad a retirar debe ser positiva");
        }
    }
    
    public void calcularInteres() {
        double interes = balance * TASA_INTERES;
        balance += interes;
        System.out.printf("\nInterés Platino calculado: $%.2f" + 
                         ". Nuevo balance: $%.2f%n", interes, super.getBalance());
    }
    
    @Override
    public String toString() {
        return "Cuenta Platino - " + super.toString();
    }
}