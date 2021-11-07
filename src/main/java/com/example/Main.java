package com.example;

import com.example.Helper.Functions;
import com.example.JMS.Productor;
import org.apache.activemq.broker.BrokerService;

import javax.jms.JMSException;

public class Main {

    public static void main(String[] args) throws JMSException, InterruptedException {

        if (args.length == 0) {
            mensajesParametros();
            return;
        }

        int mode = Integer.parseInt(args[0]);

        System.out.println("Testing args: " + mode);
        if (mode == Functions.SERVER_MODE) {
            System.out.println("Inicializando Servidor JMS");
            //Subiendo la versión embedded de ActiveMQ.
            try {
                BrokerService broker = new BrokerService();
                //configurando el broker.
                broker.addConnector("tcp://0.0.0.0:61616");
                //Inicializando
                broker.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (mode == Functions.CLIENT_MODE) {
            System.out.println("Inicializando Productor");
            long idDispo = Long.parseLong(args[1]);
            System.out.println("Testing args: " + idDispo);

            for (int i = 0; i < 5; i++) {
                new Productor().enviarMensaje(idDispo, "notificacion_sensores");
                Functions.delay(3);
            }
        }

    }

    private static void mensajesParametros() {
        System.out.println("Enviar 2 parámetros: \n" +
                "1-) [ID Dispositivo]\n" +
                "2-) [Modo de corrida]\t(1 == Iniciar Servidor || 2 == Iniciar productor)");

    }
}
