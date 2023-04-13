package solr.customSolrCore.model

import org.apache.solr.client.solrj.beans.Field

class MovieDocument{

    @Field
    String id;

    @Field
    String movieTitle;

    @Field
    String director;

    @Field
    String[] cast;

}