package pl.tim3erland.interview;

import java.util.List;

public interface Scoreboard {
    void startMatch(String homeTeam, String awayTeam);
    void startMatchAndUpdateScore(Match match);

    void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);

    void finishMatch(String homeTeam, String awayTeam);

    List<String> summaryMatches();

    String summaryMatchesAsString();
    List<Match> getMatches();
}
