package dulceCodigo.models;

public class Camion {
    public String placa;
    public double capacidadKg;
    public double cargaActualKg;
    public String ciudadAsignada;
    public boolean disponible;
    
    public Camion(String placa, double capacidadKg) {
        this.placa = placa;
        this.ciudadAsignada = "Sin asignar";
        this.disponible = true;
        this.cargaActualKg = 0.0;
        
        if (capacidadKg < 100) {
            this.capacidadKg = 100;
            System.out.println("⚠️ Capacidad inválida para " + placa + ": se ajustó a 100 kg");
        } else {
            this.capacidadKg = capacidadKg;
        }
    }
    
    public Camion(String placa, double capacidadKg, String ciudad) {
        this.placa = placa;
        this.ciudadAsignada = ciudad;
        this.cargaActualKg = 0.0;
        this.disponible = true;
        
        if (capacidadKg < 100) {
            this.capacidadKg = 100;
            System.out.println("⚠️ Capacidad inválida para " + placa + ": se ajustó a 100 kg");
        } else {
            this.capacidadKg = capacidadKg;
        }
        
        System.out.println("🚗 Camión " + this.placa + " asignado a " + this.ciudadAsignada + " (" + this.capacidadKg + " kg)");
    }
    
    public Camion(Camion original) {
        this.placa = original.placa;
        this.capacidadKg = original.capacidadKg;
        this.cargaActualKg = original.cargaActualKg;
        this.ciudadAsignada = original.ciudadAsignada;
        this.disponible = original.disponible;
    }
    
    public double espacioLibre() {
        return this.capacidadKg - this.cargaActualKg;
    }
    
    public boolean cabe(double kg) {
        return kg > 0 && disponible && (cargaActualKg + kg <= capacidadKg);
    }
    
    public double porcentajeOcupacion() {
        return (this.cargaActualKg / this.capacidadKg) * 100;
    }
    
    public void cargarProducto(Producto p, int cajas) {
        double kilos = cajas * p.pesoCajaKg;
        
        if (!p.puedeDespachar(cajas)) {
            System.out.println("❌ " + p.nombre + " no tiene " + cajas + " cajas");
        } else if (kilos > this.espacioLibre()) {
            System.out.println("❌ No caben " + kilos + " kg en el camión " + this.placa);
        } else {
            p.despachar(cajas);
            this.cargaActualKg = this.cargaActualKg + kilos;
            System.out.println("✅ +" + cajas + " cajas de " + p.nombre + " (" + kilos + " kg) al camión " + this.placa);
        }
    }
    
    public String ficha() {
        return "🚗 Placa " + placa + " | " + ciudadAsignada + " | " + capacidadKg + " kg máx | carga: " + cargaActualKg 
            + " kg | espacio libre: " + espacioLibre() + " kg | ocupación: " + String.format("%.2f", porcentajeOcupacion()) + "%";
    }
}
