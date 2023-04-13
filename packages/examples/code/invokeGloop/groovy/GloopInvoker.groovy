package invokeGloop.groovy

import io.toro.gloop.object.property.GloopModel

class GloopInvoker{
	

	/**
	 * <p>A Groovy service that logs the sum and average of the numbers 1.5, 3, 4.7, 3.4, 55.4, and 15.</p>
	 * <p>The sum and average of these numbers are obtained by calling the
	 * {@code invokeGloop.gloop.ComputeNumbers.gloop} service.</p>
	 */
	static void invokeComputeNumbers() {
		
		// Invoke the Gloop service
		GloopModel output = 'invokeGloop.gloop.ComputeNumbers'.gloop( [[1.5, 3, 4.7, 3.4, 55.4, 15]] )
		GloopModel serviceOutput = ( GloopModel ) output.get( 'output' )
		Double average = ( Double ) serviceOutput.average
		Double sum = ( Double ) serviceOutput.sum

		"The sum is: ${sum}".info()
		"The average is: ${average}".info()
		
	}
	
}
