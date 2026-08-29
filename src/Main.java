public class Main {
    public static void main(String[] args) {
        System.out.println("===== CREACIÓN DE CLIENTE Y PRODUCTOS =====");
        Cliente dulceria = new Cliente("Dulcería El Trébol", "Armenia");
        
        Producto arequipe = new Producto("Arequipe de café", 4.8, 21000);
        Producto caramelos = new Producto("Caramelos de café", 6.2, 20000);
        
        arequipe.recibir(100);
        caramelos.recibir(50);
        
        System.out.println("\n===== CREACIÓN DE CAMIÓN =====");
        Camion camion = new Camion("ABC-123", 500.0);
        
        System.out.println("\n===== CREACIÓN DE TRES PEDIDOS =====");
        Pedido pedido1 = new Pedido(dulceria, arequipe, 15);
        Pedido pedido2 = new Pedido(dulceria, caramelos, 100);
        Pedido pedido3 = new Pedido(dulceria, arequipe, 20);
        
        System.out.println("\n===== PROCESANDO PEDIDOS =====");
        System.out.println("📌 Procesando pedido 1 (normal):");
        pedido1.procesar(camion);
        
        System.out.println("\n📌 Procesando pedido 2 (excede stock):");
        pedido2.procesar(camion);
        
        System.out.println("\n📌 Procesando pedido 3 (dos veces):");
        pedido3.procesar(camion);
        System.out.println("📌 Intentando procesar pedido 3 nuevamente:");
        pedido3.procesar(camion);
        
        System.out.println("\n===== FICHAS FINALES =====");
        System.out.println(pedido1.ficha());
        System.out.println(pedido2.ficha());
        System.out.println(pedido3.ficha());
        
        System.out.println("\n===== PRUEBA DE mismoDestino() =====");
        Cliente dulceria2 = new Cliente("Otro cliente", "Armenia");
        Pedido pedido4 = new Pedido(dulceria2, arequipe, 10);
        System.out.println("¿Pedido 1 y Pedido 4 van al mismo destino? " + pedido1.mismoDestino(pedido4));
        System.out.println("Nota: Usamos .equals() para comparar Strings, NO == (que compara direcciones de memoria)");
        
        System.out.println("\n===== EXPERIMENTO 3: EL DUPLICADOR DE CAMIONES =====");
        System.out.println("📋 DIAGNÓSTICO:");
        System.out.println("(1) Predice la salida ANTES de ejecutar.");
        System.out.println("(2) ¿Cuántos objetos Camion existen realmente en memoria?");
        System.out.println("(3) ¿Por qué 'vaciar el respaldo' vacía también el original?");
        System.out.println("(4) El duplicado correcto usa un segundo new.\n");
        
        System.out.println("❌ MANERA INCORRECTA (sin copiar):");
        Camion original = new Camion("ABC-123", 1000.0);
        original.cargaActualKg = 400.0;
        
        Camion respadoIncorrecto = original;
        respadoIncorrecto.placa = "XYZ-999";
        respadoIncorrecto.cargaActualKg = 0.0;
        
        System.out.println("Original: " + original.placa + " con " + original.cargaActualKg + " kg");
        System.out.println("Respaldo: " + respadoIncorrecto.placa + " con " + respadoIncorrecto.cargaActualKg + " kg");
        System.out.println("⚠️ ¡Son el MISMO objeto! El respaldo modificó el original.\n");
        
        System.out.println("✅ MANERA CORRECTA (con constructor de copia):");
        Camion original2 = new Camion("ABC-123", 1000.0);
        original2.cargaActualKg = 400.0;
        
        Camion respadoCorrecto = new Camion(original2);
        respadoCorrecto.placa = "XYZ-999";
        respadoCorrecto.cargaActualKg = 0.0;
        
        System.out.println("Original: " + original2.placa + " con " + original2.cargaActualKg + " kg");
        System.out.println("Respaldo: " + respadoCorrecto.placa + " con " + respadoCorrecto.cargaActualKg + " kg");
        System.out.println("✅ ¡Dos objetos INDEPENDIENTES! El respaldo NO modificó el original.");
    }
}

class Producto {
    String nombre = "Sin nombre";
    int stockCajas = 0;
    double pesoCajaKg = 0.0;
    boolean aprobado = false;
    int precioCaja;
    int totalDespachado = 0;
    
    Producto() {
    }
    
    Producto(String nombre, double pesoCajaKg, int precioCaja) {
        this.nombre = nombre;
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
    
    double pesoTotal() {
        return this.stockCajas * this.pesoCajaKg;
    }
    int valorInventario(){
        return this.stockCajas * this.precioCaja;
    }
    boolean puedeDespachar(int cajas){
        return cajas > 0 && cajas <= this.stockCajas;
    }
    boolean esValioso() {
            return this.valorInventario() > 500000;
        }
    String ficha() {
        return "📦 " + this.nombre + " | " + this.stockCajas + " cajas | " + this.pesoTotal() + " kg | $" + this.valorInventario();
    }
    boolean esRentable() {
        return stockCajas * precioCaja > 500000;
    }
    
    boolean tieneMaxStockQue(Producto otro) {
        return this.stockCajas > otro.stockCajas;
    }
    
    void recibir(int cajas) {
        if (cajas > 0) {
            this.stockCajas = this.stockCajas + cajas;
            System.out.println("⬆ Entraron " + cajas + " cajas de " + this.nombre);
        } else {
            System.out.println("⛔ " + cajas + " Es una cantidad inválida");
        }
    }

    void despachar(int cajas) {
        if (this.puedeDespachar(cajas)) {
            this.stockCajas = this.stockCajas - cajas;
            this.totalDespachado = this.totalDespachado + cajas;
            System.out.println("⬇ Salieron " + cajas + " cajas de " + this.nombre);
        } else {
            System.out.println("⛔ Despacho de " + cajas + " cajas rechazado");
        }
    }
    
    String resumenDelDia() {
        return "📊 " + this.nombre + " | Stock: " + this.stockCajas + " cajas | Despachado hoy: " + this.totalDespachado + " cajas";
    }
}

class Camion {
    String placa = "SIN-PLACA";
    double capacidadKg = 0.0;
    double cargaActualKg = 0.0;
    String ciudadAsignada = "Sin asignar";
    boolean disponible = true;
    
    Camion() {
    }
    
    Camion(String placa, double capacidadKg) {
        this.placa = placa;
        this.capacidadKg = capacidadKg;
        this.cargaActualKg = 0.0;
        this.disponible = true;
    }
    
    Camion(Camion original) {
        this.placa = original.placa;
        this.capacidadKg = original.capacidadKg;
        this.cargaActualKg = original.cargaActualKg;
        this.ciudadAsignada = original.ciudadAsignada;
        this.disponible = original.disponible;
    }
    Camion(String placa, double capacidadKg, String ciudad) {
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
    
    double espacioLibre(){
        return this.capacidadKg - this.cargaActualKg;
    }
    boolean cabe(double kg) {
        return kg > 0 && disponible && (cargaActualKg + kg <= capacidadKg);
    }
    double porcentajeOcupacion() {
        return (this.cargaActualKg / this.capacidadKg) * 100;
    }

    String ficha() {
        return "Placa " + placa + " | " + ciudadAsignada + " | " + capacidadKg + " kg máx | carga: " + cargaActualKg + " kg | espacio libre: " + espacioLibre() + " kg | ocupación: " + porcentajeOcupacion() + "% | disponible: " + disponible;
    }


    void mostrarFicha() {
        System.out.println("Placa: " + placa + " | Ciudad: " + ciudadAsignada
                + " | Carga actual: " + cargaActualKg + " kg"
                + " | Capacidad: " + capacidadKg + " kg");
    }

    void cargar(double kg) {
        if (!cabe(kg)) {
            System.out.println("No se puede cargar: kg debe ser positivo, camión disponible y con espacio.");
        } else {
            cargaActualKg = cargaActualKg + kg;
            System.out.println("Se cargaron " + kg + " kg en el camión " + placa + ".");
        }
    }

    void descargar(double kg) {
        if (kg <= 0) {
            System.out.println("No se puede descargar: los kg deben ser positivos.");
        } else if (kg > cargaActualKg) {
            System.out.println("No se puede descargar: la carga resultante no puede ser negativa.");
        } else {
            cargaActualKg = cargaActualKg - kg;
            System.out.println("Se descargaron " + kg + " kg del camión " + placa + ".");
        }
    }

    void enviarATaller() {
        disponible = false;
        System.out.println("🔧 Camión " + placa + " fuera de servicio.");
    }
    void cargarVariosViajes(double kgPorViaje, int viajes) {
        for (int i = 1; i <= viajes; i++) {
            System.out.println("Intentando viaje " + i + ":");
            this.cargar(kgPorViaje);
        }
    }
    
    void cargarProducto(Producto p, int cajas) {
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
}
class Bodega {
    String nombre = "Sin nombre";
    String ciudad = "Sin ciudad";
    double capacidadKg = 0.0;
    double inventarioKg = 0.0;
    boolean activa = false;

    Bodega() {
    }

    String ficha() {
        return "Bodega: " + nombre + " | " + ciudad + " | " + capacidadKg + " kg máx | inventario: " + inventarioKg + " kg | activa: " + activa;
    }
}

class Pedido {
    Cliente cliente;
    Producto producto;
    int cajas;
    boolean despachado = false;
    
    Pedido(Cliente cliente, Producto producto, int cajas) {
        this.cliente = cliente;
        this.producto = producto;
        this.cajas = cajas;
        System.out.println("📋 Pedido registrado: " + cliente.nombre + " pide " + cajas + " cajas de " + producto.nombre);
    }
    
    double pesoDelPedido() {
        return this.cajas * this.producto.pesoCajaKg;
    }
    
    void procesar(Camion camion) {
        if (this.despachado) {
            System.out.println("⚠️ Pedido ya fue despachado");
            return;
        }
        
        int stockAntes = this.producto.stockCajas;
        camion.cargarProducto(this.producto, this.cajas);
        int stockDespues = this.producto.stockCajas;
        
        if (stockAntes > stockDespues) {
            this.despachado = true;
        }
    }
    
    boolean mismoDestino(Pedido otro) {
        return this.cliente.ciudad.equals(otro.cliente.ciudad);
    }
    
    String ficha() {
        return "📦 Pedido de " + this.cliente.nombre + " (" + this.cliente.ciudad + "): " + this.cajas 
            + " cajas de " + this.producto.nombre + " - " + this.pesoDelPedido() + " kg - despachado: " + this.despachado;
    }
}

class Cliente {
    String nombre = "Sin nombre";
    String ciudad = "Sin ciudad";
    int pedidos = 0;

    Cliente() {
    }
    
    Cliente(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pedidos = 1;
        System.out.println("🎉 Cliente registrado: " + nombre + " (" + ciudad + ")");
    }

    String ficha() {
        return nombre + " | Ciudad: " + ciudad + " | pedidos: " + pedidos;
    }
}

class Bascula {
    double pesoActual;
    double taraValor;
    
    Bascula() {
        pesoActual = 0;
        taraValor = 0;
    }
    
    void pesar(double peso) {
        pesoActual = peso;
        System.out.println("Peso medido: " + pesoActual + " kg");
    }
    
    void tarar() {
        taraValor = pesoActual;
        System.out.println("Báscula tarada. Valor de tara: " + taraValor + " kg");
    }
    
    double getPesoNeto() {
        double pesoNeto = pesoActual - taraValor;
        return pesoNeto;
    }
}
