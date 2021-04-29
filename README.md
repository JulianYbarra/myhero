# MyHero (Challenge)

_En este challenge lo que esperamos es que puedas hacer una aplicación que funcione en dispositivos Android usando el lenguaje Kotlin con la temática de superhéroes de Marvel. Tendrás que obtener la información de una API que te proveemos y respetar el diseño propuesto lo más fielmente que puedas._

## Comenzando 🚀

### Requerimientos 📋

_Autenticación: Se debe integrar Firebase para realizar el registro/ingreso a través de Facebook o Email (sólo esas 2 vías)_ Actualmente no esta en funcionamiento porque se borro el proyecto de firebase, para acceder sin el login comentar linea 54 del MainActivity.kt

```
54     //tryLogin()
```

_Pantalla Principal: esta pantalla consta de un TabBar en la parte inferior_

_Personajes: Listado de personajes de Marvel, con un paginado (estilo scroll infinito) de 15 elementos, al hacer click en un item, navegar al detalle_

_Eventos: Listado de eventos futuros, con un límite de 25 elementos y ordenados por fecha de comienzo de manera ascendente. El diseño contiene una flecha arriba/abajo para expandir/colapsar la celda. Cuando se expande se muestra más información de los eventos._

_Detalle del Personaje: info del personaje (foto, nombre, descripción) + listado de cómics en los que figura._

### Requerimientos UI 📋

_Parte del objetivo de este Challenge es poder evaluar tus habilidades para respetar las pantallas y lineamientos de diseño tal como las plantean los equipos de UX / UI._

## Construido con 🛠️

_Se utilizo la siguiente api para hacer el challenge_ https://developer.marvel.com/docs#!/

* [Coil](https://github.com/coil-kt/coil)
* [Koin](https://insert-koin.io/)
* [Retrofit](https://square.github.io/retrofit/)
* [JetPack](https://developer.android.com/jetpack?hl=es-419)

## Requerimientos minimos 

* Lenguaje: Kotlin 
* Patrón MVVM + Repository 
* Coroutines. Sugerencia: Flow 
* Retrofit 
* ViewModel con LiveData 

## Requerimientos deseables
 
* Inyección de dependencias. Sugerencia: Koin 
* Navigation 
* Data binding 

## Plazo de entrega
_10 días corridos desde que aceptas comenzarlo_

