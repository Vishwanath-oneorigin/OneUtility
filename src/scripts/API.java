package scripts;

import org.testng.annotations.Test;

public class API extends One {
	
	@Test
	public void test() throws Exception
	{
		String token;
		
		String body1 = "{"+  
				"\"accnt_key\": \"oneorigin\", \r\n" + 
				"\"email\": \"mrudula-sa-jku@oneorigin.us\", \r\n" + 
				"\"password\": \"Tester@1\"}"; 
		
		String body2 = "{\r\n"
				+ "   \"file\":\"qa/sia-jku-qa/__1687249789849__UCF - 2.pdf\",\r\n"
				+ "   \"action\":\"college-transfers\"\r\n"
				+ "}";

//		token= "token_transcriptqa=s%3AeyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Im1ydWR1bGEtc2Etamt1QG9uZW9yaWdpbi51cyIsImFjY250X2tleSI6Im9uZW9yaWdpbiIsInVzZXJfcm9sZSI6InN1cGVyX2FkbWluIiwic2Vzc2lvbklkIjoiY2MwNWU5MjUtZDU3OS01ODlmLTkzN2UtZTA2MTA4MDEyMmRhIiwiaWF0IjoxNjg3MTYxNTkxfQ.GPmLAb31zk1jT2zgpQAojZOhRPjj02V3ugJNlSAjLNQ.HMb218MHxKQtZ4RcDisxSG2xbYKLg7Z0yFvW%2F2zR%2FO0; Path=/; HttpOnly; Expires=Thu, 22 Jun 2023 07:59:54 GMT;";
		
		token = POSTLogin("https://api.oneorigin.us/v2-q-ai/transcripts/verify_user", body1);
		System.out.println("return token: "+token);
		System.out.println("-______--______--_________--______");
		POSTtpDetails("https://api.oneorigin.us/v2-q-ai/transcripts/details", body2, token);

	}
	
}
