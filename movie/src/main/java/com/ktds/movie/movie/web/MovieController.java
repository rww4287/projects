package com.ktds.movie.movie.web;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.movie.movie.vo.MovieVO;

import javafx.scene.input.KeyCombination.ModifierValue;

@Controller 
public class MovieController {

	
	@ResponseBody

	@RequestMapping("/main")
	public ModelAndView viewMovieList() throws Exception{

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
	
		
		ModelAndView view = new ModelAndView();
		List<MovieVO> movieTitleList = getMovieTitleList();
		List<MovieVO> movieList = new ArrayList<MovieVO>();
		URL url = null;
		
		for ( int i = 0 ; i < movieTitleList.size() ; i ++ ){
			MovieVO movie = new MovieVO();
			String movieTitle = movieTitleList.get(i).getMovieName();
			
			String title = java.net.URLEncoder.encode(new String(movieTitle.getBytes("UTF-8")));
			
			String stringUrl = "https://apis.daum.net/contents/movie?apikey"
					  + "=97acb9cf470c84aa064b467671b7d156&q="+ title +"&output=json";
			
			url = new URL(stringUrl);
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			JSONObject object = (JSONObject)JSONValue.parse(isr);

/*			JSONObject jsonObject = (JSONObject)jsonParser.parse(readDaumAPIUrl(movieTitle));*/
			
			JSONObject channel = (JSONObject)object.get("channel");
	
			
			JSONArray itemArray = (JSONArray) channel.get("item");
			JSONObject json = (JSONObject)itemArray.get(0);
			
			JSONArray genreArray =  (JSONArray)json.get("genre");
			JSONArray resArray = (JSONArray)json.get("res");
			JSONArray actorArray = (JSONArray)json.get("actor");
			JSONArray storyArray = (JSONArray)json.get("story");
			JSONArray videoArray = (JSONArray)json.get("video");
			JSONArray openInfoArray = (JSONArray)json.get("open_info");
			JSONArray thumbnailArray = (JSONArray)json.get("thumbnail");
			JSONArray directorArray = (JSONArray)json.get("director");
			
			JSONObject res = (JSONObject)resArray.get(0);
			JSONObject video = (JSONObject)videoArray.get(0);
			JSONObject story = (JSONObject)storyArray.get(0);
			JSONObject openDate = (JSONObject)openInfoArray.get(0);
			JSONObject screeningGrade = (JSONObject)openInfoArray.get(1);
			JSONObject time = (JSONObject)openInfoArray.get(2);
			JSONObject thumbnail = (JSONObject)thumbnailArray.get(0);
			
			movie.setMovieId(movieTitleList.get(i).getMovieId());
			movie.setMovieName(movieTitleList.get(i).getMovieName());
			movie.setRank(i+1);
			movie.setAudiAcc(movieTitleList.get(i).getAudiAcc());
			
			
			List<String> genreList = new ArrayList<>();
			List<String> actorList = new ArrayList<>();
			List<String> directorList = new ArrayList<>();
			
			
			for ( int j = 0; j < genreArray.size(); j++){
				JSONObject genre = (JSONObject)genreArray.get(j);
				genreList.add((String)genre.get("content"));
			}
			
			
			for ( int j = 0; j < actorArray.size(); j++){
				JSONObject actor = (JSONObject)actorArray.get(j);
				actorList.add((String)actor.get("content"));
			}
		
			
			for ( int j = 0; j < directorArray.size(); j++){
				JSONObject director = (JSONObject)directorArray.get(j);
				directorList.add((String)director.get("content"));
				
			}
			String storyContent = (String)story.get("content");
			storyContent = storyContent.replaceAll("\"", "");
			
			movie.setGenre(genreList);
			movie.setActor(actorList);
			movie.setStory(storyContent);
			movie.setOpenDate((String)openDate.get("content"));
			movie.setScreeningGrade((String)screeningGrade.get("content"));
			movie.setTime((String)time.get("content"));
			movie.setDirector(directorList);
			movie.setThumbnail((String)thumbnail.get("content"));
			movie.setReservation((String)res.get("link"));
			movie.setVideo((String)video.get("content"));
			movie.setVideoLink((String)video.get("link"));
		
			movieList.add(movie);
			
		}
		
		
		
		view.setViewName("/movie/list");
		view.addObject("movieList",movieList);
		
		return view;
	}
	
	@RequestMapping("/movie/detail/{movieTitle}")
	public ModelAndView viewDetailPage(@PathVariable String movieTitle) throws Exception{
		
		ModelAndView view = new ModelAndView();
		MovieVO movie = new MovieVO();
		
		String title = java.net.URLEncoder.encode(new String(movieTitle.getBytes("UTF-8")));
		
		String stringUrl = "https://apis.daum.net/contents/movie?apikey"
				  + "=27b093042fd33b0bc12f314b951d029b&q="+ title +"&output=json";
		
		URL url = new URL(stringUrl);
		InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
		JSONObject object = (JSONObject)JSONValue.parse(isr);

		
		JSONObject channel = (JSONObject)object.get("channel");

		
		JSONArray itemArray = (JSONArray) channel.get("item");
		JSONObject json = (JSONObject)itemArray.get(0);
		
		JSONArray genreArray =  (JSONArray)json.get("genre");
		JSONArray actorArray = (JSONArray)json.get("actor");
		JSONArray storyArray = (JSONArray)json.get("story");
		JSONArray openInfoArray = (JSONArray)json.get("open_info");
		JSONArray thumbnailArray = (JSONArray)json.get("thumbnail");
		JSONArray directorArray = (JSONArray)json.get("director");
		
		JSONObject story = (JSONObject)storyArray.get(0);
		
		JSONObject openDate = (JSONObject)openInfoArray.get(0);
		JSONObject screeningGrade = (JSONObject)openInfoArray.get(1);
		JSONObject time = (JSONObject)openInfoArray.get(2);
		
		JSONObject thumbnail = (JSONObject)thumbnailArray.get(0);
		
		movie.setMovieName(movieTitle);
		
		
		List<String> genreList = new ArrayList<>();
		List<String> actorList = new ArrayList<>();
		List<String> directorList = new ArrayList<>();
		
		
		for ( int j = 0; j < genreArray.size(); j++){
			JSONObject genre = (JSONObject)genreArray.get(j);
			genreList.add((String)genre.get("content"));
		}
		
		
		for ( int j = 0; j < actorArray.size(); j++){
			JSONObject actor = (JSONObject)actorArray.get(j);
			actorList.add((String)actor.get("content"));
		}
	
		
		for ( int j = 0; j < directorArray.size(); j++){
			JSONObject director = (JSONObject)directorArray.get(j);
			directorList.add((String)director.get("content"));
			
		}
		
		movie.setGenre(genreList);
		movie.setActor(actorList);
		movie.setStory((String)story.get("content"));
		movie.setOpenDate((String)openDate.get("content"));
		movie.setScreeningGrade((String)screeningGrade.get("content"));
		movie.setTime((String)time.get("content"));
		movie.setDirector(directorList);
		movie.setThumbnail((String)thumbnail.get("content"));
		
		view.setViewName("/movie/detail");
		view.addObject("movie",movie);
		
		return view;
	}
	
	public List<MovieVO> getMovieTitleList() throws Exception{

		List<MovieVO> movieTitleList = new ArrayList<MovieVO>();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(readMovieTitleUrl());
		JSONObject json = (JSONObject)jsonObject.get("boxOfficeResult");
		JSONArray array = (JSONArray)json.get("dailyBoxOfficeList");
		
		
		MovieVO movie = null;
		for(int i=0; i< array.size(); i++){
			movie = new MovieVO();
			JSONObject entity = (JSONObject)array.get(i);
			String audiAcc = (String)entity.get("audiAcc");
			int audiAccInt = Integer.parseInt(audiAcc);
			
			DecimalFormat df = new DecimalFormat("#,###");
			audiAcc = df.format(audiAccInt);
			audiAcc = audiAcc + "명";
		
			
			movie.setMovieId((String)entity.get("movieCd"));
			movie.setMovieName((String)entity.get("movieNm"));
			movie.setAudiAcc(audiAcc);
			
			movieTitleList.add(movie);
		}
		
		
		return movieTitleList;
	}
	
	
	
	private static String readMovieTitleUrl() throws Exception {
		BufferedInputStream reader = null;

		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat simpleformat = new SimpleDateFormat("yyyyMMdd");
		String date = simpleformat.format(calendar.getTime());

		  try {
			  URL url = new URL(
					  "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/" 
						+ "searchDailyBoxOfficeList.json"
						+ "?key=430156241533f1d058c603178cc3ca0e"
						+ "&targetDt="+date+"&itemPerPage=9");
			
			  	reader = new BufferedInputStream(url.openStream());
			  	StringBuffer buffer = new StringBuffer();
			  	int i;
			  	byte[] b = new byte[4096];
			  	
			  	while( (i = reader.read(b)) != -1){
			  		buffer.append(new String(b, 0, i));
			  	}
			  	
			  	return buffer.toString();
			  	
		  } finally {
			  if (reader != null)
			  reader.close();
		  }
	  }
	
	
	/*private static String readDaumAPIUrl(String movieTitle) throws Exception {
		BufferedInputStream reader = null;
		System.out.println("param:"+movieTitle);
		  try {
			  URL url = new URL(
					  "https://apis.daum.net/contents/movie?apikey"
					  + "=d593272c36004deb3c8caa223a9d8c60&q="+movieTitle+"&output=json");

			  	reader = new BufferedInputStream(url.openStream());
			  	StringBuffer buffer = new StringBuffer();
			  	int i;
			  	byte[] b = new byte[4096];
			  	
			  	while( (i = reader.read(b)) != -1){
			  		buffer.append(new String(b, 0, i));
			  	}
			  	
			  	System.out.println("@"+buffer.toString());
			  	return buffer.toString();
			  	
		  } finally {
			  if (reader != null)
			  reader.close();
		  }
	  }*/
}
