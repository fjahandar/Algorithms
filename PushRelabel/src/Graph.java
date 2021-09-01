import java.util.*;

class Vertex {
    int h;
    int excess_flow;

    Vertex() {
        h = 0;
        excess_flow = 0;
    }
}

class Graph {
    int v;
    int e;
    Vertex[] vertices;
    int[][] capacity;
    int[][] flow;

    Graph(int v, int e) {
        this.v = v;
        this.e = e;
        vertices = new Vertex[v];
        for (int i = 0; i < v; i++) {
            vertices[i] = new Vertex();
        }
        capacity = new int[v][v];
        flow = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                flow[i][j] = 0;
            }
        }
    }

    void AddEdge(int x, int y, int cap) {
        capacity[x][y] = cap;
    }

    int getOverflowVertex(int s, int t) {
        for (int i = 1; i < v - 1; i++) {
            if (vertices[i].excess_flow > 0) {
                for (int j = 0; j < v; j++) {
                    if (capacity[i][j] != 0) {
                        if (capacity[i][j] != flow[i][j]) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public void preflow(int s) {
        vertices[s].h = v;
        for (int i = 1; i < v; i++) {
            if (capacity[s][i] != 0) {
                flow[s][i] = capacity[s][i];
                vertices[i].excess_flow += flow[s][i];
                AddEdge(i, s, 0);
                flow[i][s] = -flow[s][i];
            }
        }
    }

    boolean push(int u) {
        for (int i = 0; i < v; i++) {
            if (capacity[u][i] != 0) {
                if (flow[u][i] == capacity[u][i])
                    continue;
                if (vertices[u].h > vertices[i].h) {
                    int Flow = Math.min(capacity[u][i] - flow[u][i], vertices[u].excess_flow);
                    vertices[u].excess_flow -= Flow;
                    vertices[i].excess_flow += Flow;
                    flow[u][i] += Flow;
                    flow[i][u] -= Flow;
                    return true;
                }
            }
        }
        return false;
    }

    void relabel(int u) {
        System.out.println(u + "preflow");
        int minh = Integer.MAX_VALUE;
        for (int i = 0; i < v; i++) {
            if (capacity[u][i] != 0) {
                if (capacity[u][i] == flow[u][i])
                    continue;
                if (vertices[i].h < minh) {
                    minh = vertices[i].h;
                    vertices[u].h = minh + 1;
                }
            }
        }
    }

    int maxFlow(int s, int t) {
        preflow(s);
        int u = getOverflowVertex(s, t);
        while (u != -1) {
            if (!push(u))
                relabel(u);
            u = getOverflowVertex(s, t);
        }
        return vertices[t].excess_flow;
    }
}

class pushrelabel {
    public static void main(String[] args) {
        Graph G = new Graph(4, 5);
        G.AddEdge(0,1,3);
        G.AddEdge(0,2,3);
        G.AddEdge(1,3,2);
        G.AddEdge(1,2,1);
        G.AddEdge(2,3,2);
        System.out.println("The max flow is : " + G.maxFlow(0, 3));
    }
}
