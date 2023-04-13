package endpointServices.groovy

import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed

class RSSExamples {

    /**
     * Iterate over RSS feed entries and log them to the Martini console.
     * This Groovy service can be used with an RSS endpoint.
     * @param feed the object containing the RSS feed
     * @param entries the list of entries in the RSS feed
     * @param entry the newest entry in the feed
     */
    public void rssReader( SyndFeed feed, List entries, SyndEntry entry ) {
        feed.entries.each {
            "Found a new RSS item with title ${it.title}".info()
        }
    }

}