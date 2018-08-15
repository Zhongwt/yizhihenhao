//package com.yzhh.backstage.api.util.word;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigInteger;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
//import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.apache.poi.xwpf.usermodel.XWPFTable;
//import org.apache.poi.xwpf.usermodel.XWPFTableCell;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
//
//import com.yzhh.backstage.api.dto.resume.ResumeDTO;
//
//public class WordUtil {
//	
//	//public static final String path = "/home/yzhh/file/";
//	public static final String path = "D:/code/test/";
//
//	//生成doc
//	public static void makeDoc(ResumeDTO resumeDTO,String fileName) throws IOException {
//		// Blank Document
//		XWPFDocument document = new XWPFDocument();
//
//		// Write the Document in file system
//		FileOutputStream out = new FileOutputStream(new File(path+fileName));
//
//		// 添加名字
//		XWPFParagraph paragraph = document.createParagraph();
//		// 设置段落居中
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//
//		XWPFRun run = paragraph.createRun();
//		run.setText("熊早早 男");
//		// titleParagraphRun.setColor("000000");
//		run.setFontSize(18);
//
//		// 学校
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		run = paragraph.createRun();
//		run.setText("北京 | 东北农业大学 | 本科");
//		// run.setColor("696969");
//		run.setFontSize(14);
//
//		// 学校
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		run = paragraph.createRun();
//		run.setText("电话 ：132****7500 | 邮箱 ：7**********@qq.com");
//		// run.setColor("696969");
//		run.setFontSize(14);
//
//		// 换行
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.setText("\r");
//
//		// 实习期望title
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		run = paragraph.createRun();
//		run.setText("实习期望");
//		// run.setColor("696969");
//		run.setFontSize(12);
//
//		// 基本信息表格
//		XWPFTable table = document.createTable();
//		// 去表格边框
//		table.getCTTbl().getTblPr().unsetTblBorders();
//		// 列宽自动分割
//		CTTblWidth tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
//		tableWidth.setType(STTblWidth.DXA);
//		tableWidth.setW(BigInteger.valueOf(9072));
//		XWPFTableRow tableRowOne = table.getRow(0);
//		tableRowOne.getCell(0).setText("产品经历 | 北京 | 500/天 | 2天 | 实习期");
//		tableRowOne.addNewTableCell().setText("2018-07-30");
//
//		// 换行
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.setText("\r");
//
//		// 教育背景title
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		run = paragraph.createRun();
//		run.setText("教育背景");
//		run.setFontSize(12);
//
//		table = document.createTable();
//		table.getCTTbl().getTblPr().unsetTblBorders();
//		tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
//		tableWidth.setType(STTblWidth.DXA);
//		tableWidth.setW(BigInteger.valueOf(9072));
//		tableRowOne = table.getRow(0);
//		tableRowOne.getCell(0).setText("东北农业大学 | 计算机 | 背景 | 本科");
//		XWPFTableCell rightCell = tableRowOne.addNewTableCell();
//		rightCell.setText("2014.04-2018.09");
//		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  
//
//		tableRowOne = table.createRow();
//		tableRowOne.getCell(0).setText("清华大学 | 计算机 | 背景 | 本科");
//		rightCell = tableRowOne.getCell(1);
//		rightCell.setText("2014.04-2018.09");
//		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  
//
//		// 换行
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.setText("\r");
//
//		// 实习经历title
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		run = paragraph.createRun();
//		run.setText("实习经历");
//		run.setFontSize(12);
//
//		// 实习经历
//		table = document.createTable();
//		table.getCTTbl().getTblPr().unsetTblBorders();
//		tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
//		tableWidth.setType(STTblWidth.DXA);
//		tableWidth.setW(BigInteger.valueOf(9072));
//		tableRowOne = table.getRow(0);
//		tableRowOne.getCell(0).setText("天下科技 | 产品经历 | 北京");
//		rightCell = tableRowOne.addNewTableCell();
//		rightCell.setText("2017.03.20-2018.04.20");
//		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  
//
//		// 换行
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.setText("\r");
//
//		// 实习经历
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.LEFT);
//		run = paragraph.createRun();
//		run.setText("实习尽力描述我希望在实习中能够更加了解公司的制度、正以后铺平道路；也帮助我把学校中学习到得书本知识运用到现实项目，将学校学习到的综合能力发挥在实际");
//		run.setFontSize(14);
//
//		// 换行
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.setText("\r");
//
//		// 实习经历
//		table = document.createTable();
//		table.getCTTbl().getTblPr().unsetTblBorders();
//		tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
//		tableWidth.setType(STTblWidth.DXA);
//		tableWidth.setW(BigInteger.valueOf(9072));
//		tableRowOne = table.getRow(0);
//		tableRowOne.getCell(0).setText("天下科技 | 产品经历 | 北京");
//		rightCell = tableRowOne.addNewTableCell();
//		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  
//		rightCell.setText("2017.03.20-2018.04.20");
//
//		// 换行
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.setText("\r");
//
//		// 实习经历
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.LEFT);
//		run = paragraph.createRun();
//		run.setText("实习尽力描述我希望在实习中能够更加了解公司的制度、正以后铺平道路；也帮助我把学校中学习到得书本知识运用到现实项目，将学校学习到的综合能力发挥在实际");
//		run.setFontSize(14);
//
//		// 换行
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.setText("\r");
//
//		// 技能爱好title
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		run = paragraph.createRun();
//		run.setText("技能爱好");
//		run.setFontSize(12);
//
//		// 技能爱好
//		table = document.createTable();
//		table.getCTTbl().getTblPr().unsetTblBorders();
//		tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
//		tableWidth.setType(STTblWidth.DXA);
//		tableWidth.setW(BigInteger.valueOf(9072));
//		tableRowOne = table.getRow(0);
//		tableRowOne.getCell(0).setText("PS软件");
//		rightCell = tableRowOne.addNewTableCell();
//		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  
//		rightCell.setText("精通");
//
//		tableRowOne = table.createRow();
//		tableRowOne.getCell(0).setText("PS软件");
//		rightCell = tableRowOne.getCell(1);
//		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  
//		rightCell.setText("精通");
//
//		// 换行
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.setText("\r");
//
//		// 作品展示title
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		run = paragraph.createRun();
//		run.setText("作品展示");
//		run.setFontSize(12);
//
//		// 作品展示
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.LEFT);
//		run = paragraph.createRun();
//		run.setText("www.baidu.com");
//		run.setFontSize(14);
//
//		// 换行
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.setText("\r");
//
//		// 作品展示
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.LEFT);
//		run = paragraph.createRun();
//		run.setText("实习经历描述我希望在实习中能够更加了解公司制度、正以后铺平道路；也帮助我把学校中学习到得书本只是运用到显示项目，将学校学习到的综合能力发挥在实际");
//		run.setFontSize(14);
//
//		// 换行
//		paragraph = document.createParagraph();
//		run = paragraph.createRun();
//		run.setText("\r");
//
//		// 自我评价title
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.CENTER);
//		run = paragraph.createRun();
//		run.setText("自我评价");
//		run.setFontSize(12);
//
//		// 自我评价
//		paragraph = document.createParagraph();
//		paragraph.setAlignment(ParagraphAlignment.LEFT);
//		run = paragraph.createRun();
//		run.setText("实习经历描述我希望在实习中能够更加了解公司制度、正以后铺平道路；也帮助我把学校中学习到得书本只是运用到显示项目，将学校学习到的综合能力发挥在实际");
//		run.setFontSize(14);
//
//		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
//		XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
//
//		// 添加页眉
//		CTP ctpHeader = CTP.Factory.newInstance();
//		CTR ctrHeader = ctpHeader.addNewR();
//		CTText ctHeader = ctrHeader.addNewT();
//		String headerText = "一职很好";
//		ctHeader.setStringValue(headerText);
//		XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
//		// 设置为右对齐
//		headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
//		XWPFParagraph[] parsHeader = new XWPFParagraph[1];
//		parsHeader[0] = headerParagraph;
//		policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
//
//		// 添加页脚
//		CTP ctpFooter = CTP.Factory.newInstance();
//		CTR ctrFooter = ctpFooter.addNewR();
//		CTText ctFooter = ctrFooter.addNewT();
//		String footerText = "www.baidu.com";
//		ctFooter.setStringValue(footerText);
//		XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
//		headerParagraph.setAlignment(ParagraphAlignment.CENTER);
//		XWPFParagraph[] parsFooter = new XWPFParagraph[1];
//		parsFooter[0] = footerParagraph;
//		policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
//
//		document.write(out);
//		out.close();
//	}
//	
//	//生成doc
//		public static void makeDocx(ResumeDTO resumeDTO,String fileName) throws IOException, InvalidFormatException {
//			// Blank Document
//			CustomXWPFDocument document = new CustomXWPFDocument();
//
//			// Write the Document in file system
//			FileOutputStream out = new FileOutputStream(new File(path+fileName));
//			
//			// 基本信息表格生成一个4X7的表格
//			XWPFTable table = document.createTable();
//			// 去表格边框
//			table.getCTTbl().getTblPr().unsetTblBorders();
//			CTTblWidth tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
//			tableWidth.setType(STTblWidth.DXA);
//			tableWidth.setW(BigInteger.valueOf(9072));
//			XWPFTableRow tableRow = table.getRow(0);
//			tableRow.getCell(0);
//			tableRow.addNewTableCell().setText("小不点");;
//			tableRow.addNewTableCell();
//			tableRow.addNewTableCell();
//			tableRow.addNewTableCell();
//			tableRow.addNewTableCell();
//			tableRow.addNewTableCell();
//			
//			tableRow = table.createRow();
//			tableRow = table.createRow();
//			tableRow = table.createRow();
//			
//			//将第一列合并
//			mergeCellsVertically(table, 0, 0, 3);
//			//将最后一行几个合并
//			mergeCellsHorizontal(table,3,4,6);
//			
//			table.getRow(0).getCell(0).setText("头像:");
//			
//			
//			
//			
//			table.getRow(1).getCell(1).setText("性    别:");
//			table.getRow(1).getCell(2).setText("女");
//			table.getRow(1).getCell(3).setText("年    龄:");
//			table.getRow(1).getCell(4).setText("12岁");
//			table.getRow(1).getCell(5).setText("出生年月:");
//			table.getRow(1).getCell(6).setText("1990年2月14日");
//			
//			table.getRow(2).getCell(1).setText("现居城市:");
//			table.getRow(2).getCell(2).setText("北京市");
//			table.getRow(2).getCell(3).setText("学    历:");
//			table.getRow(2).getCell(4).setText("本科");
//			
//			table.getRow(3).getCell(1).setText("手机号码:");
//			table.getRow(3).getCell(2).setText("13397091205");
//			table.getRow(3).getCell(3).setText("电子邮箱:");
//			table.getRow(3).getCell(4).setText("245473357@qq.com");
//			
//			
//			try {
//				byte[] imgByte =  getImageFromNetByUrl("http://img.yizhihenhao.com/3");
//				CTP ctp = CTP.Factory.newInstance();
//				XWPFParagraph p = new XWPFParagraph(ctp, table.getRow(3).getCell(0));
//				table.getRow(3).getCell(0).addParagraph(p);
//				String picId1 = document.addPictureData(imgByte, XWPFDocument.PICTURE_TYPE_JPEG);
//				document.createPicture(picId1, document.getNextPicNameNumber(XWPFDocument.PICTURE_TYPE_PNG), 140, 180);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//
//			document.write(out);
//			out.close();
//		}
//		
//
//		// word跨列合并单元格
//		public static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {  
//	        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {  
//	            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);  
//	            if ( cellIndex == fromCell ) {  
//	                // The first merged cell is set with RESTART merge value  
//	                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);  
//	            } else {  
//	                // Cells which join (merge) the first one, are set with CONTINUE  
//	                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);  
//	            }  
//	        }  
//	    }  
//		// word跨行并单元格
//		public static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {  
//	        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {  
//	            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);  
//	            if ( rowIndex == fromRow ) {  
//	                // The first merged cell is set with RESTART merge value  
//	                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);  
//	            } else {  
//	                // Cells which join (merge) the first one, are set with CONTINUE  
//	                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);  
//	            }  
//	        }  
//	    }
//		
//		//单元格字体设置
//		private void getParagraph(XWPFTableCell cell,String cellText){
//			CTP ctp = CTP.Factory.newInstance();
//			XWPFParagraph p = new XWPFParagraph(ctp, cell);
//			p.setAlignment(ParagraphAlignment.CENTER);
//	        XWPFRun run = p.createRun();
//	        run.setText(cellText);
//	        CTRPr rpr = run.getCTR().isSetRPr() ? run.getCTR().getRPr() : run.getCTR().addNewRPr();
//	        CTFonts fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();
//	        fonts.setAscii("仿宋");
//	        fonts.setEastAsia("仿宋");
//	        fonts.setHAnsi("仿宋");
//	        cell.setParagraph(p);
//		}
//		
//		/** 
//	     * 根据地址获得数据的字节流 
//	     * @param strUrl 网络连接地址 
//	     * @return 
//	     */  
//	    public static byte[] getImageFromNetByUrl(String strUrl){  
//	        try {  
//	            URL url = new URL(strUrl);  
//	            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
//	            conn.setRequestMethod("GET");  
//	            conn.setConnectTimeout(5 * 1000);  
//	            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据  
//	            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据  
//	            return btImg;  
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//	        }  
//	        return null;  
//	    }  
//	    
//	    /** 
//	     * 从输入流中获取数据 
//	     * @param inStream 输入流 
//	     * @return 
//	     * @throws Exception 
//	     */  
//	    public static byte[] readInputStream(InputStream inStream) throws Exception{  
//	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
//	        byte[] buffer = new byte[1024];  
//	        int len = 0;  
//	        while( (len=inStream.read(buffer)) != -1 ){  
//	            outStream.write(buffer, 0, len);  
//	        }  
//	        inStream.close();  
//	        return outStream.toByteArray();  
//	    }  
//		
//		public static void main(String[] args) throws IOException, InvalidFormatException {
//			WordUtil.makeDocx(null, 123+".docx");
//			System.out.println("生成成功");
//		}
//		
//		
//
//}
