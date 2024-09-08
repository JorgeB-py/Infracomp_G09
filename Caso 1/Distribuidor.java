public class Distribuidor extends Thread {
    private TipoProdDis tipo;
    private Deposito deposito;
    private int id;
    private boolean fin;

    public Distribuidor(TipoProdDis tipo, Deposito deposito, int id) {
        this.tipo = tipo;
        this.deposito = deposito;
        this.id = id;
        this.fin = false;
    }

    @Override
    public void run() {
        while (!fin) {
            Producto producto = deposito.extraer(TipoProducto.valueOf(tipo.toString()));
            System.out.println("-------> Distribuidor " + tipo + id + " ha distribuido " + producto.getTipo());
            if (producto.getTipo() == TipoProducto.FIN_A && tipo == TipoProdDis.A) {
                fin = true;
            } else if (producto.getTipo() == TipoProducto.FIN_B && tipo == TipoProdDis.B) {
                fin = true;
            }
        }
        System.out.println("##### Distribuidor " + tipo + id + " ha terminado su trabajo");
    }

}
