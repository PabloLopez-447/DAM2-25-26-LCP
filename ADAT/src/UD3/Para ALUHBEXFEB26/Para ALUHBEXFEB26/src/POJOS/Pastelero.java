package POJOS;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Pablo López Couso DNI:77550221V

public class Pastelero implements Serializable {

    private String codigo;
    private String nome;
    private String apelidos;
    private String alias;
    private LocalDate datanacemento;
    private Character sexo;

    //Mapeo contacto
    private Contacto contacto;

    //Mapeo de tecnicas
    private Map<String, String> tecnicas = new HashMap<>();

    //Mapeo pastelerias
    private Set<Pasteleria> pastelerias = new HashSet<>();

    //Mapeo certificacion
    private Certificacion certificacion;


    public Pastelero() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public LocalDate getDatanacemento() {
        return datanacemento;
    }

    public void setDatanacemento(LocalDate datanacemento) {
        this.datanacemento = datanacemento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Map<String, String> getTecnicas() {
        return tecnicas;
    }

    public void setTecnicas(Map<String, String> tecnicas) {
        this.tecnicas = tecnicas;
    }

    public Set<Pasteleria> getPastelerias() {
        return pastelerias;
    }

    public void setPastelerias(Set<Pasteleria> pastelerias) {
        this.pastelerias = pastelerias;
    }

    public Certificacion getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(Certificacion certificacion) {
        this.certificacion = certificacion;
    }
}
