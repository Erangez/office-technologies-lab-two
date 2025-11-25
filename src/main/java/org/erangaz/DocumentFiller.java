package org.erangaz;

import org.apache.poi.xwpf.usermodel.*;

public class DocumentFiller {
    public static void fillDocument(XWPFDocument document, String placeHolder, String value) {
        if (document.getTables().isEmpty()) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String paragraphText = paragraph.getText();
                paragraphText = paragraphText.replace(placeHolder, value);
                boolean isBold = paragraph.getRuns().getFirst().isBold();
                boolean isItalic = paragraph.getRuns().getFirst().isItalic();
                String fontFamily = paragraph.getRuns().getFirst().getFontFamily();
                double fontSize = paragraph.getRuns().getFirst().getFontSizeAsDouble();
                for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                    paragraph.removeRun(i);
                }
                XWPFRun run = paragraph.createRun();
                run.setBold(isBold);
                run.setItalic(isItalic);
                run.setColor("000000");
                run.setFontFamily(fontFamily);
                run.setFontSize(fontSize);
                run.setText(paragraphText);
            }
        } else {
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            String paragraphText = paragraph.getText();
                            paragraphText = paragraphText.replace(placeHolder, value);
                            boolean isBold = paragraph.getRuns().getFirst().isBold();
                            boolean isItalic = paragraph.getRuns().getFirst().isItalic();
                            String fontFamily = paragraph.getRuns().getFirst().getFontFamily();
                            double fontSize = paragraph.getRuns().getFirst().getFontSizeAsDouble();
                            for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                                paragraph.removeRun(i);
                            }
                            XWPFRun run = paragraph.createRun();
                            run.setBold(isBold);
                            run.setItalic(isItalic);
                            run.setColor("000000");
                            run.setFontFamily(fontFamily);
                            run.setFontSize(fontSize);
                            run.setText(paragraphText);
                        }
                    }
                }
            }
        }
    }
}