package endpointServices.groovy

import io.toro.martini.tracker.Document
import io.toro.martini.tracker.Document.State
import io.toro.martini.tracker.Tracker

class TrackerResubmitExamples {

    /**
     * Adds a "Resubmit Count" property to the document, and increments that property's value.
     * This Groovy service can be used as the service of a Tracker resubmit endpoint.
     * @param document the Tracker document the re-submit is being performed on
     * @param documentState the state the re-submit is being performed on
     */
    public void resubmit( Tracker tracker, Document document, State documentState ) {
        "Endpoint called for document ${document.internalId()} - ${documentState.name()}".info()
        def count = tracker.getOrDefault('Resubmit Count', '0') as int
        tracker.put('Resubmit Count', ++count as String)
    }
    
}
