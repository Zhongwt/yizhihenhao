package com.yzhh.backstage.api.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.yzhh.backstage.api.dto.resume.ResumeDTO;

public class WordUtil {
	
	public static final String path = "/home/yzhh/file/";

	//生成doc
	public static void makeDoc(ResumeDTO resumeDTO,String fileName) throws IOException {
		// Blank Document
		XWPFDocument document = new XWPFDocument();

		// Write the Document in file system
		FileOutputStream out = new FileOutputStream(new File(path+fileName));

		// 添加名字
		XWPFParagraph paragraph = document.createParagraph();
		// 设置段落居中
		paragraph.setAlignment(ParagraphAlignment.CENTER);

		XWPFRun run = paragraph.createRun();
		run.setText("熊早早 男");
		// titleParagraphRun.setColor("000000");
		run.setFontSize(18);

		// 学校
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("北京 | 东北农业大学 | 本科");
		// run.setColor("696969");
		run.setFontSize(14);

		// 学校
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("电话 ：132****7500 | 邮箱 ：7**********@qq.com");
		// run.setColor("696969");
		run.setFontSize(14);

		// 换行
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		run.setText("\r");

		// 实习期望title
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("实习期望");
		// run.setColor("696969");
		run.setFontSize(12);

		// 基本信息表格
		XWPFTable table = document.createTable();
		// 去表格边框
		table.getCTTbl().getTblPr().unsetTblBorders();
		// 列宽自动分割
		CTTblWidth tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
		tableWidth.setType(STTblWidth.DXA);
		tableWidth.setW(BigInteger.valueOf(9072));
		XWPFTableRow tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("产品经历 | 北京 | 500/天 | 2天 | 实习期");
		tableRowOne.addNewTableCell().setText("2018-07-30");

		// 换行
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		run.setText("\r");

		// 教育背景title
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("教育背景");
		run.setFontSize(12);

		table = document.createTable();
		table.getCTTbl().getTblPr().unsetTblBorders();
		tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
		tableWidth.setType(STTblWidth.DXA);
		tableWidth.setW(BigInteger.valueOf(9072));
		tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("东北农业大学 | 计算机 | 背景 | 本科");
		XWPFTableCell rightCell = tableRowOne.addNewTableCell();
		rightCell.setText("2014.04-2018.09");
		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  

		tableRowOne = table.createRow();
		tableRowOne.getCell(0).setText("清华大学 | 计算机 | 背景 | 本科");
		rightCell = tableRowOne.getCell(1);
		rightCell.setText("2014.04-2018.09");
		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  

		// 换行
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		run.setText("\r");

		// 实习经历title
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("实习经历");
		run.setFontSize(12);

		// 实习经历
		table = document.createTable();
		table.getCTTbl().getTblPr().unsetTblBorders();
		tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
		tableWidth.setType(STTblWidth.DXA);
		tableWidth.setW(BigInteger.valueOf(9072));
		tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("天下科技 | 产品经历 | 北京");
		rightCell = tableRowOne.addNewTableCell();
		rightCell.setText("2017.03.20-2018.04.20");
		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  

		// 换行
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		run.setText("\r");

		// 实习经历
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		run = paragraph.createRun();
		run.setText("实习尽力描述我希望在实习中能够更加了解公司的制度、正以后铺平道路；也帮助我把学校中学习到得书本知识运用到现实项目，将学校学习到的综合能力发挥在实际");
		run.setFontSize(14);

		// 换行
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		run.setText("\r");

		// 实习经历
		table = document.createTable();
		table.getCTTbl().getTblPr().unsetTblBorders();
		tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
		tableWidth.setType(STTblWidth.DXA);
		tableWidth.setW(BigInteger.valueOf(9072));
		tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("天下科技 | 产品经历 | 北京");
		rightCell = tableRowOne.addNewTableCell();
		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  
		rightCell.setText("2017.03.20-2018.04.20");

		// 换行
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		run.setText("\r");

		// 实习经历
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		run = paragraph.createRun();
		run.setText("实习尽力描述我希望在实习中能够更加了解公司的制度、正以后铺平道路；也帮助我把学校中学习到得书本知识运用到现实项目，将学校学习到的综合能力发挥在实际");
		run.setFontSize(14);

		// 换行
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		run.setText("\r");

		// 技能爱好title
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("技能爱好");
		run.setFontSize(12);

		// 技能爱好
		table = document.createTable();
		table.getCTTbl().getTblPr().unsetTblBorders();
		tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
		tableWidth.setType(STTblWidth.DXA);
		tableWidth.setW(BigInteger.valueOf(9072));
		tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("PS软件");
		rightCell = tableRowOne.addNewTableCell();
		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  
		rightCell.setText("精通");

		tableRowOne = table.createRow();
		tableRowOne.getCell(0).setText("PS软件");
		rightCell = tableRowOne.getCell(1);
		rightCell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.RIGHT);  
		rightCell.setText("精通");

		// 换行
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		run.setText("\r");

		// 作品展示title
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("作品展示");
		run.setFontSize(12);

		// 作品展示
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		run = paragraph.createRun();
		run.setText("www.baidu.com");
		run.setFontSize(14);

		// 换行
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		run.setText("\r");

		// 作品展示
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		run = paragraph.createRun();
		run.setText("实习经历描述我希望在实习中能够更加了解公司制度、正以后铺平道路；也帮助我把学校中学习到得书本只是运用到显示项目，将学校学习到的综合能力发挥在实际");
		run.setFontSize(14);

		// 换行
		paragraph = document.createParagraph();
		run = paragraph.createRun();
		run.setText("\r");

		// 自我评价title
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("自我评价");
		run.setFontSize(12);

		// 自我评价
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		run = paragraph.createRun();
		run.setText("实习经历描述我希望在实习中能够更加了解公司制度、正以后铺平道路；也帮助我把学校中学习到得书本只是运用到显示项目，将学校学习到的综合能力发挥在实际");
		run.setFontSize(14);

		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

		// 添加页眉
		CTP ctpHeader = CTP.Factory.newInstance();
		CTR ctrHeader = ctpHeader.addNewR();
		CTText ctHeader = ctrHeader.addNewT();
		String headerText = "一职很好";
		ctHeader.setStringValue(headerText);
		XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
		// 设置为右对齐
		headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
		XWPFParagraph[] parsHeader = new XWPFParagraph[1];
		parsHeader[0] = headerParagraph;
		policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);

		// 添加页脚
		CTP ctpFooter = CTP.Factory.newInstance();
		CTR ctrFooter = ctpFooter.addNewR();
		CTText ctFooter = ctrFooter.addNewT();
		String footerText = "www.baidu.com";
		ctFooter.setStringValue(footerText);
		XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
		headerParagraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFParagraph[] parsFooter = new XWPFParagraph[1];
		parsFooter[0] = footerParagraph;
		policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);

		document.write(out);
		out.close();
	}

}
