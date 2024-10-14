public class SegTree<T, R> {
    private final BiFunction<R, R, R> accumulator;
    private final Function<T, R> mapper;
    int n;
    R[] tree;

    SegTree(T[] arr, Function<T, R> individualMapper, BiFunction<R, R, R> accumulator) {
        tree = (R[]) new Object[arr.length << 2];
        this.n = arr.length;
        this.mapper = individualMapper;
        this.accumulator = accumulator;
        build(arr, 0, 0, n - 1);
    }

    SegTree(List<T> list, Function<T, R> individualMapper, BiFunction<R, R, R> accumulator) {
        tree = (R[]) new Object[list.size() << 2];
        this.n = list.size();
        this.mapper = individualMapper;
        this.accumulator = accumulator;
        build(list, 0, 0, n - 1);
    }

    private void build(T[] arr, int i, int l, int r) {
        if (l == r) {
            tree[i] = mapper.apply(arr[l]);
            return;
        }
        int m = (l + r) / 2;
        int i1 = i * 2 + 1, i2 = i1 + 1;
        build(arr, i1, l, m);
        build(arr, i2, m + 1, r);
        tree[i] = accumulator.apply(tree[i1], tree[i2]);
    }

    private void build(List<T> list, int i, int l, int r) {
        if (l == r) {
            tree[i] = mapper.apply(list.get(l));
            return;
        }
        int m = (l + r) / 2;
        int i1 = i * 2 + 1, i2 = i1 + 1;
        build(list, i1, l, m);
        build(list, i2, m + 1, r);
        tree[i] = accumulator.apply(tree[i1], tree[i2]);
    }

    R query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private R query(int i, int cl, int cr, int l, int r) {
        if (l > r || l < 0 || r >= n)
            throw new IllegalArgumentException("Invalid Range: " + l + ", " + r + " for size " + n);
        if (l == cl && r == cr) return tree[i];
        int m = (cl + cr) / 2;
        int i1 = i * 2 + 1, i2 = i1 + 1;
        if (m >= r) {
            return query(i1, cl, m, l, r);
        } else if (m < l) {
            return query(i2, m + 1, cr, l, r);
        } else {
            return accumulator.apply(query(i1, cl, m, l, m), query(i2, m + 1, cr, m + 1, r));
        }
    }

    void set(int index, T val) {
        update(0, 0, n - 1, index, val);
    }

    private void update(int i, int l, int r, int index, T val) {
        if (l == r) {
            tree[i] = mapper.apply(val);
            return;
        }
        int m = (l + r) / 2;
        int i1 = i * 2 + 1, i2 = i1 + 1;
        if (index <= m) update(i1, l, m, index, val);
        else update(i2, m + 1, r, index, val);
        tree[i] = accumulator.apply(tree[i1], tree[i2]);
    }
}
