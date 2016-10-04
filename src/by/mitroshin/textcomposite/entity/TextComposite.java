package by.mitroshin.textcomposite.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by HP M6 on 30.05.2015.
 */
public class TextComposite implements Component {
    private ComponentType type;
    private ArrayList<Component> components;

    public TextComposite(ComponentType type){
        this.type = type;
        components = new ArrayList<Component>();
    }

    public TextComposite(TextComposite textComposite){
        this.type = textComposite.getType();
        components = new ArrayList<Component>(textComposite.getComponents());
    }

    @Override
    public void add(Component c) {
        components.add(c);
    }

    @Override
    public void remove(Component c) {
        components.remove(c);
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public String getContent() {
        String result = "";
        for(Component c : components){
            result = result + c.getContent();
        }
        return result;
    }

    @Override
    public void setContent(String content) {
        throw new UnsupportedOperationException(this + " cant do this!!!");
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    public List<Component> getComponents() {
        return Collections.unmodifiableList(components);
    }

}
