package customComponents;

import javax.swing.*;
import java.awt.*;
import ud2.primercomponente.CustomRadioButton;
import crearComponente.Advanced_JList;
import customCheckBox.PasswordCheckBox;

public class ConfigPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Advanced_JList<String> list;
	private PasswordCheckBox secureMode;
	private CustomRadioButton<String> rbAsc;
	private CustomRadioButton<String> rbDesc;

	public ConfigPanel() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Lista avanzada
		list = new Advanced_JList<>();
		list.getModel().addElement("Opción A");
		list.getModel().addElement("Opción B");
		list.getModel().addElement("Opción C");

		add(new JScrollPane(list), BorderLayout.CENTER);

		// Panel superior con radios
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

		ButtonGroup group = new ButtonGroup();
		rbAsc = new CustomRadioButton<>("Mover arriba");
		rbDesc = new CustomRadioButton<>("Mover abajo");

		group.add(rbAsc);
		group.add(rbDesc);

		rbAsc.setSelected(true);

		rbAsc.addActionListener(e -> list.setDesplazamientoDesc(false));
		rbDesc.addActionListener(e -> list.setDesplazamientoDesc(true));

		top.add(new JLabel("Modo de movimiento:"));
		top.add(rbAsc);
		top.add(rbDesc);

		add(top, BorderLayout.NORTH);

		// Panel inferior con PasswordCheckBox
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		secureMode = new PasswordCheckBox("Activar modo seguro");
		secureMode.setPassword("1234");

		// Cuando no está marcado, la lista queda bloqueada
		list.setActive(false);

		secureMode.addActionListener(e -> {
			list.setActive(secureMode.isSelected());
		});

		bottom.add(secureMode);
		bottom.add(new JLabel("(doble clic en la lista para mover elementos)"));

		add(bottom, BorderLayout.SOUTH);
	}
}
