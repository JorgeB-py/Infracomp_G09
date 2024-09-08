import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fabrica {
    public static String input(String mensaje) {
        try {
            System.out.println(mensaje);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        // Lectura de los inputs
        int numProductos = Integer.parseInt(input("Número de productos a producir por cada productor: "));
        int capDepProd = Integer.parseInt(input("Capacidad del depósito de producción: "));
        int capDepDist = Integer.parseInt(input("Capacidad del depósito de distribución: "));

        // Creación de los depósitos y cinta transportadora
        Deposito depProduccion = new Deposito(capDepProd);
        Deposito depDistribucion = new Deposito(capDepDist);
        CintaTransportadora cinta = new CintaTransportadora();

        // Creación de los productores
        Productor productorA1 = new Productor(TipoProdDis.A, depProduccion, numProductos, 1);
        Productor productorA2 = new Productor(TipoProdDis.A, depProduccion, numProductos, 2);
        Productor productorB1 = new Productor(TipoProdDis.B, depProduccion, numProductos, 1);
        Productor productorB2 = new Productor(TipoProdDis.B, depProduccion, numProductos, 2);

        // Creación de los distribuidores
        Distribuidor distribuidorA1 = new Distribuidor(TipoProdDis.A, depDistribucion, 1);
        Distribuidor distribuidorA2 = new Distribuidor(TipoProdDis.A, depDistribucion, 2);
        Distribuidor distribuidorB1 = new Distribuidor(TipoProdDis.B, depDistribucion, 1);
        
        // Creación de los operarios internos
        Distribuidor distribuidorB2 = new Distribuidor(TipoProdDis.B, depDistribucion, 2);
        Operario operarioProduccion = new Operario(TipoOperario.PRODUCCION, depProduccion, cinta);
        Operario operarioDistribucion = new Operario(TipoOperario.DISTRIBUCION, depDistribucion, cinta);

        // Inicio de los hilos
        productorA1.start();
        productorA2.start();
        productorB1.start();
        productorB2.start();
        distribuidorA1.start();
        distribuidorA2.start();
        distribuidorB1.start();
        distribuidorB2.start();
        operarioProduccion.start();
        operarioDistribucion.start();

        // Espera a que los hilos terminen
        try {
            System.out.println("\n\n+++++ Iniciando operación +++++\n\n");
            productorA1.join();
            productorA2.join();
            productorB1.join();
            productorB2.join();
            distribuidorA1.join();
            distribuidorA2.join();
            distribuidorB1.join();
            distribuidorB2.join();
            operarioProduccion.join();
            operarioDistribucion.join();
            System.out.println("\n\n+++++ Operación finalizada con éxito +++++\n\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
