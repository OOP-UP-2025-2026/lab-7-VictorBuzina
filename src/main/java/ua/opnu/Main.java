package ua.opnu;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // --- Завдання 1: Предикат для простих чисел. ---
        System.out.println("--- Завдання 1: Предикат для простих чисел ---");
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        System.out.println("7 просте? " + isPrime.test(7));
        System.out.println("10 просте? " + isPrime.test(10));


        Student[] students = {
                new Student("Петренко Петро", "ІП-21", new int[]{60, 90, 75}),
                new Student("Іваненко Іван", "ІП-22", new int[]{55, 65, 70}), // Має борг
                new Student("Сидоренко Марія", "ІП-21", new int[]{100, 95, 98}),
                new Student("Коваленко Олена", "ІП-22", new int[]{40, 50, 60}) // Має борг
        };

        // --- Завдання 2: Фільтрація студентів  ---
        System.out.println("\n--- Завдання 2: Фільтрація студентів (без боргів) ---");
        Predicate<Student> hasNoDebt = s -> {
            for (int mark : s.getMarks()) {
                if (mark < 60) {
                    return false;
                }
            }
            return true;
        };
        Student[] studentsWithoutDebt = filterStudents(students, hasNoDebt);
        System.out.println(Arrays.toString(studentsWithoutDebt));


        System.out.println("\n--- Завдання 3: Фільтрація за 2 предикатами ---");
        Predicate<Student> isInGroupIP21 = s -> s.getGroup().equals("ІП-21");
        Student[] filteredStudents = filterStudentsByTwoPredicates(students, hasNoDebt, isInGroupIP21);
        System.out.println(Arrays.toString(filteredStudents));


        System.out.println("\n--- Завдання 4: Consumer для виводу Прізвища та Імені ---");
        Consumer<Student> printName = s -> System.out.println(s.getName());
        forEachStudent(students, printName);


        System.out.println("\n--- Завдання 5: Виконати дію, якщо умова істинна ---");
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Consumer<Integer> printNumber = n -> System.out.println("Знайдено парне число: " + n);
        processWithCondition(numbers, isEven, printNumber);


        System.out.println("\n--- Завдання 6: Function для 2^n ---");
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);
        int[] powers = {0, 1, 2, 3, 4, 5};
        int[] resultPowers = processArray(powers, powerOfTwo);
        System.out.println(Arrays.toString(resultPowers));

        // --- Завдання 7: Function 'stringify' ---
        System.out.println("\n--- Завдання 7: Function 'stringify' (число в рядок) ---");
        Function<Integer, String> numberToString = n -> {
            switch (n) {
                case 0: return "нуль";
                case 1: return "один";
                case 2: return "два";
                case 3: return "три";
                case 4: return "чотири";
                case 5: return "п'ять";
                case 6: return "шість";
                case 7: return "сім";
                case 8: return "вісім";
                case 9: return "дев'ять";
                default: return "невідомо";
            }
        };
        int[] digits = {1, 4, 0, 9, 3};
        String[] stringifiedDigits = stringify(digits, numberToString);
        System.out.println(Arrays.toString(digits) + " -> " + Arrays.toString(stringifiedDigits));
    }



    /**
     * Завдання 2: Метод фільтрації масиву студентів
     */
    public static Student[] filterStudents(Student[] input, Predicate<Student> predicate) {
        ArrayList<Student> resultList = new ArrayList<>();
        for (Student student : input) {
            if (predicate.test(student)) {
                resultList.add(student);
            }
        }
        return resultList.toArray(new Student[0]);
    }

    /**
     * Завдання 3: Метод фільтрації за двома умовами (предикатами)
     */
    public static Student[] filterStudentsByTwoPredicates(Student[] input, Predicate<Student> p1, Predicate<Student> p2) {
        ArrayList<Student> resultList = new ArrayList<>();
        for (Student student : input) {
            if (p1.test(student) && p2.test(student)) {
                resultList.add(student);
            }
        }
        return resultList.toArray(new Student[0]);
    }

    /**
     * Завдання 4: Метод forEach для студентів
     */
    public static void forEachStudent(Student[] input, Consumer<Student> action) {
        for (Student student : input) {
            action.accept(student);
        }
    }

    /**
     * Завдання 5: Метод, який приймає Predicate та Consumer
     */
    public static <T> void processWithCondition(T[] input, Predicate<T> condition, Consumer<T> action) {
        for (T item : input) {
            if (condition.test(item)) {
                action.accept(item);
            }
        }
    }

    /**
     * Завдання 6: Метод обробки масиву
     */
    public static int[] processArray(int[] input, Function<Integer, Integer> function) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }

    /**
     * Завдання 7: Метод stringify
     */
    public static String[] stringify(int[] input, Function<Integer, String> function) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }
}