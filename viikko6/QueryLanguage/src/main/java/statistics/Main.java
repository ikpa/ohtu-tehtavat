package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        Matcher m = new Or( new HasAtLeast(40, "goals"),
                new HasAtLeast(60, "assists")
        );
        
        System.out.println(stats.matches(new All()).size());
        
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}
