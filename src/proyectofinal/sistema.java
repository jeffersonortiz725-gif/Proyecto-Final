/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import java.util.Scanner;

/**
 *
 * @author jeffe
 */
public class sistema {
    
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
    
    public static void sistemaVentas(){
    }
        //crear sscaner
        Scanner sc = new Scanner(System.in);
        
        //Crear variables
        int opcion = 0;
        
        do {
            mostrarMenuPrincipal();
            System.out.println("-------Elige una opcion [1-4]: ");
            
            if(teclado.hasNextInt()){
                opcion = teclado.nextInt();
                teclado.nextLine;
            } else{
                mostrarError("Porfavor ingrese un numero entero valido");
                teclado.Line();
                continue;
                
            }
            
            
        }
        
         
        
    }
    

