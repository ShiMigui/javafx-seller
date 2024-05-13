package gui.utils;

import java.util.function.Predicate;

import javafx.scene.control.TextField;

public final class TextfieldConstraint {
    private static TextfieldConstraint instance;
    private TextField txf;

    private TextfieldConstraint() {
    }

    public static TextfieldConstraint getInstance() {
	if (instance == null)
	    instance = new TextfieldConstraint();
	return instance;
    }

    public static TextfieldConstraint getInstance(TextField txf) {
	return getInstance().listen(txf);
    }

    public TextfieldConstraint listen(TextField txf) {
	instance.txf = txf;
	return instance;
    }

    public TextfieldConstraint maxLength(int max) {
	newListener((newV) -> newV.length() < max);
	return instance;
    }

    public TextfieldConstraint asIntegerText() {
	newListener((newV) -> newV.matches("\\d*"));
	return instance;
    }

    public TextfieldConstraint asDoubleText() {
	newListener((newV) -> newV.matches("\\d*([\\.]\\d*)?"));
	return instance;
    }

    public TextfieldConstraint newListener(Predicate<String> pred) {
	txf.textProperty().addListener((obs, oldV, newV) -> {
	    if (newV != null && !pred.test(newV))
		txf.setText(oldV);
	});
	return instance;
    }
}
