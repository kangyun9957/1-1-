package denall.admin.was.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerDto {

    String oneononeInquiryNumber;
    String serviceType;
    String registProcessorId;
    String writerEmail;
    String registProcessDayandtime;
    String categoryType;
    String inquiryTitle;
    String answerState;
    String answerManager;
    String answerManagerId;
    String answerDay;
    String modifyManager;
    String modifyManagerId;
    String lastAnswerDay;
    String inquiryContent;
    String answerContent;

    public void addAnswer(AnswerDto dto) {
        this.answerContent = dto.getAnswerContent();
        this.answerDay = dto.getAnswerDay();
        this.modifyManagerId=dto.getModifyManagerId();
        this.modifyManager = dto.getModifyManager();
        this.lastAnswerDay = dto.getLastAnswerDay();
    }
}
