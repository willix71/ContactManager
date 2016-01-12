package w.contactmgr.model;

import java.io.Serializable;

public class Contact implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	private String phone;
	private String email;
	private String website;
	private String notes;
	public Contact() {
		super();
	}

	public Contact(int id, String name) {
		super();
		this.id = (long) id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Contact klone() {
		try {
			return (Contact) clone();
		} catch(CloneNotSupportedException e) {
			throw new RuntimeException(e); // should never happen
		}
	}
}
