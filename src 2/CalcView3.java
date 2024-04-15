import java.util.Scanner;

import static java.lang.System.exit;

public class CalcView3 implements CalculadoraObserver{
    boolean activo = false;
    ControllerCalculadora ctrl;
    public CalcView3(ControllerCalculadora controllerCalculadora){
        this.ctrl = controllerCalculadora;
        this.ctrl.addObserver(this);
    }

    public void calcConsola(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Ingrese la operación matemática (ejemplo: 2 + 3): ");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo...");
                System.exit(0);
                break;
            }

            String[] partes = entrada.split(" ");
            if (partes.length != 3) {
                System.out.println("Formato incorrecto. Debe ser número operador número.");
                continue;
            }

            double num1, num2;
            try {
                num1 = Double.parseDouble(partes[0]);
                num2 = Double.parseDouble(partes[2]);
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir números. Intente de nuevo.");
                continue;
            }

            activo = true;
            switch (partes[1]) {
                case "+":
                    ctrl.sumar(num1, num2);
                    break;
                case "-":
                    ctrl.restar(num1, num2);
                    break;
                case "*":
                    ctrl.mult(num1, num2);
                    break;
                case "/":
                    ctrl.div(num1, num2);
                    break;
                default:
                    System.out.println("Operador no válido. Use +, -, * o /");
                    continue;
            }
        }
        scanner.close();
    }

    @Override
    public void updateResultado(double resultado) {
        if(activo){
            System.out.println("\nEl resultado es: " + resultado + "\n");
            activo = false;
        }
    }
}
