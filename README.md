# Proyecto

## Modulo 2: usuarios

Este **proyecto** tiene como **objetivo** la **aplicación** práctica e **integración** de todos los **conceptos** de **Programación Orientada a Objetos** vistos en el **Módulo** 2. Deberás diseñar y construir un **sistema** básico que permita gestionar **usuarios**, diferenciar sus **permisos** mediante **roles** y registrar un **historial** de las **acciones** que realizan.

1. **Gestión** de **Usuarios**
El **sistema** debe mantener un **registro** de varios **Usuarios**.
Cada **Usuario** en el **sistema** debe tener la siguiente **información** asociada:
Un **Nombre Completo**.
Un **ID** de **Usuario** que sea único para identificarlo en el **sistema**.
Un **Nombre** de **Usuario** (**Username**) que también debe ser único y se usará para iniciar **sesión**.
Una **Contraseña** (para simplificar, se manejará como una **cadena** de **texto** simple, sin aplicar **técnicas** **criptográficas** avanzadas).

El **sistema** debe ser capaz de realizar las siguientes **operaciones** relacionadas con los **usuarios**

-Crear nuevos **usuarios**: Añadir un nuevo **usuario** al **sistema** con su **información** completa.

-Buscar un **usuario**: Encontrar un **usuario** específico, principalmente por su **ID** de **Usuario** o **Nombre** de **Usuario**.

-Actualizar **información** de un **usuario**: Modificar el **Nombre Completo** o la **Contraseña** de un **usuario** existente. La **Contraseña** solo se puede actualizar si se proporciona la **contraseña** actual correcta (**validación** simple).

-Eliminar un **usuario**: Remover un **usuario** existente del **sistema**.

2. **Roles** de **Usuario** y **Permisos**
Existen diferentes **Roles** que se pueden asignar a los **usuarios**. Inicialmente, el **sistema** debe soportar al menos dos **roles**: "**Administrador**" y "**Estándar**".
Cada **Usuario** debe tener asignado un único **Rol** en el **sistema**.
Las **operaciones** sobre los **usuarios** están restringidas según el **rol** del **usuario** que intenta realizarlas:

-Solo los **usuarios** con **rol** "**Administrador**" tienen **permiso** para crear, eliminar y actualizar la **información** de cualquier **usuario** en el **sistema** (incluyendo la de otros **administradores**).

-Los **usuarios** con **rol** "**Estándar**" solo tienen **permiso** para ver y actualizar su propia **información** de **perfil**. No pueden crear, eliminar o actualizar a otros **usuarios**.

3. **Historial** de **Acciones**
El **sistema** debe registrar un **Historial** de **Acciones** para cada **usuario**.
Cada **acción** registrada debe incluir:

-Una **Descripción** de lo que hizo el **usuario** (ej. "**Inició sesión**", "**Actualizó su perfil**", "**Creó usuario Juan**").

-Una **Marca** de **Tiempo** que indique cuándo ocurrió la **acción** (puede ser un **número** simple como el **resultado** de **System.currentTimeMillis()**).

-Cada **Usuario** debe tener su propio **historial** de **acciones**. Las **acciones** registradas para un **usuario** pertenecen lógicamente a ese **usuario** y no a otro.
El **sistema** debe permitir registrar una **acción** para un **usuario** específico.
El **sistema** debe permitir mostrar el **historial** completo de **acciones** de un **usuario** específico.

4. **Inicio** de **Sesión** (Simulado)
El **sistema** debe incluir una **función** básica para simular un **inicio** de **sesión**.
Esta **función** debe recibir un **Nombre** de **Usuario** y una **Contraseña**.
Si el **Nombre** de **Usuario** y la **Contraseña** coinciden con los de un **usuario** registrado en el **sistema**, la **función** debe indicar que el **inicio** de **sesión** fue exitoso y proporcionar acceso a la **información** del **usuario** (retornar el **objeto** **usuario**, por ejemplo).
Si las **credenciales** no coinciden, la **función** debe indicar que el **inicio** de **sesión** falló.