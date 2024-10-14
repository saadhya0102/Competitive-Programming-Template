public class FenwickTree<T extends Number> {
    private final T[] tree;
    private final int size;
    private final BiFunction<T, T, T> addFn;  // Function to add two elements
    private final BiFunction<T, T, T> subtractFn;  // Function to subtract two elements
    private final T zero;

    // Constructor to initialize Fenwick Tree with array size and the operation (addition, subtraction)
    @SuppressWarnings("unchecked")
    public FenwickTree(int size, BiFunction<T, T, T> addFn, BiFunction<T, T, T> subtractFn, T zero) {
        this.size = size;
        this.tree = (T[]) new Number[size + 1];
        this.addFn = addFn;
        this.subtractFn = subtractFn;
        this.zero = zero;

        // Initialize the tree array with the zero element
        for (int i = 0; i <= size; i++) {
            tree[i] = zero;
        }
    }

    // Update the Fenwick Tree by adding value to index 'i'
    public void update(int i, T value) {
        while (i <= size) {
            tree[i] = addFn.apply(tree[i], value);
            i += i & -i;  // Move to the next index
        }
    }

    // Get the prefix sum from index 1 to i
    public T query(int i) {
        T sum = zero;
        while (i > 0) {
            sum = addFn.apply(sum, tree[i]);
            i -= i & -i;  // Move to the parent index
        }
        return sum;
    }

    // Get the sum from range [left, right]
    public T rangeQuery(int left, int right) {
        return subtractFn.apply(query(right), query(left - 1));
    }
}
