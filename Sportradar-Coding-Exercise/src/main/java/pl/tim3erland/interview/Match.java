package pl.tim3erland.interview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match implements Comparable<Match> {
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private LocalDateTime matchDateTime;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.matchDateTime = LocalDateTime.now();
    }

    public int getTotalScore() {
        return this.homeTeamScore + this.awayTeamScore;
    }


    @Override
    public int compareTo(Match o) {
        int compareTotalScore = Integer.compare(this.getTotalScore(), o.getTotalScore());
        if (compareTotalScore == 0) {
            return this.getMatchDateTime().compareTo(o.getMatchDateTime());
        }
        return compareTotalScore;
    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeTeamScore = homeScore;
        this.awayTeamScore = awayScore;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeTeamScore + " - " + awayTeam + " " + awayTeamScore;
    }
}


