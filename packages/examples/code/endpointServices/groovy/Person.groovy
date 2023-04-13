package endpointServices.groovy

import org.hibernate.validator.constraints.NotBlank

import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement

/**
 * Employee object to be used with the API
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
class Person {

	@XmlElement
	int id

	@NotBlank
	@Size(min = 1, max = 50)
	@XmlElement(required = true)
	String firstName = 'Daren'

	@NotBlank
	@Size(min = 1, max = 50)
	@XmlElement(required = true)
	String lastName = 'Klamer'


	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}