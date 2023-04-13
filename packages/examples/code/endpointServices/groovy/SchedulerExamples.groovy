package endpointServices.groovy

public class SchedulerExamples {

	/**
	 * Sends an email.
	 * The email credentials must be provided in the `package.properties` file for this to work.
	 * This Groovy service could be used in a Scheduler endpoint.
	 */
	public void sendScheduledEmail() {
		String email = 'login'.getPackageProperty()
		String password = 'password'.getPackageProperty()
		String port = 'port'.getPackageProperty()
		String subject = 'subject'.getPackageProperty()
		String[] to = 'email.bulk.to'.getPackageProperty().split(',' )
		String protocol = 'protocol'.getPackageProperty()
		String server = 'server'.getPackageProperty()
		
		to.each {
			String body = '''<html>
            <head></head>
            <body>
                Hello $name!,<br/>
                This is an email from Martini Runtime!<br/>
                <br/>
                Thanks,<br/>
                TORO
            </body>
            </html>
        '''.parse([name: it])
			"${protocol}://${URLEncoder.encode(email, 'UTF-8')}:${URLEncoder.encode(password, 'UTF-8')}@${server}:${port}/${subject}"
					.send([to: it], body)
		}
		
	}
	
}