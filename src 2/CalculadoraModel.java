import java.util.ArrayList;
import java.util.List;

public class CalculadoraModel implements Observable<CalculadoraObserver>{
    private List<CalculadoraObserver> miobserver;
    private double resultado;

    public CalculadoraModel(){
        this.miobserver = new ArrayList<>();
        this.resultado = 0;
    }

    @Override
    public void addObserver(CalculadoraObserver t) {
        if(!miobserver.contains(t)){
            miobserver.add(t);
        }
    }

    @Override
    public void removeObserver(CalculadoraObserver t) {
        if(!miobserver.contains(t)){
            miobserver.add(t);
        }
    }

    public void sumar(double n1, double n2){
        this.resultado = n1 + n2;
        notifyUpdate();
    }

    public void restar(double n1, double n2){
        this.resultado = n1 - n2;
        notifyUpdate();
    }
    public void mult(double n1, double n2){
        this.resultado = n1 * n2;
        notifyUpdate();
    }
    public void div(double n1, double n2){
        if (n2 != 0){
            this.resultado = n1 / n2;
        }
        notifyUpdate();
    }

    public void notifyUpdate(){
        for(CalculadoraObserver o: miobserver){
            o.updateResultado(this.resultado);
        }
    }
}
