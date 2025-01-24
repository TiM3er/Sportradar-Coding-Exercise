package pl.tim3erland.interview;

import java.util.List;

public class ValidatorScoreboard {
    public void validateTeamName(String teamName) {
        if (teamName == null || teamName.trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty");
        }
    }

    public void validateMatch(String homeTeam, String awayTeam) {
        validateTeamName(homeTeam);
        validateTeamName(awayTeam);
    }

    public void isMatchInProgress(List<Match> matches, String homeTeam, String awayTeam) {
        if (matches.stream().anyMatch((match) -> match.getHomeTeam().equalsIgnoreCase(homeTeam)
                && match.getAwayTeam().equalsIgnoreCase(awayTeam))) {
            throw new IllegalArgumentException("Match already started");
        }
    }

    public void validateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
    }
}
