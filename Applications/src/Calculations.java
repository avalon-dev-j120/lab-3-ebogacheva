import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;

public class Calculations {

    private String buffer1;
    private String buffer2;
    private String operator;
    private boolean operatorSet;
    private boolean dotSet1;
    private boolean dotSet2;
    private String curResult;

    private JLabel resultField;
    private HashMap<JButton, String> numberButtons;
    private HashMap<JButton, String> operatorButtons;

    public Calculations(Calculator calculator) {
        clearData();
        resultField = calculator.getResultField();
        numberButtons = calculator.getNumberButtons();
        operatorButtons = calculator.getOperatorButtons();
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

    public void handleButtonsInput(String buttonValue){
        if (buttonValue.equals("CE")){
            clearData();
            resultField.setText(buffer1);
        }

        if (buffer2.equals("")) {
            if (!operatorSet && numberButtons.containsValue(buttonValue) && curResult.equals("")) {
                buffer1 = addNumbersBuffer1(buttonValue);
                resultField.setText(buffer1);
            } else if (!operatorSet && numberButtons.containsValue(buttonValue) && !curResult.equals("")){
                clearData();
                buffer1 = addNumbersBuffer1(buttonValue);
                resultField.setText(buffer1);
            }
            if (!operatorSet && operatorButtons.containsValue(buttonValue)) {
                if (buttonValue.equals("=")) {
                    curResult = calculate(buffer1, "", "");
                    copyCurResultToClipboard(curResult);
                    resultField.setText(curResult);
                    buffer1 = curResult;
                    operatorSet = true;
                    operator = "=";
                } else {
                    operator = buttonValue;
                    operatorSet = true;
                }
            } else if (operatorSet && operatorButtons.containsValue(buttonValue)) {
                if (buttonValue.equals("=")) {
                    curResult = calculate(buffer1, "", "");
                    copyCurResultToClipboard(curResult);
                    resultField.setText(curResult);
                    buffer1 = curResult;
                    operator = "=";
                    operatorSet = true;
                } else {
                    operator = buttonValue;
                }
            } else if(operatorSet && numberButtons.containsValue(buttonValue) && !curResult.equals("")) {
                if (operator.equals("=")) {
                    clearData();
                    buffer1=addNumbersBuffer1(buttonValue);
                    resultField.setText(buffer1);
                } else {
                    buffer2 = addNumbersBuffer2(buttonValue);
                    resultField.setText(buffer2);
                }
            } else if (operatorSet && numberButtons.containsValue(buttonValue)){
                buffer2 = addNumbersBuffer2(buttonValue);
                resultField.setText(buffer2);
            }
        } else if (numberButtons.containsValue(buttonValue)){
            buffer2 = addNumbersBuffer2(buttonValue);
            resultField.setText(buffer2);
        } else if (operatorButtons.containsValue(buttonValue)){
            if (!buttonValue.equals("=")){
                curResult = calculate(buffer1 , operator, buffer2);
                copyCurResultToClipboard(curResult);
                resultField.setText(curResult);
                buffer1 = curResult;
                operator = buttonValue;
                operatorSet = true;
            } else {
                curResult = calculate(buffer1 , operator, buffer2);
                copyCurResultToClipboard(curResult);
                resultField.setText(curResult);
                buffer1 = curResult;
                if (buffer1.equals("error")) {
                    clearData();
                }
            }
        }
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
        double num2;

        double num1 = Double.parseDouble(buf1);
        if (!buf2.equals("")) {
            num2=Double.parseDouble(buf2);
        } else {
            //have no sense
            //TODO: change
            num2 = 0;
        }

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

    private void copyCurResultToClipboard(String curResult){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection selection = new StringSelection(curResult);
        clipboard.setContents(selection, selection);
    }

}
