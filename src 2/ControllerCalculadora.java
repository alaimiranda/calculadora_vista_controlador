public class ControllerCalculadora implements Observable<CalculadoraObserver>{
    private CalculadoraModel m;

    public ControllerCalculadora(){
        this.m = new CalculadoraModel();
    }
    public void sumar(double n1, double n2){m.sumar(n1, n2);}
    public void restar(double n1, double n2){m.restar(n1, n2);}
    public void mult(double n1, double n2){m.mult(n1, n2);}
    public void div(double n1, double n2){m.div(n1, n2);}


    @Override
    public void addObserver(CalculadoraObserver calculadoraObserver) {
        this.m.addObserver(calculadoraObserver);
    }

    @Override
    public void removeObserver(CalculadoraObserver calculadoraObserver) {
        this.m.removeObserver(calculadoraObserver);
    }
}
