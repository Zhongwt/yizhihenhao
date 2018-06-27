package com.yzhh.backstage.api.util;


import java.awt.Color;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.error.CommonError;

/**
 * @version 1.0
 * @description:
 *       导出Excel的工具类
 * @projectName: com.taidii.staffdevelopment.util
 * @className: staff-development
 * @author:谭农春
 * @createTime:2018/6/25 10:48
 */
public class ExcelUtil {
  /**
   * 导出Excel
   *   //拿到palette颜色板
   *  HSSFPalette palette = excel.getCustomPalette();
   * //这个是重点，具体的就是把之前的颜色 HSSFColor.LIME.index
   *  //替换为  RGB(51,204,204) 宝石蓝这种颜色
   * //你可以改为 RGB(0,255,127)
   * palette.setColorAtIndex(HSSFColor.LIME.index, (byte) 0, (byte) 255, (byte) 127);
   * @param title 标题
   *         -- 标题
   * @param dataList 内容
   *          -- 内容
   * @return
   */
  @SuppressWarnings("resource")
public static XSSFWorkbook getWorkbook(String []title, List<String[]> dataList){
    XSSFWorkbook workBook = null;
    workBook = new XSSFWorkbook();
    // 生成一个表格
    XSSFSheet sheet = workBook.createSheet();
    workBook.setSheetName(0,"sheet1");

    // 创建表格标题行 第一行
    XSSFRow titleRow = sheet.createRow(0);
    // 设置表头的样式
    XSSFCellStyle style = workBook.createCellStyle();
    XSSFColor color =new XSSFColor( new Color(180, 180, 180));
    // 设置自定义背景色
    style.setFillForegroundColor(color);
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style.setBorderBottom(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setBorderTop(BorderStyle.THIN);
    style.setAlignment(HorizontalAlignment.CENTER);
    Cell cell =null;
    // 设置标题
    for(int i=0;i<title.length;i++){
      cell = titleRow.createCell(i);
      cell.setCellValue(title[i]);
      cell.setCellStyle(style);
    }
    // 定义内容的样式
     CellStyle contentStyle=workBook.createCellStyle();
    // 允许Excel 单元格换行
     contentStyle.setWrapText(true);
    // 设置边框
     contentStyle.setBorderBottom(BorderStyle.THIN);
    contentStyle.setBorderLeft(BorderStyle.THIN);
    contentStyle.setBorderRight(BorderStyle.THIN);
    contentStyle.setBorderTop(BorderStyle.THIN);
    contentStyle.setAlignment(HorizontalAlignment.CENTER);
    // 内容占位符
     Cell contentCell = null;
    // 设置内容
   if(!CollectionUtils.isEmpty(dataList)){
     Integer len = title.length;
     //插入需导出的数据
     for(int i=0;i<dataList.size();i++){
       XSSFRow row = sheet.createRow(i+1);
       // 遍历单元格
       if(null!=dataList.get(i)){
         if(len != dataList.get(i).length){
           // 导出Excel 异常，表头设置长度和数据不一致。
          throw new BizException(CommonError.EXCEL_ERROR);
         }
         else {
          for(int j =0; j<len;j++){
            //遍历循环值
            contentCell = row.createCell(j);
            // 设置样式
            contentCell.setCellStyle(contentStyle);
            // 设置单元格样式
            contentCell.setCellValue(dataList.get(i)[j]);
          }
         }
       }
     }
   }
//    //数据加载完成，自动调整宽度
//    for(int i=0;i<title.length;i++) {
//      sheet.autoSizeColumn((short) i);
//    }
    return  workBook;
  }
}
