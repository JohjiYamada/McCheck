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
	private static final String APPLICATION_NAME = "Mc Check";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

	private static final String CRE_STR = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

	
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
					.filter(rowList -> rowList.size() < 1)
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
