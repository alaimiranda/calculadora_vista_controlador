import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalcView1 extends JFrame implements KeyListener, CalculadoraObserver{

    boolean activo = false;
    private ControllerCalculadora ctrl;
    private JTextField textoCalculadora;
    private String opAnt;
    private String num1;

    public CalcView1(ControllerCalculadora ctrl) {
        super("Vista calculadora 1");
        this.ctrl = ctrl;
        this.ctrl.addObserver(this);
        opAnt = "";
        num1 = "0";
        initGUI();
    }

    public JButton crearBotonNumerico(String text) {
        JButton b = new JButton(text);
        b.addActionListener((ActionEvent e)->{
            textoCalculadora.setText(textoCalculadora.getText()+b.getActionCommand());
        });
        return b;
    }

    public JButton crearBotonOperacion(String text) {
        JButton b = new JButton(text);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(opAnt == "") {
                    num1 = textoCalculadora.getText();
                    opAnt = b.getActionCommand();
                    textoCalculadora.setText(textoCalculadora.getText()+opAnt);
                }else {
                    String todo = textoCalculadora.getText();
                    String num2 = todo.split(Pattern.quote(opAnt))[1];

                    if (e.getActionCommand() == "=") {
                        activo = true;
                        executeOp(num1, num2, opAnt);

                        opAnt = "";
                    }
                    else {
                        executeOp(num1, num2, opAnt);
                        opAnt =e.getActionCommand();
                    }
                }
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

    public void initGUI() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(2, 1));
        panelPrincipal.setPreferredSize(new Dimension(Main.width, Main.height));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.LINE_AXIS));

        textoCalculadora = new JTextField(30);
        textoCalculadora.setFont(new Font("SansSerif", Font.BOLD, 20));
        textoCalculadora.setEnabled(false);
        textoCalculadora.addKeyListener(this);

        JButton botonC = new JButton("C");
        botonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textoCalculadora.setText("");
                num1 = "";
                opAnt = "";
                System.out.println("Borrado");
            }
        });

        panelSuperior.add(textoCalculadora);
        panelSuperior.add(botonC);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.LINE_AXIS));

        JPanel panelNumerico = new JPanel(new GridLayout(3,3));
        JPanel panelOperaciones = new JPanel(new GridLayout(3, 2));

        panelInferior.add(panelNumerico);
        panelInferior.add(panelOperaciones);

        for(int i = 3; i>0;i--)
            for(int j = 3; j>0;j--) {
                panelNumerico.add(crearBotonNumerico(""+(i*3-(j-1))));
            }

        panelOperaciones.add(crearBotonOperacion("*"));
        panelOperaciones.add(crearBotonOperacion("/"));
        panelOperaciones.add(crearBotonOperacion("+"));
        panelOperaciones.add(crearBotonOperacion("-"));
        panelOperaciones.add(crearBotonNumerico("0"));
        panelOperaciones.add(crearBotonOperacion("="));

        panelPrincipal.add(panelSuperior, BorderLayout.PAGE_START);
        panelPrincipal.add(panelInferior);

        this.setContentPane(panelPrincipal);
        this.setVisible(true);
        this.setLocation(200, 200);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateResultado(double res){
        if(activo){
            this.textoCalculadora.setText(""+res);
            num1 = ""+res;
            activo = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyChar() == 'c' ||e.getKeyChar() == 'C' ) {
            JButton c = new JButton();
            c.getActionListeners()[0].actionPerformed(new ActionEvent(c, 0, "C"));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }




}