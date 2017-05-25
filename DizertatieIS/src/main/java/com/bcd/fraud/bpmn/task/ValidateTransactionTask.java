package com.bcd.fraud.bpmn.task;

import com.bcd.fraud.bpmn.ProcessVariables;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.bcd.fraud.Transaction;

import static com.bcd.fraud.bpmn.task.ValidateTransactionTask.ValidationResponse.Status.FAILED;
import static com.bcd.fraud.bpmn.task.ValidateTransactionTask.ValidationResponse.Status.OK;

public class ValidateTransactionTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Transaction transaction = (Transaction) execution.getVariable("transaction");
        System.out.println("Validating transaction with id: " + transaction.getId());
        ValidationResponse response = validate(transaction);
        execution.setVariable(ProcessVariables.IS_VALID, response.isValid());
    }

    private ValidationResponse validate(Transaction t) {

        ValidationResponse response = new ValidationResponse();

        if (t == null) {
            response.setCause("Process does not contain transaction as process variable!");
        } else if (t.getTransactionType() == null) {
            response.setCause("Invalid transaction! Missing transaction type");
        } else if (t.getDateTime() == null) {
            response.setCause("Invalid transaction! Missing datetime");
        } else if (t.getAmmount() == null) {
            response.setCause("Invalid transaction! Missing amount");
        } else if (t.getAccountNumberDestination() == null && t.getAccountNumberSource() == null) {
            response.setCause("Invalid transaction! Missing account number");
        }

        return response;

    }

    static class ValidationResponse {

        enum Status {
            OK, FAILED
        }

        public ValidationResponse() {
            status = OK;
        }

        private Status status;
        private String cause;

        public Status getStatus() {
            return status;
        }

        public String getCause() {
            return cause;
        }

        public boolean isValid() {
            return status == OK && cause == null;
        }

        public void setCause(String cause) {
            status = FAILED;
            this.cause = cause;
        }
    }

}
