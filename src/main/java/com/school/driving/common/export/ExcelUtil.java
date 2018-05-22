package com.school.driving.common.export;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

	public static void setStyle(CellStyle style, short color, HSSFWorkbook work) {
		style.setFillForegroundColor(color);
		style.setFillBackgroundColor(color);
		style.setFillPattern(XSSFCellStyle.BIG_SPOTS);
		HSSFFont font = work.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
	}

	/**
	 * 导出
	 */
	public static void exportExcelMap(Map<String, String> head,
                                      List<Map<String, Object>> listMap, String excelName,
                                      HttpServletResponse response, Map<String,String> tips_red) {
		short tempColor = IndexedColors.PALE_BLUE.getIndex();
		int rowaccess = 100;
		OutputStream os = null;
		try {
			// 取得输出流
			os = response.getOutputStream();
			// DownFileUtil.downLoadFile(response, excelName, "xls");
			// 解决中文乱码
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(excelName.getBytes("utf-8"), "ISO8859-1")
					+ ".xls");
			response.setContentType("application/vnd.ms-excel;charset=gb2312");



			HSSFWorkbook work = new HSSFWorkbook();

			HSSFCellStyle style1 = work.createCellStyle();
			style1.setWrapText(true);
			ExcelUtil.setStyle(style1, tempColor, work);

			HSSFSheet sheet1 = work.createSheet("sheet1");

			if (listMap != null && listMap.size() != 0) {
				creatExcelMap(sheet1, listMap, head, style1, rowaccess, work,tips_red);
			} else {
				creatExcelHead(sheet1, head, style1);
			}

			work.write(os);
			os.flush();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void creatExcelMap(HSSFSheet sheet1,
			List<Map<String, Object>> listMap, Map<String, String> head,
			HSSFCellStyle style1, int rowaccess, HSSFWorkbook work,Map<String,String> tips_red) {

		int row = listMap.size(); // 行数+列表头
		int col = listMap.get(0).size(); // 列数
		int index = 0;
		Map.Entry<String, Object> param = null;
		List<String> newHead = new ArrayList<String>();
		HSSFRow rows0 = sheet1.createRow(0); // 填充表头

		boolean tip = false;
		// 填充头标题
		for (Map.Entry<String, String> entryHead : head.entrySet()) {

			HSSFCell cell = rows0.createCell((short) index);
			// System.out.print(entry.getKey() + ":" + entry.getValue() + "\t");
			for (Map.Entry<String, Object> entry : listMap.get(0).entrySet()) {
				if (entry.getKey().equals(entryHead.getKey())) {

					cell.setCellValue(entryHead.getValue());
					cell.setCellStyle(style1);

					newHead.add(entryHead.getKey());
					tip = true;
					break;
				}
			}
			if (tip) {
				index++;
				tip = false;
			}
		}

		int newHeadSize = newHead.size();
		HSSFCellStyle cellStyle = work.createCellStyle();

		// 内容
		for (int i = 1; i < row + 1; i++) {
			HSSFRow rows = sheet1.createRow(i);
			Map<String, Object> list = listMap.get(i - 1);
			for (int j = 0; j < newHeadSize; j++) {

				HSSFCell cell = rows.createCell((short) j);

				String key = newHead.get(j);
				Object temp = list.get(key);
				if (temp != null) {

					if ((temp instanceof Double)) {

						cellStyle.setDataFormat(HSSFDataFormat
								.getBuiltinFormat("0.00"));
						cell.setCellStyle(cellStyle);
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((double) temp);
					} else if (temp instanceof Integer) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((int) temp);
					} else if (temp instanceof Short) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((short) temp);
					} else if (temp instanceof Float) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((float) temp);
					} else if (temp instanceof Long) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((long) temp);
					} else if (temp instanceof BigDecimal) {
						cellStyle.setDataFormat(HSSFDataFormat
								.getBuiltinFormat("0.00"));
						cell.setCellStyle(cellStyle);
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(((BigDecimal) temp).doubleValue());
					} else {
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(temp.toString());
					}



					//处理内容颜色
					if(tips_red!=null){
						String val=tips_red.get(key);
						if(val!=null&&val.equals(temp)){
//							cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
//							cellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
//							cellStyle.setFillPattern(XSSFCellStyle.BIG_SPOTS);
							HSSFFont font = work.createFont();
							font.setColor(IndexedColors.RED.getIndex());
							cellStyle.setFont(font);
							cell.setCellStyle(cellStyle);
						}
					}


				} else {
					cell.setCellValue("");
				}
			}

		}

	}


	private static void creatExcelErrorMap(HSSFSheet sheet1,
									  List<Map<String, Object>> listMap, Map<String, String> head,
									  HSSFCellStyle style1, int rowaccess, HSSFWorkbook work,Map<String,String> tips_red) {

		int row = listMap.size(); // 行数+列表头
		int col = listMap.get(0).size(); // 列数
		int index = 0;
		Map.Entry<String, Object> param = null;
		List<String> newHead = new ArrayList<String>();
		HSSFRow rows0 = sheet1.createRow(0); // 填充表头
		boolean tip = false;
		// 填充头标题
		for (Map.Entry<String, String> entryHead : head.entrySet()) {

			HSSFCell cell = rows0.createCell((short) index);
			// System.out.print(entry.getKey() + ":" + entry.getValue() + "\t");
			for (Map.Entry<String, Object> entry : listMap.get(0).entrySet()) {
				if (entry.getKey().equals(entryHead.getKey())) {
					cell.setCellValue(entryHead.getValue());
					cell.setCellStyle(style1);
					newHead.add(entryHead.getKey());
					tip = true;
					break;
				}
			}
			if (tip) {
				index++;
				tip = false;
			}
		}

		int newHeadSize = newHead.size();
		HSSFCellStyle cellStyle = work.createCellStyle();

		// 内容
		for (int i = 1; i < row + 1; i++) {
			HSSFRow rows = sheet1.createRow(i);
			Map<String, Object> list = listMap.get(i - 1);

			for (int j = 0; j < newHeadSize; j++) {

				HSSFCell cell = rows.createCell((short) j);

				String qrPath = list.get("qrPath").toString();
				String[] split = qrPath.split(",");


				String key = newHead.get(j);
				Object temp = list.get(key);
				if (temp != null) {

					if ((temp instanceof Double)) {

						cellStyle.setDataFormat(HSSFDataFormat
								.getBuiltinFormat("0.00"));
						cell.setCellStyle(cellStyle);
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((double) temp);
					} else if (temp instanceof Integer) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((int) temp);
					} else if (temp instanceof Short) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((short) temp);
					} else if (temp instanceof Float) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((float) temp);
					} else if (temp instanceof Long) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((long) temp);
					} else if (temp instanceof BigDecimal) {
						cellStyle.setDataFormat(HSSFDataFormat
								.getBuiltinFormat("0.00"));
						cell.setCellStyle(cellStyle);
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(((BigDecimal) temp).doubleValue());
					} else {
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(temp.toString());
					}

					//处理内容颜色
					for(int k = 0; k<split.length; k++){
						if(j==Integer.valueOf(split[k])){
							HSSFFont font = work.createFont();
							font.setColor(IndexedColors.RED.getIndex());
							cellStyle.setFont(font);
							cell.setCellStyle(cellStyle);}
						if(11==Integer.valueOf(split[k])){
							HSSFFont font = work.createFont();
							font.setColor(IndexedColors.RED.getIndex());
							cellStyle.setFont(font);
							cell.setCellStyle(cellStyle);}
					}


				} else {
					cell.setCellValue("");
				}
			}

		}

	}


	/**
	 * 错误信息导出
	 */
	public static void exportExcelErrorMap(Map<String, String> head,
                                           List<Map<String, Object>> listMap, String excelName,
                                           HttpServletResponse response, Map<String,String> tips_red) {
		short tempColor = IndexedColors.PALE_BLUE.getIndex();
		int rowaccess = 100;
		OutputStream os = null;
		try {
			// 取得输出流
			os = response.getOutputStream();
			// DownFileUtil.downLoadFile(response, excelName, "xls");
			// 解决中文乱码
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(excelName.getBytes("utf-8"), "ISO8859-1")
					+ ".xls");
			response.setContentType("application/vnd.ms-excel;charset=gb2312");



			HSSFWorkbook work = new HSSFWorkbook();

			HSSFCellStyle style1 = work.createCellStyle();
			style1.setWrapText(true);
			ExcelUtil.setStyle(style1, tempColor, work);

			HSSFSheet sheet1 = work.createSheet("sheet1");

			if (listMap != null && listMap.size() != 0) {
				creatExcelErrorMap(sheet1, listMap, head, style1, rowaccess, work,tips_red);
			} else {
				creatExcelHead(sheet1, head, style1);
			}

			work.write(os);
			os.flush();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}




	private static void creatExcelHead(Sheet sheet1, Map<String, String> head,
									   CellStyle style1) {
		Row rows0 = sheet1.createRow(0); // 填充表头
		int index = 0;
		for (Map.Entry<String, String> entryHead : head.entrySet()) {
			Cell cell = rows0.createCell(index);
			cell.setCellValue(entryHead.getValue());
			cell.setCellStyle(style1);
			index++;

		}

	}
}
