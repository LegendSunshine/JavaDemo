package com.demo.service;


import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
    private final int presetRows;
    private CellStyle[] templateStyles;
    private int[] columnWidths;
    private List<CellRangeAddress> mergeRegions;

    Logger logger = LoggerFactory.getLogger(CustomSheetWriteHandler.class);
    public CustomSheetWriteHandler(int presetRows) {
        this.presetRows = presetRows;
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
        if (currentRowNum == presetRows - 1) {

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
                if (region.getFirstRow() == presetRows - 1) {
                    mergeRegions.add(region);
                }
            }
        }

        if(currentRowNum>presetRows){
            // 应用单元格样式
            for (int i = 0; i < templateStyles.length; i++) {
                if (templateStyles[i] != null) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        cell = row.createCell(i);
                    }
                    cell.setCellStyle(templateStyles[i]);
                    logger.info("Applied style to cell: row=" + row.getRowNum() + ", col=" + i);
                }
            }


            // 创建新的合并区域
            if (!mergeRegions.isEmpty()) {
                int rowDiff = row.getRowNum() - (presetRows - 1);
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


}
