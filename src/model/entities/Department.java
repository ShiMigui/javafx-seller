package model.entities;

import java.io.Serializable;
import java.util.Objects;

import model.interfaces.IEntity;

public class Department implements IEntity, Serializable {
    private static final long serialVersionUID = 1L;

    public Integer id;
    public String name;

    public Department() {
    }

    public Department(Integer id, String name) {
	setId(id);
	setName(name);
    }

    @Override
    public Integer getId() {
	return id;
    }

    @Override
    public void setId(Integer id) {
	this.id = id;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public void setName(String name) {
	this.name = normalizeText(name);
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if ((obj == null) || (getClass() != obj.getClass())) {
	    return false;
	}
	Department other = (Department) obj;
	return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
	return "Department [id=" + id + ", name=" + name + "]";
    }
}
