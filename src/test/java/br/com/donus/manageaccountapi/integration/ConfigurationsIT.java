package br.com.donus.manageaccountapi.integration;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationsIT {
	
	@Value( "${test.integration.username}" )
	private  String username;
	
	@Value( "${test.integration.password}" )
	private  String password;

	@SuppressWarnings("serial")
	public  HttpHeaders createHeaders(String username, String password){
		   return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64(
		            auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		      }};
		}
	
	public  HttpHeaders createHeadersWithAuthentication(){
		  return createHeaders(username, password);
		}
}