package com.bcd.fraud.bpmn.task;

import static com.bcd.fraud.bpmn.task.ValidateTransactionTask.ValidationResponse.Status.FAILED;
import static com.bcd.fraud.bpmn.task.ValidateTransactionTask.ValidationResponse.Status.OK;

import com.bcd.fraud.Transaction;
import com.bcd.fraud.bpmn.ProcessVariables;

public class ValidateTransactionTask extends TaskBase {

    @Override
    public void execute() throws Exception {
		Transaction transaction = getTransaction();
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
