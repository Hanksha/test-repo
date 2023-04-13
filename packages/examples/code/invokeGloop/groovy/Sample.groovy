package invokeGloop.groovy

class Sample{
	
	/**
	 * <p>A method that returns and logs a greeting for a given {@code name}.</p>
	 * <p>This service will be invoked by {@code invokeGloop.gloop.InvokeGroovy.gloop} to demonstrate how to invoke
	 * Groovy services from Gloop.</p>
	 * @param name who to greet
	 * @return greeting
	 */
	static String hello( String name ) {
		"Hello ${name}".info()
	}
	
}