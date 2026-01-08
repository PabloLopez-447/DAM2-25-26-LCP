package UD1.Ejercicios.ConcesionarioSeat;

public class Cliente extends Thread {
    static int numCliente;

    Vendedor vendedor;

    public Cliente(Vendedor vendedor) {
        super("" + ++numCliente);
        this.vendedor = vendedor;
    }

    @Override
    public void run() {
        Coche posibleCoche;

        while ((posibleCoche = vendedor.darCoche()) != null) {
            if (posibleCoche.intentaComprar(this)) {
                vendedor.removeCoche(posibleCoche); // En vez de borrar el coche de la lista deberia tener un boolean ocupado
                break;                              //que se ponga a true cuando un cliente este intentando comprarlo y se deja a true si lo compra o lo pone a false si no
            }
        }
    }

    @Override
    public String toString() {
        return "Cliente" + getName();
    }

}
