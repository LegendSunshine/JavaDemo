package com.demo.entity;

import java.util.List;

/**
 * @className ExportArggate
 * @description:
 * @author legend
 * @date 2025/10/27 20:47
 * @version 1.0
 */
public class ExportArggate {

    private ExcelFillExportDataBO excelFillExportDataBO;

    private List<ExcelFillExportDataListBO> list;

    public ExcelFillExportDataBO getExcelFillExportDataBO() {
        return excelFillExportDataBO;
    }

    public void setExcelFillExportDataBO(ExcelFillExportDataBO excelFillExportDataBO) {
        this.excelFillExportDataBO = excelFillExportDataBO;
    }

    public List<ExcelFillExportDataListBO> getList() {
        return list;
    }

    public void setList(List<ExcelFillExportDataListBO> list) {
        this.list = list;
    }
}
