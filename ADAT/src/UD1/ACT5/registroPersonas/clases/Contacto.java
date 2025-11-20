package UD1.ACT5.registroPersonas.clases;

import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlTransient;

//@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Email.class, Telefonos.class})
@XmlTransient
public abstract class Contacto {
}
