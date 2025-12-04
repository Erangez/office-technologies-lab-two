package org.erangaz;

import org.apache.poi.xwpf.usermodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceHoldersFinder {
    public static String[] findPlaceHolders(XWPFDocument document){
        List<String> placeHolders = new ArrayList<>();
        if (document.getTables().isEmpty()) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        String regex = "\\{\\{[a-zA-Z]+}}";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(text);
                        while (matcher.find()) {
                            placeHolders.add(matcher.group());
                        }
                    }
                }
            }
        }
        else{
            for (XWPFTable table : document.getTables()){
                for (XWPFTableRow row : table.getRows()){
                    for (XWPFTableCell cell : row.getTableCells()){
                        for (XWPFParagraph paragraph : cell.getParagraphs()){
                            for (XWPFRun run : paragraph.getRuns()) {
                                String text = run.getText(0);
                                if (text != null) {
                                    String regex = "\\{\\{[a-zA-Z]+}}";
                                    Pattern pattern = Pattern.compile(regex);
                                    Matcher matcher = pattern.matcher(text);
                                    while (matcher.find()) {
                                        placeHolders.add(matcher.group());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return placeHolders.toArray(new String[0]);
    }
}