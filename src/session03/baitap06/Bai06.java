package session03.baitap06;

import java.util.*;
import java.util.stream.Collectors;

public class Bai06 {
    public static void main(String[] args) {

        List<Post> posts = new ArrayList<>();

        posts.add(new Post(Arrays.asList("java", "backend")));
        posts.add(new Post(Arrays.asList("python", "data")));

        List<String> allTags = posts.stream()
                .flatMap(post -> post.getTags().stream())
                .collect(Collectors.toList());

        System.out.println(allTags);
    }
}