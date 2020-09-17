package sg.com.Johji;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class SpreadSheetAccesser {
	private static final String APPLICATION_NAME = "mc-check";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

	private static final String CRE_STR = 
//			"{\r\n" + 
//			"  \"type\": \"service_account\",\r\n" + 
//			"  \"project_id\": \"eastern-rider-288508\",\r\n" + 
//			"  \"private_key_id\": \"c85f13d5e510f281c2c35460131e825b6d9270a0\",\r\n" + 
//			"  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC14H/EHklB5YTO\\nLNHc9a8tGoVMbsag+nMCJ65eChC/FCivmZLNsBkFoNH6aKTV8aDTiPCeT2bC1i16\\nocD71AxNXHAJLO4GVdlc60NhANcef9KN6Z3+lzr8iKUFPTWjhTNVf+Zxm2CKinyx\\n76Z/IRV4VGIpPC7gtpHModnXTVGd4KvlosRBQ3ghUifrHtJlPeMKoxEg0x9yFe+A\\njJplqVjq7AuIUfQQ5GZ1XF+JPwhnM5l20OkHuHIHjmbnYdwHmPGPkZ+CjdJ5h44Z\\n7mktIhKcFDCqEl5WVWeOIl/PGBqnmbahNdIUCPdHcBuRlgo+YkUIMnfm9+dnbmIp\\nhLlUg4bDAgMBAAECggEAGog4C0uhwzew0oTuTo4Wsn06ceZ9GnG+wlM4mCZewAk4\\nUDmWlWP5mn0V9LI8TauUmxdQhr+tjYHCG7AqH3KVItn3zBbeWFVAwtHdP/Ln7t6V\\nwDcS4dd89ucWL7f4sbSWQwRIAU253SwLucdsogIZCDdDJKHPct+fUFtSFJsGa4Hi\\nfQH50pTdOAK8lbjEeyY8Yqie9JZxB4dBon7cfQl/eCgLg/Xvgu7dFBcbgIpasNmN\\n2kzdFL53FwdmNR9zYWTTsouvWOnmYRu7PBCSJAfjEhOcQK5SM93Y99IY06ORpeeC\\neVUj855u2yTAW5dwEdNGnMoaQcY3HGbVD9/1YGbMlQKBgQDhlBn5WbtRJDUzQHCK\\nviK1OMLnrviagfQgN5yR+A5J03q9VfZtvnQrVjNI8BNjv8gHCXEfWnJPTJt1qF4D\\ne8kxv6hBlYCQWrQO2Bt0lFTtjzKxzy1o1Sta3u8wAnP2NBnZoODSBWZWfGS829f0\\nqWyToHrRDjmI8ra+auINXoU0jwKBgQDOZ6O8EYoqeRq+fCIZAn1eZVXliHgqMS3C\\n3fpGhr/gfuhuV5ePIpdukxzV3IEzz5egut+7ETQzGr5RzYfQwhwB3FNn84ZxQsVn\\nPmbdf8cTugdIP9B25gtvRa8ej+oCRXBnJbIwfVIHzTXgzzZ3+s9BqeP+Mi3UpVGe\\n7sQprpMsjQKBgE7k5mSwPnQ1jqm7KuFLeIYbMQeoyWf4dXJjIM5ojE7DuRTJIOPn\\nDWguwMcMuCtV4tabNcY6cupLJ7/xv9fGDmro1icB67IRFxJuAMQ4MK/ZOmi3xfD5\\nRS2F9O+8m+QK+tODkb3Ch7VN0km/Nftq2f+TzACb0gGUqddVOHRmj33/AoGAMnxL\\nIk8v+l+Q2RLHzFI9k/85WWFd8wQAa2+cLe/7ss+XzpWCLrKJU7SMEwtSVvfp4JiU\\n7M6yc+WQs7WQCNHRw+q+Q+je84kfe7dZtcfxS7Pu4A8r0ElIdrkWsmUFGfUeHzyr\\nLw0YKWxaR1invAUO+MKzT5O6fg7JvhQsyv02ZmkCgYEAvtgkz5AoRS0GtcmUwiLb\\nLGWtJvgkp0+n9pCTbexEkAHSWWNPFMVn00fzGg3YLS6ko1c30a1tpeqbfrfA0O2f\\nENfIJ41gs3qQWELOm3J4BlNCAbNgzlsAp/WiNdjhCLQ78L1JxfMoDe36t200ZZxZ\\n0wbDdUkWcLNxjdSbWCAabRs=\\n-----END PRIVATE KEY-----\\n\",\r\n" + 
//			"  \"client_email\": \"mc-checker@eastern-rider-288508.iam.gserviceaccount.com\",\r\n" + 
//			"  \"client_id\": \"102365287226709599335\",\r\n" + 
//			"  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n" + 
//			"  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n" + 
//			"  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n" + 
//			"  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/mc-checker%40eastern-rider-288508.iam.gserviceaccount.com\"\r\n" + 
//			"}\r\n" + 
//			"";
			System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

	
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		InputStream is = new ByteArrayInputStream(CRE_STR.getBytes());
		Credential credential = GoogleCredential.fromStream(is).createScoped(SCOPES);
		return credential;
	}

	public static List<String> getMembers() throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1jR3wSMGHiGXycvG7fNRKZJfBrM4NB3t5De8grEMtNFo";
		final String range = "Staus Summary!D2:D30";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			return Collections.emptyList();
		} else {
			return values.stream()
					.filter(rowList -> rowList.size() > 0)
					.map(rowList -> (String) rowList.get(0)).collect(Collectors.toList());
		}
	}
	
//	public static String getHelpHtml() throws IOException, GeneralSecurityException {
//		// Build a new authorized API client service.
//		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//		final String spreadsheetId = "1ZwShgbArTsYaQkJ7DTpAJmiYsjniEJQuYxW_QOsaUfg";
//		final String range = "Help!A1:A1";
//		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//				.setApplicationName(APPLICATION_NAME).build();
//		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
//		List<List<Object>> values = response.getValues();
//		if (values == null || values.isEmpty()) {
//			return "";
//		} else {
//			return (String) values.get(0).get(0);
//		}
//	}	
}
