package dulceCodigo.models;

public class Producto {
    public String nombre;
    public int stockCajas;
    public double pesoCajaKg;
    public int precioCaja;
    public int totalDespachado;
    
    public Producto(String nombre, double pesoCajaKg, int precioCaja) {
        this.nombre = nombre;
        this.pesoCajaKg = pesoCajaKg;
        this.precioCaja = precioCaja;
        this.stockCajas = 0;
        this.totalDespachado = 0;
        
        if (pesoCajaKg > 0) {
            this.pesoCajaKg = pesoCajaKg;
        } else {
            this.pesoCajaKg = 1.0;
            System.out.println("⚠️ Peso inválido para " + nombre + ": se ajustó a 1.0 kg");
        }
        
        System.out.println("🏭 Producto registrado: " + this.nombre);
    }
    
    public double pesoTotal() {
        return this.stockCajas * this.pesoCajaKg;
    }
    
    public int valorInventario() {
        return this.stockCajas * this.precioCaja;
    }
    
    public boolean puedeDespachar(int cajas) {
        return cajas > 0 && cajas <= this.stockCajas;
    }
    
    public boolean esRentable() {
        return stockCajas * precioCaja > 500000;
    }
    
    public boolean tieneMaxStockQue(Producto otro) {
        return this.stockCajas > otro.stockCajas;
    }
    
    public void recibir(int cajas) {
        if (cajas > 0) {
            this.stockCajas = this.stockCajas + cajas;
            System.out.println("⬆ Entraron " + cajas + " cajas de " + this.nombre);
        } else {
            System.out.println("⛔ " + cajas + " Es una cantidad inválida");
        }
    }
    
    public void despachar(int cajas) {
        if (this.puedeDespachar(cajas)) {
            this.stockCajas = this.stockCajas - cajas;
            this.totalDespachado = this.totalDespachado + cajas;
            System.out.println("⬇ Salieron " + cajas + " cajas de " + this.nombre);
        } else {
            System.out.println("⛔ Despacho de " + cajas + " cajas rechazado");
        }
    }
    
    public String resumenDelDia() {
        return "📊 " + this.nombre + " | Stock: " + this.stockCajas + " cajas | Despachado hoy: " + this.totalDespachado + " cajas";
    }
    
    public String ficha() {
        return "📦 " + this.nombre + " | Stock: " + this.stockCajas + " cajas | Peso: " + this.pesoTotal() 
            + " kg | Valor: $" + this.valorInventario();
    }
}
