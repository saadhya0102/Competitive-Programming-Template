public class DSU {
    int[] parents;
    int[] sizes;
    int components;
    int size;

    public DSU(int n) {
        size = n;
        parents = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
        components = n;
    }

    public int find(int x) {
        return parents[x] == x ? x : (parents[x] = find(parents[x]));
    }

    public boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) return false;
        size--;
        if (sizes[xRoot] < sizes[yRoot]) {
            return union(yRoot, xRoot);
        }
        parents[yRoot] = xRoot;
        sizes[xRoot] += sizes[yRoot];
        components--;
        return true;
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public boolean fullyConnected() {
        return components == 1;
    }
}
