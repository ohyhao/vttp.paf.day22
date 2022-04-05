package vttp2022.paf.day22.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
 
@Service
public class GiphyService {

    private final static String URL = "https://api.giphy.com/v1/gifs/search";

    //set GIPHY_API_KEY=aGBUFxhlG9Xfu66b1u4noUbd3kRm5l5w
    @Value("${giphy.api.key}")
    private String giphyKey;

    public List<String> getGiphs(String q) {
        return getGiphs(q, "pg", 10);
    }

    public List<String> getGiphs(String q, String rating) {
        return getGiphs(q, rating, 10);
    }

    public List<String> getGiphs(String q, Integer limit) {
        return getGiphs(q, "pg", limit);
    }

    public List<String> getGiphs(String q, String rating, Integer limit) {

        List<String> result = new LinkedList<>();

        String searchUrl = UriComponentsBuilder
            .fromUriString(URL)
            .queryParam("api_key", giphyKey)
            .queryParam("q", q)
            .queryParam("limit", limit)
            .queryParam("rating", rating)
            .toUriString();

        System.out.printf(">>>>> url = %s\n", searchUrl);

        RequestEntity<Void> req = RequestEntity
            .get(searchUrl)
            .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        InputStream is = new ByteArrayInputStream(resp.getBody().getBytes());
        JsonReader r = Json.createReader(is);
        JsonObject o = r.readObject();

        JsonArray data = o.getJsonArray("data");

        for (int i = 0; i < data.size(); i++) {
            JsonObject gif = data.getJsonObject(i);
            String url = gif.getJsonObject("images").getJsonObject("fixed_width").getString("url");
            result.add(url);
        }

        System.out.printf(">>>>> imagesUrl = %s\n", result);

        return result;
    }
    
}
