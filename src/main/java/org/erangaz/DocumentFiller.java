package org.erangaz;

import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DocumentFiller {
    public static void fillDocument(XWPFDocument document, String placeHolder, String value, boolean wrongText) {
        if (document.getTables().isEmpty()) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()){
                    String text = run.getText(0);
                    if (text != null && text.contains(placeHolder)){
                        run.setText(value, 0);
                        run.setColor(wrongText ? "EE204D" : "000000");
                        run.setBold(false);
                        run.setItalic(false);
                        return;
                    }
                }
            }
        } else {
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            for (XWPFRun run : paragraph.getRuns()){
                                String text = run.getText(0);
                                if (text != null && text.contains(placeHolder)){
                                    run.setText(value, 0);
                                    run.setColor(wrongText ? "EE204D" : "000000");
                                    run.setBold(false);
                                    run.setItalic(false);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void fillImageData(XWPFDocument document, String[] imagesData){
        if (document.getTables().isEmpty()) {
            for (int i = 0; i < document.getParagraphs().size(); i++){
                XWPFParagraph currentParagraph = document.getParagraphs().get(i);
                if (currentParagraph.getText().contains("{{image}}")){
                    for (int k = currentParagraph.getRuns().size() - 1; k >= 0; k--){
                        currentParagraph.removeRun(k);
                    }
                    XmlCursor cursor = currentParagraph.getCTP().newCursor();
                    document.removeBodyElement(i);
                    XWPFParagraph paragraph = document.insertNewParagraph(cursor);
                    paragraph.setAlignment(ParagraphAlignment.CENTER);
                    for (String imageData : imagesData) {
                        if (imageData.contains(".png")) {
                            try {
                                FileInputStream fis = new FileInputStream(imageData);
                                paragraph.createRun().addPicture(fis, PictureType.PNG, imageData,
                                        Units.toEMU(300), Units.toEMU(100));
                                paragraph.createRun().addBreak();
                            } catch (FileNotFoundException e) {
                                System.out.printf("Файл с именем %s не найден!\n", imageData);
                            } catch (InvalidFormatException e) {
                                System.out.println("Неверный тип файла! Должен быть .png.");
                            } catch (IOException e) {
                                System.out.println("Ошибка при работе с файлом!");
                            }
                        } else {
                            paragraph.createRun().addBreak();
                            paragraph.createRun().setText(imageData);
                            paragraph.createRun().addBreak();
                        }
                    }
                }
            }
        } else {
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (int i = 0; i < cell.getParagraphs().size(); i++){
                            XWPFParagraph currentParagraph = cell.getParagraphs().get(i);
                            if (currentParagraph.getText().contains("{{image}}")){
                                for (int k = currentParagraph.getRuns().size() - 1; k >= 0; k--){
                                    currentParagraph.removeRun(k);
                                }
                                XmlCursor cursor = currentParagraph.getCTP().newCursor();
                                cell.removeParagraph(i);
                                XWPFParagraph paragraph = cell.insertNewParagraph(cursor);
                                paragraph.setAlignment(ParagraphAlignment.CENTER);
                                for (String imagesDate : imagesData) {
                                    if (imagesDate.contains(".png")) {
                                        try {
                                            FileInputStream fis = new FileInputStream(imagesDate);
                                            paragraph.createRun().addPicture(fis, PictureType.PNG, imagesDate,
                                                    Units.toEMU(300), Units.toEMU(100));
                                            paragraph.createRun().addBreak();
                                        } catch (FileNotFoundException e) {
                                            System.out.printf("Файл с именем %s не найден!\n", imagesDate);
                                        } catch (InvalidFormatException e) {
                                            System.out.println("Неверный тип файла! Должен быть .png.");
                                        } catch (IOException e) {
                                            System.out.println("Ошибка при работе с файлом!");
                                        }
                                    } else {
                                        paragraph.createRun().addBreak();
                                        paragraph.createRun().setText("\n" + imagesDate);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}