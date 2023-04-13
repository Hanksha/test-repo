package bigData.cassandra.conf

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session
import io.toro.martini.ipackage.MartiniPackage
import io.toro.martini.GroovyMethods

class CassandraConfiguration {

	
	private static Cluster cassandra = 'java:comp/env/cassandra/cassandra-example'.jndi()
	
	public static void createKeyspace() {
		Session session = cassandra.connect()
		
		String restaurantKeyspace = '''
					CREATE KEYSPACE IF NOT EXISTS restaurants with replication = {
							'class':'SimpleStrategy',
							'replication_factor':3
						};
				'''
		
		String examplesKeyspace = '''
					CREATE KEYSPACE IF NOT EXISTS cassandra_examples with replication = {
							'class':'SimpleStrategy',
							'replication_factor':3
						};
				'''
		
		session.execute(restaurantKeyspace)
		session.execute(examplesKeyspace)
	}
	public static void createRestaurantTable() {
		Session session = cassandra.connect("restaurants")
		
		String addressType = readCQL('CreateTypeAddress.cql')
		String gradeType = readCQL('CreateTypeGrade.cql')
		String restaurantsTable = readCQL('CreateTableRestaurants.cql')
		
		session.execute(addressType)
		session.execute(gradeType)
		session.execute(restaurantsTable)
	}
	
	public static void createExamplesTables() {
		Session session = cassandra.connect("cassandra_examples")
		
		String blob = readCQL('blob/CreateTableBlobExample.cql')
		String timeuuid = readCQL('timeuuid/CreateTableTimeuuidExample.cql')
		String tuple = readCQL('tuple/CreateTableTupleExample.cql')
		String udtType1 = readCQL('udt/CreateTypeType1.cql')
		String udtType2 = readCQL('udt/CreateTypeType2.cql')
		String udt = readCQL('udt/CreateTableUdtExample.cql')
		String uuid = readCQL('uuid/CreateTableUuidExample.cql')
		
		session.execute(blob)
		session.execute(timeuuid)
		session.execute(tuple)
		session.execute(udtType1)
		session.execute(udtType2)
		session.execute(udt)
		session.execute(uuid)
	}
	
	private static String readCQL(String fileLocation) {
		String resourcePath = 'resources/queries/bigData/cassandra/'
		
		MartiniPackage pkg = GroovyMethods.getPackage()
		fileLocation = pkg.getInfo().getHome().toPath().resolve(resourcePath + fileLocation)
		
		return new File(fileLocation).text
	}
}
