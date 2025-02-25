package student;

import java.util.List;
import java.util.function.Predicate;

/**
 * FilterParser is responsible for parsing filter strings
 * and converting them into predicates for filtering BoardGame streams.
 */
public class FilterParser {

    /**
     * Parses a filter string and returns a list of predicates.
     *
     * @param filter The filter string to parse.
     * @return A list of predicates for filtering BoardGame objects.
     */
    public static List<Predicate<BoardGame>> parse(String filter) {
        // TODO: Implement the logic to parse filter strings
        throw new UnsupportedOperationException("Unimplemented method 'parse'");
    }

    /**
     * Parses a single filter condition into a Predicate.
     *
     * @param condition The filter condition to parse.
     * @return A predicate for filtering BoardGame objects.
     */
    private static Predicate<BoardGame> parseCondition(String condition) {
        // TODO: Implement condition parsing
        throw new UnsupportedOperationException("Unimplemented method 'parseCondition'");
    }
}
