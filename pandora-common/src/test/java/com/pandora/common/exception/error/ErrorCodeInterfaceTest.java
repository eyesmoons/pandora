package com.pandora.common.exception.error;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ErrorCodeInterfaceTest {

    @Test
    void testI18nKey() {
        String i18nKey = ErrorCode.Client.COMMON_FORBIDDEN_TO_CALL.i18nKey();
        Assertions.assertEquals("20001_COMMON_FORBIDDEN_TO_CALL", i18nKey);
    }
}
