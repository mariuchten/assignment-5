
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
 

public class BipartiteChecker {

    public static void main(String args[]) throws FileNotFoundException, IOException {
         String file;
          

         
          if (args[0].equals("-b")){
             file = args[1];
          }else{
              file = args[0];
          }
            BufferedReader br = null;
            String line = "";
            String fileSplitBy = "\\s+";
            int d;
            int d2;
            int max = 0; 
            ArrayList<Integer> intList = new ArrayList<Integer>(); 

            br = new BufferedReader(new FileReader(file));
            while (((line = br.readLine()) != null)) {

              
                String[] dj = line.split(fileSplitBy);
                d = Integer.parseInt(dj[0]);
                if (d > max) {
                    max = d;
                }
                d2 = Integer.parseInt(dj[1]);
                if (d2 > max) {
                    max = d2;
                }

                intList.add(Integer.parseInt(dj[0]));
                intList.add(Integer.parseInt(dj[1]));

            } 

            max++; 

            boolean[][] adjacencylist = new boolean[max][max];

            for (int i = 0; i < adjacencylist.length; i++) {
                for (int j = 0; j < adjacencylist.length; j++) {
                    adjacencylist[i][j] = false;

                }
            }
            int size = intList.size(); 
            for (int k = 0; k < size; k = k + 2) {
                adjacencylist[intList.get(k)][intList.get(k + 1)] = true;
                adjacencylist[intList.get(k + 1)][intList.get(k)] = true;
            }
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(0);
            int vertex = 0;
            String[] color = new String[max];

            int metritis = 1;
            for (int i = 0; i < max; i++) {
                color[i] = "white";
            }

            color[0] = "red";
            int[] visited = new int[max];
            for (int i = 0; i < max; i++) {

                visited[i] = 0;

            }
            int metritis_akmon = (size) / 2;
             String[] col;
            if (args[0].equals("-b")){
           col = BFS(adjacencylist, vertex, color, metritis_akmon, queue);}
            else{
             col= DFS(adjacencylist, vertex, color, metritis, visited, metritis_akmon);
            }
            ArrayList<Integer> red = new ArrayList<Integer>();
            ArrayList<Integer> blue = new ArrayList<Integer>();

            if (col[0] == "green") {
                System.out.println("Graph is not bipartite.");
            } else {
                for (int i = 0; i < col.length; i++) {
                    if (col[i] == "red") {
                        red.add(i);

                    } else {
                        blue.add(i);
                    }
                }

                System.out.println("to 1o sunolo apoteleitai apo tous komvous");
                for (int i = 0; i < red.size(); i++) {

                    System.out.println(red.get(i));

                }
                System.out.println("to 2o sunolo apoteleitai apo tous komvous");
                for (int i = 0; i < blue.size(); i++) {

                    System.out.println(blue.get(i));

                }
            }
        
    }

    public static String[] DFS(boolean[][] adjacencyList, int vertex, String[] color, int metritis, int[] visited, int metritis_akmon) {

        outerloop:

        if (metritis_akmon != 0) {

            for (int j = 0; j < color.length; j++) {

                if (vertex != j) {
                    if (adjacencyList[vertex][j] == true) {

                        --metritis_akmon;

                        if ((color[vertex] != color[j])) {
                            adjacencyList[vertex][j] = false;
                            adjacencyList[j][vertex] = false;
                            if (color[j] == "white") {
                                if (color[vertex] == "red") {
                                    color[j] = "blue";

                                } else {
                                    color[j] = "red";
                                }
                                if (metritis < color.length) {
                                    visited[metritis] = j;

                                    metritis++;

                                }

                                vertex = j;

                                return DFS(adjacencyList, vertex, color, metritis, visited, metritis_akmon);
                            }
                        }

                        if (color[vertex] == color[j]) {

                            color[0] = "green";
                            break outerloop;

                        }

                    }
                    if ((adjacencyList[vertex][color.length - 1] == false) & (j == (color.length - 1))) {
                        if (metritis < color.length) {
                            vertex = visited[metritis - 1];
                            metritis--;

                            return DFS(adjacencyList, vertex, color, metritis, visited, metritis_akmon);
                        }
                    }

                }
            }

        }

        return color;
    }

    public static String[] BFS(boolean[][] adjacencyList, int vertex, String[] color, int metritis_akmon, Queue<Integer> queue) {

        outerloop:

        while (queue.size() != 0) {
            vertex = queue.poll();

            for (int j = 0; j < color.length; j++) {
                if (vertex != j) {
                    if (adjacencyList[vertex][j] == true) {
                        queue.add(j);
                        if ((color[vertex] != color[j])) {
                            adjacencyList[vertex][j] = false;
                            adjacencyList[j][vertex] = false;
                            if (color[j] == "white") {
                                if (color[vertex] == "red") {
                                    color[j] = "blue";

                                } else {
                                    color[j] = "red";
                                }

                            }
                        }

                        if (color[vertex] == color[j]) {

                            color[0] = "green";
                            break outerloop;

                        }

                    }

                }
            }

        }

        return color;
    }
}
