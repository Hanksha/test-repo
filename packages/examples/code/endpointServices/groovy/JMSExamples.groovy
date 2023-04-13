package endpointServices.groovy

import io.toro.martini.api.APIResponse
import org.hibernate.validator.constraints.NotBlank
import org.springframework.web.bind.annotation.RequestParam

import javax.jms.Message
import javax.validation.constraints.Size

public class JMSExamples {

    /**
     * Sends a reply to the given message.
     * Before the reply is sent, the reply is logged to the Martini console first.
     * This is a Groovy service that could be used with a JMS listener endpoint.
     * @param message the message to log and reply to
     */
    public void receiveJMS(Message message) {
        def reply = "${Thread.currentThread().id} Got a JMS message from destination ${message.JMSDestination}, the message is ${message}".info()
        Thread.currentThread().sleep(5000)
        message.replyWith( reply ) 
    }

    /**
     * Sends a message to a particular topic on the broker.
     * @param jmsQueue the name of the topic to send the text to
     * @param messageContent the message content
     */
    public static APIResponse sendJMSMessage(
            @NotBlank @RequestParam('jmsQueue') @Size(min = 1, max = 20) String jmsQueue,
            @NotBlank @RequestParam('messageContent') String messageContent ) {
        jmsQueue.publishString(messageContent) { replyMessage ->
			// This gets called when a reply is received, parameter is the actual JMS message object
			"Received a reply for ${messageContent}. The reply text is ${replyMessage.text}.".info()
        }

        if (true)
            return new APIResponse( 'Success' )

        // There are other, simpler ways to send JMS messages; see below
        jmsQueue.publishString(messageContent)

        // Same as the line above, but the queue and content is switched around
        messageContent.publishTo(jmsQueue)

        // Publish as a byte array
        jmsQueue.publishBytes(messageContent.bytes)

    }
}