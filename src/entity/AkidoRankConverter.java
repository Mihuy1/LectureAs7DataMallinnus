package entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AkidoRankConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String rank) {
        if (rank == null) return null;

        switch (rank) {
            case "rank 1":
                return 1;
            case "rank 2":
                return 2;
            case "rank 3":
                return 3;
            case "rank 4":
                return 4;
            case "rank 5":
                return 5;
            default:
                throw new IllegalArgumentException("Unknown rank: " + rank);
        }
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;

        switch (dbData) {
            case 1:
                return "rank 1";
            case 2:
                return "rank 2";
            case 3:
                return "rank 3";
            case 4:
                return "rank 4";
            case 5:
                return "rank 5";
            default:
                throw new IllegalArgumentException("Unknown rank code: " + dbData);
        }
    }
}