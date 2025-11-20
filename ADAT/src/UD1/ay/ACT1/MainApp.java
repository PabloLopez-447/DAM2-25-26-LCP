package UD1.ay.ACT1;

import UD1.ay.ACT1.excepciones.ArchivoNoExisteException;
import UD1.ay.ACT1.excepciones.DirectorioNoExisteException;
import UD1.ay.ACT1.excepciones.NoEsDirectorioException;
import UD1.ficherosYDirectorios.OperacionesIO;

public class MainApp {

    public static void main(String[] args)
            throws DirectorioNoExisteException, NoEsDirectorioException, ArchivoNoExisteException {

        //String ruta = "/Users/eva";
        String ruta = "C:\\";

        //String ruta2 = "/Users/eva/Pictures";
        String ruta2 = "C:\\ficheros";
        String rutaNoExiste = "/Users/ev";
        String rutaNoDir = "/Users/eva/Pictures/foto.png";

        // System.out.println(Utilidades.formatearFecha(300973474));
        // Utilidades.mostrarInfo(dir, "--");

        // OperacionesIO.visualizarContenido(ruta);
        // OperacionesIO.listarDirectorios(ruta);
        //OperacionesIO.recorrerRecursivo(ruta);
        //OperacionesIO.filtrarPorExtension(ruta, ".txt");
        OperacionesIO.filtrarPorExtensionYOrdenar(ruta2, ".txt", false);
        /*
         * try {
         * OperacionesIO.copiarArchivo(
         * "/Users/eva/Documents/origen.txt",
         * "/Users/eva/Documents/adat/origen.txt");
         * } catch (DirectorioNoExisteException | ArchivoNoExisteException e) {
         * System.err.println("Error al copiar archivo");
         * }
         */
        /*
         * try {
         * OperacionesIO.moverArchivo(
         * "/Users/eva/Documents/origen.txt",
         * "/Users/eva/Documents/adat/movido.txt");
         * } catch (DirectorioNoExisteException | ArchivoNoExisteException |
         * NoEsDirectorioException e) {
         * System.err.println("error al mover archivo");
         * }
         */
        /*
         * try {
         * OperacionesIO.copiarDirectorio(
         * "/Users/eva/Documents/carpetaOrigen",
         * "/Users/eva/Documents/carpetaDestino");
         * System.out.println("directorio copiado");
         * } catch (DirectorioNoExisteException | ArchivoNoExisteException |
         * NoEsDirectorioException e) {
         * System.err.println("error al copiar directorio");
         * }
         */
        /*
         * try {
         * OperacionesIO.borrar("/Users/eva/Documents/adat/movido.txt");
         * } catch (ArchivoNoExisteException | DirectorioNoExisteException e) {
         * System.err.println("error al borrar archivo");
         * }
         */

        /*try {
            OperacionesIO.borrar("/Users/eva/Documents/carpetaDestino");
        } catch (ArchivoNoExisteException | DirectorioNoExisteException e) {
            System.err.println("error al borrar directorio");
        }*/

    }
}
