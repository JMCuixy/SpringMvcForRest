package org.rest.net;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.rest.model.Area;
import org.rest.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.Map;

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

    /*GET请求*/


    /**
     * getForObject
     * <p>
     * http://restapi.amap.com/v3/config/district?key=2c95fdacd3f72bdbfec55bd7eac7b5c0
     *
     * @return
     */
    public Area getForObject() {
        String host = "http://restapi.amap.com/v3/config/district?key=2c95fdacd3f72bdbfec55bd7eac7b5c0";
        return restTemplate.getForObject(host, Area.class);
    }


    public Area getForObject(String key) {
        String host = "http://restapi.amap.com/v3/config/district?key={key}";
        return restTemplate.getForObject(host, Area.class, key);
    }

    public Area getForObject(Map<String, String> map) {
        String host = "http://restapi.amap.com/v3/config/district?key={key}";
        return restTemplate.getForObject(host, Area.class, map);
    }

    /*getForEntity*/


    public <T> ResponseEntity<T> getForEntity(Class<T> clazz) {
        String host = "http://restapi.amap.com/v3/config/district?key=2c95fdacd3f72bdbfec55bd7eac7b5c0";
        return restTemplate.getForEntity(host, clazz);

    }

    /*PUT请求*/
    /*PUT请求 接受一个URI 用来标识服务器上的资源位置，另外还接受一个对象，代表资源的Java表述*/

    public void put(UserDTO userDTO) {
        restTemplate.put("http://localhost:8080/updateUser", userDTO);
    }


}
