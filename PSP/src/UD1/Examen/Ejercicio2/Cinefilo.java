package UD1.Examen.Ejercicio2;

public class Cinefilo extends Thread {
    Cine cine;

    public Cinefilo(String name, Cine cine) {
        super(name);
        this.cine = cine;
    }

    @Override
    public void run() {
        Pelicula pelicula;
        while((pelicula = cine.getPeliculaRnd()) != null){
            if (pelicula.comprarEntrada(this)){
                break;
            }
        }
        if((pelicula = cine.getPeliculaRnd()) != null){
            System.out.println(this + " quedo sin entrada");
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
