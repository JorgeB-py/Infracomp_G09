import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de productos a producir: ");
        int cantidadProductos = scanner.nextInt();
        System.out.println("Ingrese la capacidad del deposito de produccion: ");
        int capacidadDepositoProduccion = scanner.nextInt();
        System.out.println("Ingrese la capacidad del deposito de distribucion: ");
        int capacidadDepositoDistribucion = scanner.nextInt();
        scanner.close();
        Deposito depositoProduccion = new Deposito(capacidadDepositoProduccion);
        Deposito depositoDistribucion = new Deposito(capacidadDepositoDistribucion);
        CintaTransportadora cintaTransportadora = new CintaTransportadora();

        int cantidadProductosA = (cantidadProductos / 2)+(cantidadProductos % 2);
        int cantidadProductosB = cantidadProductos / 2;


        OperarioProductor op1 = new OperarioProductor(TipoProducto.A, (cantidadProductosA/2)+(cantidadProductosA%2), depositoProduccion);
        OperarioProductor op2 = new OperarioProductor(TipoProducto.A, cantidadProductosA/2, depositoProduccion);
        OperarioProductor op3 = new OperarioProductor(TipoProducto.B, (cantidadProductosB/2)+(cantidadProductosB%2), depositoProduccion);
        OperarioProductor op4 = new OperarioProductor(TipoProducto.B, cantidadProductosB/2, depositoProduccion);

        

        OperarioDistribuidor od1 = new OperarioDistribuidor(TipoProducto.A, depositoDistribucion);
        OperarioDistribuidor od2 = new OperarioDistribuidor(TipoProducto.A, depositoDistribucion);
        OperarioDistribuidor od3 = new OperarioDistribuidor(TipoProducto.B, depositoDistribucion);
        OperarioDistribuidor od4 = new OperarioDistribuidor(TipoProducto.B, depositoDistribucion);

        OperarioInterno opInterno = new OperarioInterno(depositoProduccion, depositoDistribucion, TipoOperario.A, cintaTransportadora);
        OperarioInterno opInterno2 = new OperarioInterno(depositoProduccion, depositoDistribucion, TipoOperario.B, cintaTransportadora);

        op1.start();
        op2.start();
        op3.start();
        op4.start();
        od1.start();
        od2.start();
        od3.start();
        od4.start();
        opInterno.start();
        opInterno2.start();
        
    }
}
