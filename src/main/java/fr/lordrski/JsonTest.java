package fr.lordrski;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
	
	private String str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20;
	
	public JsonTest() {}

	public JsonTest(String str, String str2, String str3, String str4,
			String str5, String str6, String str7, String str8, String str9,
			String str10, String str11, String str12, String str13,
			String str14, String str15, String str16, String str17,
			String str18, String str19, String str20) {
		this.str = str;
		this.str2 = str2;
		this.str3 = str3;
		this.str4 = str4;
		this.str5 = str5;
		this.str6 = str6;
		this.str7 = str7;
		this.str8 = str8;
		this.str9 = str9;
		this.str10 = str10;
		this.str11 = str11;
		this.str12 = str12;
		this.str13 = str13;
		this.str14 = str14;
		this.str15 = str15;
		this.str16 = str16;
		this.str17 = str17;
		this.str18 = str18;
		this.str19 = str19;
		this.str20 = str20;
	}

	public static void main(String[] args) {
		int length = 2000;
		List<JsonTest> json = new ArrayList<>();
		for (int i=0; i<length; i++) {
			json.add(new JsonTest("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"));
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new FileOutputStream("test.json"), json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public String getStr4() {
		return str4;
	}

	public void setStr4(String str4) {
		this.str4 = str4;
	}

	public String getStr5() {
		return str5;
	}

	public void setStr5(String str5) {
		this.str5 = str5;
	}

	public String getStr6() {
		return str6;
	}

	public void setStr6(String str6) {
		this.str6 = str6;
	}

	public String getStr7() {
		return str7;
	}

	public void setStr7(String str7) {
		this.str7 = str7;
	}

	public String getStr8() {
		return str8;
	}

	public void setStr8(String str8) {
		this.str8 = str8;
	}

	public String getStr9() {
		return str9;
	}

	public void setStr9(String str9) {
		this.str9 = str9;
	}

	public String getStr10() {
		return str10;
	}

	public void setStr10(String str10) {
		this.str10 = str10;
	}

	public String getStr11() {
		return str11;
	}

	public void setStr11(String str11) {
		this.str11 = str11;
	}

	public String getStr12() {
		return str12;
	}

	public void setStr12(String str12) {
		this.str12 = str12;
	}

	public String getStr13() {
		return str13;
	}

	public void setStr13(String str13) {
		this.str13 = str13;
	}

	public String getStr14() {
		return str14;
	}

	public void setStr14(String str14) {
		this.str14 = str14;
	}

	public String getStr15() {
		return str15;
	}

	public void setStr15(String str15) {
		this.str15 = str15;
	}

	public String getStr16() {
		return str16;
	}

	public void setStr16(String str16) {
		this.str16 = str16;
	}

	public String getStr17() {
		return str17;
	}

	public void setStr17(String str17) {
		this.str17 = str17;
	}

	public String getStr18() {
		return str18;
	}

	public void setStr18(String str18) {
		this.str18 = str18;
	}

	public String getStr19() {
		return str19;
	}

	public void setStr19(String str19) {
		this.str19 = str19;
	}

	public String getStr20() {
		return str20;
	}

	public void setStr20(String str20) {
		this.str20 = str20;
	}

}
