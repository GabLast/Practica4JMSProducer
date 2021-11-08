package com.example.Helper;

import com.example.Models.Sensor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Random;

public class Functions {

    public static final int SERVER_MODE = 1;
    public static final int CLIENT_MODE = 2;

    public static String toJSON(Sensor sensor){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNodes = objectMapper.createObjectNode();

        jsonNodes.put("fechaGeneracion", sensor.getFechaGeneracion().toString());
        jsonNodes.put("idDispositivo", sensor.getIdDispositivo());
        jsonNodes.put("temperatura", sensor.getTemperatura());
        jsonNodes.put("humedad", sensor.getHumedad());

        String json = null;

        try{
           json = objectMapper.writeValueAsString(jsonNodes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static float randomFloatGenerator(){
//        https://stackoverflow.com/questions/40431966/what-is-the-best-way-to-generate-a-random-float-value-included-into-a-specified/51247968
        Random r = new Random();
        float min = 0;
        float max = 100;
        float random = min + r.nextFloat() * (max - min);

        return random;
    }

    public static void delay(int seconds) {
        try
        {
            Thread.sleep(seconds * 1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
