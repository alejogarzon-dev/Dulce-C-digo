package dulceCodigo;

import dulceCodigo.models.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== SIBOM - Dulce Café S.A. ======\n");
        
        // Fabricar 3 productos
        Producto caramelos = new Producto("Caramelos de café", 6.2, 20000);
        Producto arequipe = new Producto("Arequipe de café", 4.8, 21000);
        Producto galletas = new Producto("Galletas de café", 3.5, 15000);
        
        // Stock inicial
        caramelos.recibir(100);
        arequipe.recibir(80);
        galletas.recibir(60);
        
        // Fabricar 2 camiones
        Camion camion1 = new Camion("ABC-123", 500.0, "Armenia");
        Camion camion2 = new Camion("DEF-456", 800.0, "Pereira");
        
        Producto[] productos = {caramelos, arequipe, galletas};
        Camion[] camiones = {camion1, camion2};
        
        Scanner scanner = new Scanner(System.in);
        boolean abierto = true;
        
        while (abierto) {
            System.out.println("\n📋 ===== MENÚ PRINCIPAL =====");
            System.out.println("1. Ver catálogo (fichas de los 3 productos)");
            System.out.println("2. Recibir producción (elegir producto y cantidad)");
            System.out.println("3. Cargar camión (elegir producto, camión y cajas)");
            System.out.println("4. Ver estado de los camiones");
            System.out.println("5. Reporte general (valor total del inventario sumando los 3)");
            System.out.println("6. Cerrar bodega");
            System.out.print("\nElige opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("\n📦 CATÁLOGO DE PRODUCTOS:");
                    for (int i = 0; i < productos.length; i++) {
                        System.out.println((i + 1) + ". " + productos[i].ficha());
                    }
                    break;
                    
                case 2:
                    System.out.println("\n🎯 RECIBIR PRODUCCIÓN:");
                    System.out.println("Elige producto:");
                    for (int i = 0; i < productos.length; i++) {
                        System.out.println((i + 1) + ". " + productos[i].nombre);
                    }
                    System.out.print("Opción: ");
                    int prodIdx = scanner.nextInt() - 1;
                    
                    if (prodIdx >= 0 && prodIdx < productos.length) {
                        System.out.print("¿Cuántas cajas? ");
                        int cantidad = scanner.nextInt();
                        productos[prodIdx].recibir(cantidad);
                    } else {
                        System.out.println("❌ Opción inválida");
                    }
                    break;
                    
                case 3:
                    System.out.println("\n🚗 CARGAR CAMIÓN:");
                    System.out.println("Elige producto:");
                    for (int i = 0; i < productos.length; i++) {
                        System.out.println((i + 1) + ". " + productos[i].nombre);
                    }
                    System.out.print("Producto: ");
                    int pIdx = scanner.nextInt() - 1;
                    
                    System.out.println("\nElige camión:");
                    for (int i = 0; i < camiones.length; i++) {
                        System.out.println((i + 1) + ". " + camiones[i].placa);
                    }
                    System.out.print("Camión: ");
                    int cIdx = scanner.nextInt() - 1;
                    
                    System.out.print("¿Cuántas cajas? ");
                    int cajas = scanner.nextInt();
                    
                    if (pIdx >= 0 && pIdx < productos.length && cIdx >= 0 && cIdx < camiones.length) {
                        camiones[cIdx].cargarProducto(productos[pIdx], cajas);
                    } else {
                        System.out.println("❌ Opción inválida");
                    }
                    break;
                    
                case 4:
                    System.out.println("\n🚗 ESTADO DE CAMIONES:");
                    for (Camion c : camiones) {
                        System.out.println(c.ficha());
                    }
                    break;
                    
                case 5:
                    System.out.println("\n📊 REPORTE GENERAL:");
                    int valorTotal = 0;
                    for (Producto p : productos) {
                        int valor = p.valorInventario();
                        valorTotal = valorTotal + valor;
                        System.out.println("- " + p.nombre + ": $" + valor);
                    }
                    System.out.println("💰 VALOR TOTAL DEL INVENTARIO: $" + valorTotal);
                    break;
                    
                case 6:
                    abierto = false;
                    System.out.println("\n👋 ¡Bodega cerrada! Hasta luego.");
                    break;
                    
                default:
                    System.out.println("❌ Opción no válida");
            }
        }
        
        scanner.close();
    }
}
