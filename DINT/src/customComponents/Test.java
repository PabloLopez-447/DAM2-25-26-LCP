package customComponents;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Demo");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(new ConfigPanel());
            f.setSize(400, 300);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
