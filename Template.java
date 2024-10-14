import java.io.*;
import java.math.*;
import java.util.*;
import java.util.function.*;

public class Template {
    public static void main(String[] args) {
        Scanner sc = new Scanner();
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            
        }
        flush();
    }


    @SuppressWarnings("all")
    static boolean debug = false;
    @SuppressWarnings("all")
    static int MOD = 1_000_000_007;
    @SuppressWarnings("all")
    static int MOD2 = 998_244_353;
    @SuppressWarnings("all")
    static int[][] dir2 = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    @SuppressWarnings("all")
    static int[][] dir3 = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};

    @SuppressWarnings("all")
    static long invMod(long val, long mod) {
        val = val % mod;
        return val <= 1 ? val : mod - mod / val * invMod(mod % val, mod) % mod;
    }

    @SuppressWarnings("all")
    static long invMod2(long val, long mod) {
        return exp(val, mod - 2, mod);
    }

    @SuppressWarnings("all")
    static long exp(long val, long pow, long mod) {
        if (pow < 0) return invMod(exp(val, -pow, mod), mod);
        val %= mod;
        long result = 1;
        while (pow > 0) {
            if ((pow & 1) > 0) result = (result * val) % mod;
            val = (val * val) % mod;
            pow /= 2;
        }
        return result;
    }

    @SuppressWarnings("all")
    static long[] fact = null;
    @SuppressWarnings("all")
    static long[] invFact = null;
    @SuppressWarnings("all")
    static long tempMod = -1;

    @SuppressWarnings("all")
    static void popFact(int max) {
        fact = new long[max + 1];
        fact[0] = 1;
        for (int i = 1; i <= max; i++) {
            fact[i] = i * fact[i - 1];
        }
        tempMod = -1;
    }

    @SuppressWarnings("all")
    static void popFact(int max, long mod) {
        fact = new long[max + 1];
        invFact = new long[max + 1];
        fact[0] = 1;
        for (int i = 1; i <= max; i++) {
            fact[i] = (i * fact[i - 1]) % mod;
        }
        invFact[max] = invMod(fact[max], mod);
        for (int i = max; i >= 1; i--) {
            invFact[i - 1] = (i * invFact[i]) % mod;
        }
        tempMod = mod;
    }

    @SuppressWarnings("all")
    static long choose(int n, int k) {
        if (k > n) return 0;
        if (n > 20) {
            k = Math.min(k, n - k);
            long result = 1;
            for (int x = 0; x < k; x++) {
                result *= (n - x);
                result /= (x + 1);
            }
            return result;
        }
        if (tempMod != -1 || fact == null || fact.length <= n) popFact(Math.max(n, n << 1));
        return fact[n] / fact[k] / fact[n - k];
    }

    @SuppressWarnings("all")
    static long choose(int n, int k, long mod) {
        if (k > n) return 0;
        if (tempMod != mod || fact == null || fact.length <= n || invFact == null || invFact.length <= n)
            popFact(Math.max(n, n << 1), mod);
        return ((fact[n] * invFact[k]) % mod * invFact[n - k]) % mod;
    }

    @SuppressWarnings("all")
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @SuppressWarnings("all")
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @SuppressWarnings("all")
    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    @SuppressWarnings("all")
    static long phi(long n) {
        long result = n;
        for (long x = 2; x * x <= n; x++) {
            if (n % x == 0) {
                while (n % x == 0) n /= x;
                result -= result / x;
            }
        }
        return result - (n > 1 ? result / n : 0);
    }

    @SuppressWarnings("all")
    static int getMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }

    @SuppressWarnings("all")
    static long getMin(long[] arr) {
        long min = Long.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }

    @SuppressWarnings("all")
    static short getMin(short[] arr) {
        short min = Short.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }

    @SuppressWarnings("all")
    static <U extends Comparable<U>> U getMin(U[] arr) {
        int size = arr.length;
        if (size == 0) return null;
        U min = arr[0];
        for (int i = 1; i < size; i++) {
            U next = arr[i];
            if (next == null) continue;
            if (min == null || next.compareTo(min) < 0) min = next;
        }
        return min;
    }

    @SuppressWarnings("all")
    static <U extends Comparable<U>> U getMin(List<U> list) {
        if (list.isEmpty()) return null;
        U min = list.get(0);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            U next = list.get(i);
            if (next == null) continue;
            if (min == null || next.compareTo(min) < 0) min = next;
        }
        return min;
    }

    @SuppressWarnings("all")
    static int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    @SuppressWarnings("all")
    static long getMax(long[] arr) {
        long max = Long.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    @SuppressWarnings("all")
    static short getMax(short[] arr) {
        short max = Short.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    @SuppressWarnings("all")
    static <U extends Comparable<U>> U getMax(List<U> list) {
        if (list.isEmpty()) return null;
        U max = list.get(0);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            U next = list.get(i);
            if (next == null) continue;
            if (max == null || next.compareTo(max) > 0) max = next;
        }
        return max;
    }

    @SuppressWarnings("all")
    static <U extends Comparable<U>> U getMax(U[] arr) {
        int size = arr.length;
        if (size == 0) return null;
        U max = arr[0];
        for (int i = 1; i < size; i++) {
            U next = arr[i];
            if (next == null) continue;
            if (max == null || next.compareTo(max) > 0) max = next;
        }
        return max;
    }

    @SuppressWarnings("all")
    static int[] prefSum(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = arr[i] + (i == 0 ? 0 : result[i - 1]);
        }
        return result;
    }

    @SuppressWarnings("all")
    static long[] prefSum(long[] arr) {
        int len = arr.length;
        long[] result = new long[len];
        for (int i = 0; i < len; i++) {
            result[i] = arr[i] + (i == 0 ? 0L : result[i - 1]);
        }
        return result;
    }

    @SuppressWarnings("all")
    static short[] prefSum(short[] arr) {
        int len = arr.length;
        short[] result = new short[len];
        for (int i = 0; i < len; i++) {
            result[i] = (short) (arr[i] + (i == 0 ? 0 : result[i - 1]));
        }
        return result;
    }

    @SuppressWarnings("all")
    static BigInteger[] prefSum(BigInteger[] arr) {
        int len = arr.length;
        BigInteger[] result = new BigInteger[len];
        for (int i = 0; i < len; i++) {
            result[i] = arr[i].add(i == 0 ? BigInteger.ZERO : result[i - 1]);
        }
        return result;
    }

    @SuppressWarnings("all")
    static int[] suffixSum(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = arr[i] + (i == len - 1 ? 0 : result[i + 1]);
        }
        return result;
    }

    @SuppressWarnings("all")
    static long[] suffixSum(long[] arr) {
        int len = arr.length;
        long[] result = new long[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = arr[i] + (i == len - 1 ? 0 : result[i + 1]);
        }
        return result;
    }

    @SuppressWarnings("all")
    static short[] suffixSum(short[] arr) {
        int len = arr.length;
        short[] result = new short[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = (short) (arr[i] + (i == len - 1 ? 0 : result[i + 1]));
        }
        return result;
    }

    @SuppressWarnings("all")
    static BigInteger[] suffixSum(BigInteger[] arr) {
        int len = arr.length;
        BigInteger[] result = new BigInteger[len];
        for (int i = len - 1; i >= 0; i++) {
            result[i] = arr[i].add(i == len - 1 ? BigInteger.ZERO : result[i + 1]);
        }
        return result;
    }

    @SuppressWarnings("all")
    static <U extends Comparable<U>> int lowerBound(int l, int r, U target, Function<Integer, U> function) {
        while (l < r) {
            int mid = (l + r) >> 1;
            U curr = function.apply(mid);
            int c = curr.compareTo(target);
            if (c < 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    @SuppressWarnings("all")
    static <U extends Comparable<U>> long lowerBound(long l, long r, Function<Long, U> function, U target) {
        while (l < r) {
            long mid = (l + r) >> 1;
            U curr = function.apply(mid);
            int c = curr.compareTo(target);
            if (c < 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    @SuppressWarnings("all")
    static <U extends Comparable<U>> int upperBound(int l, int r, U target, Function<Integer, U> function) {
        while (l < r) {
            int mid = (l + r) >> 1;
            U curr = function.apply(mid);
            int c = curr.compareTo(target);
            if (c <= 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    @SuppressWarnings("all")
    static <U extends Comparable<U>> long upperBound(long l, long r, Function<Long, U> function, U target) {
        while (l < r) {
            long mid = (l + r) >> 1;
            U curr = function.apply(mid);
            int c = curr.compareTo(target);
            if (c <= 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    @SuppressWarnings("all")
    static PrintWriter pw = new PrintWriter(System.out);
    @SuppressWarnings("all")
    static String delim = " ";

    @SuppressWarnings("all")
    static void println() {
        pw.println();
        if (debug) pw.flush();
    }

    @SuppressWarnings("all")
    static void print(Object x) {
        pw.print(x);
        if (debug) pw.flush();
    }

    @SuppressWarnings("all")
    static <T> void println(Object x) {
        if (x.getClass().isArray()) {
            String result;
            if (x instanceof int[]) {
                result = Arrays.toString((int[]) x);
            } else if (x instanceof long[]) {
                result = Arrays.toString((long[]) x);
            } else if (x instanceof double[]) {
                result = Arrays.toString((double[]) x);
            } else if (x instanceof float[]) {
                result = Arrays.toString((float[]) x);
            } else if (x instanceof boolean[]) {
                result = Arrays.toString((boolean[]) x);
            } else if (x instanceof short[]) {
                result = Arrays.toString((short[]) x);
            } else if (x instanceof char[]) {
                result = Arrays.toString((char[]) x);
            } else if (x instanceof byte[]) {
                result = Arrays.toString((byte[]) x);
            } else {
                result = Arrays.toString((Object[]) x);
            }
            pw.println(result);
            if (debug) pw.flush();
            return;
        }
        pw.println(x);
        if (debug) pw.flush();
    }

    @SuppressWarnings("all")
    static <T> void iterPrint(Iterable<T> arr) {
        boolean space = false;
        for (T t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    @SuppressWarnings("all")
    static <T> void iterPrint(T[] arr) {
        boolean space = false;
        for (T t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    @SuppressWarnings("all")
    static void iterPrint(int[] arr) {
        boolean space = false;
        for (int t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    @SuppressWarnings("all")
    static void iterPrint(long[] arr) {
        boolean space = false;
        for (long t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    @SuppressWarnings("all")
    static void iterPrint(double[] arr) {
        boolean space = false;
        for (double t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    @SuppressWarnings("all")
    static void iterPrint(char[] arr) {
        boolean space = false;
        for (char t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    @SuppressWarnings("all")
    static void iterPrint(boolean[] arr) {
        boolean space = false;
        for (boolean t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    @SuppressWarnings("all")
    static void iterPrint(float[] arr) {
        boolean space = false;
        for (float t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    @SuppressWarnings("all")
    static void iterPrint(short[] arr) {
        boolean space = false;
        for (short t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    @SuppressWarnings("all")
    static void iterPrint(byte[] arr) {
        boolean space = false;
        for (short t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    @SuppressWarnings("all")
    static void printf(String format, Object... args) {
        pw.printf(format, args);
        if (debug) pw.flush();
    }

    static void flush() {
        pw.flush();
    }

    @SuppressWarnings("all")
    static class Scanner {
        BufferedReader br;
        StringTokenizer curr;
        String delim = " \t\n\r\f";

        Scanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        Scanner(String fileName) {
            try {
                br = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Switching to stdin...");
                br = new BufferedReader(new InputStreamReader(System.in));
            }
        }

        boolean setDelimiter(String delim) {
            this.delim = delim;
            return curr == null;
        }

        String getDelimiter() {
            return delim;
        }

        String next() {
            if (curr == null) {
                try {
                    curr = new StringTokenizer(br.readLine(), delim);
                } catch (IOException e) {
                    return null;
                }
            }
            String result = curr.nextToken();
            if (!curr.hasMoreTokens()) curr = null;
            return result;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        float nextFloat() {
            return Float.parseFloat(next());
        }

        BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        boolean nextBoolean() {
            return Boolean.parseBoolean(next());
        }

        byte nextByte() {
            return Byte.parseByte(next());
        }

        short nextShort() {
            return Short.parseShort(next());
        }

        String nextLine() {
            if (curr == null) {
                try {
                    return br.readLine();
                } catch (IOException e) {
                    return null;
                }
            }
            StringBuilder remaining = new StringBuilder();
            remaining.append(curr.nextToken());
            while (curr.hasMoreTokens()) {
                remaining.append(' ').append(curr.nextToken());
            }
            return remaining.toString();
        }

        String[] nextStringArray(int n) {
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = next();
            }
            return arr;
        }

        int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

        double[] nextDoubleArray(int n) {
            double[] arr = new double[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextDouble();
            }
            return arr;
        }

        float[] nextFloatArray(int n) {
            float[] arr = new float[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextFloat();
            }
            return arr;
        }

        BigInteger[] nextBigIntegerArray(int n) {
            BigInteger[] arr = new BigInteger[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextBigInteger();
            }
            return arr;
        }

        boolean[] nextBooleanArray(int n) {
            boolean[] arr = new boolean[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextBoolean();
            }
            return arr;
        }

        byte[] nextByteArray(int n) {
            byte[] arr = new byte[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextByte();
            }
            return arr;
        }

        short[] nextShortArray(int n) {
            short[] arr = new short[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextShort();
            }
            return arr;
        }

        List<String> nextStringList(int n) {
            List<String> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(next());
            }
            return list;
        }

        List<Integer> nextIntList(int n) {
            List<Integer> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(nextInt());
            }
            return list;
        }

        List<Long> nextLongList(int n) {
            List<Long> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(nextLong());
            }
            return list;
        }

        List<Double> nextDoubleList(int n) {
            List<Double> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(nextDouble());
            }
            return list;
        }

        List<Float> nextFloatList(int n) {
            List<Float> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(nextFloat());
            }
            return list;
        }

        List<BigInteger> nextBigIntegerList(int n) {
            List<BigInteger> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(nextBigInteger());
            }
            return list;
        }

        List<Boolean> nextBooleanList(int n) {
            List<Boolean> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(nextBoolean());
            }
            return list;
        }

        List<Byte> nextByteList(int n) {
            List<Byte> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(nextByte());
            }
            return list;
        }

        List<Short> nextShortList(int n) {
            List<Short> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(nextShort());
            }
            return list;
        }
    }

    @SuppressWarnings("all")
    static class ListNode<T> {
        ListNode<T> prev;
        ListNode<T> next;
        T val;

        ListNode() {
            val = null;
        }

        ListNode(T val) {
            this.val = val;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            if (prev != null) {
                result.append(prev.val);
                result.append(" -> ");
            }
            result.append(val);
            if (next != null) {
                result.append(" <- ");
                result.append(next.val);
            }
            return result.toString();
        }

        public String forwardString() {
            return forwardString("[", ", ", "]");
        }

        public String backwarString() {
            return backwardString("[", ", ", "]");
        }

        public String forwardString(String prefix, String delim, String suffix) {
            StringBuilder result = new StringBuilder();
            result.append(prefix);
            result.append(val);
            ListNode<T> curr = next;
            while (curr != null) {
                result.append(delim);
                result.append(curr.val);
                curr = curr.next;
            }
            result.append(suffix);
            return result.toString();
        }

        private String backwardString(String prefix, String delim, String suffix) {
            StringBuilder result = new StringBuilder();
            result.append(suffix);
            result.insert(0, val);
            ListNode<T> curr = prev;
            while (curr != null) {
                result.insert(0, delim);
                result.insert(0, curr.val);
                curr = curr.prev;
            }
            result.insert(0, prefix);
            return result.toString();
        }
    }

    @SuppressWarnings("all")
    static class BSTNode<T> {
        public BSTNode<T> left;
        public BSTNode<T> right;
        public T val;

        public BSTNode() {
            this.val = null;
        }

        public BSTNode(T val) {
            this.val = val;
        }

        public List<T> toPreorderList() {
            List<T> result = new ArrayList<>();
            executePreOrder(node -> result.add(node.val));
            return result;
        }

        public List<T> toInorderList() {
            List<T> result = new ArrayList<>();
            executeInOrder(node -> result.add(node.val));
            return result;
        }

        public List<T> toPostorderList() {
            List<T> result = new ArrayList<>();
            executePostOrder(node -> result.add(node.val));
            return result;
        }

        public void executePreOrder(Consumer<BSTNode<T>> action) {
            action.accept(this);
            if (left != null) left.executePreOrder(action);
            if (right != null) right.executePreOrder(action);
        }

        public void executeInOrder(Consumer<BSTNode<T>> action) {
            if (left != null) left.executeInOrder(action);
            action.accept(this);
            if (right != null) right.executeInOrder(action);
        }

        public void executePostOrder(Consumer<BSTNode<T>> action) {
            if (left != null) left.executePostOrder(action);
            if (right != null) right.executePostOrder(action);
            action.accept(this);
        }

        public <R> R accumulatePreOrder(R initial, Function<BSTNode<T>, R> function, BiFunction<R, R, R> accumulator) {
            R result = initial;
            result = accumulator.apply(result, function.apply(this));
            if (left != null) result = left.accumulatePreOrder(result, function, accumulator);
            if (right != null) result = right.accumulatePreOrder(result, function, accumulator);
            return result;
        }

        public <R> R accumulateInOrder(R initial, Function<BSTNode<T>, R> function, BiFunction<R, R, R> accumulator) {
            R result = initial;
            if (left != null) result = left.accumulateInOrder(result, function, accumulator);
            result = accumulator.apply(result, function.apply(this));
            if (right != null) result = right.accumulateInOrder(result, function, accumulator);
            return result;
        }

        public <R> R accumulatePostOrder(R initial, Function<BSTNode<T>, R> function, BiFunction<R, R, R> accumulator) {
            R result = initial;
            if (left != null) result = left.accumulatePostOrder(result, function, accumulator);
            if (right != null) result = right.accumulatePostOrder(result, function, accumulator);
            result = accumulator.apply(result, function.apply(this));
            return result;
        }
    }

    @SuppressWarnings("all")
    static class DSU {
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

    @SuppressWarnings("all")
    static class SegTree<T, R> {
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

    @SuppressWarnings("all")
    static class State<A> {
        public A a;

        public State() {
            a = null;
        }

        public State(A a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return toString("<", ">");
        }

        public String toString(String prefix, String suffix) {
            StringBuilder sb = new StringBuilder();
            sb.append(prefix);
            sb.append(a);
            sb.append(suffix);
            return sb.toString();
        }
    }

    @SuppressWarnings("all")
    static class DualState<A, B> {
        public A a;
        public B b;

        public DualState() {
            a = null;
            b = null;
        }

        public DualState(A a, B b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return toString("<", ", ", ">");
        }

        public String toString(String prefix, String delimiter, String suffix) {
            StringBuilder sb = new StringBuilder();
            sb.append(prefix);
            sb.append(a);
            sb.append(delimiter);
            sb.append(b);
            sb.append(suffix);
            return sb.toString();
        }
    }

    @SuppressWarnings("all")
    static class TriState<A, B, C> {
        public A a;
        public B b;
        public C c;

        public TriState() {
            a = null;
            b = null;
            c = null;
        }

        public TriState(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return toString("<", ", ", ">");
        }

        public String toString(String prefix, String delimiter, String suffix) {
            StringBuilder sb = new StringBuilder();
            sb.append(prefix);
            sb.append(a);
            sb.append(delimiter);
            sb.append(b);
            sb.append(delimiter);
            sb.append(c);
            sb.append(suffix);
            return sb.toString();
        }
    }

    @SuppressWarnings("all")
    static class QuadState<A, B, C, D> {
        public A a;
        public B b;
        public C c;
        public D d;

        public QuadState() {
            a = null;
            b = null;
            c = null;
            d = null;
        }

        public QuadState(A a, B b, C c, D d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString() {
            return toString("<", ", ", ">");
        }

        public String toString(String prefix, String delimiter, String suffix) {
            StringBuilder sb = new StringBuilder();
            sb.append(prefix);
            sb.append(a);
            sb.append(delimiter);
            sb.append(b);
            sb.append(delimiter);
            sb.append(c);
            sb.append(delimiter);
            sb.append(d);
            sb.append(suffix);
            return sb.toString();
        }
    }
}
