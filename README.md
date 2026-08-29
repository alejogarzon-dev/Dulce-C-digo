Dulce Código — Simulador de gestión de pedidos
===============================================

Descripción
-----------
Proyecto de Programación 1 (Java) que modela una tienda de dulces: productos, clientes, camiones y pedidos. Sirve como ejercicio de POO (clases, encapsulamiento, constructores, getters/setters) y proporciona una pequeña simulación de creación y procesamiento de pedidos.

Características
---------------
- Modelos: Producto, Cliente, Pedido, Camión, Bodega, Báscula.
- Simulación básica de creación de pedidos y cálculo de totales/pesos.
- Código listo para abrir en IntelliJ IDEA y ejecutar la clase Main.

Inicio rápido
-------------
1. Clonar el repositorio:

   git clone https://github.com/alejogarzon-dev/Dulce-C-digo.git

2. Abrir en IntelliJ IDEA: File → Open → seleccionar la carpeta del proyecto.
3. Ejecutar la clase principal (Main) desde el IDE.

Ejecutar desde línea de comandos (genérico)
-----------------------------------------
Si prefieres compilar y ejecutar manualmente:

   javac -d out src\\**\\*.java
   java -cp out dulceCodigo.Main

Ejemplo de funcionamiento
-------------------------
Escenario: se crean 2 productos y un pedido para un cliente.
Salida de ejemplo (simulada):

   === Simulación Dulce Código ===
   Producto creado: Chocolate - Precio: 1500 - Peso: 0.2kg
   Producto creado: Caramelo - Precio: 500 - Peso: 0.05kg
   Cliente: Juan Pérez (ID: 1)
   Pedido #1: 2 x Chocolate, 5 x Caramelo -> Total: 5500
   Peso total del pedido: 0.6kg
   Pedido procesado y asignado al camión #3

Ajusta los nombres de clases/paquetes si tu IDE sugiere otra ruta; el objetivo es abrir Main y ejecutar la simulación.

Notas
-----
- Si añades un sistema de build (Maven/Gradle) será más sencillo compilar y gestionar dependencias.
- Este README omite la sección de contacto por petición explícita.
