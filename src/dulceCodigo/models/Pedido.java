package dulceCodigo.models;

public class Pedido {
    public Cliente cliente;
    public Producto producto;
    public int cajas;
    public boolean despachado;
    
    public Pedido(Cliente cliente, Producto producto, int cajas) {
        this.cliente = cliente;
        this.producto = producto;
        this.cajas = cajas;
        this.despachado = false;
        System.out.println("📋 Pedido registrado: " + cliente.nombre + " pide " + cajas + " cajas de " + producto.nombre);
    }
    
    public double pesoDelPedido() {
        return this.cajas * this.producto.pesoCajaKg;
    }
    
    public void procesar(Camion camion) {
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
    
    public boolean mismoDestino(Pedido otro) {
        return this.cliente.ciudad.equals(otro.cliente.ciudad);
    }
    
    public String ficha() {
        return "📦 Pedido de " + this.cliente.nombre + " (" + this.cliente.ciudad + "): " + this.cajas 
            + " cajas de " + this.producto.nombre + " - " + this.pesoDelPedido() + " kg - despachado: " + this.despachado;
    }
}
