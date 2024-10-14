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
