# Sputnikfy
[![Build Status](https://travis-ci.com/ivankwist/sputnikfy.svg?branch=master)](https://travis-ci.com/ivankwist/sputnikfy)
[![codecov](https://codecov.io/gh/ivankwist/sputnikfy/branch/master/graph/badge.svg)](https://codecov.io/gh/ivankwist/sputnikfy)

Trabajo Práctico 1 de la materia Programación Distribuida II de la Universidad Nacional de Avellaneda.

Nos contactan desde Volgogrado los dueños de Sputnikfy una plataforma rusa de música para que desde Argentina los ayudemos con el desarrollo. 

La idea es que sea una app cliente/servidor pero para no mandar evento por evento se guardará un archivo XML localmente para luego ser subido por el dispositivo móvil.  

## Requisitos

Para usar la aplicación es necesario contar con Docker y Docker Compose instalado.

## Instalación

Una vez clonado el repositorio, debemos crear la imagen de Docker corriendo el siguiente comando en la raiz del proyecto:

```
docker build -t sputnikfy:latest .
```

Luego, para levantar el contenedor corremos el siguiente comando en el mismo directorio:

```
docker-compose up
```

Hecho esto, deberiamos tener la aplicación de Sputnikfy en ejecución.

## Modo de uso

Sputnikfy valida archivos XML de actividad en el puerto 8080 del URL raíz. 

```
http://127.0.0.1:8080/
```

Para realizar esta validación es necesario enviar en un POST un archivo XML con el nombre de campo "file". La aplicación devolvera un JSON con los siguientes campos:

* validation: Booleano con el resultado de la validacion
* error: String con el error de la validacion. Si se tratara de un archivo valido, el mismo será null.

El código HTTP será 200 si se trata de un archivo valido y un 406 de un no valido.

