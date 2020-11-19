package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    private int endGameScore = 4;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName))
            player1Score += 1;
        else
            player2Score += 1;
    }
    
    public String getScore() {
        String finalScore;
        
        if (winOrAdvantageOrDeuce()) {
            return winOrAdvantageOrDeuceScore();
        }
        
        String score1 = getIndividualScore(player1Name);
        String score2 = getIndividualScore(player2Name);
        
        if (player1Score == player2Score) {
            score2 = "All";
        }
        
        finalScore = score1 + "-" + score2;
        
        return finalScore;
    }
    
    private String winOrAdvantageOrDeuceScore() {
        int score = player1Score - player2Score;
        String result;
        
        if (score == 0) {
            return "Deuce";
        }
        
        if (Math.abs(score) == 1) {
            result = "Advantage ";
        } else {
            result = "Win for ";
        }
        
        if (score < 0) {
            result += player2Name;
        } else {
            result += player1Name;
        }
        
        return result;
    }
    
    private boolean winOrAdvantageOrDeuce() {
        return player1Score >= endGameScore || player2Score >= endGameScore;
    }
    
    private String getIndividualScore(String playerName) {
        int numericalScore;
        if (playerName.equals(this.player1Name)) {
            numericalScore = player1Score;
        } else {
            numericalScore = player2Score;
        }
        
        String result = "";
            
        switch(numericalScore) {
            case 0:
                result = "Love";
                break;
                
            case 1:
                result = "Fifteen";
                break;
                
            case 2:
                result = "Thirty";
                break;
                
            case 3:
                result = "Forty";
        }
        
        return result;
    }
}