package adventofcode;

import java.util.LinkedList;

class Graph {
    int vertex;
    LinkedList<String> list[];

    public Graph (int vertex) {
        this.vertex = vertex;
        list = new LinkedList[vertex];

        for(int i=0; i<vertex; i++) {
            list[i] = new LinkedList<>();
        }
    }

    public void addEdge(String source, String destination) {
        list[source].add(destination);
    }
}






public class Commands {

}
