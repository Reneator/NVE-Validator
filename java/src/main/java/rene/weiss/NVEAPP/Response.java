package rene.weiss.NVEAPP;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter @Setter @NoArgsConstructor
public class Response {

    private String errorMessage;
    private String nummerVersandEinheit; //only nve without checkDigit
    private String checkDigit; // only checkDigit
    private String nVE; //nve including checkDigit

}
