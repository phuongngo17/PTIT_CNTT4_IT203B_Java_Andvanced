package session09.miniproject;

import java.util.List;
import java.util.stream.Collectors;

public class SortByScoreDesc implements SortStrategy {

    @Override
    public List<User> sort(List<User> users) {
        return users.stream()
                .sorted((a, b) -> Double.compare(b.getScore(), a.getScore()))
                .collect(Collectors.toList());
    }
}
