import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashSet;
import java.util.Set;

// Calculator - UI
// Calculations - logic, processing operations

public class Calculations {

    final private String RESULT_ERROR = "error";

    private static class Operand {

        final private String defaultValue;

        public String buffer;
        public boolean dotPresent;

        Operand(String defaultValue) {
            this.defaultValue = defaultValue;
            reset();
        }

        void reset() {
            buffer = defaultValue;
            dotPresent = false;
        }

        public String handleCharacters(String newChar){

            final boolean isDefault = buffer.equals(defaultValue);
            final boolean newIsDot = newChar.equals(".");

            if (isDefault) {
                if (newIsDot) {
                    buffer = "0.";
                    dotPresent = true;
                } else {
                    buffer = newChar;
                }
            } else {
                if (dotPresent) {
                    if (!newIsDot) {
                        buffer += newChar;
                    }
                } else {
                    if (newIsDot) {
                        buffer += newChar;
                        dotPresent = true;
                    } else if (defaultValue.isEmpty() && buffer.equals("0")){
                        buffer = newChar;
                    } else {
                        buffer += newChar;
                    }
                }
            }

            return buffer;
        }

    }

    private Operand opL = new Operand("0");
    private Operand opR = new Operand("");

    private String operator;
    private String curResult;

    private Set<String> numberCharacters;
    private Set<String> operators;

    public Calculations() {
        clearData();

        numberCharacters = new HashSet<String>() {
            {
                add("0");
                add("1");
                add("2");
                add("3");
                add("4");
                add("5");
                add("6");
                add("7");
                add("8");
                add("9");
                add(".");
            }
        };

        operators = new HashSet<String>() {
            {
                add("+");
                add("-");
                add("*");
                add("/");
                add("=");
            }
        };
    }

    private void clearData(){
        opL.reset();
        opR.reset();

        operator = "";

        curResult = "";
    }

    private boolean isOperatorAssigned() {
        return !operator.isEmpty();
    }

    private String setNewResult(String result) {
        curResult = result;
        copyCurResultToClipboard(curResult);
        return result;
    }

    public String handleInput(String input){

        if (input.equals("CE")){
            clearData();
            return opL.buffer;
        }

        final boolean isNumChar = numberCharacters.contains(input);
        final boolean isOpChar = operators.contains(input);

        String res = null;

        if (opR.buffer.equals("")) {
            if (isNumChar) {
                if (!isOperatorAssigned()) {
                    if (!curResult.equals("")) {
                        clearData();
                    }
                    res = addendToLeft(input);
                } else {
                    if(!curResult.equals("") && operator.equals("=")) {
                        clearData();
                        res = addendToLeft(input);
                    } else {
                        res = appendToRight(input);
                    }
                }
            } else if (isOpChar) {
                if (input.equals("=")) {
                    res = setNewResult(calculateBuffer1(opL.buffer));
                    opL.buffer = curResult;
                }
                operator = input;
            }
        } else if (isNumChar){
            res = appendToRight(input);
        } else if (isOpChar){
            res = setNewResult(calculate(opL.buffer , operator, opR.buffer));
            opL.buffer = curResult;
            if (!input.equals("=")){
                operator = input;
            } else {
                if (opL.buffer.equals(RESULT_ERROR)) {
                    clearData();
                }
            }
        }
        return res;
    }


    private String addendToLeft(String curStr){
        return opL.handleCharacters(curStr);
    }


    private String appendToRight(String curStr){
        return opR.handleCharacters(curStr);
    }

    private String calculate(String buf1, String op, String buf2){

        double result;

        double num1 = Double.parseDouble(buf1);
        double num2 = Double.parseDouble(buf2);

        switch (op){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    return RESULT_ERROR;
                } else {
                    result = num1 / num2;
                    break;
                }
            default:
                result = num1;
        }

        clearData();
        return String.valueOf(result);
    }

    private String calculateBuffer1(String buf1){
        double num1 = Double.parseDouble(buf1);
        clearData();
        return String.valueOf(num1);
    }

    private void copyCurResultToClipboard(String curResult){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection selection = new StringSelection(curResult);
        clipboard.setContents(selection, selection);
    }

}
