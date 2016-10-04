package by.mitroshin.textcomposite.entity;

import java.util.ArrayList;

/**
 * Created by HP M6 on 01.06.2015.
 */
public class Word implements Component {
    private String content;

    public Word(String content){
        this.content = content;
    }

    @Override
    public void add(Component c) {
        throw new UnsupportedOperationException(this + " dont have childs and cant do this!!!");
    }

    @Override
    public void remove(Component c) {
        throw new UnsupportedOperationException(this + " dont have childs and cant do this!!!");
    }

    @Override
    public Component getChild(int index) {
        throw new UnsupportedOperationException(this + " dont have childs and cant do this!!!");
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public ComponentType getType() {
        return ComponentType.WORD;
    }

}
