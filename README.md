# Arquitectura de Software
## Examen de Segundo Parcial

**Descripción**: Sistema de un Banco que implementa la ejecución de operaciones 
de depósito y retiro de cuentas de ese banco. 
El sistema es monolito, utiliza una fuente de datos
volátil (en memoria) y asume un número de cuentas pre-existentes para fines demostrativos.

### Estilo Arquitectónico
Por Capas
  - Presentation layer (`UserInterface.class`, y clases en paquete `front`)
  - Business Logic Layer (`CuentaService.class`, y clases en paquete `service`)
  - Data Layer (`CuentaRepository.class`, y clases en paquete `repository`)

### Casos de Uso
1. Crear Cuentas
2. Realizar depósitos a una cuenta
3. Realizar retiros a una cuenta
4. Consultar una cuenta

## Patrones de Diseño
Los comentarios acerca del cómo y por qué se utilizaron los siguientes patrones
se encuentran en la clase `Main`.

#### Estructurales
- Facade
- Singleton

#### Creacionales
- Factory

#### Comportamiento
- Command

### Otras Notas

Para este proyecto se decidió modificar lo menos posible el esqueleto de clases inicial.

La clase UserInterface captura el input del usuario y crea los comandos correspondientes
a un depósito, retiro, alta o consulta de cuenta, por medio de la clase auxiliar OperationFactory.
Los comandos creados son enviados al servicio CuentaService. Éstos llaman al método de CuentaService
correspondiente con parámetros.

La clase CuentaService hace validaciones simples y manda a llamar al singleton del repositorio,
mientras que regresa el resultado a la pantalla directamente.

El repositorio es un singleton representado por RepositorySingleton
y hereda todos los métodos de CuentaRepository. Los datos del repositorio son
gestionados por medio de CuentaService.