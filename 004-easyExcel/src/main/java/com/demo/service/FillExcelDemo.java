package com.demo.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.demo.entity.ExcelFillExportDataBO;
import com.demo.entity.ExcelFillExportDataListBO;
import com.demo.entity.ExportArggate;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @className FillExcelDemo
 * @description:
 * @author legend
 * @date 2025/10/27 20:37
 * @version 1.0
 */
public class FillExcelDemo {

    private  static  int rowNum = 8;

    Logger logger = LoggerFactory.getLogger(FillExcelDemo.class);

    public static void main(String[] args) {
        FillExcelDemo fillExcelDemo = new FillExcelDemo();
        try {
            fillExcelDemo.fillExportExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillExportExcel() throws IOException {
        ExportArggate exportArggate = buildExcelData();
        String exportExcelPath = "C:\\Users\\14023\\Desktop\\test\\fillExport-"+System.currentTimeMillis()+".xlsx";
        File file = new File(exportExcelPath);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()){
            file.createNewFile();
        }

        // 先准备模板
        Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\14023\\Desktop\\fillTemplate.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);
        
        // 获取模板行（第9行）
        Row templateRow = sheet.getRow(rowNum); // 索引从0开始，所以是8
        
        // 计算需要插入的行数
        int listSize = exportArggate.getList().size()-6;
        
        // 从模板行开始，向下插入所需的行数
        // 复制模板行的样式到新插入的行
        for (int i = rowNum; i < rowNum+listSize; i++) {
            insertRow(sheet, i+1);
        }
        
        // 保存预处理后的模板
        String preparedTemplate = "C:\\Users\\14023\\Desktop\\preparedTemplate.xlsx";
        try (FileOutputStream out = new FileOutputStream(preparedTemplate)) {
            workbook.write(out);
        }
        
        // 使用预处理后的模板进行填充
        try(ExcelWriter excelWriter = EasyExcel.write(exportExcelPath).withTemplate(preparedTemplate).build();) {
            WriteSheet writeSheet = EasyExcel.writerSheet()
                    .registerWriteHandler(new CustomSheetWriteHandler(rowNum,exportArggate.getList().size()))
                    .build();
            // 写入数据
            excelWriter.fill(exportArggate.getExcelFillExportDataBO(), writeSheet);
            excelWriter.fill(new FillWrapper("data1", exportArggate.getList()), writeSheet);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public ExportArggate buildExcelData(){
        ExportArggate exportArggate = new ExportArggate();
        ExcelFillExportDataBO excelFillExportDataBO = new ExcelFillExportDataBO();
        excelFillExportDataBO.setTitle("标题");
        excelFillExportDataBO.setOne(1);
        excelFillExportDataBO.setTwo(2);
        excelFillExportDataBO.setThree(3);
        excelFillExportDataBO.setFour(4);
        excelFillExportDataBO.setFive(5);
        excelFillExportDataBO.setSix(6);
        List<ExcelFillExportDataListBO> excelFillExportDataListBOS = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            ExcelFillExportDataListBO excelFillExportDataListBO = new ExcelFillExportDataListBO();
            excelFillExportDataListBO.setName("张三"+ i);
            excelFillExportDataListBO.setNumber(i);
            excelFillExportDataListBOS.add(excelFillExportDataListBO);
        }
        exportArggate.setList(excelFillExportDataListBOS);
        exportArggate.setExcelFillExportDataBO(excelFillExportDataBO);
        return exportArggate;
    }


    // 替代 shiftRows 的实现
    public void insertRow(Sheet sheet, int rowIndex) {
        int lastRowNum = sheet.getLastRowNum();
        // 从后往前移动行，避免覆盖
        for (int i = lastRowNum; i >= rowIndex; i--) {
            Row sourceRow = sheet.getRow(i);
            if (sourceRow != null) {
                Row targetRow = sheet.createRow(i + 1);
                copyRow(sourceRow, targetRow);
                sheet.removeRow(sourceRow);
            }
        }
    }

    /**
     * 复制行
     * @param sourceRow 源行
     * @param targetRow 目标行
     */
    private void copyRow(Row sourceRow, Row targetRow) {
        Sheet sheet = sourceRow.getSheet();
        
        // 复制行高
        targetRow.setHeight(sourceRow.getHeight());
        targetRow.setHeightInPoints(sourceRow.getHeightInPoints());

        // 先处理合并区域
        List<CellRangeAddress> mergedRegions = new ArrayList<>();
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
            if (mergedRegion.getFirstRow() == sourceRow.getRowNum()) {
                // 记录需要移动的合并区域
                mergedRegions.add(mergedRegion);
                // 移除原来的合并区域
                sheet.removeMergedRegion(i);
                i--; // 因为移除了一个区域，索引需要回退
            }
        }

        // 复制单元格
        for (Cell sourceCell : sourceRow) {
            Cell targetCell = targetRow.createCell(sourceCell.getColumnIndex());
            
            // 复制单元格样式
            CellStyle sourceCellStyle = sourceCell.getCellStyle();
            if (sourceCellStyle != null) {
                CellStyle newCellStyle = targetRow.getSheet().getWorkbook().createCellStyle();
                newCellStyle.cloneStyleFrom(sourceCellStyle);
                targetCell.setCellStyle(newCellStyle);
            }

            // 复制单元格内容
            switch (sourceCell.getCellType()) {
                case STRING:
                    targetCell.setCellValue(sourceCell.getStringCellValue());
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(sourceCell)) {
                        targetCell.setCellValue(sourceCell.getDateCellValue());
                    } else {
                        targetCell.setCellValue(sourceCell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    targetCell.setCellValue(sourceCell.getBooleanCellValue());
                    break;
                case FORMULA:
                    targetCell.setCellFormula(sourceCell.getCellFormula());
                    break;
                case BLANK:
                    targetCell.setBlank();
                    break;
                default:
                    targetCell.setBlank();
            }
        }

        // 复制行属性
        targetRow.setZeroHeight(sourceRow.getZeroHeight());
        targetRow.setRowStyle(sourceRow.getRowStyle());

        // 重新添加合并区域到新行
        for (CellRangeAddress region : mergedRegions) {
            int rowDiff = targetRow.getRowNum() - sourceRow.getRowNum();
            CellRangeAddress newRegion = new CellRangeAddress(
                region.getFirstRow() + rowDiff,
                region.getLastRow() + rowDiff,
                region.getFirstColumn(),
                region.getLastColumn()
            );
            sheet.addMergedRegion(newRegion);
        }

        // 记录日志
        logger.info("已复制行 " + sourceRow.getRowNum() + " 到行 " + targetRow.getRowNum() +
                "，包含 " + sourceRow.getLastCellNum() + " 个单元格，处理合并区域 " + mergedRegions.size() + " 个");
    }

}
