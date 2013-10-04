package batchMQ;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.jms.*;

/**
 * Created by Bennu Ltda.
 * User: Sergio Puas
 * Date: 04-10-13
 */
@Stateless
public class TestMQ {

    @Resource(lookup = "jms/MiFabricaDeConexiones")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/MiColaDeSolicitud")
    private Destination queue;

    public void sendMessage(String msg) {

        Connection connection;
        MessageProducer sender;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            sender = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            message.setText(msg);
            sender.send(message);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }


}
