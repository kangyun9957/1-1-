package denall.admin.was.model.dto;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

@Data
public class ResponseDto {
    private Object data;
    private Integer status = 200;
    private Integer totalCount;
    private Integer result;
    private String code = "OK";
    private String message = "Success";
    private Date timestamp = new Date();

    public ResponseDto(){

    }

    public ResponseDto(Object data){
        this.data = data;
    }
    public static ResponseEntity<ResponseDto> ok(Object data){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(data);

        return ResponseEntity.ok(responseDto);
    }
    public static ResponseEntity<ResponseDto> ok(Object data,int totalCount){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(data);
        responseDto.setTotalCount(totalCount);

        return ResponseEntity.ok(responseDto);
    }




}
