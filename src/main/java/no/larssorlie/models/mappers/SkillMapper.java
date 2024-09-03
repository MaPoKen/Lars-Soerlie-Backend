package no.larssorlie.models.mappers;

import no.larssorlie.models.domain.Skill;
import no.larssorlie.models.dto.NewSkillDTO;
import no.larssorlie.models.dto.SkillDTO;

public class SkillMapper {
    public static SkillDTO toDTO(Skill skill){
        return new SkillDTO()
                .toBuilder()
                .id(skill.getId())
                .name(skill.getName())
                .iconUrl(skill.getIconUrl()).build();
    }

    public static Skill toModel(SkillDTO skill){
        return new Skill()
                .toBuilder()
                .id(skill.getId())
                .name(skill.getName())
                .iconUrl(skill.getIconUrl()).build();
    }

    public static Skill toModel(NewSkillDTO skill){
        return new Skill()
                .toBuilder()
                .name(skill.getName())
                .iconUrl(skill.getIconUrl()).build();
    }

    public static Skill toModel(NewSkillDTO skill, Long id){
        return new Skill()
                .toBuilder()
                .id(id)
                .name(skill.getName())
                .iconUrl(skill.getIconUrl()).build();
    }
}
