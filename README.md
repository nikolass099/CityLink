# CityLink
CityLink es una aplicacion de consola desarrolada en Java como Proyecto integrador para la materia Programacion I (2026), la cual Permite registrar y buscar clientes, administrar repartidores y pedidos, asignar entregas, actualizar estados y consultar listados y reportes.

## Integrante y Datos académicos
 **Integrante:** Ruiz Nicolas Fabricio
- **Materia:** Programación I
- **Institución:** Universidad Nacional de Villa Mercedes
- **Año:** 2026

## Información técnica

- **Lenguaje:** Java 17
- **Gestor de construcción:** Apache Maven
- **Versión:** 1.4-SNAPSHOT
- **Tipo de aplicación:** consola
- **Persistencia:** en memoria; los datos se reinician al cerrar el programa

## Requisitos

- JDK 17 o superior.
- Apache Maven instalado y disponible en el `PATH`.
- Una terminal con acceso a la carpeta raíz del proyecto.

Verifica las herramientas con:

```powershell
java -version
mvn -version
```

## Compilar y ejecutar

Desde la carpeta del proyecto que contiene `pom.xml`, compila:

```powershell
mvn clean package
```

Ejecuta la aplicación con:

```powershell
java -cp target/classes com.citylink.app.Main
```

Al iniciar, se cargan datos de prueba en memoria y aparece el menú principal. Ingresa el número de la opción deseada y sigue las instrucciones. Selecciona **11** para salir.

## Funcionalidades

El menú principal permite:

1. Registrar clientes.
2. Registrar repartidores y seleccionar su vehículo.
3. Registrar pedidos normales, urgentes o frágiles.
4. Asignar pedidos a repartidores, verificando la capacidad del vehículo.
5. Cambiar el estado de los pedidos.
6. Buscar clientes por código o nombre.
7. Listar pedidos con información de costo y tiempo estimado.
8. Consultar el estado de pedidos o repartidores.
9. Consultar el reporte de importes y comisiones.
10. Eliminar clientes.
11. Salir.

Las transiciones previstas para los pedidos son **Pendiente → En camino → Entregado** y **Pendiente → Cancelado**.

## Declaración de uso de IA

**Herramienta Utilizada**: Gemini Notebook LLMs.

**Propósito de uso**: Se utilizo IA para la explicación de errores técnicos y trabas a la hora de crear el programa

**Verificación de resultado**: Todos los códigos sugeridos por la herramienta fueron revisados, compilados en JDK 21 y validados manualmente mediante pruebas de consola por parte del integrante del grupo, para garantizar la integridad de la lógica de negocio.
