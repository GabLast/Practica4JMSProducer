package com.example.JMS;

import com.example.Helper.Functions;
import com.example.Models.Sensor;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.time.LocalDateTime;

public class Productor {

    public Productor(){

    }

    public void enviarMensaje(long idDispositivo, String topicName, boolean prod) throws JMSException {
        ActiveMQConnectionFactory factory;

        if(prod){
            factory = new ActiveMQConnectionFactory("tcp://server:61616");
        }else {
            factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        }
//        System.out.println(prod);
        Connection connection = factory.createConnection("admin", "admin");
        connection.start();
        // Creando una sesión no transaccional y automatica.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Creamos o nos connectamos a la una cola, por defecto ActiveMQ permite
        // la creación si no existe. Si la cola es del tipo Queue es acumula los mensajes, si es
        // del tipo topic es en el momento.
        Topic topic = session.createTopic(topicName);
        MessageProducer producer = session.createProducer(topic);

        Sensor sensor = new Sensor(LocalDateTime.now(), idDispositivo, Functions.randomFloatGenerator(), Functions.randomFloatGenerator());

        // Creando un mensaje de texto y enviando.
        TextMessage msgToSend = session.createTextMessage(Functions.toJSON(sensor));
        producer.send(msgToSend);

        //Desconectando la referencia.
        producer.close();
        session.close();
        connection.stop();
    }
}
