import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Archivo{

    public static String read(String s) {
        File f;
        FileReader fr;
        try {
            f= new File(s);
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String aux="", ss="";
            while(true){
                //se lee linea por linea
                aux=br.readLine();
                if (aux!=null) {
                    //aqui es donde validas el comentario y que no alla un null o salto tons toma la linea y salta
                    if (validarComent(aux) ==true&& aux.length()!=0){
                        ss=ss+aux+"\n";
                    }
                } else {
                    break;
                }
            }
            br.close();
            fr.close();
            //elimina los espacios del inicio y del final pa que solo queden en medio
            ss =ss.trim();
            //elimina los tabuladores
            ss=ss.replaceAll("\t","");
            return ss;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());    
        }

        return null;
    }
    //validas los comentarios para que sespués de un // no se toma en cuenta
    public static boolean validarComent(String s) {
        boolean b=true;
        for (int i = 0; i <s.length(); i++) {
            if (s.charAt(i) == '/' && s.charAt(i+1) == '/') {
                b=false;
            }
        }

        return b;
    }


    public static void write(String s) {
        try {
            String ruta = "EjemploPrueba.dev";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


