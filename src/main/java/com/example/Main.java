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

            if (args.length > 2) {
                System.out.println("Production Profile: " + true);
            } else {
                System.out.println("Production Profile: " + false);
            }

            //delay porque el docker-compose no espera a que
            //la app web se conecte al servidor ActiveMQ
            Functions.delay(11);
            for (int i = 0; i < 10; i++) {
                if(args.length > 2) {
                    new Productor().enviarMensaje(idDispo, "notificacion_sensores", true);
                }else {
                    new Productor().enviarMensaje(idDispo, "notificacion_sensores", false);
                }

                Functions.delay(3);
            }
            System.exit(0);
        } else {
            System.out.println("Argumentos de corrida inválidos!!!");
        }

    }

    private static void mensajesParametros() {
        System.out.println("Enviar 2 parametros: \n" +
                "1-) [ID Dispositivo]\n" +
                "2-) [Modo de corrida]\t(1 == Iniciar Servidor || 2 == Iniciar productor)");

    }
}
