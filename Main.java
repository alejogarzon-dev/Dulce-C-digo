class Producto {
    String nombre;
    double pesoCajaKg;
    int precioCaja;
    int stockCajas;
    
    Producto(String nombre, double pesoCajaKg, int precioCaja) {
        this.nombre = nombre;
        this.precioCaja = precioCaja;
        this.stockCajas = 0;
        
        if (pesoCajaKg > 0) {
            this.pesoCajaKg = pesoCajaKg;
        } else {
            this.pesoCajaKg = 1.0;
            System.out.println("⚠️ Peso inválido para " + nombre + ": se ajustó a 1.0 kg");
        }
        
        System.out.println("🏭 Producto registrado: " + this.nombre);
    }
    
    boolean esRentable() {
        int precioStock = precioCaja * stockCajas;
        return precioStock > 500000;
    }
}

class Main {
    public static void main(String[] args) {
        Producto p1 = new Producto("Laptop Premium", 2.5, 150000);
        Producto p2 = new Producto("Mouse básico", 0.2, 15000);
        Producto p3 = new Producto("Monitor gamer", -1.5, 200000);
        
        System.out.println("\n📊 Estado inicial:");
        System.out.println(p1.nombre + " - Peso: " + p1.pesoCajaKg + "kg, Precio: $" + p1.precioCaja + ", Stock: " + p1.stockCajas);
        System.out.println(p2.nombre + " - Peso: " + p2.pesoCajaKg + "kg, Precio: $" + p2.precioCaja + ", Stock: " + p2.stockCajas);
        System.out.println(p3.nombre + " - Peso: " + p3.pesoCajaKg + "kg, Precio: $" + p3.precioCaja + ", Stock: " + p3.stockCajas);
        
        System.out.println("\n💰 ¿Es rentable?");
        System.out.println(p1.nombre + ": " + p1.esRentable());
        System.out.println(p2.nombre + ": " + p2.esRentable());
        System.out.println(p3.nombre + ": " + p3.esRentable());
    }
}
