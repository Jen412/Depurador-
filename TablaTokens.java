import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;

public class TablaTokens {
    private String reservadas[];
    private String tipos[];
    private char carSim[];
    private String compAlg[];
    private ArrayList tokens;

    public TablaTokens(){
        reservadas = new String[7];
        reservadas[0] = "imprifimifir";
        reservadas[1] = "lefefer";
        reservadas[2] = "sifi";
        reservadas[3] = "sifinofo";
        reservadas[4] = "pafarafa";
        reservadas[5] = "miefentrafas";
        reservadas[6] = "hafacefer";
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
        Vector v;
        Vector lineas[]=null;
        File f;
        FileReader fr;
        boolean banErrores=false, banImprimir=false, banLeer=false, banTipo =false, banId=false, banSi=false , banSiNo=true;

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
                if (c!=' ') {
                    palabra+=c;
                }
                else{
                    // System.out.println(palabra);
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
                        v = new Vector<>();
                        v.add(palabra);
                        v.add("");
                        v.add(" ");
                        tokens.add(v);
                    }
                    else if(palabra.compareTo(reservadas[1])==0){
                        banImprimir =false;
                        banLeer =true;
                        banTipo = false;
                        banSi =false;
                        banSiNo=false;
                        v = new Vector<>();
                        v.add(palabra);
                        v.add("");
                        v.add(" ");
                        tokens.add(v);
                    }
                    else if(palabra.compareTo(reservadas[2])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =true;
                        banSiNo=false;
                    }
                    else if(palabra.compareTo(reservadas[3])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =false;
                        banSiNo=true;
                    }
                    else if(palabra.compareTo(reservadas[4])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =false;
                        banSiNo=false;
                    }
                    else if(palabra.compareTo(reservadas[5])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =false;
                        banSiNo=false;
                    }
                    else if(palabra.compareTo(reservadas[6])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = false;
                        banSi =false;
                        banSiNo=false;
                    }
                    else if (palabra.compareTo(tipos[0])==0 || palabra.compareTo(tipos[1])==0 ||
                    palabra.compareTo(tipos[2])==0 ||palabra.compareTo(tipos[3])==0 ||palabra.compareTo(tipos[4])==0){
                        banImprimir =false;
                        banLeer =false;
                        banTipo = true;
                        banSi =false;
                        banSiNo=false;
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
                if (banErrores ==false) {
                    for (int j = 0; j < tipos.length; j++) {
                        if (palabra.compareTo(tipos[j])==0) {
                            v = new Vector<>();
                            v.add(palabra);
                            v.add("tipo");
                            v.add(" ");
                            tokens.add(v);
                        }
                    }
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
                v = new Vector<>();
                v.add(palabra);
                v.add("if");
                v.add(" ");
                tokens.add(v);
                palabra="";
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
                palabra="";
                
                if (auxL.charAt(posicion) == carSim[8]) {
                    for (int j = posicion; j < auxL.length(); j++) {
                        char c = auxL.charAt(j);
                        if (c == carSim[8] || c!= ' ') {
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
                    palabra="";
                }
                else if(validarNum(auxL.charAt(posicion)) ==true){
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
                    v = new Vector<>();
                    v.add(palabra);
                    v.add("constante");
                    v.add(" ");
                    tokens.add(v);
                    palabra="";
                }

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

                if (auxL.charAt(posicion) == carSim[8]) {
                    for (int j = posicion; j < auxL.length(); j++) {
                        char c = auxL.charAt(j);
                        if (c == carSim[8] || c!= ' ' && c!= carSim[4]) {
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
                    palabra="";
                }
                else if(validarNum(auxL.charAt(posicion)) ==true){
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
                    palabra="";
                }
                if (auxL.charAt(posicion)!= carSim[4]) {
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
                    if (palabra.compareTo(compAlg[6]) == 0 ||palabra.compareTo(compAlg[7]) == 0) {
                        
                    }
                }
            }
            if (banSiNo==true) {
                v = new Vector<>();
                v.add(palabra);
                v.add("else");
                v.add(" ");
                tokens.add(v);
                palabra="";
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
}