package com.topdesk;

import java.util.regex.Pattern;

public class MyLicensePlateChecker implements ILicensePlateChecker {

    Pattern pattern;
    int TOTAL;

    @Override
    public void setSpecification(LicensePlateSpecification specification) {
        if (specification == null)
            return;
        String CODES = String.join("|", specification.getValidTownCodes());
        int MIN_LETTERS = specification.getMinNumLetters();
        int MIN_DIGITS = specification.getMinNumDigits();
        TOTAL = specification.getTotalLength();
        pattern = Pattern.compile(String.format("(%s)-[a-zA-Z]{%d,} \\d{%d,}", CODES, MIN_LETTERS, MIN_DIGITS));
    }

    @Override
    public boolean isValid(LicensePlate licensePlate) {
        // TOTAL + 2 is because of addtional "-" and " " character in toString.
        return licensePlate != null && pattern != null && licensePlate.toString().length() == TOTAL + 2
                && pattern.matcher(licensePlate.toString()).matches();
    }

}
