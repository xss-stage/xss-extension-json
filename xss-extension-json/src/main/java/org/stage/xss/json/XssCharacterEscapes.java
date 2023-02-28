package org.stage.xss.json;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

public class XssCharacterEscapes extends CharacterEscapes{

    private final int[] asciiEscapes;

    private final CharSequenceTranslator translator;

    XssCharacterEscapes() {
        asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;

        translator = new AggregateTranslator(
                new LookupTranslator(EntityArrays.BASIC_ESCAPE),
                new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE),
                new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE),
                new LookupTranslator(getLookUpMap())
        );
    }

    private Map<CharSequence, CharSequence> getLookUpMap(){
        HashMap<CharSequence, CharSequence> map = new HashMap<>();
        map.put("\'", "&#39;");
        return map;
    }

    @Override
    public int[] getEscapeCodesForAscii(){
        return asciiEscapes;
    }

    @Override
    public SerializableString getEscapeSequence(int ch){
        return new SerializedString(translator.translate(Character.toString((char) ch)));
    }

}
