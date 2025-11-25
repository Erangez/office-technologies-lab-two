package org.erangaz;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String pathOutput, documentName;
        String[] valuesToPaste = new String[8];
        System.out.println("Заполнение реферата.");
        System.out.print("Введите путь для сохранения: ");
        pathOutput = input.nextLine();
        System.out.print("Введите имя документа: ");
        documentName = input.nextLine();
        System.out.println("Введите следующие данные для реферата через ENTER!");
        System.out.println("Авторы, правообладатель, программа, аннотация, тип ЭВМ, язык, ос, объем программы.");
        for (int i = 0; i < valuesToPaste.length; i++){
            valuesToPaste[i] = input.nextLine();
        }
        ReportGenerator.createReport(pathOutput, documentName, valuesToPaste);
    }
}