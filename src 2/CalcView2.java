import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

public class CalcView2 extends JFrame implements KeyListener, CalculadoraObserver{

    boolean activo = false;
    private ControllerCalculadora ctrl;
    private JTextField text1;
    private JTextField text2;
    private JTextField result;
    private String opAnt;
    private String num1;
    private String num2;

    public CalcView2(ControllerCalculadora ctrl) {
        super("Vista calculadora 2");
        this.ctrl = ctrl;
        this.ctrl.addObserver(this);
        opAnt = "";
        num1 = "0";
        initGUI();
    }

    private void initGUI() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3,1));
        panelPrincipal.setPreferredSize(new Dimension(Main.width, Main.height));

        JPanel panelNumeros = new JPanel();
        panelNumeros.setLayout(new BoxLayout(panelNumeros, BoxLayout.LINE_AXIS));

        this.text1 = new JTextField(10);
        this.text1.setFont(new Font("SansSerif",Font.BOLD,20));
        this.text1.setEditable(true);
        this.text1.addKeyListener(this);

        this.text2 = new JTextField(10);
        this.text2.setFont(new Font("SansSerif",Font.BOLD,20));
        this.text2.setEditable(true);
        this.text2.addKeyListener(this);

        panelNumeros.add(this.text1);
        panelNumeros.add(this.text2);

        JPanel panelOp = new JPanel();
        panelOp.setLayout(new BoxLayout(panelOp, BoxLayout.LINE_AXIS));


        panelOp.add(Box.createGlue());
        panelOp.add(crearBotonOperacion("+"));
        panelOp.add(crearBotonOperacion("-"));
        panelOp.add(crearBotonOperacion("*"));
        panelOp.add(crearBotonOperacion("/"));
        panelOp.add(Box.createGlue());

        panelPrincipal.add(panelNumeros);
        panelPrincipal.add(panelOp);

        this.result = new JTextField(20);
        this.result.setFont(new Font("SansSerif",Font.BOLD,20));
        this.result.setEditable(false);

        panelPrincipal.add(result);

        this.setContentPane(panelPrincipal);
        this.setVisible(true);
        this.setLocation(200, 200);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton crearBotonOperacion(String text) {
        JButton b = new JButton(text);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                num1 = text1.getText();
                num2 = text2.getText();
                opAnt = b.getActionCommand();
                activo = true;
                executeOp(num1, num2, opAnt);
            }
        });
        return b;
    }

    public void executeOp(String num1, String num2, String op) {
        double n1 = Double.parseDouble(num1);
        double n2 = Double.parseDouble(num2);

        switch(op) {
            case "+":
                ctrl.sumar(n1, n2);
                break;
            case "-":
                ctrl.restar(n1, n2);
                break;
            case "*":
                ctrl.mult(n1, n2);
                break;
            case "/":
                ctrl.div(n1, n2);
                break;
        }
    }

    @Override
    public void updateResultado(double resultado) {
        if(activo){
            this.result.setText(""+resultado);
            activo = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
