import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import student.BoardGame;
import student.Planner;
import student.IPlanner;
import student.GameData;
import student.GameList;
import student.BoardGameFilter;
import student.Operations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * JUnit test for the Planner class.
 * Just a sample test to get you started, also using
 * setup to help out. 
 */
public class TestPlanner {
    static Set<BoardGame> games;

    @BeforeAll
    public static void setup() {
        games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));
    }


    // ========================
    // Planner Tests
    // ========================

    /**
     * Test filtering by name.
     * Should return a single game with the exact name "Go".
     */
    @Test
    public void testFilterByName() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name == Go").toList();
        assertEquals(1, filtered.size());
        assertEquals("Go", filtered.get(0).getName());
    }

    /**
     * Test filtering by year published.
     * Should return all games published after 2003.
     */
    @Test
    public void testFilterByYearPublished() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("yearPublished > 2003").toList();
        assertEquals(4, filtered.size());
    }

    /**
     * Test filtering by difficulty.
     * Should return games with difficulty greater than or equal to 8.0.
     */
    @Test
    public void testFilterByDifficulty() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("difficulty >= 8.0").toList();
        assertEquals(3, filtered.size());
    }

    /**
     * Test sorting by rating in ascending order.
     * Ensures the first game has the lowest rating and the last has the highest.
     */
    @Test
    public void testSortByRatingAscending() {
        IPlanner planner = new Planner(games);
        List<BoardGame> sorted = planner.filter("", GameData.RATING, true).toList();
        assertEquals("Monopoly", sorted.get(0).getName());
        assertEquals("Chess", sorted.get(sorted.size() - 1).getName());
    }

    /**
     * Test sorting by rating in descending order.
     * Ensures the first game has the highest rating and the last has the lowest.
     */
    @Test
    public void testSortByRatingDescending() {
        IPlanner planner = new Planner(games);
        List<BoardGame> sorted = planner.filter("", GameData.RATING, false).toList();
        assertEquals("Chess", sorted.get(0).getName());
        assertEquals("Monopoly", sorted.get(sorted.size() - 1).getName());
    }

    /**
     * Test filtering and sorting together.
     * Filters games published from 2000 onwards and sorts them by difficulty in descending order.
     */
    @Test
    public void testFilterAndSortByDifficulty() {
        IPlanner planner = new Planner(games);
        List<BoardGame> sorted = planner.filter("yearPublished >= 2000", GameData.DIFFICULTY, false).toList();
        assertEquals("Chess", sorted.get(0).getName());
        assertEquals("Monopoly", sorted.get(sorted.size() - 1).getName());
    }

    /**
     * Test handling of invalid filter conditions.
     * Should throw an IllegalArgumentException.
     */
    @Test
    public void testInvalidFilterThrowsException() {
        IPlanner planner = new Planner(games);
        assertThrows(IllegalArgumentException.class, () -> planner.filter("invalidFilter > 100"));
    }

    /**
     * Test handling of invalid sorting attributes.
     * Should throw an IllegalArgumentException.
     */
    @Test
    public void testInvalidSortingThrowsException() {
        IPlanner planner = new Planner(games);
        assertThrows(IllegalArgumentException.class, () -> planner.filter("", null, true));
    }

    // ========================
    //  GameList Tests
    // ========================

    /**
     * Test adding a game by name.
     */
    @Test
    public void testAddByName() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("name == Go");
        gameList.addToList("Go", filteredGames);
        assertEquals(1, gameList.count());
        assertTrue(gameList.getGameNames().contains("Go"));
    }

    /**
     * Test adding a game using a single number index.
     */
    @Test
    public void testAddByIndex() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        gameList.addToList("1", filteredGames);  // Should add the first game
        assertEquals(1, gameList.count());
    }

    /**
     * Test adding games using a range (e.g., "1-3").
     */
    @Test
    public void testAddByRange() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        gameList.addToList("1-3", filteredGames);  // Should add the first 3 games
        assertEquals(3, gameList.count());
    }

    /**
     * Test adding all games.
     */
    @Test
    public void testAddAll() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        gameList.addToList("all", filteredGames);
        assertEquals((int) planner.filter("").count(), gameList.count()); // Should match total filtered count
    }

    /**
     * Test removing a game by name.
     */
    @Test
    public void testRemoveByName() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        gameList.addToList("all", filteredGames);
        gameList.removeFromList("Go");
        assertFalse(gameList.getGameNames().contains("Go"));
    }

    /**
     * Test removing a game by index.
     */
    @Test
    public void testRemoveByIndex() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        gameList.addToList("all", filteredGames);
        gameList.removeFromList("1");  // Removes the first game
        assertEquals((int) planner.filter("").count() - 1, gameList.count());
    }

    /**
     * Test removing a range of games.
     */
    @Test
    public void testRemoveByRange() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        gameList.addToList("all", filteredGames);
        gameList.removeFromList("1-3");  // Removes the first 3 games
        assertEquals((int) planner.filter("").count() - 3, gameList.count());
    }

    /**
     * Test removing all games.
     */
    @Test
    public void testRemoveAll() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        gameList.addToList("all", filteredGames);
        gameList.removeFromList("all");
        assertEquals(0, gameList.count());
    }

    /**
     * Test clearing the list.
     */
    @Test
    public void testClearGameList() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        gameList.addToList("all", filteredGames);
        gameList.clear();
        assertEquals(0, gameList.count());
    }

    /**
     * Test that duplicate games are not added.
     */
    @Test
    public void testNoDuplicates() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();

        List<BoardGame> filteredList = planner.filter("").toList(); //  Convert stream to list

        gameList.addToList("1", filteredList.stream());
        gameList.addToList("1", filteredList.stream());  //  New stream instance
        assertEquals(1, gameList.count());
    }

    /**
     * Test invalid inputs to addToList.
     */
    @Test
    public void testInvalidAddInput() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        assertThrows(IllegalArgumentException.class, () -> gameList.addToList("invalid", filteredGames));
    }

    /**
     * Test invalid range input to addToList.
     */
    @Test
    public void testInvalidRangeAdd() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        assertThrows(IllegalArgumentException.class, () -> gameList.addToList("10-1", filteredGames));
    }

    /**
     * Test invalid range input to removeFromList.
     */
    @Test
    public void testInvalidRangeRemove() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        gameList.addToList("all", filteredGames);
        assertThrows(IllegalArgumentException.class, () -> gameList.removeFromList("10-1"));
    }

    /**
     * Test sorting order.
     */
    @Test
    public void testSortingOrder() {
        IPlanner planner = new Planner(games);
        GameList gameList = new GameList();
        Stream<BoardGame> filteredGames = planner.filter("");
        gameList.addToList("all", filteredGames);

        // Ensures expected list is correctly sorted
        List<String> expectedNames = planner.filter("")
                .map(BoardGame::getName)
                .sorted(String::compareToIgnoreCase)
                .toList();

        List<String> actualSortedNames = gameList.getGameNames();

        assertEquals(expectedNames, actualSortedNames, "Sorting order is incorrect.");
    }
    // ========================
    // BoardGameFilter Tests
    // ========================

    /**
     * Test filtering games by difficulty.
     * Should return games with difficulty above 7.5.
     */
    @Test
    public void testBoardGameFilterByDifficulty() {
        Predicate<BoardGame> filter = BoardGameFilter.byDifficulty(7.5, Operations.GREATER_THAN);
        List<BoardGame> filtered = games.stream().filter(filter).toList();
        assertTrue(filtered.size() > 0);
    }

    /**
     * Test filtering games by rating.
     * Should return games with rating >= 8.0.
     */
    @Test
    public void testBoardGameFilterByRating() {
        Predicate<BoardGame> filter = BoardGameFilter.byRating(8.0, Operations.GREATER_THAN_EQUALS);
        List<BoardGame> filtered = games.stream().filter(filter).toList();
        assertTrue(filtered.size() > 0);
    }

    /**
     * Test filtering games by year published.
     * Should return games published in or after 2000.
     */
    @Test
    public void testBoardGameFilterByYearPublished() {
        Predicate<BoardGame> filter = BoardGameFilter.byYear(2000, Operations.GREATER_THAN_EQUALS);
        List<BoardGame> filtered = games.stream().filter(filter).toList();
        assertTrue(filtered.size() > 0);
    }
}