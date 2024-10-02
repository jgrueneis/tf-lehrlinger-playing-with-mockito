package tf.lehrlinge;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayWithMockitoTest {

    @Test
    void verify_createMockWithinTest() {
        // Given
        MockMe mockMe = Mockito.mock(MockMe.class);

        // When
        Integer answerToEverything = mockMe.theAnswerToEverything();

        // Then
        assertThat(answerToEverything).isEqualTo(0);
    }

    @Test
    void verify_defineAnAnswer() {
        // Given
        Integer answer = 42;
        MockMe mockMe = Mockito.mock(MockMe.class);
        Mockito.when(mockMe.theAnswerToEverything()).thenReturn(answer);

        // When
        Integer answerToEverything = mockMe.theAnswerToEverything();

        // Then
        assertThat(answerToEverything).isEqualTo(answer);
    }

    @Test
    void verify_aVoidMethodCall() {
        //Given
        Integer wasItCalledArgument = 42;
        MockMe mockMe = Mockito.mock(MockMe.class);
        // not possible to do
        //Mockito.when(mockMe.wasItCalled(42)).thenReturn(...) ??

        // When
        mockMe.wasItCalled(wasItCalledArgument);

        // Then
        Mockito.verify(mockMe).wasItCalled(wasItCalledArgument);
        Mockito.verify(mockMe).wasItCalled(Mockito.anyInt());
        // the argument needs to match!
        // would not work: Mockito.verify(mockMe).wasItCalled(43);
    }

    @Test
    void verify_throwingAnException_fromMethodWithReturnVoid() {
        // Given
        MockMe mockMe = Mockito.mock(MockMe.class);
        Mockito.doThrow(new RuntimeException("for testing purposes"))
                .when(mockMe).wasItCalled(42);

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            mockMe.wasItCalled(42);
        });
    }

    @Test
    void verify_throwingAnException_fromMethodWithReturnValue() {
        // Given
        MockMe mockMe = Mockito.mock(MockMe.class);
        Mockito.when(mockMe.theAnswerToEverything())
                .thenThrow(new RuntimeException("for testing purposes"));

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            mockMe.theAnswerToEverything();
        });
    }

    // more: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3
}

interface MockMe {
    Integer theAnswerToEverything();

    void wasItCalled(Integer askMe);
}

