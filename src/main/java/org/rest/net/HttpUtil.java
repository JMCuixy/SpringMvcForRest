package org.rest.net;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.rest.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by XiuYin.Cui on 2018/5/24.
 */
@Component
public class HttpUtil {

    private final HttpClient httpClient = HttpClients.createDefault();

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 使用Apache HTTP Client 发送请求
     */
    public void httpGet() {
        HttpGet httpGet = new HttpGet("http://");
        httpGet.setHeader("Accept", "application/json");
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readValue(entity.getContent(), Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*get请求*/

    /*getForObject*/
    public Area getForObject() {
        return restTemplate.getForObject("http://restapi.amap.com/v3/config/district?key=2c95fdacd3f72bdbfec55bd7eac7b5c0", Area.class);

    }


}
