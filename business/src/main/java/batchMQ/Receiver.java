package batchMQ;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Bennu Ltda.
 * User: Sergio Puas
 * Date: 04-10-13
 */
@MessageDriven(mappedName = "jms/MiColaDeSolicitud", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class Receiver implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                System.out.println(msg.getText());
            } else {
                System.out.println("Message of wrong type: " + message.getClass().getName());
            }
        } catch (Exception te) {
            te.printStackTrace();
        }
    }

}
