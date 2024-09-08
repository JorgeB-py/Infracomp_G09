public class Operario extends Thread {
    private TipoOperario tipo;
    private Deposito deposito;
    private CintaTransportadora cinta;
    private int pFinales;

    public Operario(TipoOperario tipo, Deposito deposito, CintaTransportadora cinta) {
        this.tipo = tipo;
        this.deposito = deposito;
        this.cinta = cinta;
        this.pFinales = 0;
    }

    @Override
    public void run() {
        while (pFinales < 4) {
            Producto producto;
            if (tipo == TipoOperario.PRODUCCION) {
                while (deposito.estaVacio()) {
                    Thread.yield();
                }
                producto = deposito.extraer();
                System.out.println("--> Operario PRODUCCION ha extraído " + producto.getTipo() + " del depósito de producción");
                while (!cinta.estaVacio()) {
                    Thread.yield();
                }
                cinta.almacenar(producto);
                System.out.println("---> Operario PRODUCCION ha almacenado " + producto.getTipo() + " en la cinta");
            } else {
                while (cinta.estaVacio()) {
                    Thread.yield();
                }
                producto = cinta.extraer();
                System.out.println("----> Operario DISTRIBUCION ha extraído " + producto.getTipo() + " de la cinta");
                while (deposito.estaLleno()) {
                    Thread.yield();
                }
                deposito.almacenar(producto, true);
                System.out.println("-----> Operario DISTRIBUCION ha almacenado " + producto.getTipo() + " en el depósito de distribución");
            }
            if (producto.getTipo() == TipoProducto.FIN_A || producto.getTipo() == TipoProducto.FIN_B) {
                pFinales++;
                System.out.println("***** Operario " + tipo + " ha procesado un producto de terminación: " + producto.getTipo());
            }
        }
        System.out.println("Operario " + tipo + " ha terminado su trabajo");
    }

    public Operario(TipoOperario tipo) {
        this.tipo = tipo;
    }

    public TipoOperario getTipo() {
        return tipo;
    }

}
