package by.mitroshin.textcomposite.entity;

/**
 * Created by HP M6 on 02.06.2015.
 */
public class Punctuation implements Component {
    private String content;

    public Punctuation(String content){
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

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public ComponentType getType() {
        return ComponentType.PUNCTUATION;
    }

}
