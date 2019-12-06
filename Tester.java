package ThreadQuiniela;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Tester extends Thread{
    ArrayList<Path> paths;
    Path resultats;

    public Tester( ArrayList<Path> paths, Path resultats) {
        this.paths = paths;
        this.resultats = resultats;
    }

    @Override
    public void run() {
        //for (int i = 0; i <paths.size() ; i++) { System.out.println("print "+paths.get(i)); }
        Quiniela quinielaPremiada=quinielaPremiada();

        for (Path p:paths) {
            System.out.println("path "+p.getFileName());
            try {
                BufferedReader br=new BufferedReader(new FileReader(String.valueOf(p)));// OJO necesita el Path en string, no el FileName solo, si no no lo encuentra
                String data;
                while((data=br.readLine())!=null){
                    System.out.println("holaa "+data);
                    String[]aux=data.split(",");
                    //quiniela cliente
                    Quiniela q=new Quiniela(Integer.parseInt(aux[0]),aux[1],aux[2],aux[3],aux[4],aux[5]);
                        //for (int i = 0; i <q.getArrayApuestas().length ; i++) {System.out.println(q.getArrayApuestas()[i]); }
                    int numAciertos=q.coincidencesNumber(quinielaPremiada);
                    //nombre del cliente
                    int punt=p.getFileName().toString().lastIndexOf(".");
                    String client=p.getFileName().toString().substring(0,punt);
                    if(numAciertos>3){
                        addWinner(numAciertos,client,q);

                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //private Quiniela resultat(){}

    private void addWinner(int numAciertos, String client,Quiniela q){
        BufferedWriter bw = null;
        try {
            String filename=numAciertos+"_aciertos.csv";
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Carol\\IdeaProjects\\M09Threads\\ThreadQuiniela\\src\\resultatQuinieles\\"+filename,true));
            bw.write("client: "+client+"-->"+q.toString()+"\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<Quiniela> getQuiniela(Path file){//del Path se va a leer y se retorna un ArrayList con objetos QUiniela
        ArrayList<Quiniela> listQuinielas = new ArrayList<>();
        try {
            BufferedReader br=new BufferedReader(new FileReader((String.valueOf(file.getFileName()))));//leo fichero normal para luego hacer objetos


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    return listQuinielas;
    }

    private Quiniela quinielaPremiada(){
        Path premio = Paths.get("C:\\Users\\Carol\\IdeaProjects\\M09Threads\\ThreadQuiniela\\src\\premio.csv");
        Quiniela premiada=null;
        try {
            BufferedReader br=new BufferedReader(new FileReader(String.valueOf(premio)));
            String data=br.readLine();
            String[]aux=data.split(",");
            premiada=new Quiniela(Integer.parseInt(aux[0]),aux[1],aux[2],aux[3],aux[4],aux[5]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return premiada;

    }
}
