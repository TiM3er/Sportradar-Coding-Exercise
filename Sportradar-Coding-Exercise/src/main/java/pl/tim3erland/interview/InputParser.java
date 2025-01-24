package pl.tim3erland.interview;

import java.util.Arrays;

public class InputParser {

    public static final String INVALID_MATCH_FORMAT = "Invalid match format";

    public static Match parseMatch(String input) {
        String[] parts = input.split("\s-\s");
        if (parts.length != 2) {
            throw new IllegalArgumentException(INVALID_MATCH_FORMAT);
        }
        String[] homeData = parts[0].trim().split(" ");
        String[] awayData = parts[1].trim().split(" ");
        if (homeData.length < 2 || awayData.length < 2) {
            throw new IllegalArgumentException(INVALID_MATCH_FORMAT);
        }
        String homeTeam = String.join(" ", Arrays.copyOf(homeData, homeData.length - 1)).trim();
        int homeScore = Integer.parseInt(homeData[homeData.length - 1]);
        String awayTeam = String.join(" ", Arrays.copyOf(awayData, awayData.length - 1)).trim();
        int awayScore = Integer.parseInt(awayData[awayData.length - 1]);
        Match match = new Match(homeTeam, awayTeam);
        match.updateScore(homeScore, awayScore);
        return match;
    }
}
