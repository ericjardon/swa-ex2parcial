# Arquitectura de Software
## Examen de Segundo Parcial

**Descripción**: Sistema de un Banco que implementa la ejecución de operaciones 
de depósito y retiro de cuentas de ese banco. 
El sistema es monolito, utiliza una fuente de datos
volátil (en memoria) y asume un número de cuentas pre-existentes para fines demostrativos.

El estilo de arquitectura es Por Capas (Layered).

Cumple con las funcionalidades de:
1. Crear Cuentas
2. Realizar depósitos a una cuenta
3. Realizar retiros a una cuenta

Si la operación se realiza sobre una cuenta de banco A, B o C, se cobra una comisión de acuerdo
al siguiente criterio:
- Si es banco "A" la comisión es 30 pesos
- Si es banco "B" la comisión es 15 pesos
- Si es banco "C" la comisión es 10 pesos


### Patrones Estructurales
- Facade
- Singleton

### Patrones Creacionales
- Factory

### Patrones de Comportamiento
- Command