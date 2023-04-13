package solr.customSolrCore

import org.apache.solr.servlet.SolrRequestParsers
import solr.customSolrCore.model.MovieDocument

import javax.servlet.http.HttpServletRequest

class MovieSolrAPI {

	/**
	 * Add document to the custom solr core
	 * @param movieTitle
	 * @param director
	 * @param casts
	 * @return
	 */
	public def addDocument( String movieTitle,
							String director,
							String[] casts ) {
		def movieDocument = new MovieDocument(movieTitle: movieTitle, director: director, cast: casts)
		'movie-core'.writeToIndex( null, movieDocument ).toString()
	}

	/**
	 * Query custom solr core document indexes
	 * @param req
	 * @return
	 */
	public def search( HttpServletRequest req ) {
		println SolrRequestParsers.parseQueryString( req.getQueryString() ).getMap()
		'movie-core'.query( null, SolrRequestParsers.parseQueryString( req.getQueryString() ).getMap() ).toString()
	}

}
