import javax.swing.*;

public class Main {
    public static int width=400,height=200;

    public static void main(String[] args) {
        ControllerCalculadora ctrl = new ControllerCalculadora();
        SwingUtilities.invokeLater(()->{new CalcView1(ctrl);});
        SwingUtilities.invokeLater(()->{new CalcView2(ctrl);});
    }
}