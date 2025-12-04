package org.erangaz;
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String pathOutput, documentName, value;
        String[] imagesData = new String[0];
        String[] values = new String[8];

//      Реферат
//        System.out.println("--------------- Реферат ---------------");
//        System.out.println("Заполнение реферата.");
//        System.out.print("Введите путь для сохранения: ");
//        pathOutput = input.nextLine();
//        System.out.print("Введите только имя документа без расширения: ");
//        documentName = input.nextLine() + ".docx";
//        System.out.println("Для заполнения полей документа используйте текст.");
//        System.out.println("Неправильные поля отмечены красным цветом.");
//        System.out.println("Поля для заполнения:\n" +
//                "1. Авторы " +
//                "2. Правообладатель " +
//                "3. Программа " +
//                "4. Аннотация " +
//                "5. Тип ЭВМ " +
//                "6. Язык " +
//                "7. ОС " +
//                "8. Объем программы");
//        for (int i = 0; i < values.length; i++){
//            System.out.printf("%d >> ", i + 1);
//            value = input.nextLine();
//            values[i] = value;
//        }
//        ReportGenerator.createReport(documentName, pathOutput, values);
        // Отчет
//        System.out.println("--------------- Лабораторная работа ---------------");
//        imagesData = new String[0];
//        values = new String[15];
//        System.out.println("Заполнение отчета лабораторной работы.");
//        System.out.print("Введите путь для сохранения: ");
//        pathOutput = input.nextLine();
//        System.out.print("Введите только имя документа без расширения: ");
//        documentName = input.nextLine() + ".docx";
//        System.out.println("""
//                Для заполнения полей документа используйте текст. \n
//                Неправильные поля отмечены красным цветом. \n
//                Изображение должно быть в формате .png! \
//                Если ваше изображение не в формате .png, напишите перед ним флаг --c \n
//                Для вставки изображений и подписи к нему сначала напишите флаг --img. Для выхода введите --exit
//                Например:
//                """);
//        System.out.println("--img\n/pictures/Анапа2010.png\nАнапа 2010\n--exit\n");
//        System.out.println("Поля для заполнения:\n" +
//                "1. Полное название организации " +
//                "2. Название кафедры " +
//                "3. Номер работы " +
//                "4. Название дисциплины " +
//                "5. Номер группы " +
//                "6. ФИО студента " +
//                "7. ФИО преподавателя " +
//                "8. Год " +
//                "9. Цель работы " +
//                "10. Вариант задания " +
//                "11. Текст задания " +
//                "12. Решение " +
//                "13. Листинг программы " +
//                "14. Результат работы. " +
//                "15. Вывод ");
//        for (int i = 0; i < values.length; i++){
//            System.out.printf("%d >> ", i + 1);
//            value = input.nextLine();
//            if (value.contains("--img")){
//                int j = 0;
//                while(!value.contains("--exit")){
//                    imagesData = Arrays.copyOf(imagesData, imagesData.length + 1);
//                    System.out.printf("%d [img] >> ", i + 1);
//                    value = input.nextLine();
//                    if (value.contains("--c"))
//                        value = ImageConverter.getImage(value.substring(4));
//                    imagesData[j] = value;
//                    j++;
//                }
//                imagesData = Arrays.copyOf(imagesData, imagesData.length - 1);
//                values[i] = "{{image}}";
//            }
//            else
//                values[i] = value;
//        }
//        LabReport.createLabReport(documentName, pathOutput, values, imagesData);
        // Согласие на обработку персональных данных
//        System.out.println("--------------- Согласие на обработку персональных данных ---------------");
//        values = new String[7];
//        System.out.println("Заполнение отчета согласия.");
//        System.out.print("Введите путь для сохранения: ");
//        pathOutput = input.nextLine();
//        System.out.print("Введите только имя документа без расширения: ");
//        documentName = input.nextLine() + ".docx";
//        System.out.println("Для заполнения полей документа используйте текст.");
//        System.out.println("Неправильные поля отмечены красным цветом.");
//        System.out.println("Поля для заполнения:\n" +
//                "1. Название программа для ЭВМ или базы данных " +
//                "2. ФИО " +
//                "3. Адрес места жительства " +
//                "4. Серия и номер паспорта " +
//                "5. Дата выдачи паспорта " +
//                "6. Кем выдан паспорт " +
//                "7. Фамилия и инициалы");
//        for (int i = 0; i < values.length; i++){
//            System.out.printf("%d >> ", i + 1);
//            value = input.nextLine();
//            values[i] = value;
//        }
//        PersonalDataConsentGenerator.createReport(documentName, pathOutput, values);
    }

}