package by.mitroshin.textcomposite.action;

import by.mitroshin.textcomposite.entity.*;

/**
 * Created by HP M6 on 07.06.2015.
 */
public class ChangeText {

    public static TextComposite changeFirstAndLast(TextComposite textComposite) {
        TextComposite text = new TextComposite(textComposite);
        Component component;
        for (int i = 0; i < text.getComponents().size(); i++) {
            component = text.getChild(i);
            if (component.getType() == ComponentType.PARAGRAPH) {
                TextComposite paragraph = (TextComposite) component;
                for (int j = 0; j < paragraph.getComponents().size(); j++) {
                    TextComposite sentence = (TextComposite) paragraph.getChild(j);
                    if ("\n".equals(sentence.getContent())) {
                        continue;
                    }
                    String first = null;
                    int firstPosition = 0;
                    int lastPosition = 0;
                    String last = null;
                    for (int k = 0; k < sentence.getComponents().size(); k++) {
                        if (sentence.getChild(k) instanceof Word) {
                            first = sentence.getChild(k).getContent();
                            firstPosition = k;
                            break;
                        }
                    }
                    for (int k = sentence.getComponents().size() - 1; k >= 0; k--) {
                        if (sentence.getChild(k) instanceof Word) {
                            last = sentence.getChild(k).getContent();
                            lastPosition = k;
                            break;
                        }
                    }
                    sentence.getChild(firstPosition).setContent(last);
                    sentence.getChild(lastPosition).setContent(first);
                }
            }
        }
        return text;
    }

}
