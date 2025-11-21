package UD1.dom.biblioteca.persistencia;

import UD1.dom.XMLDOMUtils;
import UD1.dom.biblioteca.clases.Copia;
import UD1.dom.biblioteca.clases.Libro;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaDOM {
    Document doc;
    public BibliotecaDOM(Document doc) {
        this.doc = doc;
    }

    public List<Libro> listarLibrosSeccion(String seccion) {
        List<Libro> libros = new ArrayList<>();
        String xpath = String.format("//seccion[@nombre='%s']/libros/libro", seccion);
        NodeList nodos = (NodeList) XMLDOMUtils.evaluarXPath(doc,xpath, XPathConstants.NODESET);

        for (int i = 0; i < nodos.getLength(); i++) {
            Node node = nodos.item(i);
            if (node instanceof Element) {
                Libro libro = parseLibro((Element) node);
                libros.add(libro);
            }
        }
        return libros;
    }

    public Libro parseLibro(Element elem) {
        String id = elem.getAttribute("id");
        String isbn = elem.getAttribute("isbn");
        String titulo = elem.getAttribute("titulo");
        String autor = elem.getAttribute("autor");

        NodeList autoresnode = elem.getElementsByTagName("autor");
        List<String> autores = new ArrayList<String>();

        for (int i = 0; i < autoresnode.getLength(); i++) {
            autores.add(autoresnode.item(i).getTextContent());
        }

        LocalDate fecha = LocalDate.parse(elem.getAttribute("fechaEdicion"));
        String editorial = elem.getAttribute("editorial");
        Float precio = Float.parseFloat(elem.getAttribute("precio"));

        NodeList copias = elem.getElementsByTagName("copia");
        List<Copia> copiases = new ArrayList<Copia>();

        for (int i = 0; i < copias.getLength(); i++) {
            Element copia = (Element) copias.item(i);
            int numeroCopia = Integer.parseInt(copia.getAttribute("numero"));
            String estado = copia.getAttribute("estado");
            Copia copia1 = new Copia(numeroCopia, estado);
            copiases.add(copia1);
        }

        return new Libro(id, isbn, titulo, fecha, precio, autores, copiases);
    }
}
