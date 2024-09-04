public class Main {
    public static void main(String[] args) {
        Deposito depositoProduccion = new Deposito(100);
        Deposito depositoDistribucion = new Deposito(100);


        OperarioProductor op1 = new OperarioProductor(TipoProducto.A, 10, depositoProduccion);
        OperarioProductor op2 = new OperarioProductor(TipoProducto.A, 10, depositoProduccion);
        OperarioProductor op3 = new OperarioProductor(TipoProducto.B, 10, depositoProduccion);
        OperarioProductor op4 = new OperarioProductor(TipoProducto.B, 10, depositoProduccion);
        OperarioDistribuidor od1 = new OperarioDistribuidor(TipoProducto.A, depositoDistribucion);
        OperarioDistribuidor od2 = new OperarioDistribuidor(TipoProducto.A, depositoDistribucion);
        OperarioDistribuidor od3 = new OperarioDistribuidor(TipoProducto.B, depositoDistribucion);
        OperarioDistribuidor od4 = new OperarioDistribuidor(TipoProducto.B, depositoDistribucion);
        
        op1.start();
        op2.start();
        op3.start();
        op4.start();
        od1.start();
        od2.start();
        od3.start();
        od4.start();
    }
}
