package atm;

import card.Card;
import card.MasterCard;
import card.Visa;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

class CardScanner {
    private static final String VISA = "visa";
    private static final String MC = "mc";

    private static String report;

    enum  Attrs {
        TYPE, FIRST_NAME, SUR_NAME, PIN, CVV, DATE, ACCOUNT
    }

    static Card readCard(String cartPath) {
        Card card = null;

        try {
            File file = new File(cartPath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
//            List<String> attributes = new LinkedList<>();
            Map<Attrs, String> attributes = new HashMap<>();

            String line;
            Attrs[] emumValues = Attrs.values();
            int index = 0;
            while ((line = reader.readLine()) != null) {
                attributes.put(emumValues[index], line);
                index++;
            }

            reader.close();

            report = "Карточка вставлена";

            if (attributes.get(Attrs.TYPE).equals(VISA)) {
                card = new Visa(attributes.get(Attrs.FIRST_NAME), attributes.get(Attrs.SUR_NAME),
                        attributes.get(Attrs.PIN), attributes.get(Attrs.CVV), attributes.get(Attrs.DATE),
                        attributes.get(Attrs.ACCOUNT));
            } else if (attributes.get(Attrs.TYPE).equals(MC)) {
                card = new MasterCard(attributes.get(Attrs.FIRST_NAME), attributes.get(Attrs.SUR_NAME),
                        attributes.get(Attrs.PIN), attributes.get(Attrs.CVV), attributes.get(Attrs.DATE)
                        , attributes.get(Attrs.ACCOUNT));
            } else {
                report = "Неизвестаная карта";
            }
        } catch (IOException e) {
            report = "Ошибка! Ваша карта не считывается";
            e.printStackTrace();
        }

        return card;
    }

    static String getReport() {
        return report;
    }
}
