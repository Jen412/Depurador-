import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Archivo{

    public static String read(String s) {
        File f;
        FileReader fr;
        String aux="", ss="";
        try {
            f= new File(s);
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while(true){
                aux=br.readLine();
                if (aux!=null) {
                    ss=ss+aux+"\n";
                } else {
                    break;
                }
            }
            br.close();
            fr.close();
            return s;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());    
        }

        return null;
    }
}
