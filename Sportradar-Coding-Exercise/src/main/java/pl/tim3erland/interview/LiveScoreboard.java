package pl.tim3erland.interview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class LiveScoreboard implements Scoreboard {
    private List<Match> matches;
    private ValidatorScoreboard validatorScoreboard;

    public LiveScoreboard() {
        this.matches = new ArrayList<>();
        this.validatorScoreboard = new ValidatorScoreboard();
    }

    public void startMatch(String homeTeam, String awayTeam) {
        validatorScoreboard.validateMatch(awayTeam, homeTeam);
        validatorScoreboard.isMatchInProgress(matches, homeTeam, awayTeam);
        matches.add(new Match(homeTeam, awayTeam));
    }

    @Override
    public void startMatchAndUpdateScore(Match match) {
        startMatch(match.getHomeTeam(), match.getAwayTeam());
        updateScore(match.getHomeTeam(), match.getAwayTeam(), match.getHomeTeamScore(), match.getAwayTeamScore());
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = findMatch(homeTeam, awayTeam);
        if (match == null) {
            throw new NoSuchElementException("Match not found");
        }
        validatorScoreboard.validateScore(homeScore, awayScore);
        match.updateScore(homeScore, awayScore);
    }

    private Match findMatch(String homeTeam, String awayTeam) {
        return matches.stream().filter(match -> match.getHomeTeam().equalsIgnoreCase(homeTeam) && match.getAwayTeam().equalsIgnoreCase(awayTeam)).findFirst().orElse(null);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam);
        if (match == null) {
            throw new NoSuchElementException("Match not found");
        }
        matches.remove(match);
    }

    @Override
    public List<String> summaryMatches() {
        return matches.stream().sorted(Comparator.reverseOrder()).map(Match::toString).collect(Collectors.toList());
    }

    @Override
    public String summaryMatchesAsString() {
        List<String> summariedMatches = summaryMatches();
        return summariedMatches.stream().collect(Collectors.joining("\n"));
    }

    @Override
    public List<Match> getMatches() {
        return Collections.unmodifiableList(this.matches);
    }
}
