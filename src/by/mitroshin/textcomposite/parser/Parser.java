package by.mitroshin.textcomposite.parser;

import by.mitroshin.textcomposite.entity.*;
import by.mitroshin.textcomposite.exception.TechnicalException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by HP M6 on 30.05.2015.
 */
public class Parser {

    private static  ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.regulars");

    public static TextComposite parseText(String filePath) throws TechnicalException {
       try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            TextComposite result = new TextComposite(ComponentType.TEXT);
            String stringFromFile;
            TextComposite emptySentence = new TextComposite(ComponentType.SENTENCE);
            emptySentence.add(new Word("\n"));
            while ((stringFromFile = reader.readLine()) != null) {
                if (stringFromFile.trim().matches(resourceBundle.getString("listing"))) {
                    Listing listing = new Listing(stringFromFile+"\n");
                    result.add(listing);
                } else if (!stringFromFile.isEmpty()) {
                    TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
                    if (stringFromFile.matches(resourceBundle.getString("stringWithNumber"))) {
                        TextComposite sentence = parseStringToSentence(stringFromFile);
                        paragraph.add(sentence);
                        paragraph.add(emptySentence);
                        result.add(paragraph);
                        continue;
                    }
                    String[] sentences = stringFromFile.split(resourceBundle.getString("sentence"));
                    for (String string : sentences) {
                        TextComposite sentence = parseStringToSentence(string);
                        paragraph.add(sentence);
                    }
                    paragraph.add(emptySentence);
                    result.add(paragraph);
                } else {
                    result.add(emptySentence);
                }
            }
            return result;
        } catch (IOException e) {
            throw new TechnicalException(filePath + "error with file!!!", e);
        }
    }

    private static TextComposite parseStringToSentence (String string) {
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
        Punctuation space = new Punctuation(" ");
        String wordWithoutPunct;
        String punct;
        Word word;
        Punctuation punctuation;
        String[] parts = string.split(" ");
        for (String part : parts) {
            if (part.matches(resourceBundle.getString("number"))) {
                word = new Word(part);
                sentence.add(word);
                sentence.add(space);
            } else if (part.matches(resourceBundle.getString("word"))) {
                word = new Word(part);
                sentence.add(word);
                sentence.add(space);
                continue;
            } else if (part.matches(resourceBundle.getString("punctuation"))) {
                sentence.add(new Punctuation(part));
                sentence.add(space);
                continue;
            } else if (part.matches(resourceBundle.getString("wordWithPunct"))) {
                wordWithoutPunct = part.replaceAll(resourceBundle.getString("punctuation"), "");
                word = new Word(wordWithoutPunct);
                punct = part.replaceAll(resourceBundle.getString("word"), "");
                punctuation = new Punctuation(punct);
                if (("\"\"").equals(punct)) {
                    sentence.add(new Punctuation("\""));
                    sentence.add(word);
                    sentence.add(new Punctuation("\""));
                    sentence.add(space);
                    continue;
                } else if ("(".equals(punct)) {
                    sentence.add(new Punctuation(punct));
                    sentence.add(word);
                    sentence.add(space);
                    continue;
                } else if (punct.length() > 1){
                    sentence.add(word);
                    char[] puncts = punct.toCharArray();
                    for (int i = 0; i < punct.length(); i++) {
                        sentence.add(new Punctuation(String.valueOf((puncts[i]))));
                    }
                    sentence.add(space);
                    continue;
                }
                sentence.add(word);
                sentence.add(punctuation);
                sentence.add(space);
            } else {
                if ("".equals(part)) {
                    sentence.add(space);
                } else {
                    word = new Word(part);
                    sentence.add(word);
                    sentence.add(space);
                }
            }
        }
        return sentence;
    }

        public static void writeTextToFile(TextComposite text, String filePath) throws TechnicalException {
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(text.getContent());
            } catch (IOException e) {
                throw new TechnicalException(filePath + "error with file!!!", e);
            }
        }


}
