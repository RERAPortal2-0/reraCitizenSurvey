package in.gov.rera.citizen.survey.security;



public class StrEncUtill {
    
	static final String indexString="1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final String[] keyArr={
			"roNOPQRSTUVxyzABCLMWXYZuvwcdefghijklmnFGHIstJKbpq1234567890aDE",
			"yzABCDEFGHINOPWX1bcd56789VYZQRSTUqrsJ234txijklmnop0aKLMuvwefgh",
			"TDE10abcdMghijVWpqrNOPUefxyGHIJKLzAFBCo9RS456stuvQ23klmn78XwYZ",
			"890abcdefghijklmnopqrswxyzABCDEFGHIJKLMNOYZPQ1234567tuvRSTUVWX",
			"tuvwxyzABCDEFGHIY67890abZUVWXlmnopqrs12345cdefghijkJKLMNOPQRST",
			"uvLMNOPQRSyTUVWJK9abcdefgh1234zABCDE5678wxijklst0mnXYZFGHIopqr",
			"ZKLMN7890afghijklmnopqrstuvwxyzABCDEOPQR123456FGHIJbcdeSTUVWXY",
			"TUVWqrstuvwxyzABCDEFGH1234XYZ7890abcdefgIJKLMNhijklmnop56OPQRS",
			"mn45GHIJKLMfghijklvw67890abcdetuxyzArsNOPQRSTUVBopqWXYZ123CDEF",
			"stu56YZwxyzABCDEFGHIbcJKLM7890avdefghijklmnopqrRSTUVWXNOPQ1234",
			"KLMNOPQR1234lmnopqrsfghijkTUVWXYZ567890atuvwxyzABCDEFGHIJbcdeS",
			"OPQRSTUVWXYZ7890ahijklmno56bcdefgIJKLMNpqrstuvwxyzABCDEFGH1234",
			"sNOPQRSTUVBCDEFGHIJKLMftuvwghijklmnopqxyzArWXYZ1234567890abcde",
			"ijklmnop0aKzABCDEFGHIQRSTUqrsJ234LMuvwefghNOPWX1bcdytx56789VYZ",
			"BCo9RabcdefxyGHIJKLzAFMghijVWpqS456stuvQ23klmn78XwYZTDE10rNOPU",
			"uvwcqroxyzABCQRSTUVWXdefghijklmnFGHIJKbpYZ1234567890aDLMNOPEst",
			"ijkrsJ234tx5678nop0aKLMuvwefghNOPWX1bcdZyzlmGHIQRSTUqABCDEF9VY",
			"B23klmn78XwYZTDE10abcdefxyGHICo9RS45LzAFMghijVWpqrNO6stuvQJKPU",
			"wxghijklmnstuvRSTUVyzABCDEFGHIJKLMNOPQ1234567890abcopqrdefWXYZ",
			"aqrs123456fghijkJKLbcdeVWXlmnopIYZU7890MNOPQRSTtuvwxyzABCDEFGH",
			"rabcdefghijklstuE5678wxyTUVWJK90mnXYZFGHIopqRS1234vLMNOPQzABCD",
			"4567klmnopqrstuvwxyzABCDEFGfghijOPQR123HIJbcdeSTUVWXYZKLMN890a",
			"hWXYZ7890abcdefgIJKijklmnopqrstuvwxyzABCDEFGH123456OPQRSTUVLMN",
			"xMfghijklm0abcdetuvwnopqWXYyzArsNOPQRSTUVBCDEFGHIJKLZ123456789",
			"stuSTUVWXYZJKLIbcdefghijklmnopqrR67890aMNOPQ12345vwxyzABCDEFGH",
			"qroxyzABCLMNOPQRFGHIJKbpZ1234567890aDEstSTUVWXYuvwcdefghijklmn",
			"BCDEFGHIQRcdyzA56789VYZSTUqrsJ234txijklmnop0aKLMuvwefghNOPWX1b",
			"S456stuvQ23klmnDE10abcdefxyGHIBCo9RJKLzAFMghijVWpqrNOPU78XwYZT",
			"CDEFGHIJKLMNOPQuvRSTUVWXYZ1234567890abcdewxyzABfghijklmnopqrst",
			"bcqrs1234567890afghijkJKLMdeUVWXlmnopNOPQRSTtuvwxyzABCDEFGHIYZ",
			"zopqra34jklstuvLMNOPQABCDbcdefghi90mnXYZFGHIRS12E5678wxyTUVWJK",
			"wabcdefghijklmnopqrstuvRSTUVWXYZxyzABCDEFGHIJKLMNOPQ1234567890",
			"bcHIYZUVWXlmnopqrs1234567defghijkJKLMNOPQRSTtuvwxyzABCDEFG890a",
			"XYZFGHIopqrabcdefghijklstzABCDE5678wxyTUVWJK90mnuvLMNOPQRS1234",
			"HIJbcdeSTUVWXYZKLMNOPQR12345fghijklmnopqrstuvwxyzABCDEFG67890a",
			"vwxyzABCDEFGH123456OPQRSTUVhijklmnopqrstuWXYZ7890abcdefgIJKLMN",
			"KLMfghijklmnopqWXYZ123xyzArsNOPQRSTUVBCDEFGHIJ4567890abcdetuvw",
			"stuvwxyzABCJKLMNOPQ123456HIbcdefghiDEFGWXYZjklmnopqrRSTUV7890a",
			"BCDEFGHIbcde7890ajklmnopqrRSTUVWXYZfghi123456JstuvwxyzAKLMNOPQ",
			"hijklmnFGHIJKbpqrox7890aDEstyzABCLMNOPQRSTUuvwcdefgVWXYZ123456"
			};
	
	
	
	 public static String decrypt(String str){
		 
		 
		 str=revertTxt(str);
		 String s=str.substring(0,2);
		 int idx=Integer.parseInt(s);
		 str=str.substring(2);
		 str=revertTxt(str,idx);
			    String result = "";
			    for (int i = 0; i < str.length(); i+=3) {
			      String hex =  str.substring(i+1, i+3);
			      result += (char) (Integer.parseInt(hex, 16) ^ (Integer.parseInt(String.valueOf(str.charAt(i)))));
			    }
			    return result;
			  }

	 
	 private static String replaceTxt(String orgtxt,int idx){
		
		 StringBuilder sb=new StringBuilder();
		 char[] ch=orgtxt.toCharArray();
		 int xx=0;
		 for(char c:ch){
		sb.append(keyArr[xx].charAt(indexString.indexOf(""+c)))	 ;
		xx++;
		if(xx>31)xx=0;
		
		 }
		return sb.toString();
	 }
	 public static String encrypt(String str){
		    int code;
		    String result = "";
		    for (int i = 0; i < str.length(); i++) {
		      code = Math.round((float) Math.random()*8+1);
		      result += code + Integer.toHexString( ((int) str.charAt(i) ) ^ code );
		    }
		int idx=    Math.round((float) Math.random()*28);
		result=replaceTxt(result,idx);
	str=""+	idx;
	if(str.length()<2) str="0"+str;
		    return replaceTxt(str+result);
		  }
	 
	 private static String replaceTxt(String orgtxt){
		
		 StringBuilder sb=new StringBuilder();
		 char[] ch=orgtxt.toCharArray();
		 int xx=0;
		 for(char c:ch){
		sb.append(keyArr[xx].charAt(indexString.indexOf(""+c)))	 ;
		xx++;
		if(xx>31)xx=0;
		
		 }
		return sb.toString();
	 }
	 private static String revertTxt(String enctxt){
		 StringBuilder sb=new StringBuilder();
		 char[] ch=enctxt.toCharArray();
		 int xx=0;
		 for(char c:ch){
		sb.append(indexString.charAt(keyArr[xx].indexOf(""+c)))	 ;
		xx++;
		if(xx>31)xx=0;
		
		 }
		return sb.toString();
	 }
	 private static String revertTxt(String enctxt,int idx){
		 StringBuilder sb=new StringBuilder();
		 char[] ch=enctxt.toCharArray();
		 int xx=0;
		 for(char c:ch){
		sb.append(indexString.charAt(keyArr[xx].indexOf(""+c)))	 ;
		xx++;
		if(xx>31)xx=0;
		
		 }
		return sb.toString();
	 }
	 
	 public static void main(String[] args) {
		
			
		 for(int x=0;x<50;x++){
			 String pass="gjhgjh$%$#43434";
			 pass=encrypt(pass);
				System.out.println(pass);
				System.err.println(decrypt(pass));
		 }
		
	}
}
