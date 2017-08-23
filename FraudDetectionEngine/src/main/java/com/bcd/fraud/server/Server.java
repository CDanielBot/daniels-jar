package com.bcd.fraud.server;

import com.bcd.fraud.bpmn.BpmnProcess;
import com.bcd.fraud.bpmn.ProcessManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;

public class Server {

    public static void main(String args[]) {

        try{
            Server server = new Server();
            server.init();
            System.err.println("Server ready");
        }catch(Exception e){
            System.err.println("Failed to start server!!");
            e.printStackTrace();
        }


    }

    private final ProcessManager processManager;

    private Server() {
        processManager = new ProcessManager();
    }

    private void init() throws IOException, InterruptedException {
        startRmi();
        makeServicePublic();
        deployBpmnProcesses();
    }


    private void deployBpmnProcesses() {
        processManager.deployProcess(BpmnProcess.CARD_FRAUD_DETECTION);
    }

    private void startRmi() throws InterruptedException, IOException {

        Path projectDir = Paths.get(System.getProperty("user.dir"));
        Path classesDir = projectDir.resolve("target" + File.separator + "classes");

        final String startCmdLineCommand = "cmd.exe /c";
        final String changeToClassesDir = "cd \"" + classesDir.toString() + "\"";
        final String startRmiCommand = "start rmiregistry";

        final String command = startCmdLineCommand + " " + changeToClassesDir + " & " + startRmiCommand;

        final Process process = Runtime.getRuntime().exec(command);

        new Thread(new ReadErrorStream(process)).start();

        process.waitFor();
    }

    private static class ReadErrorStream implements Runnable {

        private Process process;

        public ReadErrorStream(Process p){
            process = p;
        }

        @Override
        public void run() {
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;

            try {
                while ((line = input.readLine()) != null)
                    System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void makeServicePublic() {
        try {
            Naming.rebind("//localhost/TransactionService", new TransactionServiceBpmn());
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
