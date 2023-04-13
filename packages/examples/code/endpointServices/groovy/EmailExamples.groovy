package endpointServices.groovy

import io.toro.gloop.annotation.GloopObjectParameter;
import javax.mail.Address
import javax.activation.DataSource

public class EmailExamples {

    /**
     * Logs the following information to the Martini console:
     * <ul>
     * <li>The sender of the email</li>
     * <li>The receiver of the email</li>
     * <li>The content of the email</li>
     * <li>Attachment names and content</li>
     * <li>The number of attachments in the email</li>
     * </ul>
     *
     * This is a Groovy service that can be used with an Email endpoint.
     * 
     * @param from the sender's email address
     * @param to the email address(es) which received the email
     * @param body the content of the email
     * @param attachments a collection of <a href="http://docs.oracle.com/javaee/5/api/javax/activation/DataSource.html">DataSource</a> objects
     */
    public static String readEmailAttachment(
		@GloopObjectParameter('attachments#io.toro.martini.email.DataSource[]{\n}') List<DataSource> attachments,
		String fromAddress,
		String body,
		@GloopObjectParameter('to#io.toro.martini.email.Address[]{\n}') Address[] to ) {
        "Got an email from ${fromAddress} sent to ${to[0]} with body ${body}".info()
        attachments.each {
            "Attachment ${it.name} has content ${it.inputStream.text}".info()
        }
        "Received ${attachments.size()} attachment(s).".info()
    }
}