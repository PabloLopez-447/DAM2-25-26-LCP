package UD1.actividad1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import UD1.ACT1.excepciones.ArchivoNoExisteException;
import UD1.ACT1.excepciones.DirectorioNoExisteException;
import UD1.ACT1.excepciones.NoEsDirectorioException;

public class OperacionesIO {

    public static String ruta = "C:/";

    /*
     * 1. visualizarContenido(String ruta)
     * • Lista el contenido de un directorio (solo el nivel actual).
     * • Muestra nombre, tipo (<DIR> o <FICHERO>), tamaño en KB (solo ficheros) y
     * fecha de última modificación.
     * • Los siguientes errores que se puedan producir se controlarán lanzado
     * excepciones propias y son:
     * o Si el parámetro introducido no existe, se visualizará un mensaje de error
     * correspondiente.
     * o Si el parámetro introducido no representa a un directorio, se visualizará
     * un mensaje de error
     * correspondiente.
     */

    public static void visualizarContenido(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException {
        File dir = new File(ruta);

        Utilidades.validarDirectorio(dir);

        File[] ficheros = dir.listFiles();
        if (ficheros != null) {
            for (File f : ficheros) {
                Utilidades.mostrarInfo(f, "");
            }
        }
    }

    public static void listarDirectorios(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException {
        System.out.println("LISTANDO DIRECTORIO " + ruta);
        File dir = new File(ruta);
        System.out.println("--");

        Utilidades.validarDirectorio(dir);
        File[] ficheros = dir.listFiles();
        if (ficheros != null) {
            for (File f : ficheros) {
                Utilidades.mostrarInfo(f, "");
            }
        }
    }

    /*
     * 2. recorrerRecursivo(String ruta) - Lista el contenido de un directorio y
     * todos sus subdirectorios con sangría.
     */
    // ojo con que se recorra al revés

    // solo se resuelve si es un directorio
    public static void recorrerRecursivo(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException {
        System.out.println("recorrer recursivo: " + ruta);
        File dir = new File(ruta);

        Utilidades.validarDirectorio(dir);
        recorrer(dir, "---");

    }

    public static void recorrer(File dir, String sangria) {

        File[] ficheros = dir.listFiles();
        if (ficheros == null) {
            return;
        }

        for (File file : ficheros) {
            Utilidades.mostrarInfo(file, sangria);

            if (file.isDirectory()) {
                // recorrer(dir, sangria + "---");
                // INFINITO - no entra nunca al objeto file si no al raíz de nuevo xd

                recorrer(file, sangria + "---");
            }
        }

    }

    /*
     * 3.- filtrarPorExtension(String ruta, String extension)
     * Lista solo los archivos que tengan la extensión en el directorio indicado.
     * Parámetros:
     * • Una extensión (por ejemplo: .txt, .pdf, .jpg).
     * • Un directorio.
     * Errores para controlar:
     * Si la ruta no existe → DirectorioNoExisteException
     * Si la ruta no es un directorio → NoEsDirectorioException
     * Si no se encuentra ningún archivo con esa extensión → mostrar mensaje
     * informativo.
     */

    public static void filtrarPorExtension(String ruta, String extension)
            throws DirectorioNoExisteException, NoEsDirectorioException {

        System.out.println("Filtro extensiones");

        File dir = new File(ruta);
        // se pasa un filtro para que consuma menos recursos que un comparator
        // File[] ficheros = dir.listFiles(new Filtro(".java"));
        File[] ficheros = dir.listFiles(new Filtro(extension));

        if (ficheros == null || ficheros.length == 0) {
            System.out.println("No hay ficheros con esa extension.");
            return;
        }

        for (File file : ficheros) {
            Utilidades.mostrarInfo(file, "");
        }

    }

    /*
     * 4.- filtrarPorExtensionYOrdenar(String ruta, String extension, boolean
     * descendente)
     * Lista solo los archivos que tengan la extensión indicada en el directorio
     * especificado y en todos sus subdirectorios
     * (recursivo), ordenados alfabéticamente.
     * ✓ Si descendente es true → orden de Z a A.
     * ✓ Si descendente es false → orden de A a Z.
     * ✓ La ordenación ignora mayúsculas y minúsculas.
     * Parámetros:
     * • String extension → extensión a buscar.
     * • String ruta → ruta del directorio donde buscar.
     * • boolean descendente → true para orden descendente, false para ascendente.
     * Errores: la ruta no existe, no es un directorio
     * Si no se encuentra ningún archivo con esa extensión → mostrar mensaje
     * informativo.
     * 
     */

    // con Comparator y arrays.sort
    public static void filtrarPorExtensionYOrdenar(String ruta, String extension, boolean descendente)
            throws DirectorioNoExisteException, NoEsDirectorioException {

        File dir = new File(ruta);
        Utilidades.validarDirectorio(dir);

        // File[] ficheros = dir.listFiles();

        ArrayList<File> filtrados = new ArrayList<>();

        // recursivo espero
        recorridoYFiltrado(dir, extension.toLowerCase(), filtrados);

        if (filtrados.isEmpty()) {
            System.out.println("no se encontró ningún archivo con la extensión " + extension);
            return;
        }

        File[] archivosArray = filtrados.toArray(new File[0]);
        Arrays.sort(archivosArray, new ComparaNombres(descendente));

        System.out.println("encontrados y ordenados:");
        for (File archivo : archivosArray) {
            System.out.println(archivo.getAbsolutePath());
        }

    }

    public static void recorridoYFiltrado(File dir, String extension, ArrayList<File> lista) {
        File[] ficheros = dir.listFiles();
        if (ficheros == null)
            return;

        for (File f : ficheros) {
            if (f.isDirectory()) {
                recorridoYFiltrado(f, extension, lista);
            } else if (f.getName().toLowerCase().endsWith(extension.toLowerCase())) {
                lista.add(f);
            }
        }
    }

    /*
     * 6.- copiarArchivo(String origen, String destino)
     * • Copia un archivo desde una ruta de origen a una ruta de destino.
     * • Si el directorio destino no existe, lo crea.
     * • Si ya existe un archivo con el mismo nombre en destino, lo sobrescribe.
     * Parámetros:
     * • String origen → ruta completa del archivo a copiar.
     * • String destino → ruta completa donde se guardará la copia.
     * Errores a controlar:
     * • Si el archivo origen no existe → ArchivoNoExisteException.
     * • Si el origen es un directorio → mostrar mensaje de error.
     * • Si no se puede crear el directorio destino → mostrar mensaje de error.
     */

    // con fileinput Stream -- abrir y cerrar en try catch -- pepa lo hizo con while
    // y -1 (buffer,0,byte)

    public static void copiarArchivo(String origen, String destino)
            throws DirectorioNoExisteException, NoEsDirectorioException, ArchivoNoExisteException {

        File archivoOrigen = new File(origen);
        File archivoDestino = new File(destino);

        if (!archivoOrigen.exists()) {
            throw new ArchivoNoExisteException("El archivo origen no existe: " + origen);
        }

        // Obtener y validar el directorio padre del archivo destino
        File dirDestino = archivoDestino.getParentFile();
        Utilidades.validarDirectorio(dirDestino);

        if (!dirDestino.exists() && !dirDestino.mkdirs()) {
            throw new DirectorioNoExisteException(
                    "no se pudo crear el directorio destino");
        }
        // Utilidades.validarDirectorio(archivoDestino); //solo esto salta la excepcion
        // de que no es directorio

        // crear directorio si no existe
        if (dirDestino != null && !dirDestino.exists() && !dirDestino.mkdirs()) {
            throw new DirectorioNoExisteException(
                    "No se pudo crear el directorio de destino" + dirDestino.getAbsolutePath());
        }

        try (
                FileInputStream in = new FileInputStream(archivoOrigen);
                FileOutputStream out = new FileOutputStream(archivoDestino)) {

            byte[] buffer = new byte[1024]; // buffer de 1kb ?
            int bytesLeidos;
            while ((bytesLeidos = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesLeidos);
            }

            System.out.println("copiado de " + origen + " a " + destino);

        } catch (IOException e) {
            System.err.println("error al copiar: " + e.getMessage());
        }
    }

    /*
     * 7.- moverArchivo(String origen, String destino)
     * • Mueve un archivo desde una ruta de origen a una ruta de destino.
     * • Si el directorio destino no existe, lo crea.
     * • Si ya existe un archivo con el mismo nombre en destino, lo sobrescribe.
     * Parámetros:
     * • String origen → ruta completa del archivo a mover.
     * • String destino → ruta completa donde se moverá el archivo.
     * Errores a controlar:
     * • Si el archivo origen no existe → ArchivoNoExisteException.
     * • Si el origen es un directorio → mostrar mensaje de error.
     * • Si no se puede crear el directorio destino → mostrar mensaje de error.
     */
    public static void moverArchivo(String origen, String destino)
            throws DirectorioNoExisteException, NoEsDirectorioException, ArchivoNoExisteException {

        File archivoOrigen = new File(origen);
        // File archivoDestino = new File(destino);

        if (!archivoOrigen.exists()) {
            throw new ArchivoNoExisteException("archivo origen no existe" + origen);
        }
        if (archivoOrigen.isDirectory()) {
            System.out.println("el origen es un directorio");
            return;
        }

        copiarArchivo(origen, destino);

        if (!archivoOrigen.delete()) {
            System.err.println("archivo origen no borrado");
        } else {
            System.out.println("movido de " + origen + " a " + destino);
        }
    }

    /*
     * 8.- copiarDirectorio(String origen, String destino)
     * • Copia un directorio completo con todo su contenido (archivos y
     * subdirectorios).
     * • Mantiene la estructura original.
     * • Si el directorio destino no existe, lo crea.
     * • Mostar información de los directorios y archivos copiados.
     * Parámetros:
     * • String origen → ruta del directorio a copiar.
     * • String destino → ruta donde se creará la copia.
     * Errores a controlar:
     * • Si el directorio origen no existe → DirectorioNoExisteException.
     * • Si el origen no es un directorio → NoEsDirectorioException.
     * • Si no se puede crear el directorio destino → mostrar mensaje de error.
     */
    public static void copiarDirectorio(String origen, String destino)
            throws DirectorioNoExisteException, NoEsDirectorioException, ArchivoNoExisteException {

        File dirOrigen = new File(origen);
        File dirDestino = new File(destino);

        if (!dirOrigen.exists()) {
            throw new DirectorioNoExisteException("directorio origen no existe");
        }
        if (!dirOrigen.isDirectory()) {
            throw new NoEsDirectorioException("ruta origen no es un directorio");
        }

        if (!dirDestino.exists() && !dirDestino.mkdirs()) {
            throw new DirectorioNoExisteException("no se pudo crear el directorio destino: " + destino);
        }

        File[] elementos = dirOrigen.listFiles();

        for (File elemento : elementos) {
            String destinoElemento = destino + File.separator + elemento.getName();
            if (elemento.isDirectory()) {
                copiarDirectorio(elemento.getAbsolutePath(), destinoElemento);
            } else {
                copiarArchivo(elemento.getAbsolutePath(), destinoElemento);
            }
        }
    }

    /*
     * 9.- borrar(String ruta)
     * • Borra un archivo o un directorio.
     * • Si es un directorio, borra todo su contenido recursivamente y luego el
     * propio directorio.
     * • Mostrar información de los archivos y directorios borrados.
     * Parámetros:
     * • String ruta → ruta del archivo o directorio a borrar.
     * Errores a controlar:
     * • Si la ruta no existe → ArchivoNoExisteException o
     * DirectorioNoExisteException.
     * • Si no se puede borrar → mostrar mensaje de error.
     */

    public static void borrar(String ruta) throws ArchivoNoExisteException, DirectorioNoExisteException {
        File f = new File(ruta);

        if (f.isDirectory()) {
            File[] elementos = f.listFiles();
            if (elementos != null) {
                for (File elemento : elementos) {
                    borrar(elemento.getAbsolutePath()); // recursiva
                }
            }
        }

        if (f.delete()) {
            System.out.println("borrado ok ");
        } else {
            System.err.println("no borrado");
        }
    }
}
