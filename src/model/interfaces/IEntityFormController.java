package model.interfaces;

import javafx.stage.Stage;

public interface IEntityFormController<E extends IEntity> extends IController {
    void setReference(E reference);
    
    void setStage(Stage stage);
    
    void subscribeListener(IDataChangeListener listener);
}
