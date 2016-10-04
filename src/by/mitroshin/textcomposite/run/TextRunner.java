package by.mitroshin.textcomposite.run;

import by.mitroshin.textcomposite.action.ChangeText;
import by.mitroshin.textcomposite.action.DeleteFromText;
import by.mitroshin.textcomposite.entity.TextComposite;
import by.mitroshin.textcomposite.exception.TechnicalException;
import by.mitroshin.textcomposite.parser.Parser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created by HP M6 on 30.05.2015.
 */
public class TextRunner {

    public final static Logger LOG = Logger.getLogger(TextRunner.class);

    static{
        new DOMConfigurator().doConfigure("config/log4j.xml", LogManager.getLoggerRepository());
    }

    public static void main(String[] args) {
        try {

            TextComposite textComposite = Parser.parseText("input/text.txt");

            Parser.writeTextToFile(textComposite, "output/result.txt");

            TextComposite changed = ChangeText.changeFirstAndLast(Parser.parseText("input/text.txt"));
            Parser.writeTextToFile(changed, "output/changed.txt");

            TextComposite deleted = DeleteFromText.deleteWordsByLength(Parser.parseText("input/text.txt"), 9);
            Parser.writeTextToFile(deleted, "output/deleted.txt");


        } catch (TechnicalException e) {
            LOG.error("TechnicalException!!!", e);
        }
    }
}
