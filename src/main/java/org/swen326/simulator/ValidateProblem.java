package org.swen326.simulator;

/**
 * This record gets returned by functions performing input validation.
 * @param validated True: input is correct. False: if input is incorrect.
 * @param errorMessage Error message if false is returned.
 */
public record ValidateProblem(boolean validated, String errorMessage) {
    public ValidateProblem(boolean validated, String errorMessage){
        this.validated = validated;
        if(errorMessage == null){
            throw new IllegalArgumentException("errorMessage is null. If you want it to be empty, make it an empty" +
                    "string.");
        }
        this.errorMessage = errorMessage;
    }
}
