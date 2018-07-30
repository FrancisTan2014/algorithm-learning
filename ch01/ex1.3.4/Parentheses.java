import java.util.*;
import java.util.function.*;

/**
 * Exercise 1.3.4
 * Write a client Parentheses that reads in a text stream from standard input
 * and use a stack to determine whether its parentheses are properly balanced.
 * For example, your program should print true for [()]{}{[()()]()} and false
 * for [(]).
 */

public class Parentheses {

    private static List<String> couples = Arrays.asList("()", "{}", "[]");

    public static void main(String[] args) {
        String sequence = "[()]{}{[()()]()}";
        test(sequence);

        sequence = "[(])";
        test(sequence);
    }

    private static void test(String sequence) {
        boolean balanced = isBalanced(sequence);
        String txt = balanced ? "balanced" : "not balanced";
        System.out.println("The sequence \"" + sequence + "\" is " + txt);
    }

    private static boolean isBalanced(String sequence) {
        Collection<Character> rightChars = getChars("right");

        Stack<Character> stack = new Stack<>();
        int len = sequence.length();
        for (int i = 0; i < len; i++) {
            char c = sequence.charAt(i);
            boolean isRightChar = contains(rightChars, c);
            if (!isRightChar) {
                stack.push(c);
            } else {
                char another = getAnotherSide(c);
                char pop = stack.pop();
                if (another != pop) {
                    return false;
                }                
            }
        }

        return true;
    }

    private static Collection<Character> getChars(String side) {
        int index = side == "left" ? 0 : 1;
        LinkedList<Character> list = new LinkedList<>();
        for (String s : couples) {
            list.add(s.charAt(index));
        }
        return list;
    }

    private static char getAnotherSide(char c) {
        String couple = find(couples, (String s) -> s.contains(c + ""));
        return couple.replace(c + "", "").charAt(0);
    }

    private static <T> boolean contains(Iterable<T> collection, T item) {
        for (T e : collection) {
            if (e == item) {
                return true;
            }
        }
        return false;
    }

    private static <T> T find(Iterable<T> collection, Predicate<T> predicate) {
        for (T item : collection) {
            boolean match = predicate.test(item);
            if (match) {
                return item;
            }
        }
        return null;
    }

}