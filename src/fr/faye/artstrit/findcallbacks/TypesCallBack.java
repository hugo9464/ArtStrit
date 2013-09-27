//package fr.faye.artstrit.findcallbacks;
//
//import java.util.List;
//
//import android.util.Log;
//
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//
//import fr.faye.artstrit.models.Artist;
//
//
//public class TypesCallBack extends FindCallback<ParseObject> {
//
//	
//	private final String TAG;
//
//	public TypesCallBack( ){
//		TAG = getClass().getSimpleName();
//	}
//	
//	@Override
//	public void done(List<ParseObject> typesList, ParseException e) {
//		 if (e == null) {
//	        
//	            for(int i=0 ; i<typesList.size() ; i++){
//	            	ParseQuery<ParseObject> artists_query = ParseQuery.getQuery("artists");
//	            	artists_query.whereEqualTo(Artist.TYPE, typesList.get(i).getString(Artist.TYPE));
//	        		artists_query.findInBackground(new ArtistsCallBack(context));
//	            }
//
//	        } else {
//	            Log.i(TAG, "Error: " + e.getMessage());
//	        }
//	}
//
//}
