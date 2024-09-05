# Infracomp_G09
Caso 1 infracomp

## Integrantes:
- Wilmer Arévalo, 202214720
- Jorge Bustamante, 202210581
- Paul Paffen, 202222496

## Entendimiento del Problema
El problema presentado consiste en modelar una arquitectura de concurrencia en una fábrica mediante el uso de hilos (*threads*), donde se coordina la producción y distribución de dos tipos de productos, "A" y "B". En la fábrica, los operarios productores son responsables de crear productos, los cuales deben ser almacenados temporalmente en un depósito de producción antes de ser transportados a un depósito de distribución a través de una cinta transportadora. Los operarios distribuidores, por su parte, son los encargados de retirar estos productos del depósito de distribución para llevarlos a su destino final.

Cada uno de estos actores (productores, operarios internos y distribuidores) se representa mediante un hilo que debe ejecutar sus tareas de forma concurrente, siguiendo ciertas restricciones y asegurando que no haya conflictos en el acceso a recursos compartidos como los depósitos y la cinta transportadora. El objetivo es diseñar un sistema que permita la coordinación eficiente entre estos hilos, garantizando que los productos se muevan de manera ordenada a través de las distintas etapas del proceso de producción y distribución, evitando problemas como la sobresaturación de depósitos, intentos de acceso a recursos vacíos, o hilos que quedan en espera indefinidamente.

La correcta sincronización entre los hilos es crucial, especialmente considerando que los operarios solo pueden manejar productos de un tipo específico (A o B), y que las operaciones en los depósitos y la cinta transportadora deben ser coordinadas para evitar condiciones de carrera (*race conditions*). Además, el sistema debe manejar correctamente la finalización de los hilos cuando se han procesado todos los productos, utilizando señales especiales de terminación (`FIN_A` y `FIN_B`).

## Arquitectura General de la Solución
Para la solución se ha diseñado el siguiente Modelo Conceptual. (Figura 1)
![Modelo Conceptual](https://github.com/user-attachments/assets/ab3121d1-1dd5-4cd4-8d5b-388ac834caab)
Figura 1. Modelo Conceptual

## Estrategias de Solución
Para abordar el problema, se han diseñado las siguientes estrategias:

1. **Estructura del Sistema**: 
   - Se han definido clases principales que representan a los diferentes actores en la fábrica: `OperarioProductor`, `OperarioDistribuidor`, y `OperarioInterno`. Cada uno de estos actores es modelado como un hilo que ejecuta sus tareas concurrentemente.
   - Además, se han implementado clases que modelan los recursos compartidos, como `Deposito`, que almacena productos en colas, y `CintaTransportadora`, que gestiona el movimiento de productos entre depósitos.

2. **Sincronización y Comunicación**:
   - Se ha implementado un mecanismo de sincronización utilizando las herramientas básicas de Java como `synchronized`, `wait`, `notify`, y `notifyAll`. Estos mecanismos aseguran que los hilos interactúen de manera segura con los recursos compartidos, evitando problemas de concurrencia como condiciones de carrera.
   - Los hilos productores almacenan productos en el `Deposito` de producción y notifican a los operarios internos para que muevan los productos a la cinta transportadora. Los operarios internos, a su vez, sincronizan su acceso a la cinta transportadora y mueven los productos al depósito de distribución.
   - Los distribuidores están sincronizados para esperar productos en el depósito de distribución y retirarlos cuando estén disponibles.

3. **Gestión de Finalización**:
   - Los hilos productores, al completar la producción de todos sus productos, generan un producto de terminación (`FIN_A` o `FIN_B`). Esto permite que los hilos distribuidores y operarios internos detecten cuándo deben terminar su ejecución.
   - Los operarios internos cuentan los productos de finalización que procesan y, cuando han manejado todos los productos de terminación, detienen su ejecución de manera ordenada.

4. **Espera Activa y Pasiva**:
   - Se ha definido que los operarios productores y distribuidores trabajen en espera pasiva (`wait`), de modo que solo actúan cuando los recursos que necesitan están disponibles, evitando la ocupación innecesaria de recursos.
   - Los operarios internos, por otro lado, operan en espera semi-activa (`yield`), cediendo el control a otros hilos cuando no pueden realizar su tarea, pero manteniéndose disponibles para actuar tan pronto como sea posible.

5. **Pruebas y Validación**:
   - Se han diseñado pruebas para validar el funcionamiento del sistema en diferentes escenarios, como la sobrecarga del depósito de producción, la sincronización de acceso a la cinta transportadora, y la correcta terminación de los hilos.

Este diseño y las estrategias implementadas aseguran un flujo de trabajo eficiente en la fábrica, con una correcta sincronización entre los hilos, evitando los problemas típicos de los sistemas concurrentes y asegurando que todas las tareas se completen de manera ordenada y sin errores.
