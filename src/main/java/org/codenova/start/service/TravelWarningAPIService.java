package org.codenova.start.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codenova.start.model.travel.Item;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service // controll 보조 클래스에 (+ DB 작업이 아님)
public class TravelWarningAPIService {

    public Item[] findAll(int page) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://apis.data.go.kr/1262000/TravelWarningServiceV2/getTravelWarningListV2?serviceKey=Vw25fQSAsfNycj/AXwgHlM66HYmyfKPkX8pSs7dRqhRB1CqtZvhH0mUoAjue6h3CmrUQTjIBD3mHhflG7pedpA==&pageNo=" +page+ "&numOfRows=10",
                HttpMethod.GET,
                null,   // 그외 설정할 헤더값
                String.class
        );
        String rawBody = response.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(rawBody);

        Item[] TravelWarning = mapper.readValue(root.path("response").path("body").path("items").path("item").toString(), Item[].class);

        return TravelWarning;

    }

    public Item findByIsoCode(String isoCode) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://apis.data.go.kr/1262000/TravelWarningServiceV2/getTravelWarningListV2?serviceKey=Vw25fQSAsfNycj/AXwgHlM66HYmyfKPkX8pSs7dRqhRB1CqtZvhH0mUoAjue6h3CmrUQTjIBD3mHhflG7pedpA==&numOfRows=10&pageNo=1&cond[isoCode::EQ]="+isoCode,
                HttpMethod.GET,
                null,   // 그외 설정할 헤더값
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());
        Item item = objectMapper.readValue(root.path("response").path("body").path("items").path("item").get(0).toString(),
                Item.class);

        return item;

    }
}
