package use_case.connect;
import java.util.HashMap;

public class ConnectOutputData {
    final private HashMap outputData;

    public ConnectOutputData(HashMap<String, Object> outputData){this.outputData = outputData;}

    public HashMap getOutputData(){return this.outputData;}
}
