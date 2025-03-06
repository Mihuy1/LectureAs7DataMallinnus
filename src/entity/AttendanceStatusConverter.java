package entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AttendanceStatusConverter implements AttributeConverter<AttendanceStatus, String> {

    @Override
    public String convertToDatabaseColumn(AttendanceStatus status) {
        return (status == null) ? null : status.getStatus();
    }

    @Override
    public AttendanceStatus convertToEntityAttribute(String code) {
        return (code == null) ? null : AttendanceStatus.fromCode(code);
    }
}
