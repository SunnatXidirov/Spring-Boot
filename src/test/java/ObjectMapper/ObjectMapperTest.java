package ObjectMapper;

import com.app.Jackson.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Builder;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ObjectMapperTest {

    @Test
    void simpleCode() throws JsonProcessingException {

        String postJSON = """
                {
                  "userId": 1,
                  "id": 1,
                  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                  "body": "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                }
                """;
        ObjectMapper mapper = new ObjectMapper();
        Post readValue = mapper.readValue(postJSON, Post.class);
        System.out.println(readValue.getTitle());
        String s = mapper.writeValueAsString(readValue);
        System.out.println(s);
    }

    @Test
    void objectFromStringReader() throws IOException {
        String postJSON = """
                {
                  "userId": 1,
                  "id": 1,
                  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                  "body": "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                }
                """;
        StringReader stringReader = new StringReader(postJSON);
        ObjectMapper mapper = new ObjectMapper();
        Post readValue = mapper.readValue(stringReader, Post.class);
        System.out.println("title : " + readValue.getTitle());
    }

    @Test
    void objectFromFile() throws IOException {

        File file = new File("data/file.txt");
        ObjectMapper mapper = new ObjectMapper();
        Post readValue = mapper.readValue(file, Post.class);
        System.out.println(readValue);
    }

    @Test
    void objectFromInptStream() throws IOException {
        InputStream inputStream = new FileInputStream("data/file.txt");
        ObjectMapper mapper = new ObjectMapper();
        Post readValue = mapper.readValue(inputStream, Post.class);
        System.out.println(readValue);
    }

    @Test
    void objectFromURL() throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
        ObjectMapper mapper = new ObjectMapper();
        Post readValue = mapper.readValue(url, Post.class);
        System.out.println(readValue);
    }

    @Test
    void objectFromByteArray() throws IOException {
        byte[] bytes = """
                {
                  "userId": 1,
                  "id": 1,
                  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                  "body": "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                }
                """.getBytes();
        ObjectMapper mapper = new ObjectMapper();
        Post readValue = mapper.readValue(bytes, Post.class);
        System.out.println(readValue);
    }

    @Test
    void objectFromStringArray() throws IOException {
        String postJSON = """
                [
                 {
                    "userId": 1,
                    "id": 1,
                    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                    "body": "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                  },
                  {
                    "userId": 1,
                    "id": 2,
                    "title": "qui est esse",
                    "body": "est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla"
                  },
                  {
                    "userId": 1,
                    "id": 3,
                    "title": "ea molestias quasi exercitationem repellat qui ipsa sit aut",
                    "body": "et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut"
                  }
                  ]
                """;
        ObjectMapper mapper = new ObjectMapper();
        Post[] posts = mapper.readValue(postJSON, Post[].class);
        for (Post post : posts) {
            System.out.println(post);
            System.out.println("-------------------");
        }
    }

    @Test
    void ListFromStringArray() throws IOException {
        String postJSON = """
                [
                 {
                    "userId": 1,
                    "id": 1,
                    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                    "body": "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                  },
                  {
                    "userId": 1,
                    "id": 2,
                    "title": "qui est esse",
                    "body": "est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla"
                  },
                  {
                    "userId": 1,
                    "id": 3,
                    "title": "ea molestias quasi exercitationem repellat qui ipsa sit aut",
                    "body": "et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut"
                  }
                  ]
                """;
        ObjectMapper mapper = new ObjectMapper();
        List<Post> posts = mapper.readValue(postJSON, new TypeReference<List<Post>>() {
        });

        for (Post post : posts) {
            System.out.println(post);
            System.out.println("-------------------");
        }
    }

    @Test
    void mapFromStringJSON() throws IOException {
        String postJSON = """
                 {
                    "userId": 1,
                    "id": 1,
                    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                    "body": "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                  }
                """;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> posts = mapper.readValue(postJSON, new TypeReference<Map<String, String>>() {
        });
        System.out.println(posts);
    }

    @Test
    void unknownFieldExeption() throws JsonProcessingException {
        String postJSON = """
                {
                  "userId": 1,
                  "id": 1,
                  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                  "map": "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                }
                """;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Post post = mapper.readValue(postJSON, Post.class);
        System.out.println(post);

    }

    @Test
    void jsonFromObject() throws IOException {
        Post post = new Post();
        post.setId(1);
        post.setUserId(2);
        post.setTitle("qui est esse");
        post.setBody("est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla");
        OutputStream op = new FileOutputStream("data/serialize.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(op, post);
        String s = mapper.writeValueAsString(post);
        System.out.println(s);
    }

    @Test
    void dateFromat() throws IOException {
        Post post = new Post();
        post.setId(1);
        post.setUserId(2);
        post.setTitle("qui est esse");
        post.setBody("est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla");
        post.setDate(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM:dd:yyyy");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);
        String s = mapper.writeValueAsString(post);
        System.out.println(s);
    }
    @Test
    void yamlFromat() throws IOException {
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        Post post = new Post();
        post.setId(1);
        post.setUserId(2);
        post.setTitle("qui est esse");
        post.setBody("est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla");
        post.setDate(new Date());
        String s = mapper.writeValueAsString(post);
        System.out.println(s);

    }
}
