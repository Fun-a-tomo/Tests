import java.util.List;

import org.atilika.kuromoji.Token;

public class RepMessage {
	List<Token> tokens;

	public RepMessage(List<Token> token){
		this.tokens = token;
	}

	public String Message(){
		String res = "";
		boolean v = false;
		if(tokens.isEmpty())return res;
		for(Token token : tokens){
			if(token.getPartOfSpeech().contains("動詞,自立")){
				res+=token.getSurfaceForm();
				v = true;
			}
			if((token.getPartOfSpeech().contains("動詞,非自立")) ||(token.getPartOfSpeech().contains("助動詞")) && v){
				res+=token.getSurfaceForm();
			}
			if(!token.getPartOfSpeech().contains("動詞")&&!token.getPartOfSpeech().contains("助動詞")){
				if(v)v=false;
			}
			if(!res.isEmpty() && !v){
				break;
			}
		}
		return res;
	}

}
