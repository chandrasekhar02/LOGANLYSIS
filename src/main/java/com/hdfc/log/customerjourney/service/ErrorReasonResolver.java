package com.hdfc.log.customerjourney.service;

import com.hdfc.log.customerjourney.enums.ErrorCode;

public class ErrorReasonResolver {

    public static ErrorCode resolve(String dropOffStep, String journeyStatus) {

        if ("KYC_SUBMITTED".equals(dropOffStep)
                && "FAILED".equals(journeyStatus)) {
            return ErrorCode.KYC_DOC_INVALID;
        }

        if ("VERIFICATION".equals(dropOffStep)) {
            return ErrorCode.VERIFICATION_FAILED;
        }

        if ("FORM_STARTED".equals(dropOffStep)) {
            return ErrorCode.FORM_INCOMPLETE;
        }

        return ErrorCode.UNKNOWN;
    }
}
