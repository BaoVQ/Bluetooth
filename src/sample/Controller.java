package sample;

import grovy.Enforcement;
import grovy.Run;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.bluez.exceptions.*;
import uiautomater.UIAutomater;

import javax.swing.text.TabableView;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {


    @FXML private ListView<String> listLogView;
    @FXML private static TextArea logTextArea;
    @FXML private TextField textFieldPi;
    @FXML private TextField textFieldDevice;

    public static void addLog(String log){
        logTextArea.appendText(log);
    }

    @FXML
    protected void btnClickStart(ActionEvent event) throws BluezNotReadyException, BluezAuthenticationTimeoutException, BluezAlreadyConnectedException, InterruptedException, BluezAuthenticationFailedException, IOException, BluezAlreadyExistsException, BluezInProgressException, BluezInvalidArgumentsException, BluezAuthenticationCanceledException, BluezFailedException, BluezDoesNotExistException, BluezAuthenticationRejectedException, BluezConnectionAttemptFailedException {
        //System.out.print(textFieldPi.getText());
        Run run = new Run();
        run.Start();

    }

    @FXML
    protected  void clickBtnDevice(ActionEvent event) throws IOException {
        //textFieldPi.getText();
        UIAutomater uiAutomater = new UIAutomater();
        //System.out.print(uiAutomater.getMACBT());
        //textFieldDevice.setText("abc");
        textFieldDevice.setText(uiAutomater.getMACBT());
        System.out.print("Bluetooth Address of Device: ");
        System.out.print(uiAutomater.getMACBT());
        //System.out.print(textFieldPi.getId());
    }

    @FXML
    protected  void clickBtnPi(ActionEvent event) throws IOException {
        //textFieldPi.setText("abc");
        textFieldPi.getText();
        System.out.print("IP Address of Pi");
        System.out.print(textFieldPi.getText());
        //System.out.print(textFieldPi.getId());
    }

    @FXML
    protected void handleLoadFile(ActionEvent event) throws InterruptedException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(Main.stage);
        System.out.print("File Path: ");

        if (file != null) {
            System.out.print(file.getAbsolutePath());
        }
//        logTextArea.appendText("File Grovy: ");
//        logTextArea.appendText(file.getAbsolutePath());
//        logTextArea.appendText("\nLoad Script: OK \n");


//        Enforcement.printString("asdasdasd");


    }



    private void buildConsole() {
        Console console = new Console(listLogView);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
//        System.setErr(ps);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        tableChucnang.setCellValueFactory(new PropertyValueFactory<User,String>("chucnang"));
//        tableChucnang.setCellValueFactory(new PropertyValueFactory<User,String>("trangthai"));
//        tableMain.getItems().setAll(parseUserList());
        buildConsole();

    }

}
