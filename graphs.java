import java.util.*;

public class graphs {
    private int V; // Number of vertices
    private List<LinkedList<Integer>> adj; // Adjacency list for each vertex
    private boolean isDirected; // Flag to indicate if the graph is directed or undirected

    // Constructor to create an empty graph
    public graphs(int v, boolean isDirected) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new LinkedList<Integer>());
        }
        this.isDirected = isDirected;
    }

    // Function to add an edge between vertices v and w
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        if (!isDirected) {
            adj.get(w).add(v);
        }
    }

    // Function to perform Breadth-First Search (BFS) traversal on the graph
    public void BFS(int s) {
        boolean[] visited = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s] = true;
        queue.add(s);
        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");
            Iterator<Integer> i = adj.get(s).listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    // Function to perform Depth-First Search (DFS) traversal on the graph
    public void DFS(int v) {
        boolean[] visited = new boolean[V];
        DFSUtil(v, visited);
    }

    // Helper function for DFS traversal
    private void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        Iterator<Integer> i = adj.get(v).listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    // Function to find the shortest path between two vertices using Breadth-First
    // Search (BFS)
    public int shortestPath(int s, int d) {
        boolean[] visited = new boolean[V];
        int[] dist = new int[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s] = true;
        queue.add(s);
        dist[s] = 0;
        while (queue.size() != 0) {
            s = queue.poll();
            Iterator<Integer> i = adj.get(s).listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                    dist[n] = dist[s] + 1;
                    if (n == d) {
                        return dist[d];
                    }
                }
            }
        }
        return -1;
    }

    // Function to detect cycles in the graph using Depth-First Search (DFS)
    public boolean hasCycle() {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (hasCycleUtil(i, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    // Helper function for cycle detection
    private boolean hasCycleUtil(int v, boolean[] visited, boolean[] recStack) {
        if (!visited[v]) {
            visited[v] = true;
            recStack[v] = true;
            Iterator<Integer> i = adj.get(v).listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n] && hasCycleUtil(n, visited, recStack)) {
                    return true;
                } else if (recStack[n]) {
                    return true;
                }
            }
        }
        recStack[v] = false;
        return false;
    }

    // Function to find the Minimum Spanning Tree using Kruskal's Algorithm
    public List<int[]> kruskalMST() {
        List<int[]> mst = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        DisjointSet ds = new DisjointSet(V);
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                pq.offer(new int[] { u, v, 1 }); // 1 is the default weight
            }
        }
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int u = edge[0], v = edge[1], w = edge[2];
            if (ds.find(u) != ds.find(v)) {
                mst.add(new int[] { u, v, w });
                ds.union(u, v);
            }
        }
        return mst;
    }

    // Function to perform Topological Sorting using Depth-First Search
    public List<Integer> topologicalSort() {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    // Helper function for Topological Sorting
    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int u : adj.get(v)) {
            if (!visited[u]) {
                topologicalSortUtil(u, visited, stack);
            }
        }
        stack.push(v);
    }

    // Function to find Strongly Connected Components using Kosaraju's Algorithm
    public List<List<Integer>> kosarajuSCC() {
        List<List<Integer>> scc = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                kosarajuDFS(i, visited, stack);
            }
        }
        graphs gt = getTranspose();
        visited = new boolean[V];
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                gt.kosarajuDFS(v, visited, component);
                scc.add(component);
            }
        }
        return scc;
    }

    // Helper function for Kosaraju's Algorithm
    private void kosarajuDFS(int v, boolean[] visited, List<Integer> component) {
        visited[v] = true;
        component.add(v);
        for (int u : adj.get(v)) {
            if (!visited[u]) {
                kosarajuDFS(u, visited, component);
            }
        }
    }

    // Function to get the transpose of the graph
    private graphs getTranspose() {
        graphs gt = new graphs(V, isDirected);
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                gt.addEdge(v, u);
            }
        }
        return gt;
    }

}

class DisjointSet { //used for minimum spanning tree
    int[] parent;
    int[] rank;
    int n;

    public DisjointSet(int n) {
        this.n = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py)
            return;
        if (rank[px] > rank[py]) {
            parent[py] = px;
        } else if (rank[py] > rank[px]) {
            parent[px] = py;
        } else {
            parent[px] = py;
            rank[py]++;
        }
    }
}