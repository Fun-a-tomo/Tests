import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.atilika.kuromoji.Token;

import net.java.sen.SenFactory;
import net.java.sen.StringTagger;

public class MorphAnal2 {
	String stat;
	StringTagger tagger = SenFactory.getStringTagger(null);
	List tokens = new ArrayList();
	
	@SuppressWarnings("deprecation")
	public MorphAnal2(String stat){
		this.stat = stat;
		try{
		tokens = tagger.analyze("こんにちは、ラーメンです",tokens);
		} catch (IOException e) {
			e.printStackTrace();
	}
	}
	
	public void EndMorphAnal() throws Throwable{
		this.finalize();
	}
	
	
	@SuppressWarnings("unchecked")
	public void MorphAnalStart(){
		for (int i=0;tokens.get(i) != null;i++) {
			Token token = (Token) tokens.get(i);
		    System.out.println("==================================================");
		    System.out.println("allFeatures : " + token.getAllFeatures());
		    System.out.println("partOfSpeech : " + token.getPartOfSpeech());
		    System.out.println("position : " + token.getPosition());
		    System.out.println("reading : " + token.getReading());
		    System.out.println("surfaceFrom : " + token.getSurfaceForm());
		    System.out.println("allFeaturesArray : " + Arrays.asList(token.getAllFeaturesArray()));
		    System.out.println("辞書にある言葉? : " + token.isKnown());
		    System.out.println("未知語? : " + token.isUnknown());
		    System.out.println("ユーザ定義? : " + token.isUser());
		}
	}
	
	
	
}
