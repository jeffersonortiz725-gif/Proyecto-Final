/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import java.util.Scanner;

public class SistemaVentas {
    
    // Variables de Caja y estadisticas

    private static int ventasRealizadas       = 0;
    private static int totalProductosVendidos = 0;
    private static double totalVendido        = 0.0;

    // Formas de pago
    private static int ventasEfectivo         = 0;
    private static double montoEfectivo       = 0.0;
    private static int ventasTarjeta          = 0;
    private static double montoTarjeta        = 0.0;
    private static int ventasTransferencia    = 0;
    private static double montoTransferencia  = 0.0;

    // Control de venta mayor y menor
    private static double ventaMayor          = 0.0;
    private static double ventaMenor          = 0.0;
    
    public static void iniciar() {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        do {
            mostrarMenuPrincipal();
            System.out.print("   Seleccione una opcion [1-4]: ");

            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                teclado.nextLine(); 
            } else {
                mostrarError("Por favor, ingrese un numero entero valido.");
                teclado.nextLine();
                continue;
            }

            // Control de criterios 
            switch (opcion) {
                case 1:
                    registrarVenta(teclado);
                    break;
                case 2:
                    consultarCaja();
                    break;
                case 3:
                    mostrarEstadisticas();
                    break;
                case 4:
                    mostrarMensajeDespedida();
                    break;
                default:
                    mostrarError("Opcion fuera de rango. Ingrese un valor entre 1 y 4.");
                    break;
            }

        } while (opcion != 4);
    }

    // Menu Principal
    public static void mostrarMenuPrincipal() {
        System.out.println("=====================================================");
        System.out.println("#          SISTEMA DE VENTAS Y FACTURACION BASICA          #");
        System.out.println("====================================================");
        System.out.println("  1. Registrar una venta                                  ");
        System.out.println("  2. Consultar caja                                       ");
        System.out.println("  3. Mostrar estadisticas                                 ");
        System.out.println("  4. Salir                                                ");
        System.out.println("====================================================");
    }

    //Registro de Ventas
    public static void registrarVenta(Scanner teclado) {
        mostrarEncabezado("REGISTRO DE NUEVA VENTA");

        // Lectura de la cantidad de productos
        int numProductos = 0;
        while (numProductos <= 0) {
            System.out.print("   Cuantos productos incluye la compra: ");
            if (teclado.hasNextInt()) {
                numProductos = teclado.nextInt();
                teclado.nextLine();
                if (numProductos <= 0) {
                    mostrarError("La cantidad de productos debe ser mayor a 0.");
                }
            } else {
                mostrarError("Debe ingresar un número entero válido.");
                teclado.nextLine();
            }
        }

        double subtotalVenta = 0.0;

        // Cantidad de Productos
        for (int i = 1; i <= numProductos; i++) {
            System.out.println(" Producto " + i + " de " + numProductos);

            System.out.print("    Codigo   : ");
            String codigo = teclado.nextLine();

            System.out.print("    Nombre   : ");
            String nombre = teclado.nextLine();

            // Validación de precio
            double precio = 0.0;
            while (precio <= 0) {
                System.out.print("    Precio (Q): ");
                if (teclado.hasNextDouble()) {
                    precio = teclado.nextDouble();
                    if (precio <= 0) {
                        mostrarError("El precio debe ser mayor a Q0.00.");
                    }
                } else {
                    mostrarError("Ingrese un precio numerico valido.");
                    teclado.nextLine();
                }
            }

            // Validación de cantidad
            int cantidad = 0;
            while (cantidad <= 0) {
                System.out.print("    Cantidad : ");
                if (teclado.hasNextInt()) {
                    cantidad = teclado.nextInt();
                    if (cantidad <= 0) {
                        mostrarError("La cantidad debe ser mayor a 0.");
                    }
                } else {
                    mostrarError("Ingrese una cantidad entera valida.");
                    teclado.nextLine();
                }
            }
            teclado.nextLine(); 

            double subtotalProducto = precio * cantidad;
            subtotalVenta += subtotalProducto;
            System.out.printf(" Subtotal del producto: Q%,.2f%n", subtotalProducto);
        }

        // Calculo de descuentos
        double porcentajeDescuento = 0.0;
        if (subtotalVenta < 200) {
            porcentajeDescuento = 0.00;
        } else if (subtotalVenta <= 499.99) {
            porcentajeDescuento = 0.05;
        } else if (subtotalVenta <= 999.99) {
            porcentajeDescuento = 0.08;
        } else {
            porcentajeDescuento = 0.10;
        }

        double descuentoMonto    = subtotalVenta * porcentajeDescuento;
        double montoConDescuento = subtotalVenta - descuentoMonto;
        double iva               = montoConDescuento * 0.12;
        double totalPagar        = montoConDescuento + iva;

        // Selección de forma de pago[cite: 1]
        int formaPago = 0;
        while (formaPago < 1 || formaPago > 3) {
            System.out.println("Seleccione su Forma de Pago ");
            System.out.println("   1) Efectivo");
            System.out.println("   2) Tarjeta");
            System.out.println("   3) Transferencia");
            System.out.print("   Elija una opcion [1-3]: ");

            if (teclado.hasNextInt()) {
                formaPago = teclado.nextInt();
                if (formaPago < 1 || formaPago > 3) {
                    mostrarError("Opcion no valida. Elija entre 1, 2 o 3.");
                }
            } else {
                mostrarError("Ingrese un numero correspondiente a la forma de pago.");
                teclado.nextLine();
            }
        }

        double efectivoRecibido = 0.0;
        double cambio           = 0.0;
        String nombreFormaPago  = "";

        // Proceso del Pago
        switch (formaPago) {
            case 1:
                nombreFormaPago = "Efectivo";
                while (efectivoRecibido < totalPagar) {
                    System.out.printf("   Total a cobrar: Q%,.2f. Ingrese el efectivo recibido: Q", totalPagar);
                    if (teclado.hasNextDouble()) {
                        efectivoRecibido = teclado.nextDouble();
                        if (efectivoRecibido < totalPagar) {
                            mostrarError(String.format("Efectivo insuficiente. Faltan: Q%,.2f", (totalPagar - efectivoRecibido)));
                        }
                    } else {
                        mostrarError("Ingrese un valor numérico valido.");
                        teclado.nextLine();
                    }
                }
                cambio = efectivoRecibido - totalPagar;
                ventasEfectivo++;
                montoEfectivo += totalPagar;
                break;

            case 2:
                nombreFormaPago = "Tarjeta";
                ventasTarjeta++;
                montoTarjeta += totalPagar;
                break;

            case 3:
                nombreFormaPago = "Transferencia";
                ventasTransferencia++;
                montoTransferencia += totalPagar;
                break;
        }

        // Actualización de contadores globales 
        ventasRealizadas++;
        totalProductosVendidos += numProductos;
        totalVendido += totalPagar;

        if (ventasRealizadas == 1) {
            ventaMayor = totalPagar;
            ventaMenor = totalPagar;
        } else {
            if (totalPagar > ventaMayor) ventaMayor = totalPagar;
            if (totalPagar < ventaMenor) ventaMenor = totalPagar;
        }

        // Resumen del comprobante de venta
        String etiquetaDescuento = String.format("Descuento (%.0f%%)", porcentajeDescuento * 100);

        System.out.println("------------------------------------------------------------");
        System.out.println("                  COMPROBANTE DE VENTA                    ");
        System.out.println("---------------------------------------------------------");
        System.out.printf(" %-24s : %-29d %n", "Productos comprados", numProductos);
        System.out.printf(" %-24s : Q%-28.2f %n", "Subtotal", subtotalVenta);
        System.out.printf(" %-24s : Q%-28.2f %n", etiquetaDescuento, descuentoMonto);
        System.out.printf(" %-24s : Q%-28.2f %n", "Monto c/ descuento", montoConDescuento);
        System.out.printf(" %-24s : Q%-28.2f %n", "IVA (12%)", iva);
        System.out.println("-------------------------------------------------------");
        System.out.printf(" %-24s : Q%-28.2f │%n", "TOTAL A PAGAR", totalPagar);
        System.out.printf(" %-24s : %-29s │%n", "Forma de Pago", nombreFormaPago);

        if (formaPago == 1) {
            System.out.printf(" %-24s : Q%-28.2f %n", "Efectivo Recibido", efectivoRecibido);
            System.out.printf(" %-24s : Q%-28.2f %n", "Cambio Devuelto", cambio);
        }
        System.out.println("----------------------------------------------------");
    }

    //  Consultar Caja
    public static void consultarCaja() {
        System.out.println("------------------------------------------------------------");
        System.out.println("                   CONSULTA DE CAJA                       ");
        System.out.println("----------------------------------------------------------");
        System.out.printf(" %-24s : %-29d │%n", "Ventas realizadas", ventasRealizadas);
        System.out.printf(" %-24s : %-29d │%n", "Productos vendidos", totalProductosVendidos);
        System.out.printf(" %-24s : Q%-28.2f │%n", "Total en Caja", totalVendido);
        System.out.println("-----------------------------------------------------");
        System.out.printf(" %-20s : %-3d (Monto: Q%-14.2f) %n", "Ventas Efectivo", ventasEfectivo, montoEfectivo);
        System.out.printf(" %-20s : %-3d (Monto: Q%-14.2f) %n", "Ventas Tarjeta", ventasTarjeta, montoTarjeta);
        System.out.printf(" %-20s : %-3d (Monto: Q%-14.2f) %n", "Ventas Transferencia", ventasTransferencia, montoTransferencia);
        System.out.println("-----------------------------------------------------------");
    }

    // Mostrar Estadisticas
   
    public static void mostrarEstadisticas() {
        System.out.println("------------------------------------------------------------");
        System.out.println("                ESTADISTICAS DEL SISTEMA                  ");
        System.out.println("---------------------------------------------------------");

        if (ventasRealizadas == 0) {
            System.out.println("│  No existen ventas registradas.                          │");
        } else {
            double promedioVentas = totalVendido / ventasRealizadas;

            System.out.printf(" %-24s : %-29d %n", "Numero total de ventas", ventasRealizadas);
            System.out.printf(" %-24s : %-29d %n", "Total productos vendidos", totalProductosVendidos);
            System.out.printf(" %-24s : Q%-28.2f %n", "Total dinero vendido", totalVendido);
            System.out.printf(" %-24s : Q%-28.2f %n", "Promedio por venta", promedioVentas);
            System.out.println("------------------------------------------------------------");
            System.out.println("  Ventas por forma de pago:                               ");
            System.out.printf("    - Efectivo             : %-29d %n", ventasEfectivo);
            System.out.printf("    - Tarjeta              : %-29d %n", ventasTarjeta);
            System.out.printf("    - Transferencia        : %-29d %n", ventasTransferencia);
            System.out.println("-----------------------------------------------------------");
            System.out.printf(" %-24s : Q%-28.2f %n", "Venta de mayor valor", ventaMayor);
            System.out.printf(" %-24s : Q%-28.2f %n", "Venta de menor valor", ventaMenor);
        }
        System.out.println("---------------------------------------------------------------");
    }

    // Metodos Adicionales
    public static void mostrarEncabezado(String titulo) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("  " + titulo);
        System.out.println("-------------------------------------------------------------");
    }

    public static void mostrarError(String mensaje) {
        System.out.println("  1"
                + " ERROR: " + mensaje);
    }

    public static void mostrarMensajeDespedida() {
        System.out.println("------------------------------------------------------------");
        System.out.println("  Gracias por usar el sistema Regresando al menu...     ");
        System.out.println("------------------------------------------------------------");
    }
}