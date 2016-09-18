import java.util.Arrays;
import java.util.List;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;

public class MorphAnal {
	String stat;
	Tokenizer tokenizer = Tokenizer.builder().build();
	List<Token> tokens;

	public MorphAnal(String stat){
		this.stat = stat;
		tokens = tokenizer.tokenize(stat);
	}

	public void EndMorphAnal() throws Throwable{
		this.finalize();
	}


	public void MorphAnalResult(){
		for (Token token : tokens) {
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

	public List<Token> MorphAnalStart(){
		return tokens;
	}

}
