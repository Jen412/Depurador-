public class Depurador{
    
    public static void main(String[] args) {
        Archivo a = new Archivo();
        String s = a.read("EjercicioPrueba.txt");
        a.write(s);
    }
}

