package it.unibo.oop.lab.collections2;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Instructions
 * 
 * This will be an implementation of
 * {@link it.unibo.oop.lab.collections2.SocialNetworkUser}:
 * 
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 * 
 * @param <U> Specific user type
 */
public class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

	/*
	 * 
	 * [FIELDS]
	 * 
	 * Define any necessary field
	 * 
	 * In order to save the people followed by a user organized in groups, adopt a
	 * generic-type Map:
	 * 
	 * think of what type of keys and values would best suit the requirements
	 */
	private final HashMap<String, HashSet<U>> followedGroups;

	/*
	 * [CONSTRUCTORS]
	 * 
	 * 1) Complete the definition of the constructor below, for building a user
	 * participating in a social network, with 4 parameters, initializing:
	 * 
	 * - firstName - lastName - username - age and every other necessary field
	 * 
	 * 2) Define a further constructor where age is defaulted to -1
	 */

	public SocialNetworkUserImpl(final String name, final String surname, final String user) {
		this(name, surname, user, -1);
	}

	/**
	 * Builds a new {@link SocialNetworkUserImpl}.
	 * 
	 * @param name    the user firstname
	 * @param surname the user lastname
	 * @param userAge user's age
	 * @param user    alias of the user, i.e. the way a user is identified on an
	 *                application
	 */
	public SocialNetworkUserImpl(final String name, final String surname, final String user, final int userAge) {
		super(name, surname, user, userAge);
		this.followedGroups = new HashMap<>();
	}

	/*
	 * [METHODS]
	 * 
	 * Implements the methods below
	 */

	@Override
	public boolean addFollowedUser(final String circle, final U user) {
		if (followedGroups.containsKey(circle) != true) {
			// If the group doesn't exist, it creates a new group
			followedGroups.put(circle, new HashSet<>());
		}
		return followedGroups.get(circle).add(user);

	}

	@Override
	public Collection<U> getFollowedUsersInGroup(final String groupName) {
		return followedGroups.get(groupName)==null?new HashSet<U>():new HashSet<U>(followedGroups.get(groupName));
	}

	@Override
	public List<U> getFollowedUsers() {
		List<U> allFollowers=new LinkedList<U>();
		for(HashSet<U> elem: followedGroups.values()) {
			for(U user:elem) {
				if(allFollowers.contains(user)==false) {
					allFollowers.add(user);
				}	
			}
		}
		return allFollowers;
	}

}
