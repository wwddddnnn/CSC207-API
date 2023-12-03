package connect_test;
import org.junit.jupiter.api.Test;

import use_case.connect.*;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ConnectInteractorTest {

    static class MockConnectDataAccess implements ConnectDataAccessInterface {
        @Override
        public HashMap<String, String> getResult(String title) {
            HashMap<String, String> result = new HashMap<>();
            result.put("hash", "testHash");
            return result;
        }
    }

    static class MockConnectOutputBoundary implements ConnectOutputBoundary {
        HashMap<String, Object> outputDataReceived;

        @Override
        public void prepareView(ConnectOutputData outputData) {
            this.outputDataReceived = outputData.getOutputData();
        }

        public HashMap<String, Object> getOutputDataReceived() {
            return outputDataReceived;
        }
    }

    @Test
    void testExecute() {
        // Create mock implementations
        MockConnectDataAccess mockDataAccess = new MockConnectDataAccess();
        MockConnectOutputBoundary mockOutputBoundary = new MockConnectOutputBoundary();

        // Create instance of ConnectInteractor
        ConnectInteractor interactor = new ConnectInteractor(mockDataAccess, mockOutputBoundary);

        // Create test input
        LocalDate testTime = LocalDate.now();
        ConnectInputData inputData = new ConnectInputData("testTitle", testTime);

        // Execute the method
        interactor.execute(inputData);

        // Assert the expected output
        HashMap<String, Object> outputDataReceived = mockOutputBoundary.getOutputDataReceived();
        assertNotNull(outputDataReceived, "Output data should not be null");
        assertEquals("testHash", outputDataReceived.get("userHash"), "The hash in the output data should match the expected value");
        assertEquals("testTitle", outputDataReceived.get("username"), "The username in the output data should match the input title");
        assertEquals(testTime, outputDataReceived.get("date"), "The date in the output data should match the input time");
    }
}
