package UD1.ay.ACT5.registroPersonas;

import UD1.ay.ACT5.registroPersonas.persistencia.GestorPersonas;

public class Main {
    public static void main(String[] args) {
        //String rutaXML = "src/xml/registro2.xml";
        GestorPersonas gestorPersonas = new GestorPersonas();
        gestorPersonas.generarXML();

    }
}