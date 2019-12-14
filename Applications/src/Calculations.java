import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashSet;
import java.util.Set;

public class Calculations {

    private String buffer1;
    private String buffer2;
    private String operator;
    private boolean operatorSet;
    private boolean dotSet1;
    private boolean dotSet2;
    private String curResult;

    private Set<String> numberCharacters;
    private Set<String> operators;

    public Calculations() {
        clearData();

        numberCharacters = new HashSet<>() {
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

        operators = new HashSet<>() {
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
        buffer1 = "0";
        buffer2 = "";
        operator = "";
        operatorSet = false;
        dotSet1 = false;
        dotSet2 = false;
        curResult = "";
    }

    public String handleInput(String inputValue){
        if (inputValue.equals("CE")){
            clearData();
            return buffer1;
        }

        String res = null;

        if (buffer2.equals("")) {
            if (!operatorSet && numberCharacters.contains(inputValue) && curResult.equals("")) {
                buffer1 = addNumbersBuffer1(inputValue);
                res = buffer1;
            } else if (!operatorSet && numberCharacters.contains(inputValue) && !curResult.equals("")){
                clearData();
                buffer1 = addNumbersBuffer1(inputValue);
                res = buffer1;
            }
            if (!operatorSet && operators.contains(inputValue)) {
                if (inputValue.equals("=")) {
                    //curResult = calculate(buffer1, "", "");
                    curResult = calculateBuffer1(buffer1);
                    copyCurResultToClipboard(curResult);
                    res = curResult;
                    buffer1 = curResult;
                    operatorSet = true;
                    operator = "=";
                } else {
                    operator = inputValue;
                    operatorSet = true;
                }
            } else if (operatorSet && operators.contains(inputValue)) {
                if (inputValue.equals("=")) {
                    //curResult = calculate(buffer1, "", "");
                    curResult = calculateBuffer1(buffer1);
                    copyCurResultToClipboard(curResult);
                    res = curResult;
                    buffer1 = curResult;
                    operator = "=";
                    operatorSet = true;
                } else {
                    operator = inputValue;
                }
            } else if(operatorSet && numberCharacters.contains(inputValue) && !curResult.equals("")) {
                if (operator.equals("=")) {
                    clearData();
                    buffer1=addNumbersBuffer1(inputValue);
                    res = buffer1;
                } else {
                    buffer2 = addNumbersBuffer2(inputValue);
                    res = buffer2;
                }
            } else if (operatorSet && numberCharacters.contains(inputValue)){
                buffer2 = addNumbersBuffer2(inputValue);
                res = buffer2;
            }
        } else if (numberCharacters.contains(inputValue)){
            buffer2 = addNumbersBuffer2(inputValue);
            res = buffer2;
        } else if (operators.contains(inputValue)){
            if (!inputValue.equals("=")){
                curResult = calculate(buffer1 , operator, buffer2);
                copyCurResultToClipboard(curResult);
                res = curResult;
                buffer1 = curResult;
                operator = inputValue;
                operatorSet = true;
            } else {
                curResult = calculate(buffer1 , operator, buffer2);
                copyCurResultToClipboard(curResult);
                res = curResult;
                buffer1 = curResult;
                if (buffer1.equals("error")) {
                    clearData();
                }
            }
        }
        return res;
    }

    private String addNumbersBuffer1(String curStr){

        if (buffer1.equals("0") && (!dotSet1)) {
            if (curStr.equals(".")) {
                buffer1 += curStr;
                dotSet1 = true;
            } else {
                buffer1 = curStr;
            }
        } else if (!buffer1.equals("0") && (!dotSet1)) {
            if (curStr.equals(".")) {
                buffer1 += curStr;
                dotSet1 = true;
            } else {
                buffer1 += curStr;
            }
        } else if (!buffer1.equals("0") && dotSet1) {
            if (!curStr.equals(".")) {
                buffer1 += curStr;
            }
        }
        return buffer1;
    }

    private String addNumbersBuffer2(String curString){
        if (buffer2.equals("") && (!dotSet2)) {
            if (curString.equals(".")) {
                buffer2 = "0" + curString;
                dotSet2 = true;
            } else {
                buffer2 = curString;
            }
        } else if (!buffer2.equals("") && (!dotSet2)) {
            if (curString.equals(".")) {
                buffer2 += curString;
                dotSet2 = true;
            } else if (buffer2.equals("0")){
                buffer2 = curString;
            } else {
                buffer2 += curString;
            }
        } else if (!buffer2.equals("") && dotSet2) {
            if (!curString.equals(".")) {
                buffer2 += curString;
            }
        }
        return buffer2;
    }

    private String calculate(String buf1, String op, String buf2){

        double result;

        double num1 = Double.parseDouble(buf1);
        double num2=Double.parseDouble(buf2);

        switch (op){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1*num2;
                break;
            case "/":
                if (num2 == 0){
                    return "error";
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
