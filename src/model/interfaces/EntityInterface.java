package model.interfaces;

public interface EntityInterface {
	void setId(Integer id);

	Integer getId();

	void setName(String name);

	String getName();
	
	default String normalizeText(String text) {
		return text.trim().replaceAll("\\s{2,}", " ").toUpperCase();
	}
}
