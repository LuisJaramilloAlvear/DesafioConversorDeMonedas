# Conversor de Monedas

## Descripción

**Conversor de Monedas** es una aplicación que permite consultar y consumir la API de [exchangerate-api](https://www.exchangerate-api.com/). Los usuarios pueden ingresar una cantidad en una moneda específica y convertirla a otra moneda de su elección utilizando las tasas de cambio actuales.

## Requisitos

- Java SDK 17
- [Gson](https://github.com/google/gson) para el manejo de JSON.

## Instalación

Para correr este proyecto localmente, sigue estos pasos:

1. Clona este repositorio en tu máquina local.
   ```bash
   git clone https://github.com/tu_usuario/conversor-de-monedas.git
   ```
2. Navega al directorio del proyecto.
   ```bash
   cd conversor-de-monedas
   ```
3. Añade la dependencia de Gson. Puedes hacerlo añadiéndolo a tu archivo `pom.xml` o descargando el JAR y agregándolo manualmente en tu IDE.
    - **Maven**:
      ```xml
      <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.9</version>
      </dependency>
      ```
4. Compila y ejecuta el proyecto con tu entorno de desarrollo Java preferido.

## Uso

1. Ejecuta la aplicación.
2. Ingresa la cantidad que deseas convertir.
3. Selecciona la moneda de origen.
4. Selecciona la moneda de destino.
5. Obtén la cantidad convertida utilizando las tasas de cambio actuales de la API de exchangerate-api.

## Capturas de Pantalla

A continuación se muestran algunas capturas de pantalla del programa funcionando:

![Pantalla de Inicio](images/1.png)
*Aqui pueden notar las primeras opciones , tenemos un listado de 8 tipos de moneda a elegir,
como estoy utilizando HashMap podemos agregar mas monedas de forma dinamica sin afectar el 
funcionamiento del codigo.*

![Pantalla de Conversión](images/3.png)
*Aqui determinamos la opcion 5 (CLP - Peso Chileno) y le decimos al programa que queremos convertir la cantidad de
750.000, posteriormente nos solicitara a que tipo de moneda queremos convertirlo, para finalmente recibir el resultado
ya convertido, estaremos en un LOOP hasta que indiquemos que queremos salir con la opcion que siempre aparecera al final 
de los tipos de monedas, esta opcion podria variar si agregas mas monedas.*

![Guardando el Registro](images/resultado.png)
*Cada vez que el usuario hace una conversion se va generando un log donde queda guardado con fecha y hora la conversion
realizada.*

## Contribución

Las contribuciones son bienvenidas. Para contribuir, sigue estos pasos:

1. Haz un fork de este repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva funcionalidad'`).
4. Sube tu rama (`git push origin feature/nueva-funcionalidad`).
5. Crea un nuevo Pull Request.

## Licencia

Este proyecto está licenciado bajo la licencia MIT. Consulta el archivo `LICENSE` para más detalles.
