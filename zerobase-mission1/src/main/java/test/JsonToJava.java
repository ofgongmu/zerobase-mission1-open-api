package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import test.DAO;

public class JsonToJava {
	
	public static void main(String[] args) {
		
		String result = "";
		
		try {
			DAO dao = new DAO();
			URL url = new URL("http://openapi.seoul.go.kr:8088/50534c754f6f66673431627a705148/json/TbPublicWifiInfo/1/5/");
			
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
			
			JSONParser parser = new JSONParser();
			
			
			JSONObject obj = (JSONObject)parser.parse(result);
			JSONObject info = (JSONObject)obj.get("TbPublicWifiInfo");
			JSONArray row = (JSONArray)info.get("row");

			
			for(int i = 0; i < row.size(); i++) {
				JSONObject temp = (JSONObject)row.get(i);
				String manNum = (String) temp.get("X_SWIFI_MGR_NO");
				float dist = 0; //(float) temp.get("");
				String locGu = (String) temp.get("X_SWIFI_WRDOFC");
				String wifiName = (String) temp.get("X_SWIFI_MAIN_NM");
				String locAd = (String) temp.get("X_SWIFI_ADRES1");
				String locAd2 = (String) temp.get("X_SWIFI_ADRES2");
				String locFloor = (String) temp.get("X_SWIFI_INSTL_FLOOR");
				String instType = (String) temp.get("X_SWIFI_INSTL_TY");
				String instAd = (String) temp.get("X_SWIFI_INSTL_MBY");
				String service = (String) temp.get("X_SWIFI_SVC_SE");
				String netType = (String) temp.get("X_SWIFI_CMCWR");
				String instYear = (String) temp.get("X_SWIFI_CNSTC_YEAR");
				String inOut = (String) temp.get("X_SWIFI_INOUT_DOOR");
				String envir = (String) temp.get("X_SWIFI_REMARS3");
				String coorX = (String) temp.get("LAT");
				String coorY = (String) temp.get("LNT");
				String workDt = (String) temp.get("WORK_DTTM");
				
				DTO dto = new DTO(manNum, dist, locGu, wifiName, locAd, locAd2, locFloor,
						instType, instAd, service, netType, instYear, inOut, envir,
						coorX, coorY, workDt); 
				
				dao.load(dto);
			}
			
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		;
		
		
	}

}