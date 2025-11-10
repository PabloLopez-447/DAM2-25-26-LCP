/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplesVentanas2;

/**
 *
 * @author DAM2
 */
public class Usuario {
    private int id;
    private String nome;
    private String apelidos;
    private String provincia;

    @Override
    public String toString() {
        return "Usuario ID " + id + "- "+nome + " " + apelidos + " (" + provincia + ")";
    }


    public Usuario(int id, String nome, String apelidos, String provincia) {
        this.id = id;
        this.nome = nome;
        this.apelidos = apelidos;
        this.provincia = provincia;
    }
    
    
}
