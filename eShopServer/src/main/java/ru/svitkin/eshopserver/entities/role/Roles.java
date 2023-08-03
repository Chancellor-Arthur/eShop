package ru.svitkin.eshopserver.entities.role;

public class Roles {
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String USER = "ROLE_USER";

	private Roles() {
		throw new IllegalStateException("Utility class");
	}
}
