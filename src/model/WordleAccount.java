package model;

public class WordleAccount {
    private final String username;
    private final String password;
    private int[]score;

    public WordleAccount(String username, String password) {
        this.username = username;
        this.password = password;
        score = new int[6];
        for(int i=0;i<score.length;i++){
            score[i]=0;
        }
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public void updateScore(int score){
        this.score[score-1]++;
    }

    public int[] getScore() {
        return score;
    }
}
