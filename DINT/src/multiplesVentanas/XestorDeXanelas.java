package multiplesVentanas;

import java.util.Vector;

public class XestorDeXanelas {
	static private int numXanelasDatosPersoais = 0;
	static private int numXanelasDatosAcademicos = 0;
	static private Vector<DatosPersoais> xanelasDatosPersoais = new Vector<>();
	

	public static void cerrarXanelasDatosPersoais() {
		numXanelasDatosPersoais--;
	}

	public static boolean abrirXanelasDatosPersoais() {
		if (numXanelasDatosPersoais < 5) {
			numXanelasDatosPersoais++;
			return true;
		} else {
			return false;
		}
	}

	public static void cerrarXanelasDatosAcademicos() {
		numXanelasDatosAcademicos--;
	}

	public static boolean abrirXanelasDatosAcademicos() {
		if (numXanelasDatosAcademicos < 2) {
			numXanelasDatosAcademicos++;
			return true;
		} else {
			return false;
		}
	}

	public static void engadirXanelaDatosPersoais(DatosPersoais xanela) {
		xanelasDatosPersoais.add(xanela);
	}

	public static void eliminarXanelaDatosPersoais(DatosPersoais xanela) {
		for (int i = 0; i < xanelasDatosPersoais.size(); i++) {
			if (xanelasDatosPersoais.elementAt(i) == xanela) {
				xanelasDatosPersoais.removeElementAt(i);
				break;
			}
		}
		System.out.println(xanelasDatosPersoais.size());
	}

	public static Vector<DatosPersoais> recuperarXanelasDatosPersoais() {
		return xanelasDatosPersoais;
	}
}