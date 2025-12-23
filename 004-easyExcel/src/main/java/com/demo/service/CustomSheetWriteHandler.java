package com.demo.service;


import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @className CustomSheetWriteHandler
 * @description:
 * @author legend
 * @date 2025/10/27 21:08
 * @version 1.0
 */
public class CustomSheetWriteHandler implements SheetWriteHandler,RowWriteHandler {
    private final int templateRowIndex;
    private final int totalDataRows;
    private int dataRowCounter = 0;
    private CellStyle[] templateStyles;
    private int[] columnWidths;
    private List<CellRangeAddress> mergeRegions;

    Logger logger = LoggerFactory.getLogger(CustomSheetWriteHandler.class);
    public CustomSheetWriteHandler(int templateRowIndex, int totalDataRows) {
        this.templateRowIndex = templateRowIndex;
        this.totalDataRows = totalDataRows;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        logger.info("=== afterSheetCreate 开始执行 ===");
        // 在这里只记录一下 writeSheetHolder，后续使用
        Sheet sheet = writeSheetHolder.getSheet();
        Workbook workbook = writeWorkbookHolder.getWorkbook();

        // 保存必要的引用和初始化
        templateStyles = new CellStyle[10];
        columnWidths = new int[10];
        mergeRegions = new ArrayList<>();

        logger.info("Sheet holder 已保存，等待数据写入后处理");
    }


    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer relativeRowIndex, Boolean isHead) {
        int currentRowNum = row.getRowNum();
        logger.info("afterRowDispose: rowNum = " + currentRowNum);

        Sheet sheet = writeSheetHolder.getSheet();
        // 如果是预设行，保存样式信息
        if (currentRowNum == templateRowIndex - 1) {

            // 保存单元格样式
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                if (columnIndex < templateStyles.length) {
                    templateStyles[columnIndex] = cell.getCellStyle();
                }
            }

            // 保存列宽
            for (int i = 0; i < columnWidths.length; i++) {
                columnWidths[i] = sheet.getColumnWidth(i);
            }
            // 保存合并区域信息
            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                CellRangeAddress region = sheet.getMergedRegion(i);
                if (region.getFirstRow() == templateRowIndex - 1) {
                    mergeRegions.add(region);
                }
            }
        }

        if(currentRowNum >= templateRowIndex){
            dataRowCounter++;
            applyCellStyle(row, sheet);

            // 如果是最后一行数据，则设置整个区域的边框
            if (dataRowCounter == totalDataRows) {
                setBorderForMergeRegion(sheet);
            }

            // 创建新的合并区域
            if (!mergeRegions.isEmpty()) {
                int rowDiff = row.getRowNum() - (templateRowIndex - 1);
                for (CellRangeAddress region : mergeRegions) {
                    CellRangeAddress newRegion = new CellRangeAddress(
                            region.getFirstRow() + rowDiff,
                            region.getLastRow() + rowDiff,
                            region.getFirstColumn(),
                            region.getLastColumn()
                    );
                    try {
                        sheet.addMergedRegion(newRegion);
                        logger.info("Added merge region: " + newRegion.formatAsString());
                    } catch (IllegalStateException e) {
                        logger.info("Merge region already exists: " + newRegion.formatAsString());
                    }
                }
            }
        }
    }

    private void applyCellStyle(Row row, Sheet sheet) {
        Workbook workbook = sheet.getWorkbook();
        // 应用单元格样式
        for (int i = 0; i < templateStyles.length; i++) {
            if (templateStyles[i] != null) {
                Cell cell = row.getCell(i);
                if (cell == null) {
                    cell = row.createCell(i);
                }
                CellStyle newCellStyle = workbook.createCellStyle();
                newCellStyle.cloneStyleFrom(templateStyles[i]);
                cell.setCellStyle(newCellStyle);
                logger.info("Applied style to cell: row=" + row.getRowNum() + ", col=" + i);
            }
        }
    }

    private void setBorderForMergeRegion(Sheet sheet) {
        // 定义合并区域的范围，这里的行范围应该是从模板行开始，到所有数据行结束
        // 列范围则根据你的模板来确定，这里假设是A到B列
        CellRangeAddress region = new CellRangeAddress(templateRowIndex -1, templateRowIndex -1 + totalDataRows -1, 0, 1);
        // 设置上边框
        RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
        // 设置下边框
        RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
        // 设置左边框
        RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
        // 设置右边框
        RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);

        logger.info("Set border for merge region: " + region.formatAsString());
    }
}
