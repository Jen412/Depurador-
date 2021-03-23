import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;

public class TablaTokens {
    private static String reservadas[];
    private static String tipos[];
    private static char carSim[];
    private static String compAlg[];
    private static ArrayList tokens;
    static Vector v;
    static boolean banErrores=false, banImprimir=false, banLeer=false, banTipo =false, banId=false, banSi=false , banSiNo=false, banWhile= false;

    public TablaTokens(){
        reservadas = new String[9];
        reservadas[0] = "imprifimifir";
        reservadas[1] = "lefefer";
        reservadas[2] = "sifi";
        reservadas[3] = "sifinofo";
        reservadas[4] = "pafarafa";
        reservadas[5] = "miefentrafas";
        reservadas[6] = "hafacefer";
        reservadas[7] = "verdadero";
        reservadas[8] = "falso";
        tipos = new String[5];
        tipos[0] = "ent";
        tipos[1] = "cad";
        tipos[2] = "dec";
        tipos[3] = "car";
        tipos[4] = "vefa";
        carSim = new char[12];
        carSim[0] = '#';
        carSim[1] = '{';
        carSim[2] = '}';
        carSim[3] = '(';
        carSim[4] = ')';
        carSim[5] = '[';
        carSim[6] = ']';
        carSim[7] = ';';
        carSim[8] = '!';
        carSim[9] = '<';
        carSim[10] = '>';
        carSim[11] = '"';
        compAlg =  new String[14];
        compAlg[0] = "may";
        compAlg[1] = "min";
        compAlg[2] = "mayi";
        compAlg[3] = "mini";
        compAlg[4] = "===";
        compAlg[5] = "|=";
        compAlg[6] = "yy";
        compAlg[7] = "oo";
        compAlg[8] = "=";
        compAlg[9] = "+";
        compAlg[10] = "-";
        compAlg[11] = "*";
        compAlg[12] = "/";
        compAlg[13] = "^";
    }

    public void tablaTokens(Vector[] lineasD) {
        tokens = new ArrayList<>();
        Vector lineas[]=null;
        File f;
        FileReader fr;
        

        int cont=0;
        try {
            f= new File("EjemploPrueba.dep");
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String aux="";

            while (true) {
                aux=br.readLine();
                if (aux!=null){
                    cont++;
                }
                else{
                    break;
                }
            }
            lineas = new Vector[cont];
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            for (int i = 0; i < lineas.length; i++) {
                Vector x = new Vector<>();
                x.add(i+1);
                x.add(br.readLine());
                lineas[i] = x;
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.err.println("archivo no encontrado");
        }
        for (int i = 0; i <lineas.length; i++) {
            String auxL =(String) lineas[i].get(1);
            String palabra="";
            int posicion=0;
            for (int j = 0; j < auxL.length(); j++) {
                char c = auxL.charAt(j);
                if (c!=' ' || c == '}') {
                    // System.out.println("desde el if " + c);
                    if(c=='}'){
                        agregarCierreL();
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =false;
                        banSiNo=false;
                        banWhile= false;
                    }
                    palabra+=c;
                }
                else{
                    posicion = j+1;
                    banTipo=false;
                    banSi=false;
                    banSiNo=false;
                    if (palabra.compareTo(reservadas[0])==0) {
                        banImprimir =true;
                        banLeer =false;
                        banTipo = false;
                        banSi =false;
                        banSiNo=false;
                        banWhile= false;
                        agregarReservada(palabra, "print");
                    }
                    else if(palabra.compareTo(reservadas[1])==0){
                        banImprimir =false;
                        banLeer =true;
                        banTipo = false;
                        banSi =false;
                        banSiNo=false;
                        banWhile= false;
                        agregarReservada(palabra, "read");
                    }
                    else if(palabra.compareTo(reservadas[2])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =true;
                        banSiNo=false;
                        banWhile= false;
                        agregarReservada(palabra, "if");
                    }
                    else if(palabra.compareTo(reservadas[3])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =false;
                        banSiNo=true;
                        banWhile= false;
                        agregarReservada(palabra, "else");
                    }
                    else if(palabra.compareTo(reservadas[4])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =false;
                        banSiNo=false;
                        banWhile= false;
                    }
                    else if(palabra.compareTo(reservadas[5])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =false;
                        banSiNo=false;
                        banWhile= true;
                        agregarReservada(palabra, "while");
                    }
                    else if(palabra.compareTo(reservadas[6])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =false;
                        banSiNo=false;
                        banWhile= false;
                    }
                    else if (palabra.compareTo(tipos[0])==0 || palabra.compareTo(tipos[1])==0 ||
                    palabra.compareTo(tipos[2])==0 ||palabra.compareTo(tipos[3])==0 ||palabra.compareTo(tipos[4])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = true;
                        banSi =false;
                        banSiNo=false;
                        banWhile= false;
                    }
                    break;
                }

            }
            //imprimir
            if (banImprimir==true) {
                palabra="";
                if (auxL.charAt(posicion)==carSim[11] && auxL.charAt(auxL.length()-1)==';'){
                    // System.out.println(auxL.charAt(posicion));
                    for (int j = posicion; j <auxL.length(); j++) {
                        char c = auxL.charAt(j);
                        if (c!= ';'){
                           palabra+=c;
                        }
                        else {
                            break;
                        }
                    }
                    if (banErrores == false) {
                        v = new Vector<>();
                        v.add(palabra);
                        v.add("literal");
                        v.add(" ");
                        tokens.add(v);
                    }
                    else{
                        System.exit(0);
                    }
                }
            }
            //leer
            if (banLeer == true) {
                palabra="";
                posicion = agregarTipo(posicion, auxL, palabra);
                palabra="";
                posicion = agregarVariables(posicion, auxL, palabra);
            }
            if (banTipo ==true) {
                v = new Vector<>();
                v.add(palabra);
                v.add("tipo");
                v.add(" ");
                tokens.add(v);
                palabra="";
                for (int j = posicion; j < auxL.length(); j++) {
                    char c = auxL.charAt(j);
                    if (c == carSim[8]) {
                        palabra+=c;
                    }
                    else if(c != ' '&& c!= ';') {
                        palabra+= c;
                    }
                    else{
                        break;
                    }
                    if (palabra.charAt(0) != '!'&& palabra.length() >0) {
                        banId=false;
                    }
                    else{
                        banId = true;
                    }
                }
                for (int j = 0; j <tipos.length; j++) {
                    if (palabra.equals(tipos[j])==false && banId !=false) {
                        v = new Vector<>();
                        v.add(palabra);
                        v.add("id");
                        v.add(" ");
                        tokens.add(v);
                        break;
                    }
                }
            }
            if (banSi==true) {
                palabra="";
                posicion = agregarAperturaP(posicion, auxL, palabra);
                palabra="";
                
                if (auxL.charAt(posicion) == carSim[8]) {
                    posicion =agregarVariables(posicion, auxL, palabra);
                    palabra="";
                }
                else if(validarNum(auxL.charAt(posicion)) ==true){
                    posicion = agregarConstante(posicion, auxL, palabra);
                    palabra="";
                }

                posicion= agregarComp(posicion, auxL, palabra);

                if (auxL.charAt(posicion) == carSim[8]) {
                    posicion = agregarVariables(posicion, auxL, palabra);
                    palabra="";
                }
                else if(validarNum(auxL.charAt(posicion)) ==true){
                    posicion = agregarConstante(posicion, auxL, palabra);
                    palabra="";
                }else if(validarBool(auxL.charAt(posicion)) == true){
                    posicion = agregarBool(posicion, auxL, palabra);
                    palabra="";
                }
                

                if (auxL.charAt(posicion)!= carSim[4]) {
                    do {
                        for (int j = posicion; j < auxL.length(); j++) {
                            char c = auxL.charAt(j);
                            if (c!= ' ') {
                                palabra+=c;
                            }
                            else{
                                posicion = j+1;
                                break;
                            }
                        }
                        if (auxL.charAt(posicion-2) != carSim[4]) {   
                            agregarLog(palabra);
                        }
                        palabra ="";
                        if (auxL.charAt(posicion) == carSim[8]) {
                            posicion =agregarVariables(posicion, auxL, palabra);
                            palabra="";
                        }
                        else if(validarNum(auxL.charAt(posicion)) ==true){
                            posicion = agregarConstante(posicion, auxL, palabra);
                            palabra="";
                        }
        
                        posicion= agregarComp(posicion, auxL, palabra);
        
                        if (auxL.charAt(posicion) == carSim[8]) {
                            posicion = agregarVariables(posicion, auxL, palabra);
                        }
                        else if(validarNum(auxL.charAt(posicion)) ==true){
                            posicion = agregarConstante(posicion, auxL, palabra);
                            palabra="";
                        }
                    }while(auxL.charAt(posicion-2) != carSim[4]);
                }
                posicion =agregarCierreP(posicion-2, auxL, palabra);
                palabra ="";

                posicion = agregarAperturaL(posicion+1, auxL, palabra);
                palabra ="";
            }
            if (banSiNo==true) {
                palabra ="";
                posicion = agregarAperturaL(posicion, auxL, palabra);
                palabra ="";
            }

            if (banWhile == true) {
                palabra="";
                posicion = agregarAperturaP(posicion, auxL, palabra);
                palabra="";
                
                if (auxL.charAt(posicion) == carSim[8]) {
                    posicion =agregarVariables(posicion, auxL, palabra);
                    palabra="";
                }
                else if(validarNum(auxL.charAt(posicion)) ==true){
                    posicion = agregarConstante(posicion, auxL, palabra);
                    palabra="";
                }

                posicion= agregarComp(posicion, auxL, palabra);

                if (auxL.charAt(posicion) == carSim[8]) {
                    posicion = agregarVariables(posicion, auxL, palabra);
                }
                else if(validarNum(auxL.charAt(posicion)) ==true){
                    posicion = agregarConstante(posicion, auxL, palabra);
                    palabra="";
                }
                if (auxL.charAt(posicion)!= carSim[4]) {
                    do {
                        for (int j = posicion; j < auxL.length(); j++) {
                            char c = auxL.charAt(j);
                            if (c!= ' ') {
                                palabra+=c;
                            }
                            else{
                                posicion = j+1;
                                break;
                            }
                        }
                        if (auxL.charAt(posicion-2) != carSim[4]) {   
                            agregarLog(palabra);
                        }
                        palabra ="";
                        if (auxL.charAt(posicion) == carSim[8]) {
                            posicion =agregarVariables(posicion, auxL, palabra);
                            palabra="";
                        }
                        else if(validarNum(auxL.charAt(posicion)) ==true){
                            posicion = agregarConstante(posicion, auxL, palabra);
                            palabra="";
                        }
        
                        posicion= agregarComp(posicion, auxL, palabra);
        
                        if (auxL.charAt(posicion) == carSim[8]) {
                            posicion = agregarVariables(posicion, auxL, palabra);
                        }
                        else if(validarNum(auxL.charAt(posicion)) ==true){
                            posicion = agregarConstante(posicion, auxL, palabra);
                            palabra="";
                        }
                    }while(auxL.charAt(posicion-2) != carSim[4]);
                }
                posicion =agregarCierreP(posicion-2, auxL, palabra);
                palabra ="";

                posicion = agregarAperturaL(posicion+1, auxL, palabra);
                palabra ="";
            }
        }

        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i));
        }
    }

    public static boolean validarNum(char c) {
        boolean b=false;
        if (c >=48 && c<= 57) {
            b=true;
        }
        return b;
    }
    public static int agregarVariables(int posicion, String auxL, String palabra) {
        for (int j = posicion; j < auxL.length(); j++) {
            char c = auxL.charAt(j);
            if (c == carSim[8] || c!= ' ' && c!= ';' && c!= carSim[4]) {
                palabra+=c;
            }
            else{
                posicion = j+1;
                break;
            }
        }
        v = new Vector<>();
        v.add(palabra);
        v.add("id");
        v.add(" ");
        tokens.add(v);
        return posicion;
    }
    public static int agregarTipo(int posicion, String auxL, String palabra) {
        for (int j = posicion; j < auxL.length(); j++) {
            char c = auxL.charAt(j);
            if(c != ' ') {
                palabra+= c;
            }
            else{
                posicion = j+1;
                break;
            }
        }
        for (int j = 0; j < tipos.length; j++) {
            if (palabra.compareTo(tipos[j])==0) {
                v = new Vector<>();
                v.add(palabra);
                v.add("tipo");
                v.add(" ");
                tokens.add(v);
                break;
            }
        }
        return posicion;
    }
    public static void agregarReservada(String palabra, String nombre) {
        v = new Vector<>();
        v.add(palabra);
        v.add(nombre);
        v.add(" ");
        tokens.add(v);
    }
    private static int agregarConstante(int posicion, String auxL, String palabra) {
        for (int j = posicion; j < auxL.length(); j++) {
            char c = auxL.charAt(j);
            if (c!= ' ' && c!= carSim[4]) {
                palabra+=c;
            }
            else{
                posicion = j+1;
                break;
            }
        }
        v = new Vector<>();
        v.add(palabra);
        v.add("constante");
        v.add(" ");
        tokens.add(v);

        return posicion;
    }

    public static int agregarComp(int posicion, String auxL, String palabra) {
        for (int j = posicion; j < auxL.length(); j++) {
            char c = auxL.charAt(j);
            if (c!= ' ') {
                palabra+=c;
            }
            else{
                posicion = j+1;
                break;
            }
        }
        for (int j = 0; j <6; j++) {
            if (palabra.compareTo(compAlg[j])==0) {
                v = new Vector<>();
                v.add(palabra);
                v.add("operador");
                v.add(" ");
                tokens.add(v);
                palabra="";
                break;
            }    
        }
        return posicion;
    }

    public static int agregarAperturaP(int posicion, String auxL, String palabra) {
        for (int j = posicion; j < auxL.length(); j++) {
            char c = auxL.charAt(j);
            if (c == carSim[3]) {
                palabra+=c;
            }
            else{
                posicion = j;
                break;
            }
        }
        v = new Vector<>();
        v.add(palabra);
        v.add("( apertura");
        v.add(" ");
        tokens.add(v);

        return posicion;
    }

    public static void agregarLog(String palabra) {
        String op ="";
        if (palabra.compareTo(compAlg[7])==0) {
            op= "||";
        }
        else if (palabra.compareTo(compAlg[6])==0) {
            op="&&";
        }
        v = new Vector<>();
        v.add(palabra);
        v.add(op);
        v.add(" ");
        tokens.add(v);
    }

    public static int agregarCierreP(int posicion, String auxL, String palabra) {
        for (int j = posicion; j < auxL.length(); j++) {
            char c = auxL.charAt(j);
            if (c == carSim[4]) {
                palabra+=c;
            }
            else{
                posicion = j;
                break;
            }
        }
        v = new Vector<>();
        v.add(palabra);
        v.add(") Cierre");
        v.add(" ");
        tokens.add(v);
        return posicion;
    }

    public static int agregarAperturaL(int posicion, String auxL, String palabra) {
        for (int j = posicion; j < auxL.length(); j++) {
            char c = auxL.charAt(j);
            if (c == carSim[1]) {
                palabra+=c;
            }
            else{
                posicion = j;
                break;
            }
        }
        v = new Vector<>();
        v.add(palabra);
        v.add("{ apertura");
        v.add(" ");
        tokens.add(v);
        return posicion;
    }
    public static void agregarCierreL() {
        v = new Vector<>();
        v.add("}");
        v.add("} Cierre");
        v.add(" ");
        tokens.add(v);
    }

    public static boolean validarBool(char c) {
        boolean b=false;
        System.out.println(c);
        if (c == 'v' || c == 'f') {
            b=true;
        }
        return b;
    }

    public static int agregarBool(int posicion, String auxL, String palabra) {
        for (int j = posicion; j < auxL.length(); j++) {
            char c = auxL.charAt(j);
            if (c != ' ') {
                palabra+=c;
            }
            else{
                posicion = j;
                break;
            }
        }
        v = new Vector<>();
        v.add(palabra);
        v.add("booleano");
        v.add(" ");
        tokens.add(v);

        return posicion;
    }

}