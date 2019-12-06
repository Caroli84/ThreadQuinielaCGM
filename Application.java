package ThreadQuiniela;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Application {
    public static void main(String[] args) {
        int NUM_THREADS=3;
        String dir = "C:\\Users\\Carol\\IdeaProjects\\M09Threads\\ThreadQuiniela\\src\\quinielasClientes";
        Path quinielasClientes = Paths.get(dir);
        Path resultats = Paths.get("C:\\Users\\Carol\\IdeaProjects\\M09Threads\\ThreadQuiniela\\src\\resultatQuinieles");


        //funcion que distribuye los ficheros entre los threads de la forma más equitativa
        HashMap<Integer,ArrayList<Path>> listaQuinielas = sortingFiles(NUM_THREADS,quinielasClientes);

        System.out.println("Recorrer FoREACH HashMap k-v");
        listaQuinielas.forEach((k, v) -> {
            System.out.println("Key: " + k + ", Value: " + v);
        });




        for (int i = 0; i <NUM_THREADS ; i++) {
            Tester t=new Tester(listaQuinielas.get(i),resultats);
            t.start();
        }



    }

    public static HashMap<Integer,ArrayList<Path>> sortingFiles(int NUM_THREADS,Path quinielasClientes) {
        //fichero donde se meterá el resultado
        HashMap<Integer, ArrayList<Path>> fileList = new HashMap<Integer, ArrayList<Path>>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(quinielasClientes)) {
            int next = 0;

            for (Path file : stream) {
                //rellenamos el ArrayList que irá dentro del HAshmap
                ArrayList<Path> filesForThreads;
                //si ya está creada la cajita del Thread 0 lo rellenará, sino la creará
                if (fileList.containsKey(next)) {
                    filesForThreads = fileList.get(next);//devuelve el Valor que hay en el HashMap en el Key indicado. ArrayList=ArrayList del HashMap en esa posicion
                } else {
                    filesForThreads = new ArrayList<>();
                }
                filesForThreads.add(file);//añado csv al ArrayList
                fileList.put(next, filesForThreads);//añado ArrayList en el HAshMap
                next++;
                next = next % NUM_THREADS;

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }



}