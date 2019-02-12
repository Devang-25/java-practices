
public class Test {

/*
	 const https = require('https');
	
	https.get('https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY', (resp) => {
	  let data = '';
	
	  // A chunk of data has been recieved.
	  resp.on('data', (chunk) => {
	    data += chunk;
	  });
	
	  // The whole response has been received. Print out the result.
	  resp.on('end', () => {
	    console.log(JSON.parse(data).explanation);
	  });
	
	}).on("error", (err) => {
	  console.log("Error: " + err.message);
	});
 */

//	static public String MakeRequest(String url)
//	{
//
//	 HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
//	    request.ContentType = "application/json; charset=utf-8";
//	    request.PreAuthenticate = true;
//
//	 HttpWebResponse response = request.GetResponse() as HttpWebResponse;
//	    using (Stream responseStream = response.GetResponseStream())
//	    {
//	        StreamReader reader = new StreamReader(responseStream, Encoding.UTF8);
//	        return (reader.ReadToEnd());
//
//	    }
//
//	}
//
//	public static void main(String[] args) {
//		String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman&page=1";
//		String res = MakeRequest(url);
//	}
}