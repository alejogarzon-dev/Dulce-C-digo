package dulceCodigo.models;

public class Cliente {
    public String nombre;
    public String ciudad;
    public int pedidos;

    public Cliente(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pedidos = 1;
        System.out.println("🎉 Cliente registrado: " + nombre + " (" + ciudad + ")");
    }

    public String ficha() {
        return nombre + " | Ciudad: " + ciudad + " | pedidos: " + pedidos;
    }
}
