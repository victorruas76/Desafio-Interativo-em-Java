import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final Map<Integer, String> colorMap = new HashMap<>();
    private static final String DEFAULT_TOLERANCE_COLOR = "dourado";
    private static String toleranceColor = DEFAULT_TOLERANCE_COLOR;

    static {
        colorMap.put(0, "preto");
        colorMap.put(1, "marrom");
        colorMap.put(2, "vermelho");
        colorMap.put(3, "laranja");
        colorMap.put(4, "amarelo");
        colorMap.put(5, "verde");
        colorMap.put(6, "azul");
        colorMap.put(7, "violeta");
        colorMap.put(8, "cinza");
        colorMap.put(9, "branco");
    }

    public static String getResistorColors(String input) {
        try {
            input = input.toLowerCase().replace(" ohms", "").replace(",", ".");
            StringBuilder result = new StringBuilder();

            double value;
            String multiplierColor = "preto";

            if (input.contains("k")) {
                input = input.replace("k", "");
                value = Double.parseDouble(input) * 1000;
                multiplierColor = "laranja";
            } else if (input.contains("m")) {
                input = input.replace("m", "");
                value = Double.parseDouble(input) * 1000000;
                multiplierColor = "azul";
            } else {
                value = Double.parseDouble(input);
            }

            String valueStr = String.format("%.0f", value);

            if (valueStr.length() < 2) {
                valueStr = "0" + valueStr;
            }

            int firstDigit = Character.getNumericValue(valueStr.charAt(0));
            int secondDigit = Character.getNumericValue(valueStr.charAt(1));
            
            result.append(colorMap.get(firstDigit)).append(" ");
            result.append(colorMap.get(secondDigit)).append(" ");
            
            result.append(multiplierColor).append(" ");
            result.append(toleranceColor);

            return result.toString().trim();
        } catch (NumberFormatException e) {
            return "Entrada inválida. Por favor, forneça um valor numérico válido.";
        }
    }

    public static void setToleranceColor(String color) {
        toleranceColor = color;
    }

    public static void main(String[] args) {
        System.out.println(getResistorColors("47 ohms"));
        System.out.println(getResistorColors("4.7k ohms"));
        System.out.println(getResistorColors("1M ohms"));

        setToleranceColor("prata");
        System.out.println(getResistorColors("47 ohms"));
        System.out.println(getResistorColors("4.7k ohms"));
        System.out.println(getResistorColors("1M ohms"));
    }
}
