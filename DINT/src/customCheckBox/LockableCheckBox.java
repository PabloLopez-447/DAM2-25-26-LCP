package customCheckBox;

import javax.swing.JCheckBox;

import javax.swing.*;
import java.awt.*;

public class LockableCheckBox extends JCheckBox {

	private static final long serialVersionUID = 1L;
	
	private boolean locked = false;
    private Color lockedColor = Color.GRAY;

    public LockableCheckBox(String text) {
        super(text);
        initBehavior();
    }

    private void initBehavior() {
        addActionListener(e -> {
            if (locked) {
                setSelected(!isSelected());
                Toolkit.getDefaultToolkit().beep();
            }
        });
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;

        if (locked) {
            setForeground(lockedColor);
            setToolTipText("Opción bloqueada");
        } else {
            setForeground(UIManager.getColor("CheckBox.foreground"));
            setToolTipText(null);
        }
    }

	public Color getLockedColor() {
		return lockedColor;
	}

	public void setLockedColor(Color lockedColor) {
		this.lockedColor = lockedColor;
	}
    
    
}