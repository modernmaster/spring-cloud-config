package uk.co.jamesmcguigan.spring.cloud.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Licensee {
    private int id;
    private String name;
    private List<Integer> hostIds;
}
