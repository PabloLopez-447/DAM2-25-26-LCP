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
                vendedor.removeCoche(posibleCoche);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Cliente" + getName();
    }

}
