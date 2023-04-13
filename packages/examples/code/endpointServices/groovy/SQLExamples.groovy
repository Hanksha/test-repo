package endpointServices.groovy

import io.toro.martini.api.APIException
import io.toro.martini.api.APIResponse
import io.toro.martini.database.DataSourceManager
import io.toro.martini.database.Database
import io.toro.martini.service.annotation.UiAdvanced
import io.toro.martini.transaction.Transactions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ResponseBody

class SQLExamples {

    @Autowired
    private DataSourceManager dataSourceManager

    // We'll be using data sources created during start up service execution.
    // We'll be having a second data source to perform XA transactions.
    public enum DataSource {
        dataSource1( 'example_db' ),
        dataSource2( 'example_xa_db_migrated' )

        private String source;

        public DataSource( String source ) {
            this.source = source;
        }

        public String toString() {
            source
        }

    }

    /**
     * Adds a new person to the database table. 
     * @param person the person to add
     */
    public APIResponse addPerson( DataSource dataSource, Person person ) {
        // Martini Runtime allows you to easily get a Groovy SQL object that's already connected to a Martini Runtime
		// JDBC connection pool.
        dataSource.toString().sql() { sql ->
            // Insert the record. In this example, we use a strongly typed object with JAXB and validation annotations.
            def keys = sql.executeInsert('insert into names( first_name, last_name ) values ( ?, ? )',
                    					 [person.firstName, person.lastName])
            // Send back an OK with the id of the new record.
            new APIResponse('OK', '' + keys[0][0])
        }
    }

    /**
     * Iterates through the table and returns all person records found.
     */
    public List<Person> getPersons( DataSource dataSource ) {
        // get a groovy sql object for the examples pool
        dataSource.toString().sql() { sql ->
            List<Person> persons = new LinkedList<Person>()
            // for every row in the table, we'll add an entry to the output map
            sql.eachRow('select * from names') { row ->
                persons.add( new Person( id: row.id, firstName: row.first_name, lastName: row.last_name ) )
            }
            // return the map
            persons
        }
    }

    /**
     * This method returns a single person object. Unlike the list method, this returns a populated person object.
     * @param id the id of the person to get
     * @responsecode 404 person not found
     * @return person object (if found)
     * @throws APIException if something goes wrong, or the person isn't found
     */
    public Person getPerson( DataSource dataSource, int id ) throws APIException {
        dataSource.toString().sql() { sql ->
            def row = sql.firstRow('select * from names where id = ?', [id])
            if (row == null)
                throw new APIException("No person found with id ${id}", 404)
            new Person(
                    id: row.id,
                    firstName: row.first_name,
                    lastName: row.last_name)
        }
    }

    /**
     * Update a person record in the database.
     * @param id the id of the person to update
     * @param person new data for the person record
     * @responsecode 404 person not found
     * @throws APIException if the person record isn't found
     */
    public APIResponse updatePerson( DataSource dataSource,
                                     Integer id,
                                     Person person ) throws APIException {
        dataSource.toString().sql() { sql ->
            int result = sql.executeUpdate('update names set first_name = ?, last_name = ? where id = ?',
                    [person.firstName, person.lastName, id])
            if (result == 1)
                return new APIResponse('OK', "Updated record with id ${id}")
            throw new APIException("No person found with id ${id}", 404)
        }
    }

    /**
     * Deletes a person record.
     * @param id the id of the person to delete
     * @responsecode 404 person not found
     * @throws APIException if the person record isn't found
     */
    public APIResponse deletePerson( DataSource dataSource,
                                     Integer id ) throws APIException {
        dataSource.toString().sql() { sql ->
            int result = sql.executeUpdate('delete from names where id = ?', [id])
            if (result == 1)
                return new APIResponse('OK', "Deleted record with id ${id}")
            throw new APIException("No person found with id ${id}", 404)
        }
    }

    /**
     * Send a comma separated list of person records in the following format to this method
     * to add multiple person records in one request:
     * <pre>
     *     &lt;firstName&gt;,&lt;lastName&gt;<br/>
     *     &lt;firstName&gt;,&lt;lastName&gt;,&lt;<br/>
     *     ...
     * </pre>
     * @param body the csv data to process
     * @return
     */

    public void bulkInsertPersons( DataSource dataSource,
                                   InputStream body) {
        dataSource.toString().sql() { sql ->
            sql.withBatch( 20, 'insert into names( first_name, last_name ) values ( ?, ? )' ) { ps ->
                body.eachRecord( ',' ) { row ->
                    ps.addBatch( row[ 0 ], row[ 1 ] )
                }
            }
        }
    }

    /**
     * We'll migrate data from dataSource1.names into dataSource2.names,
     * and delete the row from 'dataSource1' if successful. If it fails,
     * everything must roll back.
     * 
     * Here we demo the synchronization of two datasource resources running in an
     * XA transaction. To cause the migration rollback comment out line 188; that way,
     * you'll see the migration fail. You can also go to line 195, and change the
     * second argument into a string value.
     */
    public def migration( ) {
        "Enabling XA for data sources ${DataSource.values()}".info()
        enableDatabaseXA()
        def tx = Transactions.start()
        try {
            List<Person> persons = getPersons( DataSource.dataSource1 )
            persons.each {
                addPerson( DataSource.dataSource2, it )
                "-- inserted row ${it} to ${DataSource.dataSource2}".info()
                deletePerson( DataSource.dataSource1, it.id )
                "-- deleted row ${it} from ${DataSource.dataSource1}".info()
            }
            persons = getPersons( DataSource.dataSource2 );
            "-- Populate ${DataSource.dataSource1} with ${DataSource.dataSource2} data(s)".info()
            populateDataSource( DataSource.dataSource2, DataSource.dataSource1 );
            tx.commit()
            return [ message: 'Migration Success!', 'Persons': persons ]
        } catch (Exception ex) {
            tx.rollback()
            "Migration failed, rolling back transaction (${ex.message})".info()
            return new APIException("Migration failed, rolling back transaction (${ex.message})")
        } finally {
            "Disabling XA for data sources ${DataSource.values()}".info()
            disableDatabaseXA()
        }
    }

    private void enableDatabaseXA( ) {
        databaseXaHelper( true )
    }

    private void disableDatabaseXA() {
        databaseXaHelper( false )
    }

    private void databaseXaHelper( boolean enableXA ) {
        String[] dataSources = DataSource.values()
        for ( String dataSource : dataSources ) {
            Database connectionInfo = dataSourceManager.getDataSource( dataSource ).getConfig()
			dataSourceManager.stopDataSource( dataSource )
            connectionInfo = connectionInfo.toBuilder().xa( enableXA ).build()
            dataSourceManager.saveConfig( connectionInfo )
            dataSourceManager.startDataSource( dataSource )
        }
    }

    private void populateDataSource( DataSource fromDataSource, DataSource toDataSource ) {
        List<Person> persons = getPersons( fromDataSource )
        int index = 0;
        for ( Person p : persons ) {
            if ( index >= 5 ) {
                break;
            } else {
                addPerson( toDataSource, p )
            }
            index ++;
        }
    }

    /**
     * Hide this from the Martini Runtime UI unless all services checkbox is ticked
     * @return a person object with some default data
     */
    @UiAdvanced
    @ResponseBody
    public Person defaultPerson() {
        new Person( firstName: 'Daren ', lastName: 'Klamer')
    }

}