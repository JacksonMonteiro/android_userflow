package space.jacksonmonteiro.users.utils;
/*
Created By Jackson Monteiro on 13/01/2024
Copyright (c) 2024 GFX Consultoria
*/


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MaskUtil {
    public static final String FORMAT_CPF = "###.###.###-##";
    public static final String FORMAT_CNPJ = "##.###.###/####-##";

    public static TextWatcher mask(final EditText et, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                final String str = MaskUtil.unmask(charSequence.toString());
                String maskedString = "";

                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                int i = 0;
                for (final char c : mask.toCharArray()) {
                    if (c != '#' && str.length() > old.length()) {
                        maskedString += c;
                        continue;
                    }

                    try {
                        maskedString += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }

                    i++;
                }

                isUpdating = true;
                et.setText(maskedString);
                et.setSelection(maskedString.length());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    public static String unmask(final String str) {
        return str
                .replaceAll("[.]", "")
                .replaceAll("[-]", "")
                .replaceAll("[/]", "");
    }
}
