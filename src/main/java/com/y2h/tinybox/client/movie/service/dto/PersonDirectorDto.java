package com.y2h.tinybox.client.movie.service.dto;

import lombok.Builder;

public class PersonDirectorDto {
    private String name;
    private String birth;
    private String nation;
    private String type;

    @Builder
    public PersonDirectorDto(String name, String birth, String nation, String type) {
        this.name = name;
        this.birth = birth;
        this.nation = nation;
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PersonDirectorDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", birth='").append(birth).append('\'');
        sb.append(", nation='").append(nation).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
