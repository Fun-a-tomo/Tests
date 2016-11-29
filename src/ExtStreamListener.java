import java.io.IOException;
import java.util.Date;

import twitter4j.GeoLocation;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.User;

public class ExtStreamListener extends TweetBase implements  StatusListener {
	String account="";
	public ExtStreamListener() throws TwitterException, IOException {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public ExtStreamListener(String conf) throws TwitterException, IOException {
		super();
		account = conf;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void onException(Exception arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onStatus(Status arg0) {
		int i = 0;
		// TODO 自動生成されたメソッド・スタブ
		/*
		try {
			stat = twi.updateStatus("enjoy step"+i);
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		*/
        Double lat = null;
        Double lng = null;
        String[] urls = null;
        String[] medias = null;
        Status status = arg0;
        User user = status.getUser();
        
	    //. 位置情報が含まれていれば取得する
	    GeoLocation location = status.getGeoLocation();
	    if( location != null ){
	        double dlat = location.getLatitude();
	        double dlng = location.getLongitude();
	        lat = dlat;
	        lng = dlng;
	    }
        
	    long id = status.getId(); //. ツイートID
	    String text = status.getText(); //. ツイート本文
	    long userid = user.getId(); //. ユーザーID
	    String username = user.getScreenName(); //. ユーザー表示名
	    Date created = status.getCreatedAt(); //. ツイート日時

	    try{
        //自ツイートは無視
        	if(containSelfy(text)){
        		
        	}
        	else if(!user.getScreenName().equals(twi.getScreenName())){

			    System.out.println( "id = " + id + ", username = " + username + ", text = " + text );
				if(RandomResult(5)){
						Reply(status);
			    }
			}
	    }catch(Throwable e){
	    	new Throwable(e);
	    }

	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	/*
	public void Tweet(String twit){

	}
	*/
	private void Reply(Status stat) throws Throwable{
		MorphAnal ma = new MorphAnal(stat.getText());
		String result;
		String tweet;
		RepMessage rm = new RepMessage(ma.MorphAnalStart());
		result = rm.Message();
		ma.EndMorphAnal();
		if(!result.isEmpty()){
			tweet="@"+stat.getUser().getScreenName()+" "+stat.getUser().getName()+"、"+result+"んかワレ！";

			twi.updateStatus(new StatusUpdate(tweet).inReplyToStatusId(stat.getId()));
		}
	}
	
	private boolean containSelfy(String twit) throws IllegalStateException, TwitterException {
		return twit.contains("@"+twi.getScreenName());
	}
}

