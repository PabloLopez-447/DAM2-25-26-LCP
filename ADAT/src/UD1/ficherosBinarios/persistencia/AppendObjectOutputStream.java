package UD1.ficherosBinarios.persistencia;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendObjectOutputStream extends ObjectOutputStream {
    public AppendObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    public AppendObjectOutputStream() throws IOException, SecurityException {

    }

    @Override
    protected void writeStreamHeader() throws IOException {

    }
}
