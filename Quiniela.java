package ThreadQuiniela;

import java.io.Serializable;
import java.util.Arrays;

public class Quiniela {

    private int dia;
    private String p1,p2,p3,p4,p5; //15 partits
    private String[]arrayApuestas;

    public Quiniela(int dia, String p1, String p2, String p3, String p4, String p5) {
        this.dia = dia;
        this.arrayApuestas=new String[]{p1,p2,p3,p4,p5};
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String[] getArrayApuestas() {
        return arrayApuestas;
    }

    public void setArrayApuestas(String[] arrayApuestas) {
        this.arrayApuestas = arrayApuestas;
    }

    public int coincidencesNumber(Quiniela q){
        int coincidencias=0;
        String[]values2=q.arrayApuestas;
        String[]values1=this.arrayApuestas;
        for (int i = 0; i <values1.length ; i++) {
            if (values1[i].equals(values2[i])) {
                coincidencias++;
                }
            }
        return coincidencias;
        }



    @Override
    public String toString() {
        return "Quiniela{" +
                "dia=" + dia +
                ", arrayApuestas=" + Arrays.toString(arrayApuestas) +
                '}';
    }

    public static void main(String[] args) {
        Quiniela q=new Quiniela(7,"1","x","x","x", "1");
        Quiniela q2=new Quiniela(8,"x","x","x","x", "1");
        int i=q.coincidencesNumber(q2);
        System.out.println(i);
    }




}
