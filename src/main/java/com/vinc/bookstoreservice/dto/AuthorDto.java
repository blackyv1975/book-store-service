package com.vinc.bookstoreservice.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto extends CoreDto {

    private String name;
    private String born;
    private String dod;
    private String biography;

}
