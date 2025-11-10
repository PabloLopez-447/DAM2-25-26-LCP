package UD1.ACT4.persistenciaDOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import UD1.ACT4.clases.Corredor;

public class CorredorXML {

    Document documentoXML = XMLDOMUtils.cargarFichero("D:\\plopecous\\ADAT\\src\\UD1\\ACT4\\xml\\Corredores.xml",
            TipoValidacion.NO_VALIDAR);

    public boolean insertarCorredor(Corredor corredor) {
        Element raiz = documentoXML.getDocumentElement();

        return false;
    }

    public int obtenerSiguienteDorsal() {
        Element raiz = documentoXML.getDocumentElement();
        NodeList hijos = raiz.getChildNodes();

        for (int i = hijos.getLength() - 1; i >= 0; i--) {
            Node nodo = hijos.item(i);

            if (nodo instanceof Element corredorElement) {
                String dorsalStr = corredorElement.getAttribute("dorsal");

                if (dorsalStr != null && !dorsalStr.isBlank()) {
                    try {
                        return Integer.parseInt(dorsalStr) + 1;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return 0;
    }

    public boolean eliminarCorredorPorCodigo(String codigo) {
        Element corredorEliminar = XMLDOMUtils.buscarElementoPorId(documentoXML, codigo);
        return XMLDOMUtils.eliminarElemento(corredorEliminar);
    }

    public boolean eliminarCorredorPorDorsal(int dorsal) {

        return false;
    }

}
