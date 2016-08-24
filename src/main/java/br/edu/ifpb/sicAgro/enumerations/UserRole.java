package br.edu.ifpb.sicAgro.enumerations;

public enum UserRole {
	
	ADMIN("Adimistrador"),
	DRIVER("Motorista"),
	ATTENDANT("Recepcionista"),
	PRODUTOR("Produtor");
	
private String role;
	
	private UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
