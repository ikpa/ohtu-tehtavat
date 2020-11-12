
package ohtu;

public class Player implements Comparable {
    private String name;
    private int assists;
    private int goals;
    private String team;
    private String nationality;
    
    public int score() {
        return this.assists + this.goals;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getGoals() {
        return goals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        return ((Player) o).score() - this.score();
    }
    
    @Override
    public String toString() {
        return name + ", team: " + team + 
                ", goals: " + goals + ", assists: " + assists +
                ", score: " + this.score();
    }
      
}
