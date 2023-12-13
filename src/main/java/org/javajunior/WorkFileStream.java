package org.javajunior;

import java.io.*;
import java.util.UUID;
/**
 * Написать класс с двумя методами:
 * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл. Название файл - class.getName() + "_" + UUID.randomUUID().toString()
 * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString() и загружает объект из файла и удаляет этот файл.
 *
 * Что делать в ситуациях, когда файла нет или в нем лежит некорректные данные - подумать самостоятельно.
 */

public class WorkFileStream {
    public void saveObject(Serializable o) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream((o.getClass().getSimpleName() + "_" + UUID.randomUUID()).toString());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
            System.out.println("Объект сохранен");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void delObject(String s) throws IOException {
        File file = new File(s);
        try {
            FileInputStream fileInputStream = new FileInputStream(s);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectInputStream.readObject();
            objectInputStream.close();
            file.delete();
            System.out.println("Файл удален");
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }catch (StreamCorruptedException e){
            System.out.println("Ошибка чтения файла! Возможно файл поврежден");
        }
    }
}


