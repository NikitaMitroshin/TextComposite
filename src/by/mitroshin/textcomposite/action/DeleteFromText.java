package by.mitroshin.textcomposite.action;

import by.mitroshin.textcomposite.entity.Component;
import by.mitroshin.textcomposite.entity.ComponentType;
import by.mitroshin.textcomposite.entity.TextComposite;
import by.mitroshin.textcomposite.entity.Word;

import java.util.ResourceBundle;

public class DeleteFromText {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.regulars");

    public static TextComposite deleteWordsByLength(TextComposite textComposite, int length){
        TextComposite text = new TextComposite(textComposite);
        Component component;
        for (int i = 0; i < text.getComponents().size(); i++) {
            component = text.getChild(i);
            if (component.getType() == ComponentType.PARAGRAPH) {
                TextComposite paragraph = (TextComposite) component;
                for (int j = 0; j < paragraph.getComponents().size(); j++) {
                    TextComposite sentence = (TextComposite) paragraph.getChild(j);
                    for (int k = 0; k < sentence.getComponents().size() ; k++) {
                        if (sentence.getChild(k).getType() == ComponentType.WORD) {
                            Word word = (Word) sentence.getChild(k);
                            if (word.getContent().length() == length && word.getContent().matches(resourceBundle.getString("wordToDelete"))) {
                                paragraph.getChild(j).remove(word);
                            }
                        }
                    }
                }
            }
        }
        return text;
    }

}
