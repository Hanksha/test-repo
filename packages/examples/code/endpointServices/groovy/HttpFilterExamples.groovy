package endpointServices.groovy

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponseWrapper

class HttpFilterExamples {

	/**
	 * Alters the response to inject 'X'-prefixed headers from the request;
	 * albeit with values in uppercase. 
	 * 
	 * @param $__request the matched request
	 * @param $__response the response for the matched request
	 * @return an altered copy of the response
	 */
	static HttpServletResponse toUppercaseXPrefixedHeaders(
		HttpServletRequest $__request, HttpServletResponse $__response ) {

		$__response = new HttpServletResponseWrapper( $__response );
		Enumeration<String> headers = $__request.getHeaderNames();
		while ( headers.hasMoreElements() ) {
			String header = headers.nextElement();
			"Checking header [$header]".trace()
			if ( !header.toUpperCase().startsWith( "X-" ) ) {
				continue;
			}

			Enumeration<String> values = $__request.getHeaders( header );
			while ( values.hasMoreElements() ) {
				String value = values.nextElement();
				"Transforming value [$value] of header [$header] to uppercase".info()
				$__response.addHeader( header , value.toUpperCase() )
			}
		}

		return $__response
	}

}
