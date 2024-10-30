package ies.pedro.viewlayer;

import java.util.HashMap;
import java.util.function.BiConsumer;

public interface IWindows   {
    @FunctionalInterface
    public interface Action {
        void execute();
    }
    public void addParameter(String name,Object parameter);
    public void addParameters(HashMap<String,Object> parameters);
    public HashMap<String,Object> getResults();

    public void setOnInizialized(Action oninizialized);
    public void setOnClosed(Action onclosed);
    public void refresh();
    //public void setOnCancel(Consumer<T> onclosed);
    //public void setOnSaved(Consumer<T> onsaved);
    public void setOnAction(BiConsumer<String,Object> onaction);

}
