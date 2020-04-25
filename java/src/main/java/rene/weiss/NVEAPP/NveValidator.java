package rene.weiss.NVEAPP;

import java.util.Objects;


public class NveValidator {

    public static Response validateNVE(String nve, String checkDigit) {
        Response response = new Response();
        response.setNummerVersandEinheit(nve);

        if (Objects.isNull(nve) || nve.isEmpty()) {
            response.setErrorMessage("Bitte geben sie eine NVE mit oder ohne Prüfziffer ein!");
            return response;
        }
        if (!nve.startsWith("00")) {
            response.setErrorMessage("Die NVE muss mit '00' als Datenbezeichner anfangen!");
            return response;
        }
        if (!isNumeric(nve)) {
            response.setErrorMessage("Die NVE darf nur Nummern enthalten!");
            return response;
        }
        if (nve.length() != 19) {
            response.setErrorMessage("Die Gesamtlänge der NVE ohne Prüfsumme muss 19 betragen. Aktuell: " + nve.length());
            return response;
        }
        if (checkDigit == null || checkDigit.isEmpty()) {
            int newCheckDigit = calculateCheckDigit(nve);
            response.setCheckDigit(Integer.toString(newCheckDigit));
            return response;

        } else {
            if (!isNumeric(checkDigit)) {
                response.setErrorMessage("Die Prüfziffer muss eine Nummer sein");
                return response;
            }

            if (!checkCheckDigit(nve, checkDigit)) {
                response.setErrorMessage("Die angegebene Prüfziffer ist falsch! Um eine korrekte Prüfziffer berechnen zu" +
                        " lassen, entfernen Sie diese bitte!");
                return response;
            }
        }
        response.setCheckDigit(checkDigit);
        return response;
    }


    public static boolean checkCheckDigit(String nve, String checkDigit) {
        int newCheckDigit = calculateCheckDigit(nve);
        String newCheckDigitString = String.valueOf(newCheckDigit);
        return Objects.equals(newCheckDigitString, checkDigit);
    }

    public static int calculateCheckDigit(String nve) {
        String cacheNVE = nve.substring(2); //NVE without 00 "Datenbezeichner" and CheckDigit
        int position = -1;
        int sum = 0;
        for (char chara : cacheNVE.toCharArray()) {
            position++;
            int number = Integer.parseInt(String.valueOf(chara));
            if (isEven(position)) {
                sum += number * 3;
            } else {
                sum += number;
            }
        }

        int checkDigitOpposed = sum % 10;
        int checkDigit;

        if (checkDigitOpposed == 0) {
            checkDigit = 0;
        } else {
            checkDigit = 10 - checkDigitOpposed;
        }
        return checkDigit;

    }

    public static boolean isEven(int number) {
        return (number % 2) == 0;
    }

    /*
    Copied from https://www.baeldung.com/java-check-string-number
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
