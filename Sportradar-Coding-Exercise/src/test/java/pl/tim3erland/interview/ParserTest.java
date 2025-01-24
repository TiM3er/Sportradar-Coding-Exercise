package pl.tim3erland.interview;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void inputParcetWithLargeNumberTest() {
        Match match = InputParser.parseMatch("Poland 2137 - United States 55");
        assertEquals(match.getTotalScore(), 2192);
        assertEquals(match.getHomeTeam(), "Poland");
        assertEquals(match.getAwayTeam(), "United States");
    }

    @Test
    void inputParcetWithDashTest() {
        Match match = InputParser.parseMatch("Guinea-Bissau 720 - United States 55");
        assertEquals(match.getTotalScore(), 775);
        assertEquals(match.getHomeTeam(), "Guinea-Bissau");
        assertEquals(match.getAwayTeam(), "United States");
    }
}
