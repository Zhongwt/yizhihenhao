//package com.yzhh.backstage.api.util.word;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//
//import com.deepoove.poi.XWPFTemplate;
//import com.deepoove.poi.data.PictureRenderData;
//import com.deepoove.poi.data.TextRenderData;
//
//public class WordUtiles {
//	public static void makeDocx() throws IOException {
//		XWPFTemplate template = XWPFTemplate.compile("D:/code/test/template.docx").render(new HashMap<String, Object>() {
//			{
//				put("title", "Poi-tl 模板引擎");
//				put("urlPicture", new PictureRenderData(100, 100,".png", BytePictureUtils.getUrlByteArray("https://avatars3.githubusercontent.com/u/1394854")));
//				put("author", new TextRenderData("逗逼"));
//				
//			}
//		});
//		FileOutputStream out = new FileOutputStream("D:/code/test/out_template.docx");
//		template.write(out);
//		out.flush();
//		out.close();
//		template.close();
//	}
//	
//	public static void main(String[] args) throws IOException {
//		WordUtiles.makeDocx();
//		System.out.println("success");
//	}
//}
