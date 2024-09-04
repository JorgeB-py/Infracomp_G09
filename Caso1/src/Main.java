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


        OperarioProductor op1 = new OperarioProductor(TipoProducto.A, cantidadProductos, depositoProduccion);
        OperarioProductor op2 = new OperarioProductor(TipoProducto.A, cantidadProductos, depositoProduccion);
        OperarioProductor op3 = new OperarioProductor(TipoProducto.B, cantidadProductos, depositoProduccion);
        OperarioProductor op4 = new OperarioProductor(TipoProducto.B, cantidadProductos, depositoProduccion);

        

        OperarioDistribuidor od1 = new OperarioDistribuidor(TipoProducto.A, depositoDistribucion);
        OperarioDistribuidor od2 = new OperarioDistribuidor(TipoProducto.A, depositoDistribucion);
        OperarioDistribuidor od3 = new OperarioDistribuidor(TipoProducto.B, depositoDistribucion);
        OperarioDistribuidor od4 = new OperarioDistribuidor(TipoProducto.B, depositoDistribucion);

        OperarioInterno opInterno = new OperarioInterno(depositoProduccion, depositoDistribucion, TipoOperario.A);
        OperarioInterno opInterno2 = new OperarioInterno(depositoProduccion, depositoDistribucion, TipoOperario.B);

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
