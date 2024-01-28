package com.app.computerstore.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class UpdateExceptionTest {
    @Mock
    Object backtrace;
    @Mock
    Throwable cause;
    @Mock
    List<Throwable> SUPPRESSED_SENTINEL;
    @Mock
    List<Throwable> suppressedExceptions;
    @InjectMocks
    UpdateException updateException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}

