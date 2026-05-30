import java.util.*;

public class JavaSolutions {

    // =========================================================================
    // Q1 — Hello World Program
    // =========================================================================
    static class Q1_HelloWorld {
        public static void main(String[] args) {
            System.out.println("Hello, World!");
        }
    }

    // =========================================================================
    // Q2 — Simple Calculator
    // =========================================================================
    static class Q2_Calculator {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter first number: ");  double a = sc.nextDouble();
            System.out.print("Enter second number: "); double b = sc.nextDouble();
            System.out.print("Operation (+,-,*,/): ");  char op = sc.next().charAt(0);
            double result = switch (op) {
                case '+' -> a + b;
                case '-' -> a - b;
                case '*' -> a * b;
                case '/' -> b != 0 ? a / b : Double.NaN;
                default  -> throw new IllegalArgumentException("Unknown operator: " + op);
            };
            System.out.println("Result: " + result);
        }
    }

    // =========================================================================
    // Q3 — Even or Odd Checker
    // =========================================================================
    static class Q3_EvenOdd {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter an integer: ");
            int n = sc.nextInt();
            System.out.println(n + " is " + (n % 2 == 0 ? "Even" : "Odd"));
        }
    }

    // =========================================================================
    // Q4 — Leap Year Checker
    // =========================================================================
    static class Q4_LeapYear {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a year: ");
            int y = sc.nextInt();
            boolean leap = (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
            System.out.println(y + (leap ? " is a leap year." : " is not a leap year."));
        }
    }

    // =========================================================================
    // Q5 — Multiplication Table
    // =========================================================================
    static class Q5_MultiTable {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a number: ");
            int n = sc.nextInt();
            for (int i = 1; i <= 10; i++)
                System.out.printf("%d x %2d = %d%n", n, i, n * i);
        }
    }

    // =========================================================================
    // Q6 — Data Type Demonstration
    // =========================================================================
    static class Q6_DataTypes {
        public static void main(String[] args) {
            int     i = 42;
            float   f = 3.14f;
            double  d = 2.718281828;
            char    c = 'J';
            boolean b = true;
            System.out.println("int:     " + i);
            System.out.println("float:   " + f);
            System.out.println("double:  " + d);
            System.out.println("char:    " + c);
            System.out.println("boolean: " + b);
        }
    }

    // =========================================================================
    // Q7 — Type Casting Example
    // =========================================================================
    static class Q7_TypeCasting {
        public static void main(String[] args) {
            double d = 9.99;
            int    i = (int) d;       // truncates decimal part
            System.out.println("double " + d + " -> int " + i);

            int    x = 7;
            double y = (double) x;
            System.out.println("int " + x + " -> double " + y);
        }
    }

    // =========================================================================
    // Q8 — Operator Precedence
    // =========================================================================
    static class Q8_OpPrecedence {
        public static void main(String[] args) {
            int r1 = 10 + 5 * 2;           // 20  (* evaluated before +)
            int r2 = (10 + 5) * 2;         // 30  (parentheses first)
            int r3 = 10 + 5 * 2 - 8 / 4;  // 18
            System.out.println("10 + 5 * 2          = " + r1 + "  (* before +)");
            System.out.println("(10 + 5) * 2        = " + r2 + "  (parens first)");
            System.out.println("10 + 5*2 - 8/4      = " + r3);
        }
    }

    // =========================================================================
    // Q9 — Grade Calculator
    // =========================================================================
    static class Q9_GradeCalc {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter marks (0-100): ");
            int m = sc.nextInt();
            String grade = m >= 90 ? "A"
                         : m >= 80 ? "B"
                         : m >= 70 ? "C"
                         : m >= 60 ? "D" : "F";
            System.out.println("Grade: " + grade);
        }
    }

    // =========================================================================
    // Q10 — Number Guessing Game
    // =========================================================================
    static class Q10_GuessGame {
        public static void main(String[] args) {
            int secret = new Random().nextInt(100) + 1;
            Scanner sc = new Scanner(System.in);
            int guess, attempts = 0;
            do {
                System.out.print("Guess (1-100): ");
                guess = sc.nextInt();
                attempts++;
                if      (guess < secret) System.out.println("Too low!");
                else if (guess > secret) System.out.println("Too high!");
            } while (guess != secret);
            System.out.println("Correct! Solved in " + attempts + " attempt(s).");
        }
    }

    // =========================================================================
    // Q11 — Factorial Calculator
    // =========================================================================
    static class Q11_Factorial {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter non-negative integer: ");
            int n = sc.nextInt();
            long fact = 1;
            for (int i = 2; i <= n; i++) fact *= i;
            System.out.println(n + "! = " + fact);
        }
    }

    // =========================================================================
    // Q12 — Method Overloading
    // =========================================================================
    static class Q12_Overloading {
        static int    add(int a, int b)          { return a + b; }
        static double add(double a, double b)    { return a + b; }
        static int    add(int a, int b, int c)   { return a + b + c; }

        public static void main(String[] args) {
            System.out.println("add(3, 4)        = " + add(3, 4));
            System.out.println("add(1.5, 2.5)    = " + add(1.5, 2.5));
            System.out.println("add(1, 2, 3)     = " + add(1, 2, 3));
        }
    }

    // =========================================================================
    // Q13 — Recursive Fibonacci
    // =========================================================================
    static class Q13_Fibonacci {
        static long fib(int n) {
            if (n <= 1) return n;
            return fib(n - 1) + fib(n - 2);
        }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter n: ");
            int n = sc.nextInt();
            System.out.println("fib(" + n + ") = " + fib(n));
        }
    }

    // =========================================================================
    // Q14 — Array Sum and Average
    // =========================================================================
    static class Q14_ArrayStats {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Number of elements: ");
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Element " + (i + 1) + ": ");
                arr[i] = sc.nextInt();
            }
            long sum = 0;
            for (int x : arr) sum += x;
            System.out.println("Sum: " + sum + ", Average: " + (double) sum / n);
        }
    }

    // =========================================================================
    // Q15 — String Reversal
    // =========================================================================
    static class Q15_StringReverse {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a string: ");
            String s = sc.nextLine();
            System.out.println("Reversed: " + new StringBuilder(s).reverse());
        }
    }

    // =========================================================================
    // Q16 — Palindrome Checker
    // =========================================================================
    static class Q16_Palindrome {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a string: ");
            String raw = sc.nextLine().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            String rev = new StringBuilder(raw).reverse().toString();
            System.out.println(raw.equals(rev) ? "Palindrome" : "Not a palindrome");
        }
    }

    // =========================================================================
    // Q17 — Class and Object Creation
    // =========================================================================
    static class Q17_Car {
        String make, model;
        int year;

        Q17_Car(String make, String model, int year) {
            this.make = make; this.model = model; this.year = year;
        }
        void displayDetails() {
            System.out.printf("%d %s %s%n", year, make, model);
        }
        public static void main(String[] args) {
            new Q17_Car("Toyota", "Camry", 2022).displayDetails();
            new Q17_Car("Honda",  "Civic", 2021).displayDetails();
        }
    }

    // =========================================================================
    // Q18 — Inheritance Example
    // =========================================================================
    static class Q18_Animal {
        void makeSound() { System.out.println("(generic animal sound)"); }

        static class Dog extends Q18_Animal {
            @Override
            void makeSound() { System.out.println("Bark"); }
        }
        public static void main(String[] args) {
            new Q18_Animal().makeSound();
            new Dog().makeSound();
        }
    }

    // =========================================================================
    // Q19 — Interface Implementation
    // =========================================================================
    static class Q19_Instruments {
        interface Playable { void play(); }

        static class Guitar implements Playable {
            public void play() { System.out.println("Strumming the guitar"); }
        }
        static class Piano implements Playable {
            public void play() { System.out.println("Playing the piano"); }
        }
        public static void main(String[] args) {
            Playable[] instruments = { new Guitar(), new Piano() };
            for (Playable p : instruments) p.play();
        }
    }

    // =========================================================================
    // Q20 — Try-Catch (Division by Zero)
    // =========================================================================
    static class Q20_TryCatch {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Numerator: ");   int a = sc.nextInt();
            System.out.print("Denominator: "); int b = sc.nextInt();
            try {
                System.out.println("Result: " + (a / b));
            } catch (ArithmeticException e) {
                System.out.println("Error: Cannot divide by zero.");
            }
        }
    }

    // =========================================================================
    // Q21 — Custom Exception
    // =========================================================================
    static class Q21_AgeCheck {
        static class InvalidAgeException extends Exception {
            InvalidAgeException(String msg) { super(msg); }
        }
        static void validate(int age) throws InvalidAgeException {
            if (age < 18) throw new InvalidAgeException("Age " + age + " is under 18.");
        }
        public static void main(String[] args) {
            try {
                validate(15);
            } catch (InvalidAgeException e) {
                System.out.println("Caught: " + e.getMessage());
            }
        }
    }

    // =========================================================================
    // Q22 — File Writing
    // =========================================================================
    static class Q22_FileWrite {
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter text to save: ");
            String text = sc.nextLine();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
                bw.write(text);
            }
            System.out.println("Written to output.txt");
        }
    }

    // =========================================================================
    // Q23 — File Reading
    // =========================================================================
    static class Q23_FileRead {
        public static void main(String[] args) throws IOException {
            try (BufferedReader br = new BufferedReader(new FileReader("output.txt"))) {
                String line;
                while ((line = br.readLine()) != null) System.out.println(line);
            }
        }
    }

    // =========================================================================
    // Q24 — ArrayList Example
    // =========================================================================
    static class Q24_StudentList {
        public static void main(String[] args) {
            List<String> names = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter names (blank line to stop):");
            String name;
            while (!(name = sc.nextLine()).isEmpty()) names.add(name);
            System.out.println("Students: " + names);
        }
    }

    // =========================================================================
    // Q25 — HashMap Example
    // =========================================================================
    static class Q25_StudentMap {
        public static void main(String[] args) {
            Map<Integer, String> map = new HashMap<>();
            map.put(101, "Alice");
            map.put(102, "Bob");
            map.put(103, "Carol");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student ID: ");
            int id = sc.nextInt();
            System.out.println(map.getOrDefault(id, "Not found"));
        }
    }

    // =========================================================================
    // Q26 — Thread Creation
    // =========================================================================
    static class Q26_TwoThreads {
        static class Msg implements Runnable {
            String text; int times;
            Msg(String t, int n) { text = t; times = n; }
            public void run() {
                for (int i = 0; i < times; i++) System.out.println(text);
            }
        }
        public static void main(String[] args) {
            new Thread(new Msg("Thread A", 5)).start();
            new Thread(new Msg("Thread B", 5)).start();
        }
    }

    // =========================================================================
    // Q27 — Lambda Expressions
    // =========================================================================
    static class Q27_LambdaSort {
        public static void main(String[] args) {
            List<String> langs = new ArrayList<>(
                List.of("Python", "Java", "Kotlin", "Go", "Rust"));
            langs.sort((a, b) -> a.compareToIgnoreCase(b));
            System.out.println(langs);
        }
    }

    // =========================================================================
    // Q28 — Stream API
    // =========================================================================
    static class Q28_StreamAPI {
        public static void main(String[] args) {
            List<Integer> evens = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
            System.out.println("Even numbers: " + evens);
        }
    }

    // =========================================================================
    // Q29 — Records (Java 16+)
    // =========================================================================
    static class Q29_Records {
        record Person(String name, int age) {}

        public static void main(String[] args) {
            List<Person> people = List.of(
                new Person("Alice", 30),
                new Person("Bob",   17),
                new Person("Carol", 25));

            System.out.println("All: " + people);
            System.out.println("Adults:");
            people.stream()
                  .filter(p -> p.age() >= 18)
                  .forEach(System.out::println);
        }
    }

    // =========================================================================
    // Q30 — Pattern Matching for switch (Java 21)
    // =========================================================================
    static class Q30_PatternSwitch {
        static String describe(Object obj) {
            return switch (obj) {
                case Integer i -> "Integer: " + i;
                case String  s -> "String of length " + s.length() + ": \"" + s + "\"";
                case Double  d -> "Double: " + d;
                case null      -> "null value";
                default        -> "Other type: " + obj.getClass().getSimpleName();
            };
        }
        public static void main(String[] args) {
            System.out.println(describe(42));
            System.out.println(describe("hello"));
            System.out.println(describe(3.14));
            System.out.println(describe(null));
        }
    }

    // =========================================================================
    // Q31 — Basic JDBC Connection
    // =========================================================================
    static class Q31_JdbcConnect {
        // Requires: SQLite JDBC driver on classpath
        // Setup:    CREATE TABLE students (id INT, name TEXT);
        public static void main(String[] args) throws Exception {
            String url = "jdbc:sqlite:school.db";
            try (Connection con = DriverManager.getConnection(url);
                 Statement  st  = con.createStatement();
                 ResultSet  rs  = st.executeQuery("SELECT * FROM students")) {
                ResultSetMetaData meta = rs.getMetaData();
                while (rs.next()) {
                    for (int i = 1; i <= meta.getColumnCount(); i++)
                        System.out.print(rs.getString(i) + "\t");
                    System.out.println();
                }
            }
        }
    }

    // =========================================================================
    // Q32 — Insert and Update Operations in JDBC
    // =========================================================================
    static class Q32_StudentDAO {
        static final String URL = "jdbc:sqlite:school.db";

        static void insert(int id, String name) throws Exception {
            try (Connection c = DriverManager.getConnection(URL);
                 PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO students VALUES(?,?)")) {
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.executeUpdate();
            }
        }
        static void update(int id, String name) throws Exception {
            try (Connection c = DriverManager.getConnection(URL);
                 PreparedStatement ps = c.prepareStatement(
                     "UPDATE students SET name=? WHERE id=?")) {
                ps.setString(1, name);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        }
        public static void main(String[] args) throws Exception {
            insert(1, "Alice");
            update(1, "Alicia");
            System.out.println("Insert and update completed.");
        }
    }

    // =========================================================================
    // Q33 — Transaction Handling in JDBC
    // =========================================================================
    static class Q33_Transfer {
        static final String URL = "jdbc:sqlite:bank.db";
        // Setup: CREATE TABLE accounts (id INT, balance REAL);

        static void transfer(int from, int to, double amount) throws Exception {
            try (Connection c = DriverManager.getConnection(URL)) {
                c.setAutoCommit(false);
                try (PreparedStatement debit = c.prepareStatement(
                         "UPDATE accounts SET balance = balance - ? WHERE id = ?");
                     PreparedStatement credit = c.prepareStatement(
                         "UPDATE accounts SET balance = balance + ? WHERE id = ?")) {
                    debit.setDouble(1, amount); debit.setInt(2, from); debit.executeUpdate();
                    credit.setDouble(1, amount); credit.setInt(2, to); credit.executeUpdate();
                    c.commit();
                    System.out.println("Transfer of " + amount + " successful.");
                } catch (SQLException e) {
                    c.rollback();
                    throw e;
                }
            }
        }
        public static void main(String[] args) throws Exception {
            transfer(1, 2, 500.0);
        }
    }

    // =========================================================================
    // Q34 — Java Modules
    // NOTE: Modules require separate source trees; shown as a comment guide.
    // =========================================================================
    /*
     * Directory layout:
     *
     *   src/
     *     com.utils/
     *       module-info.java        -> module com.utils { exports com.utils; }
     *       com/utils/Greeter.java  -> public class Greeter {
     *                                      public static String greet(String n) {
     *                                          return "Hello, " + n + "!";
     *                                      }
     *                                  }
     *     com.greetings/
     *       module-info.java        -> module com.greetings { requires com.utils; }
     *       com/greetings/Main.java -> import com.utils.Greeter;
     *                                  public class Main {
     *                                      public static void main(String[] a) {
     *                                          System.out.println(Greeter.greet("World"));
     *                                      }
     *                                  }
     *
     * Compile & run:
     *   javac --module-source-path src -d mods -m com.utils,com.greetings
     *   java  --module-path mods -m com.greetings/com.greetings.Main
     */
    static class Q34_Modules {
        public static void main(String[] args) {
            System.out.println("See comment block for module setup instructions.");
        }
    }

    // =========================================================================
    // Q35 — TCP Client-Server Chat
    // =========================================================================
    // Run Q35_Server in one terminal, Q35_Client in another.
    static class Q35_Server {
        public static void main(String[] args) throws Exception {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server waiting on port 5000...");
            try (Socket s = ss.accept()) {
                BufferedReader in  = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter    out = new PrintWriter(s.getOutputStream(), true);
                Scanner console = new Scanner(System.in);
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Client: " + line);
                    System.out.print("You: ");
                    out.println(console.nextLine());
                }
            }
        }
    }
    static class Q35_Client {
        public static void main(String[] args) throws Exception {
            try (Socket s = new Socket("localhost", 5000)) {
                PrintWriter    out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in  = new BufferedReader(new InputStreamReader(s.getInputStream()));
                Scanner console = new Scanner(System.in);
                while (true) {
                    System.out.print("You: ");
                    out.println(console.nextLine());
                    System.out.println("Server: " + in.readLine());
                }
            }
        }
    }

    // =========================================================================
    // Q36 — HTTP Client API (Java 11+)
    // =========================================================================
    static class Q36_HttpDemo {
        public static void main(String[] args) throws Exception {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/torvalds"))
                .header("Accept", "application/json")
                .build();
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + res.statusCode());
            System.out.println(res.body());
        }
    }

    // =========================================================================
    // Q37 — Inspect Bytecode with javap
    // NOTE: Compile any class, then run javap -c ClassName
    // =========================================================================
    static class Q37_BytecodeDemo {
        public int add(int a, int b) { return a + b; }
        /*
         * After compiling:
         *   javap -c JavaSolutions\$Q37_BytecodeDemo.class
         *
         * Sample output:
         *   public int add(int, int);
         *     Code:
         *        0: iload_1    // push a onto stack
         *        1: iload_2    // push b onto stack
         *        2: iadd       // integer addition
         *        3: ireturn    // return int result
         */
        public static void main(String[] args) {
            System.out.println("Compile and run: javap -c JavaSolutions\\$Q37_BytecodeDemo.class");
        }
    }

    // =========================================================================
    // Q38 — Decompile a Class File
    // NOTE: Instructions for using CFR / JD-GUI
    // =========================================================================
    /*
     * 1. Compile:  javac MyClass.java
     * 2. CFR:      java -jar cfr.jar MyClass.class
     * 3. JD-GUI:   Open .class file, File > Save All Sources
     */
    static class Q38_Decompile {
        public static void main(String[] args) {
            System.out.println("Use: java -jar cfr.jar JavaSolutions.class");
        }
    }

    // =========================================================================
    // Q39 — Reflection in Java
    // =========================================================================
    static class Q39_ReflectDemo {
        public String greet(String name) { return "Hi, " + name; }
        public int    add(int a, int b)  { return a + b; }

        public static void main(String[] args) throws Exception {
            Class<?> cls = Q39_ReflectDemo.class;
            Object obj = cls.getDeclaredConstructor().newInstance();

            System.out.println("Methods:");
            for (Method m : cls.getDeclaredMethods()) {
                if (m.getName().equals("main")) continue;
                System.out.print("  " + m.getName() + "(");
                Parameter[] params = m.getParameters();
                for (int i = 0; i < params.length; i++) {
                    if (i > 0) System.out.print(", ");
                    System.out.print(params[i].getType().getSimpleName());
                }
                System.out.println(")");
            }
            // Invoke greet dynamically
            Method greet = cls.getMethod("greet", String.class);
            System.out.println("Dynamic invoke: " + greet.invoke(obj, "World"));
        }
    }

    // =========================================================================
    // Q40 — Virtual Threads (Java 21)
    // =========================================================================
    static class Q40_VirtualThreads {
        public static void main(String[] args) throws InterruptedException {
            int count = 100_000;
            Thread[] threads = new Thread[count];
            long start = System.currentTimeMillis();

            for (int i = 0; i < count; i++) {
                int id = i;
                threads[i] = Thread.ofVirtual().start(() ->
                    System.out.println("Virtual thread #" + id)
                );
            }
            for (Thread t : threads) t.join();

            System.out.println("All " + count + " virtual threads done in "
                + (System.currentTimeMillis() - start) + " ms");
        }
    }

    // =========================================================================
    // Q41 — ExecutorService and Callable
    // =========================================================================
    static class Q41_CallableDemo {
        public static void main(String[] args) throws Exception {
            ExecutorService pool = Executors.newFixedThreadPool(4);
            List<Callable<Integer>> tasks = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {
                final int n = i;
                tasks.add(() -> {
                    System.out.println("Running task " + n
                        + " on " + Thread.currentThread().getName());
                    return n * n;
                });
            }
            List<Future<Integer>> futures = pool.invokeAll(tasks);
            for (Future<Integer> f : futures)
                System.out.println("Result: " + f.get());

            pool.shutdown();
        }
    }

    // =========================================================================
    // Main — list all exercises
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("=== Core Java Exercises — 41 Solutions ===");
        System.out.println("Each exercise is an inner class: Q1_HelloWorld, Q2_Calculator, ...");
        System.out.println("Run individually, e.g.:");
        System.out.println("  javac JavaSolutions.java");
        System.out.println("  java  JavaSolutions\\$Q1_HelloWorld");
    }
}
