package org.codenova.start.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codenova.start.model.animal.AbandonmentJSON;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/rescue-animal")
public class RescueAnimalController {

    @GetMapping("/all")
    public String allHandle(Model model) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
                "http://apis.data.go.kr/1543061/abandonmentPublicService_v2/abandonmentPublic_v2?ServiceKey=Vw25fQSAsfNycj/AXwgHlM66HYmyfKPkX8pSs7dRqhRB1CqtZvhH0mUoAjue6h3CmrUQTjIBD3mHhflG7pedpA==&_type=json&numOfRows=20&upkind=417000",
                HttpMethod.GET,
                null,   // 그외 설정할 헤더값
                String.class
        );

        // System.out.println(response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        AbandonmentJSON abandonmentJSON =objectMapper.readValue(response.getBody(), AbandonmentJSON.class);

        int n = abandonmentJSON.getResponse().getHeader().getReqNo();
        System.out.println(n);

        String s1 = abandonmentJSON.getResponse().getBody().getItems().getItem().get(0).getHappenPlace();
        System.out.println(s1);

        model.addAttribute("body", abandonmentJSON.getResponse().getBody());
        return "rescue-animal/all";
    }

}
