package org.erangaz;

import org.apache.poi.xwpf.usermodel.*;

import java.util.Arrays;

public class PlaceHoldersFinder {
    public static String[] findPlaceHolders(XWPFDocument document){
        String[] placeHolders = new String[0];
        if (document.getTables().isEmpty()) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                if (paragraph.getText().contains("{{") && paragraph.getText().contains("}}")) {
                    placeHolders = Arrays.copyOf(placeHolders, placeHolders.length + 1);
                    placeHolders[placeHolders.length - 1] = paragraph.getText().substring(
                            paragraph.getText().indexOf("{"),
                            paragraph.getText().lastIndexOf("}") + 1
                    );
                }
            }
        }
        else{
            for (XWPFTable table : document.getTables()){
                for (XWPFTableRow row : table.getRows()){
                    for (XWPFTableCell cell : row.getTableCells()){
                        for (XWPFParagraph paragraph : cell.getParagraphs()){
                            if (paragraph.getText().contains("{{") && paragraph.getText().contains("}}")) {
                                placeHolders = Arrays.copyOf(placeHolders, placeHolders.length + 1);
                                placeHolders[placeHolders.length - 1] = paragraph.getText().substring(
                                        paragraph.getText().indexOf("{"),
                                        paragraph.getText().lastIndexOf("}") + 1
                                );
                            }
                        }
                    }
                }
            }
        }
        return placeHolders;
    }
}
