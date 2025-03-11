package com.example.project;

public class Encryptor {

    public static int determineColumns(int messageLen, int rows) {
        int columns = messageLen / rows;
        if (messageLen % rows != 0) {
            columns++;
        }

        if(messageLen == 0) {
            return 1;
        }
        return columns;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        int columns = determineColumns(message.length(), rows);
        String[][] array = new String[rows][columns];
        int messageIndex = 0;
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (messageIndex < message.length()) {
                    array[i][j] = message.substring(messageIndex, messageIndex + 1);
                    messageIndex++;
                } else {
                    array[i][j] = "=";
                }
            }
        }
    
        return array;
    }

    public static String encryptMessage(String message, int rows) {
        if (message == null || message.isEmpty()) {
            return "";
        }

        String[][] encryptArray = generateEncryptArray(message, rows);
        StringBuilder encryptedMessage = new StringBuilder();

        for (int j = encryptArray[0].length - 1; j >= 0; j--) {
            for (int i = 0; i < encryptArray.length; i++) {
                encryptedMessage.append(encryptArray[i][j]);
            }
        }

        return encryptedMessage.toString();
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        if (encryptedMessage == null || encryptedMessage.isEmpty()) {
            return "";
        }

        int columns = encryptedMessage.length() / rows;
        String[][] decryptArray = new String[rows][columns];

        int index = 0;
        for (int j = columns - 1; j >= 0; j--) {
            for (int i = 0; i < rows; i++) {
                decryptArray[i][j] = encryptedMessage.substring(index, index + 1);
                index++;
            }
        }

        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!decryptArray[i][j].equals("=")) {
                    decryptedMessage.append(decryptArray[i][j]);
                }
            }
        }

        return decryptedMessage.toString();
    }
}