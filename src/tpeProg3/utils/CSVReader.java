package tpeProg3.utils;

import tpeProg3.Procesador;
import tpeProg3.Tarea;
import tpeProg3.Tree;
import tpeProg3.TreeNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CSVReader {
   public CSVReader() {

   }
    public void readTasks(String taskPath,HashMap<String,Tarea> tareasHashMap,Tree tareasTree) {
        // Obtengo una lista con las lineas del archivo
        // lines.get(0) tiene la primer linea del archivo
        // lines.get(1) tiene la segunda linea del archivo... y así
        ArrayList<String[]> lines = this.readContent(taskPath);

        for (String[] line: lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            String id = line[0].trim();
            String nombre = line[1].trim();
            Integer tiempo = Integer.parseInt(line[2].trim());
            boolean critica = Boolean.parseBoolean(line[3].trim());
            Integer prioridad = Integer.parseInt(line[4].trim());
            // Aca instanciar lo que necesiten en base a los datos leidos
            Tarea tarea = new Tarea(id, nombre, tiempo, critica, prioridad);
            tareasHashMap.put(tarea.getId(), tarea);
            tareasTree.add(tarea);
        }
    }

    public void readProcessors(String processorPath, HashMap<String,Procesador> procesadoresHashMap) {

        // Obtengo una lista con las lineas del archivo
        // lines.get(0) tiene la primer linea del archivo
        // lines.get(1) tiene la segunda linea del archivo... y así
        ArrayList<String[]> lines = this.readContent(processorPath);

        for (String[] line: lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            String id = line[0].trim();
            String codigo = line[1].trim();
            boolean refrigerado = Boolean.parseBoolean(line[2].trim());
            Integer anio = Integer.parseInt(line[3].trim());
            Procesador p = new Procesador(id,codigo,refrigerado,anio);

            procesadoresHashMap.put(p.getIdProcesador(), p);
        }
    }

    private ArrayList<String[]> readContent(String path) {
        ArrayList<String[]> lines = new ArrayList<String[]>();

        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                lines.add(line.split(";"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
        return lines;
    }

}
