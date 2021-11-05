package exchange.bck.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class GenericResponse<T> {
    private String responseCode;
    private T data;

    public static GenericResponse successNoData() {
        try {
            return GenericResponse.builder()
                    .responseCode(HttpStatus.OK.getReasonPhrase())
                    .data(new ObjectMapper().readTree("{}"))
                    .build();
        } catch (Exception e) {
            return GenericResponse.builder()
                    .build();
        }
    }

    public static <T> GenericResponse<T> successWithData(T data) {
        return GenericResponse.<T>builder()
                .responseCode(HttpStatus.OK.getReasonPhrase())
                .data(data)
                .build();
    }

    public static GenericResponse error(String responseCode) {
        return GenericResponse.builder()
                .responseCode(responseCode)
                .build();
    }
}
