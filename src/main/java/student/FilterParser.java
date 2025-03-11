package student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * The {@code FilterParser} class converts filter strings into predicates
 * that can be applied to streams of {@code BoardGame} objects.
 */
public class FilterParser {

    /**
     * Parses a filter string and returns a list of predicates.
     *
     * @param filter The filter string to parse.
     * @return A list of predicates for filtering {@code BoardGame} objects.
     */
    public static List<Predicate<BoardGame>> parse(String filter) {
        List<Predicate<BoardGame>> predicates = new ArrayList<>();

        // Handle empty filter: return a predicate that allows all games
        if (filter == null || filter.trim().isEmpty()) {
            return List.of(x -> true);
        }

        // Split filter string into conditions
        String[] conditions = filter.split(",");
        for (String condition : conditions) {
            condition = condition.trim();

            // Find the operator
            Operations op = Operations.getOperatorFromStr(condition);
            if (op == null) {
                throw new IllegalArgumentException("Invalid filter operation: " + condition);
            }

            // Split into field and value
            String[] parts = condition.split(op.getOperator());
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid filter format: " + condition);
            }

            String field = parts[0].trim();
            String value = parts[1].trim();
            //Normalize field names (remove underscores, make lowercase)
            String normalizedField = field.replace("_", "").toLowerCase();
            // Match field names exactly as they appear in BoardGame
            switch (normalizedField){
                case "name":
                    predicates.add(BoardGameFilter.byName(value, op));
                    break;
                case "minplayers":
                    predicates.add(BoardGameFilter.byMinPlayers(Integer.parseInt(value), op));
                    break;
                case "maxplayers":
                    predicates.add(BoardGameFilter.byMaxPlayers(Integer.parseInt(value), op));
                    break;
                case "maxplaytime":
                    predicates.add(BoardGameFilter.byMaxPlayTime(Integer.parseInt(value), op));
                    break;
                case "minplaytime":
                    predicates.add(BoardGameFilter.byMinPlayTime(Integer.parseInt(value), op));
                    break;
                case "difficulty":
                    predicates.add(BoardGameFilter.byDifficulty(Double.parseDouble(value), op));
                    break;
                case "rank":
                    predicates.add(BoardGameFilter.byRank(Integer.parseInt(value), op));
                    break;
                case "rating":
                    predicates.add(BoardGameFilter.byRating(Double.parseDouble(value), op));
                    break;
                case "yearpublished":
                    predicates.add(BoardGameFilter.byYear(Integer.parseInt(value), op));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown filter field: " + field);
            }
        }

        return predicates;
    }
}
