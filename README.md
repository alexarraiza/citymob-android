# citymob-android
Citymob's android app repository

##LICENCIA

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Licencia Creative Commons" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Esta obra está bajo una <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Licencia Creative Commons Atribución-CompartirIgual 4.0 Internacional</a>.

##DESPLIEGE

* Crear un API KEY en Google API Console y añadirlo en un archivo xml dentro de `res/values` con el siguiente contenido:

`<resources>
    <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">[API_KEY]</string>
</resources>`

* Configurar un servidor de BBDD con un endpoint DreamFactory como forma de acceso.

* Cambiar datos en: `src/main/java/com/bitbucket/hackersforgood/citymob/NetworkConstants.java` a los usados por su Dreamfactory.

* Cambiar los datos de acceso a Dreamfactory en las implementaciones de los `Repository`.
