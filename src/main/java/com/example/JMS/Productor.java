package com.example.JMS;

import com.example.Helper.Functions;
import com.example.Models.Message;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

public class Productor {

    public Productor(){

    }

    public void enviarMensaje(long idDispositivo, String topicName) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection("admin", "admin");
        connection.start();
        // Creando una sesión no transaccional y automatica.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Creamos o nos connectamos a la una cola, por defecto ActiveMQ permite
        // la creación si no existe. Si la cola es del tipo Queue es acumula los mensajes, si es
        // del tipo topic es en el momento.
        Topic topic = session.createTopic(topicName);
        MessageProducer producer = session.createProducer(topic);

        Message message = new Message(new Date(), idDispositivo, Functions.randomFloatGenerator(), Functions.randomFloatGenerator());

        // Creando un mensaje de texto y enviando.
        TextMessage msgToSend = session.createTextMessage(Functions.toJSON(message));
        producer.send(msgToSend);

        //Desconectando la referencia.
        producer.close();
        session.close();
        connection.stop();
    }
}
