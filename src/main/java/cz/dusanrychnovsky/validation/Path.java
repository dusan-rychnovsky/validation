package cz.dusanrychnovsky.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.util.Arrays.asList;

/**
 * Represents a path through an object-graph. This can be an in-memory graph, or serialized to json, xml, etc.
 *
 * For example, suppose the following classes:
 *
 * <pre>
 * class Member {
 *
 *     String firstName;
 *     String lastName;
 *     List<ContactEmail> emails;
 *
 *     ...
 * }
 *
 * class ContactEmail {
 *
 *     String email;
 *     LocalDate startDate;
 *     LocalDate endDate;
 *
 *     ...
 * }
 * </pre>
 *
 * and the following object graph:
 *
 * <pre>
 * Member member = new Member(
 *     "John",
 *     "Snow",
 *     asList(
 *         new ContactEmail(
 *             "john.snow@gmail.com",
 *             LocalDate.now(),
 *             LocalDate.now().plusDays(3)
 *         ),
 *         new ContactEmail(
 *             "john.snow@outlook.com",
 *             LocalDate.now().minusDays(5),
 *             LocalDate.now()
 *         )
 *     )
 * );
 * </pre>
 *
 * Then the following are examples of valid paths:
 *
 * <ul>
 *     <li><em>new Path()</em> - represents member,</li>
 *     <li><em>new Path("firstName")</em> - represents member.getFirstName(),</li>
 *     <li><em>new Path("emails")</em> - represents member.getEmails(),</li>
 *     <li><em>new Path("emails", "0")</em> - represents member.getEmails().get(0),</li>
 *     <li><em>new Path("emails", "1", "email")</em> - represents member.getEmails().get(1).getEmail(),</li>
 * </ul>
 *
 * and the following are not:
 *
 * <ul>
 *     <li><em>new Path("age")</em> - no property "age" for class "Member",</li>
 *     <li><em>new Path("firstName, "0")</em> - "firstName" is not a list,</li>
 *     <li><em>new Path("emails", "2")</em> - no item with index "2" in "emails",</li>
 *     <li><em>new Path("emails", "fromDate")</em> - "emails" is a list.</li>
 * </ul>
 *
 * @author Dušan Rychnovský
 *
 */
public class Path {
	
	private final List<String> elements = new ArrayList<>();
	
	/**
	 * 
	 * @param elements
	 */
	public Path(String... elements) {
		this(asList(elements));
	}
	
	/**
	 * 
	 * @param elements
	 */
	public Path(List<String> elements) {
		for (String element : elements) {
            this.elements.add(element);
        }
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public Path append(Path other) {
		
		List<String> newElements = new ArrayList<>();
		newElements.addAll(elements);
		newElements.addAll(other.elements);
		
		return new Path(newElements);
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public Path prepend(Path other) {
		return other.append(this);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getElements() {
		return Collections.unmodifiableList(elements);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Path)) {
			return false;
		}
		
		Path other = (Path) obj;
		return elements.equals(other.elements);
	}
	
	@Override
	public int hashCode() {
		return elements.hashCode();
	}
}
