package pl.beda.javaSpringSecurityIntroduction.simple.acl;

import lombok.Value;

@Value
public class CreateDocumentDto {
    private String title;
    private String content;
}
