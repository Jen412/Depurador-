import java.util.Vector;

public class Depurador{
    
    public static void main(String[] args) {
        Archivo a = new Archivo();
        String s = a.read("EjercicioPrueba.txt");
        a.write(s);
        Vector[] lineas = a.lineas;
        TablaTokens tt = new TablaTokens();
        tt.tablaTokens(lineas);
    }
}

