package UD1.dom.corredoresYequipos.logica;

import UD1.dom.XMLDOMUtils;
import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.dom.corredoresYequipos.clases.Corredor;
import UD1.dom.corredoresYequipos.persistencia.CorredoresDOM;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class GestorCorredores {
    private Document doc;
    private CorredoresDOM dao;

    // --------------------------------------------------------
    // 1. CARGA DEL DOCUMENTO XML
    // --------------------------------------------------------
    public boolean cargarDocumento(String ruta) {
        try {
            doc = XMLDOMUtils.cargarDocumentoXMLDOM(ruta, TipoValidacion.NO_VALIDAR);
            dao = new CorredoresDOM(doc);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR al cargar el XML: " + e.getMessage());
            return false;
        }
    }

    // --------------------------------------------------------
    // 2. LISTAR TODOS
    // --------------------------------------------------------
    public void listarTodos() {
        List<Corredor> lista = dao.getCorredores();

        if (lista.isEmpty()) {
            System.out.println("No hay corredores en el archivo.");
            return;
        }

        for (Corredor c : lista) {
            System.out.println(c);
        }
    }

    // --------------------------------------------------------
    // 3. BUSCAR POR CÓDIGO
    // --------------------------------------------------------
    public void mostrarPorCodigo(String codigo) {
        Corredor c = dao.buscarPorCodigo(codigo);
        if (c == null) {
            System.out.println("No existe corredor con código " + codigo);
        } else {
            System.out.println(c);
        }
    }

    // --------------------------------------------------------
    // 4. BUSCAR POR DORSAL
    // --------------------------------------------------------
    public void mostrarPorDorsal(int dorsal) {
        Corredor c = dao.buscarPorDorsal(dorsal);
        if (c == null) {
            System.out.println("No existe corredor con dorsal " + dorsal);
        } else {
            System.out.println(c);
        }
    }

    // --------------------------------------------------------
    // 5. INSERTAR CORREDOR NUEVO
    // --------------------------------------------------------
    public void insertarCorredor(Corredor c) {

        int nuevoDorsal = dao.obtenerSiguienteDorsal();
        c.setDorsal(nuevoDorsal);

        if (dao.insertarCorredore(c)) {
            System.out.println("Corredor insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el corredor.");
        }
    }

    // --------------------------------------------------------
    // 6. ELIMINAR CORREDOR
    // --------------------------------------------------------
    public void eliminarCorredor(String codigo) {
        if (dao.borrarCorredorPorCodigo(codigo)) {
            System.out.println("Corredor eliminado.");
        } else {
            System.out.println("El corredor no existe.");
        }
    }

    // --------------------------------------------------------
    // 7. AÑADIR O MODIFICAR PUNTUACIÓN
    // --------------------------------------------------------
    public void actualizarPuntuacion(String codigo, int anio, float puntos) {
        boolean ok = dao.addOrUpdatePuntuacion(codigo, anio, puntos);

        if (ok) {
            System.out.println("Puntuación añadida o modificada correctamente.");
        } else {
            System.out.println("No existe el corredor con ese código.");
        }
    }

    // --------------------------------------------------------
    // 8. ELIMINAR PUNTUACIÓN
    // --------------------------------------------------------
    public void eliminarPuntuacion(String codigo, int anio) {
        boolean ok = dao.eliminarPuntuacionPorAnio(codigo, anio);

        if (ok) {
            System.out.println("Puntuación eliminada.");
        } else {
            System.out.println("No se pudo eliminar (corredor o año inexistente).");
        }
    }

    // --------------------------------------------------------
    // 9. GUARDAR EN DISCO
    // --------------------------------------------------------
    public void guardarDocumento(String ruta) {
        XMLDOMUtils.guardarDocumentoXML(doc, ruta);
        System.out.println("Documento guardado en " + ruta);
    }
}
