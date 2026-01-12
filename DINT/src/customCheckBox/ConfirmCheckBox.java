package customCheckBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConfirmCheckBox extends JCheckBox {

    // Propiedad propia
    private int confirmTimeout = 2000; // milisegundos
    private long lastClickTime = 0;
    private String originalText;
    private boolean waitingConfirmation = false;

    public ConfirmCheckBox(String text) {
        super(text);
        originalText = text;
        initBehavior();
    }

    private void initBehavior() {
        ActionListener[] inherited = getActionListeners();
        for (ActionListener al : inherited) {
            removeActionListener(al);
        }

        addActionListener(e -> {
            if (!isSelected()) {
                // Siempre permitimos desmarcar sin confirmación
                reset();
                return;
            }

            long now = System.currentTimeMillis();

            if (!waitingConfirmation) {
                // Primer clic: pedir confirmación
                waitingConfirmation = true;
                lastClickTime = now;
                setSelected(false);
                setText("¿Seguro? Haz clic otra vez");
                setForeground(Color.ORANGE);

                Timer t = new Timer(confirmTimeout, ev -> reset());
                t.setRepeats(false);
                t.start();
            } else {
                // Segundo clic dentro del tiempo
                if (now - lastClickTime <= confirmTimeout) {
                    waitingConfirmation = false;
                    setText(originalText);
                    setForeground(UIManager.getColor("CheckBox.foreground"));
                    setSelected(true);
                } else {
                    reset();
                }
            }
        });
    }

    private void reset() {
        waitingConfirmation = false;
        setText(originalText);
        setForeground(UIManager.getColor("CheckBox.foreground"));
        setSelected(false);
    }

    // Getter / Setter de la propiedad propia
    public int getConfirmTimeout() {
        return confirmTimeout;
    }

    public void setConfirmTimeout(int confirmTimeout) {
        this.confirmTimeout = confirmTimeout;
    }
}
