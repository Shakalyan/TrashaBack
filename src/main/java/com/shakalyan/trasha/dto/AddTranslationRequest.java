package com.shakalyan.trasha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTranslationRequest {

    private Integer dictionaryId;

    private String word;

    private String translation;

}
