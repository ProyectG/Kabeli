# Kabeli

## Resumen:
Api simple de usuarios contiene metodos de lista, busqueda por UUID, agregar, eliminar y un login.
el aplicativo esta realizado con springboot/graddle, por lo que contiene un tomcat embebido.

## Uso

Descargar, compilar o bajar el archivo pre-compilado, ejecutar: **java -jar ${aplicativo}.jar**
Se configuro puerto por defecto: **4567**

## Metodos:
```
PUT
   /api/v1/kabeli/user/add
   Metodo que permite agregar usuarios.
POST
  /api/v1/kabeli/user/update
  Metodo que permite actualizar usuarios con la misma estructura que agregar, el correo es la llave.
  Este metodo solicita el header Authorization con el token de login.
POST
  /api/v1/kabeli/user/login
  Metodo que permite logearte correo/password
GET
  /api/v1/kabeli/user/list
  Metodo que permite listar a los usuarios.
GET
  /api/v1/kabeli/user/list/{id}
  Metodo que permite listar por un UUID exacto.
DELETE
  /api/v1/kabeli/user/delete/{id}
  Metodo que permite eliminar usuarios por UUID.
```
 
 ## Documentacion API
 Se puede informar mas sobre la informacion de la api y sus metodos en la siguiente url, **luego de haber ejecutado la aplicacion.**
 
 **localhost:4567/apidoc.html**
 
 ## BD.
 Tiene un servidor local H2, para su ingreso requiere ingresar la siguiente url.
 **http://localhost:4567/h2-console/**
 
 - Driver Class: org.h2.Driver
 - JDBC URL: jdbc:h2:mem:kabeliTest
 - UserName: sa
 - Password:
 
