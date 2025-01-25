# Live Scoreboard - Documentation

## Table of Contents
1. [Introduction](#introduction)
2. [Project Structure](#project-structure)
3. [Class Descriptions](#class-descriptions)
    - [InputParser](#inputparser)
    - [LiveScoreboard](#livescoreboard)
    - [Match](#match)
    - [ValidatorScoreboard](#validatorscoreboard)
    - [Scoreboard](#scoreboard)
4. [Usage Example](#usage-example)
5. [Requirements](#requirements)
6. [Author](#author)

---

## Introduction
The "Live Scoreboard" project allows managing sports match results, including starting, updating scores, and finishing matches. It also provides functionalities to generate match summaries in list or string format.

---

## Project Structure
```
pl.tim3erland.interview
├── InputParser.java
├── LiveScoreboard.java
├── Match.java
├── ValidatorScoreboard.java
└── Scoreboard.java
```

---

## Class Descriptions

### InputParser.java

This class is responsible for parsing input text with match results.

#### Methods:
- `public static Match parseMatch(String input)`: Parses an input string in the format "Team1 X - Team2 Y".

---

### LiveScoreboard.java

This class manages the list of matches and their results.

#### Methods:
- `public void startMatch(String homeTeam, String awayTeam)`: Starts a new match.
- `public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore)`: Updates the match score.
- `public void finishMatch(String homeTeam, String awayTeam)`: Ends a match.
- `public List<String> summaryMatches()`: Returns a list of match summaries.
- `public String summaryMatchesAsString()`: Returns match summaries as a string.
- `public List<Match> getMatches()`: Returns the list of ongoing matches.


---

### Match.java

This class represents a single match between two teams.

#### Attributes:
- `private String homeTeam`: Name of the home team.
- `private String awayTeam`: Name of the away team.
- `private int homeTeamScore`: Home team score.
- `private int awayTeamScore`: Away team score.
- `private LocalDateTime matchDateTime`: Date and time of the match.

#### Methods:
- `public void updateScore(int homeScore, int awayScore)`: Sets the match score.
- `public int getTotalScore()`: Returns the total score of both teams.
- `public int compareTo(Match o)`: Compares matches based on score, and if equal, by date.
- `public String toString()`: Returns the match representation in the format "Team A X - Team B Y".


---

### ValidatorScoreboard.java

This class provides validation methods for match operations.

#### Methods:
- `public void validateTeamName(String teamName)`: Checks if a team name is valid.
- `public void validateMatch(String homeTeam, String awayTeam)`: Validates home and away team names.
- `public void isMatchInProgress(List<Match> matches, String homeTeam, String awayTeam)`: Checks if a match is already in progress.
- `public void validateScore(int homeScore, int awayScore)`: Validates match scores.


---

### Scoreboard.java

An interface defining the scoreboard operations.

#### Methods:
- `void startMatch(String homeTeam, String awayTeam);`
- `void startMatchAndUpdateScore(Match match);`
- `void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);`
- `void finishMatch(String homeTeam, String awayTeam);`
- `List<String> summaryMatches();`
- `String summaryMatchesAsString();`
- `List<Match> getMatches();`


