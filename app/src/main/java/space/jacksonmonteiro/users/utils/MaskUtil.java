package space.jacksonmonteiro.users.utils;
/*
Created By Jackson Monteiro on 13/01/2024
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

    public static String maskCpf(String cpf) {
        StringBuilder cpfFormatted = new StringBuilder();
        cpfFormatted.append(cpf.substring(0, 3));
        cpfFormatted.append(".");
        cpfFormatted.append(cpf.substring(3, 6));
        cpfFormatted.append(".");
        cpfFormatted.append(cpf.substring(6, 9));
        cpfFormatted.append("-");
        cpfFormatted.append(cpf.substring(9));

        return cpfFormatted.toString();
    }

    public static String maskCnpj(String cnpj) {
        StringBuilder cnpjFormatted = new StringBuilder();
        cnpjFormatted.append(cnpj.substring(0, 2));
        cnpjFormatted.append(".");
        cnpjFormatted.append(cnpj.substring(2, 5));
        cnpjFormatted.append(".");
        cnpjFormatted.append(cnpj.substring(5, 8));
        cnpjFormatted.append("/");
        cnpjFormatted.append(cnpj.substring(8, 12));
        cnpjFormatted.append("-");
        cnpjFormatted.append(cnpj.substring(12));

        return cnpjFormatted.toString();
    }
}
