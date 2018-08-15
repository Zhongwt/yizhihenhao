package com.yzhh.backstage.api.util.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.util.StringUtils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.yzhh.backstage.api.dto.resume.EducationalBackgroundDTO;
import com.yzhh.backstage.api.dto.resume.InternshipExperienceDTO;
import com.yzhh.backstage.api.dto.resume.ProjectExperienceDTO;
import com.yzhh.backstage.api.dto.resume.ResumeDTO;
import com.yzhh.backstage.api.dto.resume.SelfEvaluationDTO;
import com.yzhh.backstage.api.dto.resume.SkillHobbyDTO;
import com.yzhh.backstage.api.dto.resume.WorksShowDTO;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.word.BytePictureUtils;

public class ExportPDF {

	// public static final String path = "/home/yzhh/file/";
	public static final String path = "D:/code/test/";
	private static final String titlePath = "D:/code/test/images/";
	private static final String EducationalBackground = "EducationalBackground.png";
	private static final String InternshipExpectation = "InternshipExpectation.png";
	private static final String InternshipExperience = "InternshipExperience.png";
	private static final String ProjectExperience = "ProjectExperience.png";
	private static final String SkillHobby = "SkillHobby.png";
	private static final String WorkShow = "WorkShow.png";
	private static final String SelfEvaluation = "SelfEvaluation.png";

	public static void exportPDF(ResumeDTO resumeDTO, String filePath) {

		try {
			// 实例化一个document对象
			Document document = new Document();
			File file = new File(filePath);
			file.createNewFile();
			// 设置要到出的路径
			FileOutputStream out = new FileOutputStream(filePath);
			// 设置字符
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
			// Font fontZH = new Font(bfChinese, 12.0F, 0);
			// 将pdf文件输出到磁盘
			PdfWriter writer = PdfWriter.getInstance(document, out);
			// 设置页眉、页脚
			applyFooter(writer);
			// 打开生成的pdf文件
			document.open();
			// 设置头像等内容内容
			pdfResumeInfo(document, resumeDTO, bfChinese);

			// 设置教育背景
			setEducationalBackground(document, resumeDTO.getEducationalBackgroundList(), bfChinese);

			// 设置实习期望
			setInternshipExpectation(document, resumeDTO, bfChinese);

			// 设置实习经历
			setInternshipExperience(document, resumeDTO.getInternshipExperienceList(), bfChinese);

			// 设置项目经历
			setProjectExperience(document, resumeDTO.getProjectExperienceList(), bfChinese);

			// 设置技能爱好
			setSkillHobby(document, resumeDTO.getSkillHobbyList(), bfChinese);

			// 作品展示
			setWorkShow(document, resumeDTO.getWorksShowList(), bfChinese);

			// 设置自我评价
			setSelfEvaluation(document, resumeDTO.getSelfEvaluationList(), bfChinese);

			// 第七步，关闭document
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 设置用户简历基础属性表格
	private static void pdfResumeInfo(Document document, ResumeDTO resumeDTO, BaseFont bfChinese)
			throws DocumentException, MalformedURLException, IOException {
		Font nameFont = new Font(bfChinese, 18, Font.BOLD);
		Font contentFont = new Font(bfChinese, 14, Font.BOLD);
		// 按百分比分配单元格宽带
		int[] tableWidthRatio = { 13, 13, 16, 13, 16,13,16 };
		PdfPTable table = null;
		PdfPCell baseTableCell = null;
		
		int age = 0;
		
		if(!StringUtils.isEmpty(resumeDTO.getBirthday())) {
			Calendar a=Calendar.getInstance();
			age = a.get(Calendar.YEAR) - Integer.parseInt(resumeDTO.getBirthday().substring(0, 4));
		}

		// 设置第一行
		table = new PdfPTable(7);
		// 设置table 的宽度比
		table.setWidthPercentage(100.0F);
		// 设置百分比
		table.setWidths(tableWidthRatio);

		// 第一列
		baseTableCell = createCellTL("", Element.ALIGN_CENTER, contentFont, 4, 1);
		byte[] bt = BytePictureUtils.getUrlByteArray(resumeDTO.getImageUrl());
		baseTableCell.setImage(Image.getInstance(bt));// 插入图片
		table.addCell(baseTableCell);

		// 第一行
		baseTableCell = createCellTL(resumeDTO.getJobSeekerName(), Element.ALIGN_RIGHT, nameFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL("", Element.ALIGN_LEFT, contentFont, 1, 5);
		table.addCell(baseTableCell);
		// 第二行
		baseTableCell = createCellTL("性          别:", Element.ALIGN_RIGHT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(resumeDTO.getSex(), Element.ALIGN_LEFT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL("年        龄:", Element.ALIGN_RIGHT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(age == 0 ? "" : age+"岁", Element.ALIGN_LEFT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL("出生年月:", Element.ALIGN_RIGHT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(getString(resumeDTO.getBirthday()), Element.ALIGN_LEFT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		
		// 第三行
		baseTableCell = createCellTL("现居城市:", Element.ALIGN_RIGHT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(getString(resumeDTO.getCity()), Element.ALIGN_LEFT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL("学        历:", Element.ALIGN_RIGHT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(getString(resumeDTO.getEducation()), Element.ALIGN_LEFT, contentFont, 1, 3);
		table.addCell(baseTableCell);
		//第4行
		baseTableCell = createCellTL("手机号码:", Element.ALIGN_RIGHT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(getString(resumeDTO.getPhone()), Element.ALIGN_LEFT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL("邮        箱:", Element.ALIGN_RIGHT, contentFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(getString(resumeDTO.getEmail()), Element.ALIGN_LEFT, contentFont, 1, 3);
		table.addCell(baseTableCell);

		document.add(table);
		document.add(new Paragraph("\n"));
	}

	// 添加教育背景
	private static void setEducationalBackground(Document document, List<EducationalBackgroundDTO> eduList,
			BaseFont bfChinese) throws MalformedURLException, IOException, DocumentException {
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		Font contentFont = new Font(bfChinese, 12, 0);

		// 添加一个图片，设置图片的位置，大小
		Image png = Image.getInstance(titlePath + EducationalBackground);
		png.setAlignment(Image.ALIGN_LEFT);
		png.scalePercent(40, 40);
		document.add(png);
		
		document.add(new Paragraph("\n"));

		if (CollectionUtils.isNotEmpty(eduList)) {
			for (EducationalBackgroundDTO dto : eduList) {
				int[] tableWidthRatio = { 33, 33, 33 };
				PdfPTable table = new PdfPTable(3);
				table.setWidthPercentage(100.0F);
				table.setWidths(tableWidthRatio);
				PdfPCell baseTableCell = null;

				baseTableCell = createCellTL(dto.getStartTime().substring(0,4) + "-" + dto.getEndTime().substring(0,4), Element.ALIGN_LEFT,
						titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getSchool(), Element.ALIGN_LEFT, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getMajor() + "(" + dto.getEducation() + ")", Element.ALIGN_LEFT,
						titleFont, 1, 1);
				table.addCell(baseTableCell);
				document.add(table);

				// 按百分比分配单元格宽带
				int[] tableWidthRatio2 = { 2, 12, 86 };
				table = new PdfPTable(3);
				table.setWidthPercentage(100.0F);
				table.setWidths(tableWidthRatio2);

				baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
				baseTableCell.setImage(getBlueImg());
				table.addCell(baseTableCell);
				baseTableCell = createCellTL("成绩排名:", Element.ALIGN_CENTER, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getRanking(), Element.ALIGN_LEFT, contentFont, 1, 1);
				table.addCell(baseTableCell);

				baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
				baseTableCell.setImage(getBlueImg());
				table.addCell(baseTableCell);
				baseTableCell = createCellTL("主修课程:", Element.ALIGN_CENTER, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getMajorCourses(), Element.ALIGN_LEFT, contentFont, 1, 1);
				table.addCell(baseTableCell);

				baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
				baseTableCell.setImage(getBlueImg());
				table.addCell(baseTableCell);
				baseTableCell = createCellTL("荣誉奖励:", Element.ALIGN_CENTER, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getHonor(), Element.ALIGN_LEFT, contentFont, 1, 1);
				table.addCell(baseTableCell);
				document.add(table);

				document.add(new Paragraph("\n"));
			}
		}

		document.add(new Paragraph("\n"));
	}

	// 添加实习期望
	private static void setInternshipExpectation(Document document, ResumeDTO resumeDTO, BaseFont bfChinese)
			throws MalformedURLException, IOException, DocumentException {
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		Font contentFont = new Font(bfChinese, 12, 0);

		// 添加一个图片，设置图片的位置，大小
		Image png = Image.getInstance(titlePath + InternshipExpectation);
		png.setAlignment(Image.ALIGN_LEFT);
		png.scalePercent(40, 40);
		document.add(png);
		
		document.add(new Paragraph("\n"));

		int[] tableWidthRatio = { 33, 33, 33 };
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100.0F);
		table.setWidths(tableWidthRatio);
		PdfPCell baseTableCell = null;

		baseTableCell = createCellTL(getString(resumeDTO.getInternshipTime()), Element.ALIGN_LEFT, titleFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(getString(resumeDTO.getWishPositionName()), Element.ALIGN_LEFT, titleFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(getString(resumeDTO.getWishCity()), Element.ALIGN_LEFT, titleFont, 1, 1);
		table.addCell(baseTableCell);
		document.add(table);

		// 按百分比分配单元格宽带
		int[] tableWidthRatio2 = { 2, 12, 86 };
		table = new PdfPTable(3);
		table.setWidthPercentage(100.0F);
		table.setWidths(tableWidthRatio2);

		baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
		baseTableCell.setImage(getBlueImg());
		table.addCell(baseTableCell);
		baseTableCell = createCellTL("实习薪资:", Element.ALIGN_CENTER, titleFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(getString(resumeDTO.getPerDiem()), Element.ALIGN_LEFT, contentFont, 1, 1);
		table.addCell(baseTableCell);

		baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
		baseTableCell.setImage(getBlueImg());
		table.addCell(baseTableCell);
		baseTableCell = createCellTL("实习时长:", Element.ALIGN_CENTER, titleFont, 1, 1);
		table.addCell(baseTableCell);
		baseTableCell = createCellTL(getString(resumeDTO.getInternshipTime()), Element.ALIGN_LEFT, contentFont, 1, 1);
		table.addCell(baseTableCell);

		document.add(table);

		document.add(new Paragraph("\n"));
	}

	// 设置实习经历
	private static void setInternshipExperience(Document document,
			List<InternshipExperienceDTO> internshipExperienceList, BaseFont bfChinese)
			throws MalformedURLException, IOException, DocumentException {
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		Font contentFont = new Font(bfChinese, 12, 0);

		// 添加一个图片，设置图片的位置，大小
		Image png = Image.getInstance(titlePath + InternshipExperience);
		png.setAlignment(Image.ALIGN_LEFT);
		png.scalePercent(40, 40);
		document.add(png);
		
		document.add(new Paragraph("\n"));

		if (CollectionUtils.isNotEmpty(internshipExperienceList)) {
			for (InternshipExperienceDTO dto : internshipExperienceList) {
				int[] tableWidthRatio = { 25, 25, 25, 25 };
				PdfPTable table = new PdfPTable(4);
				table.setWidthPercentage(100.0F);
				table.setWidths(tableWidthRatio);
				PdfPCell baseTableCell = null;

				baseTableCell = createCellTL(dto.getStartTime() + "-" + dto.getEndTime(), Element.ALIGN_LEFT,
						titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getDutyName(), Element.ALIGN_LEFT, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getCompanyName(), Element.ALIGN_LEFT, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getCity(), Element.ALIGN_LEFT, titleFont, 1, 1);
				table.addCell(baseTableCell);
				document.add(table);

				// 按百分比分配单元格宽带
				int[] tableWidthRatio2 = { 2, 12, 86 };
				table = new PdfPTable(3);
				table.setWidthPercentage(100.0F);
				table.setWidths(tableWidthRatio2);

				baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
				baseTableCell.setImage(getBlueImg());
				table.addCell(baseTableCell);
				baseTableCell = createCellTL("实习描述:", Element.ALIGN_CENTER, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getDescription(), Element.ALIGN_LEFT, contentFont, 1, 1);
				table.addCell(baseTableCell);
				document.add(table);

				document.add(new Paragraph("\n"));
			}
		}

		document.add(new Paragraph("\n"));
	}

	// 设置项目经历
	private static void setProjectExperience(Document document, List<ProjectExperienceDTO> projectExperienceList,
			BaseFont bfChinese) throws MalformedURLException, IOException, DocumentException {
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		Font contentFont = new Font(bfChinese, 12, 0);

		// 添加一个图片，设置图片的位置，大小
		Image png = Image.getInstance(titlePath + ProjectExperience);
		png.setAlignment(Image.ALIGN_LEFT);
		png.scalePercent(40, 40);
		document.add(png);
		
		document.add(new Paragraph("\n"));

		if (CollectionUtils.isNotEmpty(projectExperienceList)) {
			for (ProjectExperienceDTO dto : projectExperienceList) {
				int[] tableWidthRatio = { 25, 25, 25, 25 };
				PdfPTable table = new PdfPTable(4);
				table.setWidthPercentage(100.0F);
				table.setWidths(tableWidthRatio);
				PdfPCell baseTableCell = null;

				baseTableCell = createCellTL(dto.getStartTime() + "-" + dto.getEndTime(), Element.ALIGN_LEFT,
						titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getProjectName(), Element.ALIGN_LEFT, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getRole(), Element.ALIGN_LEFT, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getCity(), Element.ALIGN_LEFT, titleFont, 1, 1);
				table.addCell(baseTableCell);
				document.add(table);

				// 按百分比分配单元格宽带
				int[] tableWidthRatio2 = { 2, 12, 86 };
				table = new PdfPTable(3);
				table.setWidthPercentage(100.0F);
				table.setWidths(tableWidthRatio2);

				baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
				baseTableCell.setImage(getBlueImg());
				table.addCell(baseTableCell);
				baseTableCell = createCellTL("项目描述:", Element.ALIGN_CENTER, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getDescription(), Element.ALIGN_LEFT, contentFont, 1, 1);
				table.addCell(baseTableCell);
				document.add(table);

				document.add(new Paragraph("\n"));
			}
		}

		document.add(new Paragraph("\n"));
	}

	// 设置技能爱好
	private static void setSkillHobby(Document document, List<SkillHobbyDTO> skillHobbyList, BaseFont bfChinese)
			throws MalformedURLException, IOException, DocumentException {
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		Font contentFont = new Font(bfChinese, 12, 0);

		// 添加一个图片，设置图片的位置，大小
		Image png = Image.getInstance(titlePath + SkillHobby);
		png.setAlignment(Image.ALIGN_LEFT);
		png.scalePercent(40, 40);
		document.add(png);
		
		document.add(new Paragraph("\n"));

		if (CollectionUtils.isNotEmpty(skillHobbyList)) {
			for (SkillHobbyDTO dto : skillHobbyList) {
				int[] tableWidthRatio = { 2, 12, 86 };
				PdfPTable table = new PdfPTable(3);
				table.setWidthPercentage(100.0F);
				table.setWidths(tableWidthRatio);
				PdfPCell baseTableCell = null;
				// 按百分比分配单元格宽带
				baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
				baseTableCell.setImage(getBlueImg());
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getName() + ":", Element.ALIGN_CENTER, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getLevel(), Element.ALIGN_LEFT, contentFont, 1, 1);
				table.addCell(baseTableCell);
				document.add(table);
				document.add(new Paragraph("\n"));
			}
		}

		document.add(new Paragraph("\n"));
	}

	// 设置作品展示
	private static void setWorkShow(Document document, List<WorksShowDTO> worksShowList, BaseFont bfChinese)
			throws MalformedURLException, IOException, DocumentException {
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		Font contentFont = new Font(bfChinese, 12, 0);
		Font websideFont = new Font(bfChinese, 12, 0);
		websideFont.setColor(new BaseColor(92, 145, 253));

		// 添加一个图片，设置图片的位置，大小
		Image png = Image.getInstance(titlePath + WorkShow);
		png.setAlignment(Image.ALIGN_LEFT);
		png.scalePercent(40, 40);
		document.add(png);
		
		document.add(new Paragraph("\n"));

		if (CollectionUtils.isNotEmpty(worksShowList)) {
			for (WorksShowDTO dto : worksShowList) {

				int[] tableWidthRatio = { 2, 12, 86 };
				PdfPTable table = new PdfPTable(3);
				table.setWidthPercentage(100.0F);
				table.setWidths(tableWidthRatio);
				PdfPCell baseTableCell = null;
				// 按百分比分配单元格宽带
				baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
				baseTableCell.setImage(getBlueImg());
				table.addCell(baseTableCell);
				baseTableCell = createCellTL("网址链接:", Element.ALIGN_CENTER, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getWorksUrl(), Element.ALIGN_LEFT, websideFont, 1, 1);
				table.addCell(baseTableCell);

				baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
				baseTableCell.setImage(getBlueImg());
				table.addCell(baseTableCell);
				baseTableCell = createCellTL("作品描述:", Element.ALIGN_CENTER, titleFont, 1, 1);
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getDescription(), Element.ALIGN_LEFT, contentFont, 1, 1);
				table.addCell(baseTableCell);
				document.add(table);
				document.add(new Paragraph("\n"));
			}
		}

		document.add(new Paragraph("\n"));
	}

	// 设置自我评价
	private static void setSelfEvaluation(Document document, List<SelfEvaluationDTO> selfEvaluationList,
			BaseFont bfChinese) throws MalformedURLException, IOException, DocumentException {
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		Font contentFont = new Font(bfChinese, 12, 0);

		// 添加一个图片，设置图片的位置，大小
		Image png = Image.getInstance(titlePath + SelfEvaluation);
		png.setAlignment(Image.ALIGN_LEFT);
		png.scalePercent(40, 40);
		document.add(png);
		
		document.add(new Paragraph("\n"));

		if (CollectionUtils.isNotEmpty(selfEvaluationList)) {
			for (SelfEvaluationDTO dto : selfEvaluationList) {
				int[] tableWidthRatio = { 2, 98 };
				PdfPTable table = new PdfPTable(2);
				table.setWidthPercentage(100.0F);
				table.setWidths(tableWidthRatio);
				PdfPCell baseTableCell = null;
				// 按百分比分配单元格宽带
				baseTableCell = createCellTL("", Element.ALIGN_CENTER, titleFont, 1, 1);
				baseTableCell.setImage(getBlueImg());
				table.addCell(baseTableCell);
				baseTableCell = createCellTL(dto.getDescription(), Element.ALIGN_CENTER, contentFont, 1, 1);
				table.addCell(baseTableCell);
				document.add(new Paragraph("\n"));
			}
		}

		document.add(new Paragraph("\n"));
	}

	/**
	 * 设置页眉、页脚
	 * 
	 * @param writer
	 */
	private static void applyFooter(PdfWriter writer) {
		writer.setPageEvent(new PdfPageEventHelper() {
			@Override
			public void onEndPage(PdfWriter writer, Document document) {

				PdfContentByte cb = writer.getDirectContent();
				cb.saveState();

				cb.beginText();
				BaseFont bf = null;
				try {
					bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
				} catch (Exception e) {
					e.printStackTrace();
				}
				cb.setFontAndSize(bf, 10);

				@SuppressWarnings("unused")
				float x = document.top(-20);

				float y = document.bottom(-20);

				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, writer.getPageNumber() + "",
						(document.right() + document.left()) / 2, y, 0);

				cb.endText();

				cb.restoreState();
			}
		});
	}

	private static PdfPCell createCellTL(String value, int align, Font font, int rowspan, int colspan) { // 上左有边框的表格:1.表格内容；2.字体；3.对齐方式；4.横跨列数
		Paragraph paragraph = new Paragraph(value, font);
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align); // 水平对齐
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);
		cell.setPhrase(paragraph); // 设置表格的短语
		cell.setFixedHeight(25 * rowspan);
		 cell.disableBorderSide(1);
		 cell.disableBorderSide(2); // 隐藏下右边框
		 cell.disableBorderSide(3);
		 cell.disableBorderSide(4);
		 cell.disableBorderSide(5);
		 cell.disableBorderSide(6);
		 cell.disableBorderSide(7);
		 cell.disableBorderSide(8);
		return cell;
	}

	// 获取那个蓝色的小点
	private static Image getBlueImg() throws BadElementException, MalformedURLException, IOException {
		Image png = Image.getInstance(titlePath + "icon_26.png");
		png.setAlignment(Image.ALIGN_LEFT);
		png.scalePercent(40, 40);
		return png;
	}

	private static ResumeDTO makeDate() {

		ResumeDTO resumeDTO = new ResumeDTO();
		resumeDTO.setJobSeekerName("艺小溪");
		resumeDTO.setSex("女");
		resumeDTO.setCity("北京");
		resumeDTO.setEducation("本科");
		resumeDTO.setPhone("13200002456");
		resumeDTO.setEmail("yizhihenhao@qq.com");
		resumeDTO.setImageUrl("http://img.yizhihenhao.com/20");
		resumeDTO.setBirthday("1986-07-27");
		// 实习期望
		resumeDTO.setInternshipTime("10个月");
		resumeDTO.setWishPositionName("产品经历");
		resumeDTO.setWishCity("北京");
		resumeDTO.setPerDiem("500");

		List<EducationalBackgroundDTO> educationalBackgroundList = new ArrayList<>();
		EducationalBackgroundDTO educationalBackgroundDTO = new EducationalBackgroundDTO();
		educationalBackgroundDTO.setStartTime("2015");
		educationalBackgroundDTO.setEndTime("2017");
		educationalBackgroundDTO.setSchool("厦门大学");
		educationalBackgroundDTO.setMajor("广播专业");
		educationalBackgroundDTO.setEducation("本科");
		educationalBackgroundDTO.setRanking("10%");
		educationalBackgroundDTO.setMajorCourses("好多课");
		educationalBackgroundDTO.setHonor("国家计算机一级奖");
		educationalBackgroundList.add(educationalBackgroundDTO);
		resumeDTO.setEducationalBackgroundList(educationalBackgroundList);

		return resumeDTO;
	}
	
	private static String getString(String str) {
		if(StringUtils.isEmpty(str)) {
			return "";
		}else {
			return str;
		}
	}

	public static void main(String[] args) {
		ResumeDTO resumeDTO = makeDate();
		exportPDF(resumeDTO, "test");
		System.out.println("success");
	}

}
