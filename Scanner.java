public class Scanner {
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
