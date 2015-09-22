/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadorafinal;

import java.util.ArrayList;

public class CalculadoraFinal {
    
    private ArrayList<String> nums=new ArrayList<>();
    private ArrayList<String> ops=new ArrayList<>();
    private ArrayList<String> func=new ArrayList<>();
    private boolean deg=true;
    
    public CalculadoraFinal(boolean degrees){
        deg=degrees;
        for(int i=0; i<10; i++)
            nums.add(i+"");
        nums.add(Math.PI+"");
        nums.add(Math.E+"");
        nums.add("!");
        
        ops.add("+");
        ops.add("*");
        ops.add("^");
        ops.add("-");
        ops.add("/");
        ops.add("r");
                
        func.add("sin");
        func.add("cos");
        func.add("tan");
        func.add("asin");
        func.add("acos");
        func.add("atan");
        func.add("log");
        func.add("ln");
    }
    /**
     *
     * @param datos
     * @return Un String que representa una secuencia de operaciones
     * ordenadas del menor al mayor por jerarquia matematica .
     */
    public String funciones(String datos){
        if(validacion(datos).equals("ERROR"))
            return "ERROR";
        int j;
        String s="",t;
        boolean resp=false, valid=true;
        Character c;
        int i=0;
        while(i<datos.length() && valid){
            j=0;
            while(j<func.size() && !resp){
                if(datos.startsWith(func.get(j)))
                    resp=true;
                else
                    j++;
            }
            if(resp){
                switch(j){                
                    case 0:
                        t=separa(sub(datos.split("sin")[1]));
                        if(!t.equals("ERROR")){
                            if(deg)
                                s+=Math.sin(Math.toRadians(Double.parseDouble(t)))+"";
                            else
                                s+=Math.sin(Double.parseDouble(t))+"";
                            datos=datos.substring(i+sub(datos.split("sin")[1]).length()+5);
                            i=0;
                        }else{
                            valid=false;
                        }
                        break;
                    case 1:                         
                        t=resultado(sub(datos.split("cos")[1]));
                        if(!t.equals("ERROR")){
                            if(deg)
                                s+=Math.cos(Math.toRadians(Double.parseDouble(t)))+"";
                            else
                                s+=Math.cos(Double.parseDouble(t))+"";
                            datos=datos.substring(i+sub(datos.split("cos")[1]).length()+5);
                            i=0;
                        }else{
                            valid=false;
                        }
                        break;
                    case 2:                         
                        t=resultado(sub(datos.split("tan")[1]));
                        if(!t.equals("ERROR")){
                            if(deg)
                                s+=Math.tan(Math.toRadians(Double.parseDouble(t)))+"";
                            else
                                s+=Math.tan(Double.parseDouble(t))+"";
                            datos=datos.substring(i+sub(datos.split("tan")[1]).length()+5);
                            i=0;
                        }else{
                            valid=false;
                        }
                        break;
                    case 3:
                        t=resultado(sub(datos.split("asin")[1]));
                        if(!t.equals("ERROR")){
                            if(deg)
                                s+=Math.asin(Math.toRadians(Double.parseDouble(t)))+"";
                            else
                                s+=Math.asin(Double.parseDouble(t))+"";
                            datos=datos.substring(i+sub(datos.split("asin")[1]).length()+6);
                            i=0;
                        }else{
                            valid=false;
                        }
                        break;
                    case 4:                         
                        t=resultado(sub(datos.split("acos")[1]));
                        if(!t.equals("ERROR")){
                            if(deg)
                                s+=Math.acos(Math.toRadians(Double.parseDouble(t)))+"";
                            else
                                s+=Math.acos(Double.parseDouble(t))+"";
                            datos=datos.substring(i+sub(datos.split("cos")[1]).length()+6);
                            i=0;
                        }else{
                            valid=false;
                        }
                        break;
                    case 5:                         
                        t=resultado(sub(datos.split("atan")[1]));
                        if(!t.equals("ERROR")){
                            if(deg)
                                s+=Math.atan(Math.toRadians(Double.parseDouble(t)))+"";
                            else
                                s+=Math.atan(Double.parseDouble(t))+"";
                            datos=datos.substring(i+sub(datos.split("atan")[1]).length()+6);
                            i=0;
                        }else{
                            valid=false;
                        }
                        break;
                    case 6:
                        t=resultado(sub(datos.split("log")[1]));
                        if(!t.equals("ERROR")){
                            s+=Math.log10((Double.parseDouble(t)))+"";
                            datos=datos.substring(i+sub(datos.split("log")[1]).length()+5);
                            i=0;
                        }else{
                            valid=false;
                        }
                        break;
                    case 7:
                        t=resultado(sub(datos.split("ln")[1]));
                        if(!t.equals("ERROR")){
                            s+=Math.log((Double.parseDouble(t)))+"";
                            datos=datos.substring(i+sub(datos.split("ln")[1]).length()+4);
                            i=0;
                        }else{
                            valid=false;
                        }
                }
                resp=false;
            }else{
                c=datos.charAt(i);
                s+=c;
                datos=datos.substring(1);
                i=0;
            }
            if(!valid)
                s="ERROR";
        }
        return resultado(s);
    }
    public String separa(String datos){
        Character c, d;
        String s="", res=datos;
        int j;
        boolean boo=false, boo2=false, div=false;
        for(int i=0; i<res.length(); i++){
            c=res.charAt(i);
            if(ops.contains(c.toString()) && !c.equals('-'))
                j=ops.indexOf(c.toString())%3;
            else{
                if(nums.contains(c.toString()) || c.equals('.') || c.equals('-'))
                j=3;
                else{
                    if(c.equals('('))
                        j=4;
                    else{
                        return "ERROR";
                    }
                }
            }
        switch(j){
            case 0: if(boo || boo2){
                if(calcula(s).equals("ERROR"))
                    return "ERROR";
                else{
                res=calcula(s)+""+res.substring(i);
                i=-1;
                s="";
                boo=false;
                boo2=false;
                }
            }            
            else{
                s+=c.toString();
            }
                break;
            case 1: if(boo2){
                if(calcula(s).equals("ERROR"))
                    return "ERROR";
                else{
                res=calcula(s)+""+res.substring(i);
                i=-1;
                s="";
                boo2=false; 
                }
            }            
            else{
                if(div){
                    s=calcula(s)+c.toString();
                    div=false;
                }else{
                    s+=c.toString();
                    boo=true;
                }
                if(c.toString().equals("/"))
                    div=true;
            }            
                break;
            case 2: if(boo2){
                if(calcula(s).equals("ERROR"))
                    return "ERROR";
                else{
                res=calcula(s)+""+res.substring(i);
                i=-1;
                s="";
                boo2=false; 
                }
            }            
            else{
                s+=c.toString();
                boo2=true;
            }  
                break; 
            case 3: s+=c.toString(); 
                break;
            case 4: s+=separa(sub(res.substring(i)));
                    i+=sub(res.substring(i)).length()+1;
                break;            
            }
        }
        return calcula(s)+"";
    }      
    /**
     *
     * @param datos El String del que se quiere obtener un substring.
     * @return Un String que va desde la posicion 1 del parametro hasta la 
     * posicion en la que la cantidad de parentesis de entrada es igual a la de 
     * parentesis de salida, -1.
     */
    public String sub(String datos){
        int i=1,a=1, b=0;
        Character c;
        String s="";
        while(a!=b){
            c=datos.charAt(i);
            s+=c;
            if(c.equals('('))
                a++;
            else{
                if(c.equals(')'))
                    b++;
            }
            i++;
        }            
        return s.substring(0, s.length()-1);
    }    
    /**
     *
     * @param datos Una expresion matematica con operaciones permitidas. 
     * @return a Un String que representa el resultado del calculo 
     * o "ERROR" en el caso en el que se introduzca un String con operaciones no
     * permitidas como la division entre 0
     */
    public String calcula(String datos){
        Character c=null, d=null;
        String s="", s2;
        Pila<String> num=new Pila<>();
        Pila<String> op=new Pila<>();
        double a, b;
        boolean boo=true;
        for(int i=0; i<datos.length(); i++){
            if(i!=0)
                d=c;
            c=datos.charAt(i);
            if(((nums.contains(c.toString()) || c.equals('.'))) || c.equals('-')){                
                if(c.equals('-')){
                    if(s.equals("-")){
                        s="";
                    }else{
                        if(!(i!=0 && ops.contains(d.toString())))
                            op.push("+");                    
                        if(!s.equals("")){
                         num.push(s);
                        }
                        s="-";
                    }
                 }else
                 s+=c+"";                 
            }           
            else{
                if(!s.equals("")){
                    num.push(s);
                    s=""; 
                }                                  
                op.push(c.toString());                
            }           
        }
        if(!s.equals(""))
           num.push(s);
        try{
            if(num.peek().endsWith("!")){
                    s=num.pop();
                    a=(Double.parseDouble(s.substring(0, s.length()-1)));
                    if(a>0)
                        a=mult(a);                    
                    else
                        boo=false;
                }
                else
                    a=Double.parseDouble(num.pop());
            while(!op.isEmpty() && !num.isEmpty() && boo){ 
                if(num.peek().endsWith("!")){
                    s=num.pop();
                    b=mult(Double.parseDouble(s.substring(0, s.length()-1)));
                }
                else
                    b=Double.parseDouble(num.pop());
                switch(ops.indexOf(op.pop())){
                    
                    case 0: a+=b;
                        break;
                    case 1: a*=b;
                        break;
                    case 2: a=Math.pow(b, a);
                        break;
                    case 3: a=b-a;
                        break;
                    case 4: if(a!=0)
                                a=b/a;
                            else
                                boo=false;                                            
                        break;
                    case 5: if(a>0)
                                a=Math.pow(b, 1/a);
                            else
                                boo=false;
                        break;                   
                }
            }
            if(boo)
                s2=a+"";
            else
                s2="ERROR";
        }
        catch(NumberFormatException e){
            s2="ERROR";
        }
        return s2;
    }
    public double mult(double a){
    if(a>1)
        return a*mult(a-1);
    else
        return 1;
}
    /**
     *
     * @param datos
     * @return Un boolean que sera false si el String no cumple con
     * caracteristicas de una expresion matematica valida. Estos pueden ser 
     * parentesis vacios u operandos que estan juntos.
     */
    
    public String validacion(String datos){       
        boolean resp=true;
        if(datos.length()!=0){
            Pila<Character> ch=new Pila<>();
            Character c;
            int i=0, parIzq=0, operador=0, punto=0, numero=0, parDer=0;
            while(i<datos.length() && resp){
                c=datos.charAt(i);
                if(nums.contains(c.toString())){
                    if(i==parDer+1  && parDer!=0)
                        resp=false;
                    else
                        numero=i;
                }else{
                    if(c.equals('(')){
                        if(i-1==punto  && punto!=0)
                            resp=false;
                        else{
                            if(i-1==numero || i-1==parDer){
                                datos=datos.substring(0, i)+"*"+datos.substring(i, datos.length());
                                i++;
                            }
                            ch.push(c);
                            parIzq=i;
                        }
                    }else{
                        if(c.equals(')')){
                            if((ch.isEmpty() || parIzq==i-1) || operador==i-1)
                                resp=false;
                            else{
                                ch.pop();
                                parDer=i;
                            }
                        }else{
                            if(ops.contains(c.toString())){
                                if(((((i-1==operador && operador!=0) && !c.equals('-')) || (punto+1==i && punto!=0)) || (i==0 && !c.equals('-'))) || i==datos.length()-1)
                                    resp=false;
                                else
                                    operador=i;     
                            }else{
                                if(c.equals('.')){
                                    if((parDer+1==i && parDer!=0) || i==datos.length()-1)
                                        resp=false;
                                    punto=i;
                                }
                            }
                        }                        
                    }
                }
                i++;
            }
            if(!ch.isEmpty())
                resp=false;
        }else
            resp=false;
        if(!resp)
            datos="ERROR";
       return datos;
    }
    public String resultado(String datos){
        return separa(validacion(datos));                           
    }
    
    public String promedio(String datos){
        double a=0;
        String resp;
        try{
        String[] s=datos.split(",");
        for(int i=0; i<s.length; i++){
            a+=Double.parseDouble(funciones(s[i]));
        }
        if(s.length>0)
            resp=a/s.length+"";
        else
            resp="ERROR";
        }catch(NumberFormatException e){  
            resp="ERROR";
        }
        return resp;
    }
    
    public static void main(String[] args) {
        CalculadoraFinal c=new CalculadoraFinal(true);
    }
}
