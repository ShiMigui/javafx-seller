package model.interfaces;

import java.io.Serializable;

public interface IEntity extends Serializable {
    Integer getId();

    default String normalizeText(String text) {
	return text.trim().replaceAll("\\s{2,}", " ");
    }
}
