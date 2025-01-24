package pl.tim3erland.interview;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreboardTest {

    @Test
    void testAddNewTeam() {
        Scoreboard scoreboard = new LiveScoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        assertEquals(scoreboard.getMatches().size(), 1);
        assertEquals(scoreboard.getMatches().stream().anyMatch(match -> match.getHomeTeam().equalsIgnoreCase("Mexico")), true);
        assertEquals(scoreboard.getMatches().stream().anyMatch(match -> match.getAwayTeam().equalsIgnoreCase("Canada")), true);

        scoreboard.startMatch("Spain", "Brazil");
        assertEquals(scoreboard.getMatches().size(), 2);
        try {
            scoreboard.startMatch("Spain", "");
        } catch (Exception e) {
            assertEquals(scoreboard.getMatches().size(), 2);
            assertEquals(e.getClass(), IllegalArgumentException.class);
            assertEquals(e.getMessage(), "Team name cannot be null or empty");
        }
    }

    @Test
    void testAddExistMatch() {
        Scoreboard scoreboard = new LiveScoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        assertEquals(scoreboard.getMatches().size(), 1);
        try {
            scoreboard.startMatch("Mexico", "Canada");
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            assertEquals(e.getMessage(), "Match already started");
        }
    }

    @Test
    void testUpdateMatch() {
        Scoreboard scoreboard = new LiveScoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        assertEquals(scoreboard.getMatches().size(), 1);
        scoreboard.updateScore("Mexico", "Canada", 0, 1);
        assertEquals(scoreboard.getMatches().stream()
                .anyMatch(match -> findMatchByTeamsAndScore(match, "Mexico", "Canada", 0, 1)), true);
    }

    @Test
    void testFinishMatch() {
        Scoreboard scoreboard = new LiveScoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        assertEquals(scoreboard.getMatches().size(), 1);
        scoreboard.finishMatch("Mexico", "Canada");
        assertEquals(scoreboard.getMatches().size(), 0);
    }

    private boolean findMatchByTeamsAndScore(Match match, String homeTeam, String awayTeam, int homeScore, int awayScore) {
        return match.getHomeTeam().equalsIgnoreCase(homeTeam)
                && match.getAwayTeam().equalsIgnoreCase(awayTeam)
                && match.getHomeTeamScore() == homeScore
                && match.getAwayTeamScore() == awayScore;
    }


    @Test
    void testSummaryMatches() {
        Scoreboard scoreboard = new LiveScoreboard();
        scoreboard.startMatchAndUpdateScore(InputParser.parseMatch("Mexico 0 - Canada 5"));
        scoreboard.startMatchAndUpdateScore(InputParser.parseMatch("Spain 10 - Brazil 2"));
        scoreboard.startMatchAndUpdateScore(InputParser.parseMatch("Germany 2 - France 2"));
        scoreboard.startMatchAndUpdateScore(InputParser.parseMatch("Uruguay 6 - Italy 6"));
        scoreboard.startMatchAndUpdateScore(InputParser.parseMatch("Argentina 3 - Australia 1"));
        List<String> summariedMatches = scoreboard.summaryMatches();
        summariedMatches.forEach(System.out::println);
        assertEquals(summariedMatches.get(0).toString(), "Spain 10 - Brazil 2");
        scoreboard.finishMatch("Spain", "Brazil");
        summariedMatches = scoreboard.summaryMatches();
        summariedMatches.forEach(System.out::println);
        assertEquals(summariedMatches.get(0).toString(), "Uruguay 6 - Italy 6");
    }
}
